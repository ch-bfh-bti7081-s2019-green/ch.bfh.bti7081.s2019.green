package ch.bfh.bti7081.s2019.green.layout;

import ch.bfh.bti7081.s2019.green.AuthService;
import ch.bfh.bti7081.s2019.green.MainView;
import ch.bfh.bti7081.s2019.green.view.diary.MoodDiaryView;
import ch.bfh.bti7081.s2019.green.view.reminders.RemindersLayout;
import ch.bfh.bti7081.s2019.green.view.LoginViewImpl;
import com.github.appreciated.app.layout.behaviour.AppLayout;
import com.github.appreciated.app.layout.behaviour.Behaviour;
import com.github.appreciated.app.layout.behaviour.LeftLayouts;
import com.github.appreciated.app.layout.builder.AppLayoutBuilder;
import com.github.appreciated.app.layout.component.appbar.AppBarBuilder;
import com.github.appreciated.app.layout.component.menu.left.builder.LeftAppMenuBuilder;
import com.github.appreciated.app.layout.component.menu.left.items.LeftNavigationItem;
import com.github.appreciated.app.layout.entity.DefaultBadgeHolder;
import com.github.appreciated.app.layout.notification.DefaultNotificationHolder;
import com.github.appreciated.app.layout.notification.component.AppBarNotificationButton;
import com.github.appreciated.app.layout.notification.entitiy.DefaultNotification;
import com.github.appreciated.app.layout.router.AppLayoutRouterLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.BootstrapListener;
import com.vaadin.flow.server.BootstrapPageResponse;



import static com.github.appreciated.app.layout.entity.Section.FOOTER;
import static com.github.appreciated.app.layout.entity.Section.HEADER;


public class DefaultRouterLayout extends AppLayoutRouterLayout {
    /**
     * Do not initialize here. This will lead to NPEs
     */
    private DefaultNotificationHolder notifications;
    private DefaultBadgeHolder badge;

    public DefaultRouterLayout() {

        if (!AuthService.isLoggedIn()) {
            init(AppLayoutBuilder
                    .get(Behaviour.LEFT_RESPONSIVE_HYBRID)
                    .withTitle("Patient Management System")
                    .build());
            UI.getCurrent().navigate("login");
        } else {
            LeftNavigationItem menuEntry = new LeftNavigationItem("Menu", VaadinIcon.MENU.create(), MainView.class);
            badge.bind(menuEntry.getBadge());
            init(AppLayoutBuilder
                    .get(Behaviour.LEFT_RESPONSIVE_HYBRID)
                    .withTitle("Patient Management System")
                    .withAppBar(AppBarBuilder.get()
                            .add(new AppBarNotificationButton<>(VaadinIcon.BELL, notifications))
                            .build())
                    .withAppMenu(LeftAppMenuBuilder.get()
                            .addToSection(new LeftNavigationItem("Mood Diary", VaadinIcon.BOOK.create(), MoodDiaryView.class), HEADER)
                            .addToSection(new LeftNavigationItem("Reminders", VaadinIcon.BELL.create(), RemindersLayout.class), FOOTER)
                            .build())
                    .build());
        }

    }

    //TODO schedule all existing reminder recurrences on start up
    private void scheduleAllRecurrences(){

    }
}

