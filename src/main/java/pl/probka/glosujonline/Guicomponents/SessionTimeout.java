package pl.probka.glosujonline.Guicomponents;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.spring.scopes.VaadinSessionScope;
import org.hibernate.boot.archive.internal.UrlInputStreamAccess;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.ControllerAdvice;
import pl.probka.glosujonline.Entities.OKW;

@ControllerAdvice
@Route("wylogowaniesie")
public class SessionTimeout extends Div{

    public SessionTimeout() {
        Button button = new Button("Wyloguje się", new Icon( VaadinIcon.SIGN_OUT));
        button.setIconAfterText(true);
        button.setWidth("165x");
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        button.addClickListener( buttonClickEvent ->{
        UI.getCurrent().getPage().setLocation("wybory/homepage");
        VaadinSession.getCurrent().getSession().invalidate();
        UI.getCurrent().getSession().close();

        VaadinSession.getCurrent().getService().closeSession(VaadinSession.getCurrent());
        VaadinSession.getCurrent().getService().destroy();
        });//zamykanie sesji, trace obiekt zalogowanego
        add(button);

    }
}
