package ch.bfh.bti7081.s2019.green.view.reminders;

import ch.bfh.bti7081.s2019.green.layout.DefaultRouterLayout;
import ch.bfh.bti7081.s2019.green.model.reminder.Reminder;
import ch.bfh.bti7081.s2019.green.persistence.dao.ReminderDao;
import ch.bfh.bti7081.s2019.green.scheduler.ReminderSchedulerService;
import ch.bfh.bti7081.s2019.green.scheduler.Scheduler;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.selection.SingleSelect;
import com.vaadin.flow.router.Route;

import java.time.ZonedDateTime;

@Route(layout = DefaultRouterLayout.class)
public class RemindersLayout extends VerticalLayout {

    private ReminderDao reminderDao = new ReminderDao();
    private Grid remindersGrid;
    private Reminder selectedReminder;
    private Scheduler scheduler = Scheduler.getInstance();

    public RemindersLayout() {
        initialiseLayout();
    }

    private void initialiseLayout() {
        HorizontalLayout horizontalLayout = new HorizontalLayout();

        Label header = new Label();
        header.setText("Your current prescription reminders:");

        Button addButton = new Button();
        addButton.setText("Add Reminder");

        Button removeButton = new Button();
        removeButton.setText("Delete Reminder");
        removeButton = removeButton;

        removeButton.addClickListener(e -> {
            if (this.selectedReminder != null) {
                ReminderSchedulerService.getInstance().removeReminder(selectedReminder);
                reminderDao.removeReminder(this.selectedReminder);
                remindersGrid.setItems(reminderDao.findAll());
            }
        });


        addButton.addClickListener(click -> {
            openAddReminderDialog();
        });

        horizontalLayout.add(addButton, removeButton);

        add(header);
        add(horizontalLayout);
        add(initialiseRemindersGrid());
    }

    private Grid initialiseRemindersGrid() {
        Grid<Reminder> remindersGrid = new Grid<>(Reminder.class);

        remindersGrid.setItems(reminderDao.findAll());
        remindersGrid.setColumns("prescription", "notificationTime");
        remindersGrid.addComponentColumn(e -> {
            Label hasRecurrenceLabel = new Label();
            if(e.getRecurrences().isEmpty()){
                hasRecurrenceLabel.setText("No");
            }else{
                hasRecurrenceLabel.setText("Yes");
            }
            return hasRecurrenceLabel;
        }).setHeader("Has Recurrences");

        remindersGrid.setSelectionMode(Grid.SelectionMode.SINGLE);

        SingleSelect<Grid<Reminder>, Reminder> personSelect = remindersGrid.asSingleSelect();
        personSelect.addValueChangeListener(e -> {
            this.selectedReminder = e.getValue();
        });

        this.remindersGrid = remindersGrid;
        return remindersGrid;

    }

    private void openAddReminderDialog() {
        Dialog addReminderDialog = new Dialog();

        AddReminderFormLayout dialogLayout = new AddReminderFormLayout();

        addReminderDialog.add(dialogLayout);

        addReminderDialog.setCloseOnEsc(false);
        addReminderDialog.setCloseOnOutsideClick(false);

        NativeButton confirmButton = new NativeButton("Add", event -> {
            dialogLayout.saveNewReminder();
            remindersGrid.setItems(reminderDao.findAll());
            addReminderDialog.close();
        });
        NativeButton cancelButton = new NativeButton("Cancel", event -> {
            addReminderDialog.close();
        });


        addReminderDialog.add(confirmButton, cancelButton);
        addReminderDialog.open();
    }

}
