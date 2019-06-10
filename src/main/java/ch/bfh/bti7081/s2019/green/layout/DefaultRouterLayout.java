package ch.bfh.bti7081.s2019.green.layout;

import ch.bfh.bti7081.s2019.green.AuthService;
import ch.bfh.bti7081.s2019.green.MainView;
import ch.bfh.bti7081.s2019.green.model.person.Patient;
import ch.bfh.bti7081.s2019.green.model.person.Person;
import ch.bfh.bti7081.s2019.green.model.person.Therapist;
import ch.bfh.bti7081.s2019.green.view.about.AboutView;
import ch.bfh.bti7081.s2019.green.view.diary.MoodDiaryView;
import ch.bfh.bti7081.s2019.green.view.login.LoginView;
import ch.bfh.bti7081.s2019.green.view.reminders.ReminderView;
import com.github.appreciated.app.layout.behaviour.Behaviour;
import com.github.appreciated.app.layout.builder.AppLayoutBuilder;
import com.github.appreciated.app.layout.component.appbar.AppBarBuilder;
import com.github.appreciated.app.layout.component.menu.left.builder.LeftAppMenuBuilder;
import com.github.appreciated.app.layout.component.menu.left.builder.LeftSubMenuBuilder;
import com.github.appreciated.app.layout.component.menu.left.items.LeftIconItem;
import com.github.appreciated.app.layout.component.menu.left.items.LeftNavigationItem;
import com.github.appreciated.app.layout.router.AppLayoutRouterLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;

import static com.github.appreciated.app.layout.entity.Section.FOOTER;
import static com.github.appreciated.app.layout.entity.Section.HEADER;


public class DefaultRouterLayout extends AppLayoutRouterLayout implements BeforeEnterObserver, AfterNavigationObserver {

    private static final long serialVersionUID = 8467451795454981736L;

    public DefaultRouterLayout() {
        init(AppLayoutBuilder
                .get(Behaviour.LEFT_RESPONSIVE_HYBRID)
                .withTitle("Patient Management System")
                .withAppBar(AppBarBuilder.get().build())
                .build());
    }


    @Override
    public void afterNavigation(AfterNavigationEvent afterNavigationEvent) {
        new LeftNavigationItem("Menu", VaadinIcon.MENU.create(), MainView.class);
        AppLayoutBuilder layoutBuilder = AppLayoutBuilder
                .get(Behaviour.LEFT_RESPONSIVE_HYBRID)
                .withTitle("Patient Management System")
                .withAppBar(AppBarBuilder.get().build());

        layoutBuilder.withAppMenu(createMenu());

        init(layoutBuilder.build());
    }

    private Component createMenu() {
        Person currentUser = AuthService.getCurrentUser();

        LeftAppMenuBuilder menuBuilder = LeftAppMenuBuilder.get();

        menuBuilder.addToSection(createChatItem(currentUser), HEADER);

        if (currentUser instanceof Patient) {
            if (((Patient) currentUser).hasDiary()) {
                menuBuilder.addToSection(new LeftNavigationItem("Mood Diary", VaadinIcon.BOOK.create(), MoodDiaryView.class), HEADER);
            }

            menuBuilder.addToSection(new LeftNavigationItem("Reminders", VaadinIcon.BELL.create(), ReminderView.class), HEADER);
        }

        menuBuilder.addToSection(new LeftNavigationItem("About", VaadinIcon.INFO_CIRCLE.create(), AboutView.class), HEADER);

        menuBuilder.withStickyFooter().addToSection(createLogoutItem(), FOOTER);

        return menuBuilder.build();
    }

    private Component createChatItem(final Person currentUser) {
        if (currentUser instanceof Patient) {
            return LeftSubMenuBuilder.get("Chat", VaadinIcon.COMMENTS.create())
                    .add(createChatSubMenuItem(((Patient) currentUser).getTherapist()))
                    .build();
        }

        if (currentUser instanceof Therapist) {
            Therapist therapist = (Therapist) currentUser;

            LeftSubMenuBuilder submenu = LeftSubMenuBuilder.get("Chat", VaadinIcon.COMMENTS.create());

            for (Patient patient : therapist.getPatients()) {
                submenu.add(createChatSubMenuItem(patient));
            }

            return submenu.build();
        }

        return null;
    }

    private LeftIconItem createChatSubMenuItem(final Person chatPartner) {
        LeftIconItem item = new LeftIconItem(chatPartner.getFullName(), VaadinIcon.COMMENT.create());

        item.setClickListener(e -> {
            UI.getCurrent().navigate("chat/" + chatPartner.getUsername());
        });

        return item;
    }

    private LeftNavigationItem createLogoutItem() {
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

