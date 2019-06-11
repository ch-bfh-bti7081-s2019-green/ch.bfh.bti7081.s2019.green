package ch.bfh.bti7081.s2019.green.view.reminders;

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
        ReminderDialog dialog = new ReminderDialog();
        dialog.open();
    }
}
