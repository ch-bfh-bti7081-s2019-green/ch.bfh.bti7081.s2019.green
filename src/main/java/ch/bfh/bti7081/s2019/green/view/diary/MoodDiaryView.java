package ch.bfh.bti7081.s2019.green.view.diary;

import ch.bfh.bti7081.s2019.green.AuthService;
import ch.bfh.bti7081.s2019.green.MainView;
import ch.bfh.bti7081.s2019.green.layout.DefaultRouterLayout;
import ch.bfh.bti7081.s2019.green.model.diary.Entry;
import ch.bfh.bti7081.s2019.green.model.diary.MoodDiary;
import ch.bfh.bti7081.s2019.green.model.person.Patient;
import ch.bfh.bti7081.s2019.green.model.person.Person;
import ch.bfh.bti7081.s2019.green.persistence.SessionSingleton;
import ch.bfh.bti7081.s2019.green.persistence.dao.MoodDiaryDao;
import ch.bfh.bti7081.s2019.green.view.reminders.ReminderView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;

import java.time.LocalDate;
import java.util.List;

@Route(value = "diary", layout = DefaultRouterLayout.class)
@PageTitle("Diary")
public class MoodDiaryView extends VerticalLayout implements AfterNavigationObserver, BeforeEnterObserver {
    private static final long serialVersionUID = -3626045385294032611L;
    private transient MoodDiary diary;
    private transient Patient patient;

    private void addHeading() {
        H2 heading = new H2("Entries");
        this.add(heading);
    }

    private void addCreateButton() {
        Button createButton = new Button("Create entry for today");
        createButton.addClickListener(event -> {
            Entry newEntry = new Entry();
            newEntry.setDate(LocalDate.now());
            newEntry.setMood(5);
            newEntry.setWaterDrunk(0);
            newEntry.setSleepHours(0);
            newEntry.setNotes("");

            SessionSingleton.getInstance().save(newEntry);
            this.diary.addEntry(newEntry);
            SessionSingleton.getInstance().save(this.diary);

            UI.getCurrent().getPage().reload();
        });
        this.add(createButton);
    }

    private void addGrid(List<Entry> entries) {
        Grid<Entry> grid = new Grid<>(Entry.class);

        grid.addComponentColumn(entry -> {
            Button button = new Button(VaadinIcon.PENCIL.create());
            button.addClickListener(event -> {
                EntryEditDialog dialog = new EntryEditDialog(entry.getId());
                dialog.open();
            });
            return button;
        }).setHeader("Edit");

        grid.setItems(entries);
        grid.removeColumnByKey("id");
        grid.removeColumnByKey("diary");
        grid.removeColumnByKey("activities");
        grid.removeColumnByKey("notes");
        this.add(grid);
    }

    @Override
    public void afterNavigation(AfterNavigationEvent afterNavigationEvent) {
        this.diary = patient.getDiary();
        this.addHeading();

        if (!diary.hasEntryForToday()) {
            this.addCreateButton();
        }

        this.addGrid(diary.getEntries());
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        if(AuthService.getCurrentUser() instanceof Patient && ((Patient) AuthService.getCurrentUser()).hasDiary()) {
            patient = (Patient) AuthService.getCurrentUser();
        } else {
            beforeEnterEvent.forwardTo(MainView.class);
        }
    }
}
