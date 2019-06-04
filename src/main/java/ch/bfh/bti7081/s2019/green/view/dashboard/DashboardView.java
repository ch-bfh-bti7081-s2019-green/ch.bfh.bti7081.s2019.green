package ch.bfh.bti7081.s2019.green.view.dashboard;


import ch.bfh.bti7081.s2019.green.layout.DefaultRouterLayout;
import com.sun.java.swing.plaf.windows.WindowsBorders;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "home", layout = DefaultRouterLayout.class)
@PageTitle("Diary")
public class DashboardView extends VerticalLayout {
    public DashboardView() {
        Text t = new Text("Hello, World!");
        this.add(t);
    }
}
