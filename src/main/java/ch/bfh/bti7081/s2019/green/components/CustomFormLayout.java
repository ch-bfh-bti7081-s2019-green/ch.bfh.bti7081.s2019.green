package ch.bfh.bti7081.s2019.green.components;

import com.vaadin.flow.component.formlayout.FormLayout;

/**
 * Custom form layout class to set some commonly used settings.
 */
public class CustomFormLayout extends FormLayout {
    public CustomFormLayout() {
        super();
        setResponsiveSteps(new ResponsiveStep("0", 1));
    }
}
