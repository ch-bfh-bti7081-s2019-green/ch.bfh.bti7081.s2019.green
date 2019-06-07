package ch.bfh.bti7081.s2019.green.view;

import ch.bfh.bti7081.s2019.green.layout.DefaultRouterLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.html.Label;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

@Route(value = "reminders", layout = DefaultRouterLayout.class)
public class Emergency extends VerticalLayout {

    public Emergency() {
        initializeLayout();
    }

    private void initializeLayout() {
        //HorizontalLayout horizontalLayout = new HorizontalLayout();

        Label header = new Label();
        header.setText("Emergency Contacts:");

        Label contactGeneral = new Label("112");
        Label contactHospital = new Label("031 839 21 42");
        Label contactSuicid = new Label("143");
        Label conactDoctor = new Label("079 333 31 05");


        add(header);
        //add(horizontalLayout);
        add(contactGeneral);
        add(contactHospital);
        add(contactSuicid);
        add(conactDoctor);



    }



}
