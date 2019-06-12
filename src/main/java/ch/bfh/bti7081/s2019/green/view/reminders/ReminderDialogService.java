package ch.bfh.bti7081.s2019.green.view.reminders;

import com.vaadin.flow.component.UI;

public class ReminderDialogService {

    private static volatile ReminderDialogService instance;

    private ReminderDialogService() {
    }

    public static ReminderDialogService getInstance() {
        if (instance == null) {
            instance = new ReminderDialogService();
        }
        return instance;
    }

    public void showReminder() {
        UI ui = UI.getCurrent();
        ReminderDialog dialog = new ReminderDialog();
        dialog.open();
        ui.push();
    }
}
