package pl.probka.glosujonline.guicomponents;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;

@UIScope
@Route("wybory/homepage")
public class Homepage extends VerticalLayout {


    public Homepage() {

        HorizontalLayout hl = new HorizontalLayout();
        setSpacing(true);
        setPadding(true);
        this.getStyle().set("margin", "2rem");
        hl.setWidthFull();
        hl.setHeight("80px");
        hl.add(new H1("WYBORY 2020"));
        hl.setJustifyContentMode(JustifyContentMode.CENTER);

        Label label2 = new Label("");
        Label label = new Label("Za chwilę zostaniesz poproszony o weryfikację danych osobowych. W tym celu przygotuj swój dowód osobisty.");
        Label label3 = new Label("W odpowiednio podpisane pola wpisz swoje dane. Następnie kliknij przycisk Sprawdź.");
        Label label4 = new Label("Na następnej stronie wybierz swojego kandydata i nasciśnij przycisk Zatwierdź i wyloguj się");
        Label label5 = new Label("Pamiętaj że masz na tę czynność 10 minut. Po upływie tego czasu twoja sesja wygaśnie.");
        Button dalej = new Button("Przejdź do weryfikacji obywatela", ev -> UI.getCurrent().navigate("wybory/logowanie"));
        dalej.setHeight("60px");
        VerticalLayout horizonl = new VerticalLayout();
        horizonl.setWidthFull();
        horizonl.add(new H3("Instrukcja głosowania"),label2,label,label3,label4, label5, dalej);
        horizonl.setJustifyContentMode(JustifyContentMode.CENTER);

        Image jack = new Image("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQSVqWGanstaZxqguy5-Rfb-Eiysdf1nFWrXA&usqp=CAU", "Sasin" );
        jack.setHeight("227px");
        jack.setWidth("720px");
        HorizontalLayout vl = new HorizontalLayout();
        vl.setWidthFull();
        vl.add(jack);
        vl.setJustifyContentMode(JustifyContentMode.CENTER);
        add(new TabsMenu(),hl,horizonl, vl);

    }
}
