package dev.schaer.vaadin;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.router.Route;
import org.atmosphere.interceptor.AtmosphereResourceStateRecovery;

import java.awt.*;
import java.util.function.Consumer;

/**
 * The main view contains a button and a click listener.
 */
@Route
@PWA(name = "My Application", shortName = "My Application")
public class MainView extends  VerticalLayout{

    private final Calculator calculator = new Calculator();
    private static final String LBL_TXT = "Result of %s =";
    private final Label label = new Label(String.format(LBL_TXT, ""));

    public MainView() {
        HorizontalLayout firstRow = new HorizontalLayout(btn(Operator.RESET), btn(Operator.ADD), btn(Operator.SUBTRACT));
        HorizontalLayout secondRow = new HorizontalLayout(btn(Operator.CLEAR), btn(Operator.MULTIPLY), btn(Operator.DIVIDE));
        HorizontalLayout thirdRow = new HorizontalLayout(btn(Operator.SEVEN), btn(Operator.EIGHT), btn(Operator.NINE));
        HorizontalLayout fourthRow = new HorizontalLayout(btn(Operator.FOUR), btn(Operator.FIVE), btn(Operator.SIX));
        HorizontalLayout fifthRow = new HorizontalLayout(btn(Operator.ONE), btn(Operator.TWO), btn(Operator.THREE));
        HorizontalLayout sixthRow = new HorizontalLayout(btn(Operator.ZERO), btn(Operator.COMMA), btn(Operator.CALC));

        VerticalLayout layout = new VerticalLayout(
                firstRow,
                secondRow,
                thirdRow,
                fourthRow,
                fifthRow,
                sixthRow,
                label
        );

        add(layout);
    }

    private Button btn(final Operator op){
        switch (op){
            case RESET:
                return new Button(op.getLabel(), e -> {
                    calculator.reset();
                    label.setText(String.format(LBL_TXT, ""));
                    alert("Calculator Reset!", 1500);
                });
            case CALC:
                return new Button(op.getLabel(), e -> {
                    double result = calculator.calc();
                    label.setText(String.format(LBL_TXT + " %s", calculator.getInput(), Calculator.format(result)));
                    calculator.reset();
                });
            default:
                return new Button(op.getLabel(), e -> {
                    calculator.push(op);
                    label.setText(String.format(LBL_TXT, calculator.getInput()));
                    alert(op.name().toLowerCase(), 500);
                });
        }
    }

    private void alert(String msg){
        alert(msg, 1000);
    }

    private void alert(String msg, int durationInMS){
        Notification n = new Notification(msg, durationInMS);
        n.open();
    }
}
