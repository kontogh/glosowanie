package pl.probka.glosujonline.Services;

import com.vaadin.flow.server.VaadinSession;
import org.springframework.stereotype.Service;
import pl.probka.glosujonline.Entities.Citizen;
import pl.probka.glosujonline.Entities.OKW;
import pl.probka.glosujonline.RepoInterfaces.CitizenRepository;
import pl.probka.glosujonline.RepoInterfaces.OkwRepo;

import java.util.Optional;

@Service
public class AuthService {

    private final CitizenRepository citizenRepo;
    private final OkwRepo okwRepo;

    public AuthService(CitizenRepository citizenRepo, OkwRepo okwRepo) {
        this.citizenRepo = citizenRepo;
        this.okwRepo = okwRepo;
    }
    public static class AuthExepction extends Exception{
        public AuthExepction() {
            super();
        }
    }

    public boolean authenticate(String imie, String nazwisko, String pesel,String nrDowodu) {
        boolean isRight = false;
        Optional<Citizen> citizen = citizenRepo.findById(nrDowodu);
        if(citizen.isPresent()){
            Citizen ct =  new Citizen(imie,nazwisko,pesel,nrDowodu) ;
            if(ct.compare(citizen.get())){
                VaadinSession.getCurrent().setAttribute(Citizen.class, ct);
               isRight = true;
            }
            else {
              isRight = false;
            }
        }

    return isRight; }

    public void authOkw(String username, String password) throws AuthExepction{
        OKW okw = okwRepo.getByUsername(username);
        if(okw != null && okw.checkpassword(password)){
            VaadinSession.getCurrent().setAttribute(OKW.class, okw);
        }
        else{
                throw new AuthExepction();
        }

    }
}
