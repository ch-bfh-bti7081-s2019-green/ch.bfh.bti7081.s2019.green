package ch.bfh.bti7081.s2019.green.components;

import com.vaadin.flow.component.dialog.Dialog;

/**
 * Custom dialog class to set some commonly used settings.
 */
public class CustomDialog extends Dialog {
    public CustomDialog() {
        super();
        setMaxWidth("100%");
        setWidth("850px");
        setCloseOnEsc(false);
        setCloseOnOutsideClick(false);
    }
}
