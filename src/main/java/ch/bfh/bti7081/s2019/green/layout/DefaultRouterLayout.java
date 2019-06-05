package ch.bfh.bti7081.s2019.green.layout;

import ch.bfh.bti7081.s2019.green.AuthService;
import ch.bfh.bti7081.s2019.green.MainView;
import ch.bfh.bti7081.s2019.green.view.LoginView;
import ch.bfh.bti7081.s2019.green.view.LoginViewImpl;
import ch.bfh.bti7081.s2019.green.view.chat.ChatView;
import ch.bfh.bti7081.s2019.green.view.diary.MoodDiaryView;
import ch.bfh.bti7081.s2019.green.view.reminders.RemindersLayout;
import com.github.appreciated.app.layout.behaviour.Behaviour;
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

import javax.validation.constraints.Null;

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
            try {
                //UI.getCurrent().navigate("login");
            }catch (NullPointerException e){
                System.out.println("NPE" + e.getMessage());
            }
            /*
            notifications = new DefaultNotificationHolder(newStatus -> {
            });
            badge = new DefaultBadgeHolder(5);
            for (int i = 1; i < 6; i++) {
                notifications.addNotification(new DefaultNotification("Test title" + i, "A rather long test description ..............." + i));
            }
            LeftNavigationItem menuEntry = new LeftNavigationItem("Menu", VaadinIcon.MENU.create(), LoginViewImpl.class);
            badge.bind(menuEntry.getBadge());
            init(AppLayoutBuilder
                    .get(Behaviour.LEFT_RESPONSIVE_HYBRID)
                    .withTitle("Patient Management System")
                    .build());

             */
        } else {
            notifications = new DefaultNotificationHolder(newStatus -> {
            });

            badge = new DefaultBadgeHolder(5);
            for (int i = 1; i < 6; i++) {
                // TODO replace this with actual reminders
                notifications.addNotification(new DefaultNotification("Test title" + i, "A rather long test description ..............." + i));
            }
            LeftNavigationItem menuEntry = new LeftNavigationItem("Menu", VaadinIcon.MENU.create(), MainView.class);
            badge.bind(menuEntry.getBadge());
            init(AppLayoutBuilder
                    .get(Behaviour.LEFT_RESPONSIVE_HYBRID)
                    .withTitle("Patient Management System")
                    .withAppBar(AppBarBuilder.get()
                            .add(new AppBarNotificationButton<>(VaadinIcon.BELL, notifications))
                            .build())
                    .withAppMenu(LeftAppMenuBuilder.get()
                            .addToSection(new LeftNavigationItem("Chat", VaadinIcon.COMMENTS.create(), ChatView.class), HEADER)
                            .addToSection(new LeftNavigationItem("Mood Diary", VaadinIcon.BOOK.create(), MoodDiaryView.class), HEADER)
                            .addToSection(new LeftNavigationItem("Reminders", VaadinIcon.BELL.create(), RemindersLayout.class), FOOTER)
                            .build())
                    .build());
        }
    }

    //TODO schedule all existing reminder recurrences on start up
    private void scheduleAllRecurrences() {

    }
}

