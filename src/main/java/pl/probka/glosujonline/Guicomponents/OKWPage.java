package pl.probka.glosujonline.Guicomponents;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.spring.annotation.UIScope;
import pl.probka.glosujonline.Entities.OKW;
import pl.probka.glosujonline.Entities.Voters;
import pl.probka.glosujonline.Menagers.VotersMenager;
import pl.probka.glosujonline.Services.SaveBallot;
import pl.probka.glosujonline.Services.TimefromInternet;

@UIScope
@Route("wybory/OKW-zalogowany")
public class OKWPage extends VerticalLayout {

    private final VotersMenager votersMenager;
    private final TimefromInternet hour;
    private Voters voters;

    public OKWPage(VotersMenager votersMenager/*, OKW okw*/,Voters voters,SaveBallot saveBallot,TimefromInternet hour) {
        this.votersMenager = votersMenager;
        this.voters = voters;
        this.hour = hour;
        class Togrid{String id;Boolean yn;Togrid(String id,Boolean yn){this.id=id;this.yn=yn;}String gi(){return id;}boolean gy(){return yn;}}

        OKW specoficOkw = VaadinSession.getCurrent().getAttribute(OKW.class);
        Grid<Togrid> daneobywatela = new Grid<>(Togrid.class);
        daneobywatela.removeAllColumns();
        daneobywatela.addColumn(Togrid::gi).setHeader("Nr dowodu osobistego");
        daneobywatela.addColumn(tg ->tg.gy()? "TAK": "NIE").setHeader("Czy głosował" );
        daneobywatela.setHeight("100px");
        HorizontalLayout hl0 = new HorizontalLayout(new SessionTimeout());
        hl0.setWidthFull(); hl0.setJustifyContentMode(JustifyContentMode.END);
        HorizontalLayout hl = new HorizontalLayout(new H1("Okręgowa Komisja Wyborcza"));
        hl.setWidthFull();hl.setHeight("80px");hl.setJustifyContentMode(JustifyContentMode.CENTER);

        HorizontalLayout hl2 = new HorizontalLayout(new H2("Nr "+specoficOkw.getUsername().replace("_", " ")));
        hl2.setWidthFull();hl2.setHeight("70px");hl2.setJustifyContentMode(JustifyContentMode.CENTER);

        TextField nrdowoduTF = new TextField("Podaj numer dowodu osobisego wyborcy");
        nrdowoduTF.setWidth("300px");
        Button wyczyscButton = new Button("Wyczyść dane");
        Button pobierzIDbutton = new Button("Sprawdź");
        Label nieglosowal = new Label();
        nieglosowal.setWidth("380px");
        nieglosowal.setHeight("36px");
        Button czyglosowalB = new Button("Zmień wartosc");
        czyglosowalB.setEnabled(false);
        HorizontalLayout hl4 = new HorizontalLayout(); hl4.add(nieglosowal, czyglosowalB);
        pobierzIDbutton.setWidth("300px");
        wyczyscButton.setWidth("300px");
        HorizontalLayout hl3 = new HorizontalLayout();
        hl3.setWidthFull(); hl3.add(pobierzIDbutton,wyczyscButton);
        pobierzIDbutton.addClickListener(buttonClickEvent -> {
            if(votersMenager.find(nrdowoduTF.getValue()).isPresent()){
                daneobywatela.setItems(new Togrid(nrdowoduTF.getValue(), true));
                nieglosowal.setText("Obywatel już glosował. Nie wydawaj karty");
                czyglosowalB.setEnabled(false);
            }
            else   {
                daneobywatela.setItems(new Togrid(nrdowoduTF.getValue(), false));
                nieglosowal.setText("Zmien wartosc na zagłosował a następnie wydaj kartę");
                czyglosowalB.setEnabled(true);
            }
        } );
        wyczyscButton.addClickListener(Event ->{daneobywatela.setItems(); nrdowoduTF.clear();
        nieglosowal.setText("");  czyglosowalB.setEnabled(false);});

        czyglosowalB.addClickListener(Event -> saveBallot.saveIsvoted(nrdowoduTF.getValue()));
        Button wynikiButton = new Button("Sprawdź wyniki głosowania online", new Icon(VaadinIcon.ARROW_RIGHT));
        wynikiButton.setIconAfterText(true);
        wynikiButton.addClickListener(Event -> {if(hour.getHour() < 0){
            Notification note = Notification.show("Głosowanie jeszcze nie jest zakończone.");
        }
        else{
            //RouteConfiguration.forSessionScope().setRoute("wybory/OKW-zalogowany/wyniki", OKWwynik.class);
            UI.getCurrent().navigate("wybory/OKW-zalogowany/wyniki");
        }
        });

        add(hl,hl2,nrdowoduTF, hl3, daneobywatela,hl4,wynikiButton, hl0);
    }
}
