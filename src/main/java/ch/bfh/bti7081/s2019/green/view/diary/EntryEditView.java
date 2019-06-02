package ch.bfh.bti7081.s2019.green.view.diary;

import ch.bfh.bti7081.s2019.green.layout.DefaultRouterLayout;
import ch.bfh.bti7081.s2019.green.model.diary.Activity;
import ch.bfh.bti7081.s2019.green.model.diary.Entry;
import ch.bfh.bti7081.s2019.green.model.prescription.Medication;
import ch.bfh.bti7081.s2019.green.persistence.SessionSingleton;
import ch.bfh.bti7081.s2019.green.persistence.dao.EntryDao;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.editor.Editor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import ch.bfh.bti7081.s2019.green.model.diary.ActivityType;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.timepicker.TimePicker;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.WeakHashMap;

@Route(value = "entry/edit", layout = DefaultRouterLayout.class)
@PageTitle("Edit Entry")
public class EntryEditView extends VerticalLayout implements HasUrlParameter<Integer> {

    private Entry entry;
    private Binder<Entry> binder;

    @Override
    public void setParameter(BeforeEvent event, Integer parameter) {
        // TODO: Check for permissions and existence
        EntryDao dao = new EntryDao();
        this.entry = dao.findById(parameter).get();

        this.addHeading();
        this.addForm();
        this.addUpdateButton();
    }

    private void addHeading() {
        H2 heading = new H2("Edit Entry: " + this.entry.getDate());
        this.add(heading);
    }

    private void addForm() {
        FormLayout form = new FormLayout();
        this.binder = new Binder<>();

        // Mood slider
        NumberField slider = new NumberField();
        slider.setMin(1);
        slider.setMax(10);
        slider.setHasControls(true);
        slider.setStep(1);
        slider.setValueChangeMode(ValueChangeMode.EAGER);
        binder.bind(slider, Entry::getMood, Entry::setMood);
        form.addFormItem(slider, "Mood");

        // Sleep
        NumberField sleephour = new NumberField();
        binder.bind(sleephour, Entry::getSleepHours, Entry::setSleepHours);
        form.addFormItem(sleephour, "Sleep");

        // Medication
        VerticalLayout verticalLayout = new VerticalLayout();
        Button addMedicationButton = new Button();
        addMedicationButton.addClickListener(e -> {
            verticalLayout.add(addNewActivity());
        });
        addMedicationButton.setText("Add Medication");

        // Notes
        TextArea notes = new TextArea();
        binder.bind(notes, Entry::getNotes, Entry::setNotes);
        form.addFormItem(notes, "Notes");

        // Populate form with existing data
        binder.readBean(this.entry);
        verticalLayout.add(addMedicationButton);
        for (Activity activity : entry.getActivities()) {
            verticalLayout.add(createMedicationLayout(activity));
        }
        form.addFormItem(verticalLayout, "Medication");
        this.add(form);
    }

    private void addUpdateButton() {
        Button updateButton = new Button("Update");
        updateButton.addClickListener(event -> {
            binder.writeBeanIfValid(this.entry);
            System.out.println(this.entry);
            SessionSingleton.getInstance().save(this.entry);
            UI.getCurrent().navigate("diary");
        });

        this.add(updateButton);
    }
    private HorizontalLayout addNewActivity() {
        Activity newActivity = new Activity();
        newActivity.setType(ActivityType.MEDICATION);
        newActivity.setEntry(this.entry);
        this.entry.addActivity(newActivity);

        return createMedicationLayout(newActivity);
    }

    private HorizontalLayout createMedicationLayout(Activity activity) {
        HorizontalLayout medicationLayout = new HorizontalLayout();

        TimePicker timePicker = new TimePicker();
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
        medicationLayout.add(timePicker, textField);

        return medicationLayout;
    }
}
