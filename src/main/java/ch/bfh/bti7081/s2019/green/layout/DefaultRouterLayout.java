package ch.bfh.bti7081.s2019.green.layout;

import ch.bfh.bti7081.s2019.green.AuthService;
import ch.bfh.bti7081.s2019.green.MainView;
import ch.bfh.bti7081.s2019.green.view.LoginView;
import ch.bfh.bti7081.s2019.green.view.diary.MoodDiaryView;
import ch.bfh.bti7081.s2019.green.view.reminders.ReminderView;
import com.github.appreciated.app.layout.behaviour.Behaviour;
import com.github.appreciated.app.layout.builder.AppLayoutBuilder;
import com.github.appreciated.app.layout.component.appbar.AppBarBuilder;
import com.github.appreciated.app.layout.component.menu.left.builder.LeftAppMenuBuilder;
import com.github.appreciated.app.layout.component.menu.left.items.LeftIconItem;
import com.github.appreciated.app.layout.component.menu.left.items.LeftNavigationItem;
import com.github.appreciated.app.layout.component.menu.left.items.LeftSectionItem;
import com.github.appreciated.app.layout.router.AppLayoutRouterLayout;
import com.github.appreciated.card.content.IconItem;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;

import static com.github.appreciated.app.layout.entity.Section.FOOTER;
import static com.github.appreciated.app.layout.entity.Section.HEADER;


public class DefaultRouterLayout extends AppLayoutRouterLayout implements BeforeEnterObserver {

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
                        .addToSection(new LeftNavigationItem("Reminders", VaadinIcon.BELL.create(), ReminderView.class), HEADER)
                        .addToSection(getLogoutItem(), FOOTER)
                        .build())
                .build());
    }

    private LeftNavigationItem getLogoutItem() {
        LeftNavigationItem logoutItem = new LeftNavigationItem("Logout", VaadinIcon.EXIT.create(), LoginView.class);
        logoutItem.setClickListener(e -> {
            AuthService authService = new AuthService();
            authService.logout();
        });
        return logoutItem;
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        if (!AuthService.isLoggedIn()) {
            beforeEnterEvent.forwardTo(LoginView.class);
        }
    }
}

