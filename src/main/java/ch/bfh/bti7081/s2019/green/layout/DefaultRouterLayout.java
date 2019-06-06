package ch.bfh.bti7081.s2019.green.layout;

import ch.bfh.bti7081.s2019.green.MainView;
import ch.bfh.bti7081.s2019.green.view.diary.MoodDiaryView;
import ch.bfh.bti7081.s2019.green.view.reminders.ReminderView;
import com.github.appreciated.app.layout.behaviour.Behaviour;
import com.github.appreciated.app.layout.builder.AppLayoutBuilder;
import com.github.appreciated.app.layout.component.appbar.AppBarBuilder;
import com.github.appreciated.app.layout.component.menu.left.builder.LeftAppMenuBuilder;
import com.github.appreciated.app.layout.component.menu.left.items.LeftNavigationItem;
import com.github.appreciated.app.layout.router.AppLayoutRouterLayout;
import com.vaadin.flow.component.icon.VaadinIcon;

import static com.github.appreciated.app.layout.entity.Section.FOOTER;
import static com.github.appreciated.app.layout.entity.Section.HEADER;


public class DefaultRouterLayout extends AppLayoutRouterLayout {

    private static final long serialVersionUID = 8467451795454981736L;

    public DefaultRouterLayout() {
        new LeftNavigationItem("Menu", VaadinIcon.MENU.create(), MainView.class);
        init(AppLayoutBuilder
                .get(Behaviour.LEFT_RESPONSIVE_HYBRID)
                .withTitle("Patient Management System")
                .withAppBar(AppBarBuilder.get()
                        .build())
                .withAppMenu(LeftAppMenuBuilder.get()
                        .addToSection(new LeftNavigationItem("Mood Diary", VaadinIcon.BOOK.create(), MoodDiaryView.class), HEADER)
                        .addToSection(new LeftNavigationItem("Reminders", VaadinIcon.BELL.create(), ReminderView.class), FOOTER)
                        .build())
                .build());
    }

    //TODO schedule all existing reminder recurrences on start up
    private void scheduleAllRecurrences() {

    }
}

