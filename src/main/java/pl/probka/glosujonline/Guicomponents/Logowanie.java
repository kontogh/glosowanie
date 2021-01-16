package pl.probka.glosujonline.Guicomponents;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.web.bind.annotation.RestController;
import pl.probka.glosujonline.Services.AuthService;

@UIScope
@RestController
@Route("wybory/logowanie")
public class Logowanie extends VerticalLayout {


    public Logowanie(AuthService authService){

        Header tytul = new Header();
        tytul.setText("Podaj swoje dane w formularzu aby potwierdzić tożsamość");
        tytul.setWidthFull();

        TextField tfimie = new TextField("Podaj imię");
        TextField tfnazwisko = new TextField("Podaj nazwisko");
        PeselField tfpesel = new PeselField();
        TextField tfnrdowodu = new TextField("Podaj numer dowodu osobistego");
        tfnrdowodu.setPlaceholder("Przykładowy nr: ABC123456");
        tfnrdowodu.setWidthFull();
        tfnrdowodu.setMaxLength(9);
        Button buttonpobierz = new Button("Sprawdź");
        buttonpobierz.setIcon(new Icon(VaadinIcon.INFO_CIRCLE));
        FormLayout formLayout = new FormLayout();
        formLayout.add(tfimie,tfnazwisko,tfpesel,tfnrdowodu, buttonpobierz);
        formLayout.setSizeFull();

        buttonpobierz.addClickListener(buttonClickEvent -> {
                if(authService.authenticate(tfimie.getValue(), tfnazwisko.getValue(),
                        tfpesel.getValue(), tfnrdowodu.getValue().replaceAll("\\s+", "")))
                {
                    RouteConfiguration.forSessionScope().setRoute("wybory/kandydaci", CandidatesView.class);
                    UI.getCurrent().navigate("wybory/kandydaci");
                }
                else {
                    Notification again = new Notification("Podano niepoprawne  dane");
                    again.addThemeVariants(NotificationVariant.LUMO_ERROR);
                    again.setDuration(4000);
                    again.setPosition(Notification.Position.MIDDLE);
                    again.open();
                }

        });
        add(new TabsMenu(),new H2("Wybory 2020"), tytul,formLayout);
    }
}
