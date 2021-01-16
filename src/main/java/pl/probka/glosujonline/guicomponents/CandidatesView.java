package pl.probka.glosujonline.guicomponents;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.spring.annotation.UIScope;
import pl.probka.glosujonline.entities.Citizen;
import pl.probka.glosujonline.menagers.VotersMenager;
import pl.probka.glosujonline.services.SaveBallot;

@UIScope
@Route("wybory/kandydaci")
public class CandidatesView extends VerticalLayout {


    private final Citizen citi;
    private final VotersMenager voters;

    public CandidatesView(Citizen citi, SaveBallot saveBallot, VotersMenager voters){

        this.voters = voters;
        this.citi=citi;

        VaadinSession.getCurrent().getSession().setMaxInactiveInterval(600);

        Label label1 = new Label("Zweryfikowano pozytywnie. Obywatel: ");
        Grid<Citizen> grid = new Grid<>(Citizen.class);
        grid.removeAllColumns();
        grid.setColumns("imie", "nazwisko", "pesel", "nrDowodu");
        grid.setHeight("120px");
        citi = VaadinSession.getCurrent().getAttribute(Citizen.class);
        grid.setItems(citi);
        String idid = citi.getNrDowodu();
        RadioButtonGroup<String> radioButon = new RadioButtonGroup<>();
        radioButon.setLabel("Kandydaci na Prezydenta");
        radioButon.setItems("DUDKOWSKI Antoni Janusz" , "ŚPIEWAK Robert Mateusz");
        radioButon.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
        radioButon.setRequired(true);
        Button butonInCorfimYes = new Button("Tak");
        Dialog confirm = new Dialog();
        confirm.add(
                new Text("Czy jesteś pewien?"   ),
                new Button(" Nie ", e-> confirm.close()),
                butonInCorfimYes
        );


        Button candidateButton = new Button("Zatwierdź wybór");
        candidateButton.setIcon(new Icon(VaadinIcon.CHECK_SQUARE));
        candidateButton.setIconAfterText(true);
        candidateButton.setHeight("50px");
        candidateButton.addClickListener(buttonClickEvent -> {
            if(radioButon.isEmpty()){
                Notification note = new Notification("Nie wybrano żadnego kandydata", 4000,
                    Notification.Position.MIDDLE);note.addThemeVariants(NotificationVariant.LUMO_ERROR); note.open();}
            else{
                butonInCorfimYes.addClickListener(yesvent -> {
                    Notification notification = new Notification("Zapisano wybór zaznaczonego kandydata: "
                            +radioButon.getValue() , 4000, Notification.Position.MIDDLE );
                    notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                    saveBallot.savevote(idid,radioButon.getValue());
                    radioButon.setEnabled(false);
                    notification.open();
                    confirm.close();
                    candidateButton.setDisableOnClick(true);});
                confirm.open();
            }
        });
        //Sprawdzenie czy osoba juz glosowala
        if(voters.find(citi.getNrDowodu()).isPresent()){
            add(new H1("Wybory 2020"), new H3("Już głosowowano. Wyloguj się ze strony"), new SessionTimeout());
        }
        else {
            add(label1, grid, radioButon , candidateButton, new SessionTimeout());
        }
    }

}

