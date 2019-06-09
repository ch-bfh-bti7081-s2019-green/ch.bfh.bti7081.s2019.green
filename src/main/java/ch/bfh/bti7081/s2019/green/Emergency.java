package ch.bfh.bti7081.s2019.green;

import ch.bfh.bti7081.s2019.green.layout.DefaultRouterLayout;
import ch.bfh.bti7081.s2019.green.view.reminders.ReminderAddFormLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.html.Label;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class Emergency extends VerticalLayout {

    public Emergency() {
        initializeDialog();
    }

    private void initializeDialog() {

        Label header = new Label();
        header.setText("Emergency Contacts:");

        Label contactGeneral = new Label("General: 112");
        Label contactHospital = new Label("Hospital: 031 839 21 42");
        Label contactSuicid = new Label("Suicid: 143");
        Label conactDoctor = new Label("Doctor: 079 333 31 05");

        add(header);
        add(contactGeneral);
        add(contactHospital);
        add(contactSuicid);
        add(conactDoctor);

        Dialog dialog = new Dialog();
        dialog.add(this);
        dialog.open();
    }



}
