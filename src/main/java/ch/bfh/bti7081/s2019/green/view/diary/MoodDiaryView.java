package ch.bfh.bti7081.s2019.green.view.diary;

import ch.bfh.bti7081.s2019.green.layout.DefaultRouterLayout;
import ch.bfh.bti7081.s2019.green.model.diary.Entry;
import ch.bfh.bti7081.s2019.green.model.diary.MoodDiary;
import ch.bfh.bti7081.s2019.green.persistence.dao.MoodDiaryDao;
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
        // TODO: Get the diary of the currently logged in patient
        MoodDiaryDao dao = new MoodDiaryDao();
        return dao.findById(1).get();
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
                UI.getCurrent().navigate("entry/edit/" + entry.getId());
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
