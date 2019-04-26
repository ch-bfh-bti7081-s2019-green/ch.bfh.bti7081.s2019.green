package dev.schaer.vaadin;

import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.router.Route;
import org.atmosphere.interceptor.AtmosphereResourceStateRecovery;

import java.awt.*;

/**
 * The main view contains a button and a click listener.
 */
@Route
@PWA(name = "My Application", shortName = "My Application")
public class MainView extends VerticalLayout {

    public MainView() {
        addNumberButton(1);
        addNumberButton(2);
        addNumberButton(3);
        addNumberButton(4);
        addNumberButton(5);
        addNumberButton(6);
        addNumberButton(7);
        addNumberButton(8);
        addNumberButton(9);
        Button btnSum = new Button("Sum", event -> {
            int sum = Calculator.numbers.stream()
                    .mapToInt(Integer::intValue)
                    .sum();
            alert(String.format("sum=%d", sum));
        });
        add(btnSum);
    }

    private void addNumberButton(int n){
        Button btn = new Button(Integer.toString(n), event -> {
            alert(n + " added to stack");
            Calculator.numbers.push(n);
        });
        add(btn);
    }

    private void alert(String msg){
        Notification n = new Notification(msg, 1000);
        n.open();
    }
}
