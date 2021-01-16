package pl.probka.glosujonline.Guicomponents;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;

import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vaadin.flow.spring.annotation.VaadinSessionScope;
import org.springframework.stereotype.Component;
import pl.probka.glosujonline.Services.AuthService;
@UIScope
@Route("wybory/logowanie/OKW")
public class OkwLogin extends Div {

    public OkwLogin(AuthService authService) {
        LoginOverlay lo = new LoginOverlay();
        LoginI18n i18n = LoginI18n.createDefault();
        i18n.setHeader(new LoginI18n.Header());
        i18n.getHeader().setTitle("Wybory 2020");
        i18n.getHeader().setDescription("Panel logowania Obwodowych Komisji Wyborczych");
        i18n.getForm().setTitle(" Logowanie");
        i18n.getForm().setSubmit("Obwodowa Komisja Wyborcza");
        i18n.getForm().setSubmit("Zaloguj");
        i18n.getForm().setUsername("Nazwa komisji");
        i18n.getForm().setPassword("Hasło");
        i18n.getForm().setForgotPassword("Wróć");
        i18n.getErrorMessage().setTitle("Błąd");
        i18n.getErrorMessage().setMessage("Podano niepoprawne dane");
        i18n.setAdditionalInformation("Jeżeli nie jesteś członkiem komisji wyborczej kliknij przycisk Wróc");
        lo.setI18n(i18n);
        lo.setOpened(true);
        lo.addForgotPasswordListener(forgotPasswordEvent -> UI.getCurrent().getPage().setLocation("wybory/logowanie"));
        lo.addLoginListener(loginEvent -> {try {
            authService.authOkw(loginEvent.getUsername(), loginEvent.getPassword());
            RouteConfiguration.forSessionScope().setRoute("wybory/OKW-zalogowany", OKWPage.class);
            UI.getCurrent().navigate("wybory/OKW-zalogowany");
            UI.getCurrent().getPage().reload();
        }
        catch (AuthService.AuthExepction exepction){
            lo.setError(true);
        }
        });
        add(lo);
    }
}
