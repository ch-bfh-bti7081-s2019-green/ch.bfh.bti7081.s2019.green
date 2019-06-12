package ch.bfh.bti7081.s2019.green.view.reminders;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;

public class ReminderDialog extends Dialog {

    public ReminderDialog() {
        super();
        initialiseLayout();
        this.open();
    }

    private void initialiseLayout() {
        this.add(new Label("HELLLOOOO"));
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        UI ui = attachEvent.getUI();

        ui.access(() -> {
            ui.push();
        });
    }
}
