package ch.bfh.bti7081.s2019.green.view.diary;

import ch.bfh.bti7081.s2019.green.layout.DefaultRouterLayout;
import ch.bfh.bti7081.s2019.green.model.diary.Entry;
import ch.bfh.bti7081.s2019.green.persistence.dao.EntryDao;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "entry/edit", layout = DefaultRouterLayout.class)
@PageTitle("Edit Entry")
public class EntryEditView extends VerticalLayout implements HasUrlParameter<Integer> {

    private Entry entry;

    @Override
    public void setParameter(BeforeEvent event, Integer parameter) {
        // TODO: Check for permissions and existence
        EntryDao dao = new EntryDao();
        this.entry = dao.findById(parameter).get();

        FormLayout form = new FormLayout();
        NumberField slider = new NumberField();
        slider.setValue(5.0);
        slider.setMin(1);
        slider.setMax(10);
        slider.setHasControls(true);
        slider.setStep(1);

        form.addFormItem(slider, "Mood");

        this.add(new H2("Edit Entry: " + this.entry.getDate()));
        this.add(form);
        this.add(new Button("Update"));
    }
}
