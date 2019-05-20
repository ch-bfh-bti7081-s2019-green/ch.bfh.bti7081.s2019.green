package ch.bfh.bti7081.s2019.green.layout;

import ch.bfh.bti7081.s2019.green.MainView;
import ch.bfh.bti7081.s2019.green.model.person.Person;
import ch.bfh.bti7081.s2019.green.presenter.LoginPresenter;
import ch.bfh.bti7081.s2019.green.view.LoginViewImpl;
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
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.router.Route;
import lombok.Builder;

import static com.github.appreciated.app.layout.entity.Section.FOOTER;
import static com.github.appreciated.app.layout.entity.Section.HEADER;

@Route("home")
public class DefaultRouterLayout extends AppLayoutRouterLayout {
    /**
     * Do not initialize here. This will lead to NPEs
     */
    private DefaultNotificationHolder notifications;
    private DefaultBadgeHolder badge;

    public DefaultRouterLayout() {
        notifications = new DefaultNotificationHolder(newStatus -> {
        });
        badge = new DefaultBadgeHolder(5);
        for (int i = 1; i < 6; i++) {
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
                        .addToSection(new LeftNavigationItem("Mood Diary", VaadinIcon.BOOK.create(), MainView.class), HEADER)
                        .addToSection(new LeftNavigationItem("Reminders", VaadinIcon.BELL.create(), MainView.class), FOOTER)
                        .build())
                .build());
    }
}

