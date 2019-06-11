package ch.bfh.bti7081.s2019.green.view.emergency;

import ch.bfh.bti7081.s2019.green.AuthService;
import ch.bfh.bti7081.s2019.green.model.person.Patient;
import ch.bfh.bti7081.s2019.green.model.person.Person;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class EmergencyPopup extends VerticalLayout {

    public EmergencyPopup() {
        initializeDialog();
    }

    private void initializeDialog() {

        this.add(new H3("Emergency Contacts:"));
        this.add(createPhoneNumber("112", "General Emergency"));
        this.add(createPhoneNumber("143", "Suicide Hotline"));
        this.add(createPhoneNumber("031 839 21 42", "Hospital"));
        this.add(createPhoneNumber("079 333 31 05", "Doctor"));

        Person user = AuthService.getCurrentUser();
        if (user instanceof Patient) {
            Patient patient = (Patient) user;
            this.add(createPhoneNumber(patient.getTherapist().getContactData().getPhone(), patient.getTherapist().getFullName()));
        }

        Dialog dialog = new Dialog();
        dialog.add(this);
        dialog.open();
    }

    private HorizontalLayout createPhoneNumber(final String phoneNumber, final String label) {
        return new HorizontalLayout(
                new Label(label),
                new Anchor("tel:" + phoneNumber.replace(" ", ""), phoneNumber)
        );
    }


}
