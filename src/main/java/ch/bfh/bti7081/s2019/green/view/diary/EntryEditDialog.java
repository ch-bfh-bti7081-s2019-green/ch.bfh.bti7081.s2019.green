package ch.bfh.bti7081.s2019.green.view.diary;

import ch.bfh.bti7081.s2019.green.components.CustomDialog;
import ch.bfh.bti7081.s2019.green.components.CustomFormLayout;
import ch.bfh.bti7081.s2019.green.model.diary.Activity;
import ch.bfh.bti7081.s2019.green.model.diary.ActivityType;
import ch.bfh.bti7081.s2019.green.model.diary.Entry;
import ch.bfh.bti7081.s2019.green.persistence.SessionSingleton;
import ch.bfh.bti7081.s2019.green.persistence.dao.EntryDao;
import com.github.appreciated.app.layout.component.appbar.IconButton;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.data.binder.Binder;

import java.time.Duration;
import java.time.LocalTime;


public class EntryEditDialog extends CustomDialog {

    private static final long serialVersionUID = -5760580106156198334L;
    private transient SessionSingleton db = SessionSingleton.getInstance();
    private transient Entry entry;
    private Binder<Entry> binder;
    private FormLayout form;
    private transient EntryDao dao = new EntryDao();
    private int entryId;

    public EntryEditDialog(int entryId) {
        this.entryId = entryId;
        this.entry = dao.findById(this.entryId).get();
        this.addHeading();
        this.addForm();
        this.add(new Hr());
        this.addActivityLayout(ActivityType.MEDICATION, "Medication");
        this.add(new Hr());
        this.addActivityLayout(ActivityType.FOOD, "Food");
        this.add(new Hr());
        this.addActivityLayout(ActivityType.EXERCISE, "Exercise");
        this.add(new Hr());
        this.addUpdateButton();
    }

    /**
     * This simply adds the title of the dialog.
     */
    private void addHeading() {
        H2 heading = new H2("Edit Entry: " + this.entry.getDate());
        this.add(heading);
    }

    /**
     * This adds the main form for changing the values of the entry.
     */
    private void addForm() {
        this.form = new CustomFormLayout();
        this.binder = new Binder<>(Entry.class);
        this.addMoodSlider();
        this.addWaterDrunkField();
        this.addSleepHoursField();
        this.addNotesField();
        binder.readBean(this.entry);
        this.add(form);
    }

    /**
     * Adds a input for the current mood.
     */
    private void addMoodSlider() {
        NumberField moodSlider = new NumberField();
        moodSlider.setMin(1);
        moodSlider.setMax(10);
        moodSlider.setHasControls(true);
        moodSlider.setStep(1);
        binder.bind(moodSlider, Entry::getMood, Entry::setMood);
        moodSlider.setLabel("Mood");
        form.add(moodSlider);
    }

    /**
     * Adds a numerical input for entering the amount of drunk water.
     */
    private void addWaterDrunkField() {
        NumberField waterDrunkField = new NumberField();
        waterDrunkField.setMin(0);
        waterDrunkField.setMax(20);
        waterDrunkField.setStep(0.1);
        waterDrunkField.setHasControls(true);
        waterDrunkField.setLabel("Water drunk (in litres)");
        binder.bind(waterDrunkField, Entry::getWaterDrunk, Entry::setWaterDrunk);
        form.add(waterDrunkField);
    }

    /**
     * Adds a numerical input for entering the number of hours slept.
     */
    private void addSleepHoursField() {
        NumberField sleepHoursField = new NumberField();
        sleepHoursField.setMin(0);
        sleepHoursField.setMax(24);
        sleepHoursField.setStep(0.5);
        sleepHoursField.setHasControls(true);
        sleepHoursField.setLabel("Slept hours");
        binder.bind(sleepHoursField, Entry::getSleepHours, Entry::setSleepHours);
        form.add(sleepHoursField);
    }

    /**
     * Adds the textarea for entering notes for the current diary entry.
     */
    private void addNotesField() {
        TextArea notes = new TextArea();
        notes.setLabel("Notes");
        binder.bind(notes, Entry::getNotes, Entry::setNotes);
        form.add(notes);
    }

    /**
     * Adds the vertical layout for entering activities of a specific type.
     *
     * @param activityType The type of the activity
     * @param title        The title of the layout
     */
    private void addActivityLayout(ActivityType activityType, String title) {
        VerticalLayout activityLayout = new VerticalLayout();
        activityLayout.setPadding(false);

        activityLayout.add(new H3(title));
        Button addMedicationButton = new Button("Add " + title);
        addMedicationButton.addClickListener(e -> {
            Activity activity = new Activity();
            activity.setType(activityType);
            activity.setTime(LocalTime.of(10, 0));
            entry.addActivity(activity);
            db.save(activity);
            activityLayout.addComponentAtIndex(2, getActivityRow(activity, activityLayout));
        });
        activityLayout.add(addMedicationButton);

        for (Activity activity : entry.getActivitiesByType(activityType)) {
            activityLayout.add(getActivityRow(activity, activityLayout));
        }

        this.add(activityLayout);
    }

    /**
     * Returns the row for one activity to use inside of the activity layout.
     *
     * @param activity     The activity
     * @param parentLayout The parent layout
     * @return
     */
    private HorizontalLayout getActivityRow(Activity activity, VerticalLayout parentLayout) {
        HorizontalLayout medicationLayout = new HorizontalLayout();
        TimePicker timePicker = new TimePicker();
        timePicker.setStep(Duration.ofMinutes(15));
        timePicker.addValueChangeListener(e -> {
            activity.setTime(e.getValue());
        });
        if (activity.getTime() != null) {
            timePicker.setValue(activity.getTime());
        }

        TextField textField = new TextField();
        textField.addValueChangeListener(e -> {
            activity.setText(e.getValue());
        });
        if (activity.getText() != null) {
            textField.setValue(activity.getText());
        }

        IconButton removeButton = new IconButton(VaadinIcon.TRASH.create());
        removeButton.addClickListener(e -> {
            parentLayout.remove(medicationLayout);
            entry.removeActivity(activity);
        });
        removeButton.setTitle("Remove activity");

        medicationLayout.add(timePicker, textField, removeButton);

        return medicationLayout;
    }

    /**
     * Adds a button for updating the current entry.
     */
    private void addUpdateButton() {
        Button updateButton = new Button("Update");
        updateButton.addClickListener(event -> {
            binder.writeBeanIfValid(this.entry);
            SessionSingleton.getInstance().save(this.entry);
            UI.getCurrent().getPage().reload();
        });

        this.add(updateButton);
    }
}
