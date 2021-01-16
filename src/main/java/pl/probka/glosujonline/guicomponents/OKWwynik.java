package pl.probka.glosujonline.guicomponents;


import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vaadin.flow.spring.annotation.VaadinSessionScope;
import pl.probka.glosujonline.entities.ElectionResults;
import pl.probka.glosujonline.entities.OKW;
import pl.probka.glosujonline.repoInterfaces.ResultsRepo;
import pl.probka.glosujonline.repoInterfaces.VotersRepo;
import pl.probka.glosujonline.services.CountVotes;
import java.util.List;

@UIScope
@VaadinSessionScope
@Route("wybory/OKW-zalogowany/wyniki")
public class OKWwynik extends VerticalLayout {

    private final VotersRepo votersRepo;
    private final ResultsRepo eresults;
    private final CountVotes countVotes;
    List<ElectionResults> elres ;

    public OKWwynik(VotersRepo votersRepo, ResultsRepo er, CountVotes countVotes) {
        this.eresults=er;
        this.votersRepo=votersRepo;
        this.countVotes=countVotes;

        OKW sameOkw = VaadinSession.getCurrent().getAttribute(OKW.class);
        HorizontalLayout hl = new HorizontalLayout(new H1("Okręgowa Komisja Wyborcza")); hl.setWidthFull();
        hl.setJustifyContentMode(JustifyContentMode.CENTER);
        HorizontalLayout hl2 = new HorizontalLayout(new H2(" Nr "+sameOkw.getUsername().replace("_", " ")));
        hl2.setWidthFull();
        hl2.setJustifyContentMode(JustifyContentMode.CENTER);
        HorizontalLayout hl3 = new HorizontalLayout(new H3("WYNIKI WYBORÓW:")); hl3.setWidthFull();
        hl3.setJustifyContentMode(JustifyContentMode.CENTER);
        try {
            countVotes.makeElRes(sameOkw.getId(), "ŚPIEWAK Robert Mateusz");

                countVotes.makeElRes(sameOkw.getId(),"DUDKOWSKI Antoni Janusz");
            }
        catch (Exception exep){
        }

        elres = null;
        elres= countVotes.getWyniki(sameOkw.getId());
        Grid <ElectionResults> wyniki = new Grid<>(ElectionResults.class);
        wyniki.removeAllColumns();
        wyniki.addColumn(ElectionResults::getKandydat).setHeader("Kandydat");
        wyniki.addColumn(ElectionResults::getglosy).setHeader("Uzyskane głosy");
        wyniki.addThemeVariants(GridVariant.LUMO_NO_BORDER,
                GridVariant.LUMO_NO_ROW_BORDERS, GridVariant.LUMO_ROW_STRIPES);
        wyniki.setHeight("140px");

        if(elres!=null)
        {
            wyniki.setItems(elres);
        }
        Button wrocButton = new Button("Wróć", new Icon(VaadinIcon.ARROW_LEFT));
        wrocButton.addClickListener(event-> {UI.getCurrent().navigate("wybory/OKW-zalogowany");
            /* RouteConfiguration.forSessionScope().setRoute("wybory/OKW-zalogowany/wyniki", OKWPage.class);*/});

            add(hl,hl2,hl3, wyniki,wrocButton,new SessionTimeout());

    }
}

