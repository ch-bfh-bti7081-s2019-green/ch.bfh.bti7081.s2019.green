package ch.bfh.bti7081.s2019.green.view.reminders;

import ch.bfh.bti7081.s2019.green.components.CustomFormLayout;
import ch.bfh.bti7081.s2019.green.model.prescription.Prescription;
import ch.bfh.bti7081.s2019.green.model.reminder.Reminder;
import ch.bfh.bti7081.s2019.green.model.reminder.ReminderRecurrence;
import ch.bfh.bti7081.s2019.green.persistence.dao.PrescriptionDao;
import ch.bfh.bti7081.s2019.green.persistence.dao.ReminderDao;
import ch.bfh.bti7081.s2019.green.persistence.dao.ReminderRecurrenceDao;
import ch.bfh.bti7081.s2019.green.scheduler.ReminderSchedulerService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.data.value.ValueChangeMode;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReminderAddFormLayout extends CustomFormLayout {

    private static final long serialVersionUID = 4027708884695938720L;
    private LocalDateTime selectedTime;
    private transient Prescription selectedPrescription;
    private transient PrescriptionDao prescriptionDao = new PrescriptionDao();
    private transient Reminder newReminder;

    public ReminderAddFormLayout() {
        newReminder = new Reminder();
        FormLayout formLayout = new FormLayout();
        formLayout.add();
        initialiseLayout();
    }

    private void initialiseLayout() {
        Select prescriptionSelect = new Select();
        prescriptionSelect.setLabel("Prescription");
        Optional<List<Prescription>> prescriptions = prescriptionDao.getAllPrescriptions();

        if (prescriptions.isPresent()) {
            prescriptionSelect.setItems(prescriptions.get());
        }

        prescriptionSelect.addValueChangeListener(e -> {
            this.selectedPrescription = (Prescription) e.getValue();
        });

        TimePicker timePicker = new TimePicker();

        timePicker.setLabel("Reminder Time");

        timePicker.addValueChangeListener(e -> {
            LocalDate localDate = LocalDate.of(2019, 1, 1);
            selectedTime = LocalDateTime.of(localDate, e.getValue());
        });

        VerticalLayout recurrencesVerticalLayout = new VerticalLayout();
        Button addRecurrenceButton = new Button();
        addRecurrenceButton.setText("Add a recurrence");

        addRecurrenceButton.addClickListener(e -> {
            recurrencesVerticalLayout.add(addRecurrencesLayout(null));
        });


        /*for(ReminderRecurrence recurrence : newReminder.getRecurrences()){
            recurrencesVerticalLayout.add(addRecurrencesLayout(recurrence));
        }*/

        add(prescriptionSelect);
        add(timePicker);

        add(addRecurrenceButton);
        add(recurrencesVerticalLayout);
    }

    public void saveNewReminder() {
        newReminder.setNotificationTime(this.selectedTime.atZone(ZoneId.of("Europe/Paris")));
        newReminder.setPrescription(this.selectedPrescription);
        ReminderDao reminderDao = new ReminderDao();
        reminderDao.addReminder(newReminder);
        ReminderRecurrenceDao reminderRecurrenceDao = new ReminderRecurrenceDao();
        for (ReminderRecurrence recurrence : newReminder.getRecurrences()) {
            recurrence.setReminder(newReminder);
            reminderRecurrenceDao.addReminder(recurrence);
        }
        ReminderSchedulerService.getInstance().addReminder(newReminder);
    }

    private HorizontalLayout addRecurrencesLayout(ReminderRecurrence recurrence) {
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        ReminderRecurrence newRecurrence;

        if (recurrence == null) {
            newRecurrence = new ReminderRecurrence();
        } else {
            newRecurrence = recurrence;
        }

        if (newReminder.getRecurrences() == null) {
            newReminder.setRecurrences(new ArrayList<>());
        }

        newReminder.getRecurrences().add(newRecurrence);

        Button removeButton = new Button();
        removeButton.setText("remove");
        removeButton.addClickListener(e -> {
            horizontalLayout.setVisible(false);
            newReminder.getRecurrences().remove(newRecurrence);
        });

        NumberField slider = new NumberField();
        slider.setMin(1);
        slider.setMax(10);
        slider.setHasControls(true);
        slider.setStep(1);
        slider.setValue(1.0);
        slider.setValueChangeMode(ValueChangeMode.EAGER);

        slider.addValueChangeListener(e -> {
            newRecurrence.setDuration(Duration.of(e.getValue().longValue(), ChronoUnit.HOURS));
        });

        horizontalLayout.setAlignItems(FlexComponent.Alignment.CENTER);

        horizontalLayout.add(slider);
        horizontalLayout.add(new Label("h"));
        horizontalLayout.add(removeButton);

        return horizontalLayout;
    }
}
