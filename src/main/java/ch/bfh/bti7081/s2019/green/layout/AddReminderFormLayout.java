package ch.bfh.bti7081.s2019.green.layout;

import ch.bfh.bti7081.s2019.green.model.reminder.Reminder;
import ch.bfh.bti7081.s2019.green.persistence.dao.ReminderDao;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.timepicker.TimePicker;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class AddReminderFormLayout extends FormLayout {

    private LocalDateTime selectedTime;
    private String selectedPrescription;

    public AddReminderFormLayout() {
        FormLayout formLayout = new FormLayout();
        formLayout.add();
        initialiseLayout();
    }

    private void initialiseLayout() {
        Select prescriptionSlect = new Select();
        prescriptionSlect.setLabel("Prescription");

        Select prescriptionSelect = new Select();
        List<String> prescriptions = new ArrayList<>();
        prescriptions.add("Prescription Example");

        prescriptionSelect.setItems(prescriptions);

        prescriptionSelect.addValueChangeListener(e -> {
            this.selectedPrescription = e.getValue().toString();
        });

        TimePicker timePicker = new TimePicker();

        timePicker.setLabel("Reminder Time");

        timePicker.addValueChangeListener(e -> {
            LocalDate localDate = LocalDate.of(2019, 01, 01);
            selectedTime = LocalDateTime.of(localDate, e.getValue());
        });

        add(prescriptionSelect);
        add(timePicker);
    }

    public void saveNewReminder() {
        Reminder newReminder = new Reminder();
        newReminder.setNotificationTime(this.selectedTime.atZone(ZoneId.of("Europe/Paris")));
        ReminderDao reminderDao = new ReminderDao();
        reminderDao.addReminder(newReminder);
    }
}
