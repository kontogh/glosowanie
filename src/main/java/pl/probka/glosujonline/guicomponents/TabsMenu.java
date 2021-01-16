package pl.probka.glosujonline.guicomponents;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.menubar.MenuBarVariant;

public class TabsMenu extends Div {
    public TabsMenu() {
        MenuBar menuBar = new MenuBar();
        MenuItem homepage = menuBar.addItem(new Icon(VaadinIcon.HOME), e-> UI.getCurrent().navigate("wybory/homepage"));
        MenuItem lohinOKW = menuBar.addItem("OKW", e->UI.getCurrent().navigate("wybory/logowanie/OKW"));
        menuBar.addThemeVariants(MenuBarVariant.LUMO_CONTRAST);
        add(menuBar);
    }
}
