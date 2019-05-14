package ch.bfh.bti7081.s2019.green.view.diary;

import ch.bfh.bti7081.s2019.green.layout.DefaultRouterLayout;
import ch.bfh.bti7081.s2019.green.model.diary.Entry;
import ch.bfh.bti7081.s2019.green.model.diary.MoodDiary;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.time.LocalDate;
import java.util.List;

@Route(value = "diary", layout = DefaultRouterLayout.class)
@PageTitle("Diary")
public class MoodDiaryView extends VerticalLayout {

    public MoodDiaryView() {
        MoodDiary diary = this.getDiary();
        this.addHeading();
        this.addGrid(diary.getEntries());
    }

    private MoodDiary getDiary() {
        MoodDiary diary = new MoodDiary();
        Entry entry = new Entry();
        entry.setMood(10);
        entry.setId(2);
        entry.setDate(LocalDate.now());
        diary.addEntry(entry);

        Entry entry2 = new Entry();
        entry2.setMood(5);
        entry2.setId(3);
        entry2.setDate(LocalDate.now().minusDays(1));
        diary.addEntry(entry2);
        return diary;
    }

    private void addHeading() {
        H2 heading = new H2("Entries");
        this.add(heading);
    }

    private void addGrid(List<Entry> entries) {
        Grid<Entry> grid = new Grid<>(Entry.class);

        grid.addComponentColumn(entry -> {
            Button button = new Button(VaadinIcon.PENCIL.create());
            button.addClickListener(event -> {
                UI.getCurrent().navigate("entry/" + entry.getId());
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
}
