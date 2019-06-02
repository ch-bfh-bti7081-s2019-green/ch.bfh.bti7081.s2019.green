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

        //----------------------------------------------

        /*
        Grid<Entry> grid = new Grid<>();


        List<Activity> activities = getAlignItems();
        List<Entry> activities = getItems();
        grid.setItems(persons);
        Grid.Column<Entry> nameColumn = grid.addColumn(Entry::getActivity)
                .setHeader("First name");
        Grid.Column<Entry> subscriberColumn = grid
                .addColumn(Person::isSubscriber).setHeader("Subscriber");

        Binder<Entry> binder = new Binder<>(Entry.class);
        Editor<Entry> editor = grid.getEditor();
        editor.setBinder(binder);
        editor.setBuffered(true);

        Div validationStatus = new Div();
        validationStatus.setId("validation");

        TextField field = new TextField();
        binder.forField(field)
                .withValidator(name -> !name.isEmpty(),
                        "Name should not be empty")
                .withStatusLabel(validationStatus).bind("firstName");
       // nameColumn.setEditorComponent(field);

        Checkbox checkbox = new Checkbox();
        binder.bind(checkbox, "subscriber");
        //subscriberColumn.setEditorComponent(checkbox);

        Collection<Button> editButtons = Collections
                .newSetFromMap(new WeakHashMap<>());

        Grid.Column<Entry> editorColumn = grid.addComponentColumn(person -> {
            Button edit = new Button("Edit");
            edit.addClassName("edit");
            edit.addClickListener(e -> {
                editor.editItem(person);
                field.focus();
            });
            edit.setEnabled(!editor.isOpen());
            editButtons.add(edit);
            return edit;
        });

        editor.addOpenListener(e -> editButtons.stream()
                .forEach(button -> button.setEnabled(!editor.isOpen())));
        editor.addCloseListener(e -> editButtons.stream()
                .forEach(button -> button.setEnabled(!editor.isOpen())));

        Button save = new Button("Save", e -> editor.save());
        save.addClassName("save");

        Button cancel = new Button("Cancel", e -> editor.cancel());
        cancel.addClassName("cancel");

// Add a keypress listener that listens for an escape key up event.
// Note! some browsers return key as Escape and some as Esc
        grid.getElement().addEventListener("keyup", event -> editor.cancel())
                .setFilter("event.key === 'Escape' || event.key === 'Esc'");

        Div buttons = new Div(save, cancel);
        editorColumn.setEditorComponent(buttons);

       /* editor.addSaveListener(
                event -> message.setText(event.getItem().getfirstName() + ", "
                        + event.getItem().isSubscriber));
*/

        //----------------------------------------------

        // Notes
        TextArea notes = new TextArea();
        binder.bind(notes, Entry::getNotes, Entry::setNotes);
        form.addFormItem(notes, "Notes");

        // Populate form with existing data
        binder.readBean(this.entry);
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
}
