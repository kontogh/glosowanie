package pl.probka.glosujonline.menagers;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.probka.glosujonline.entities.Citizen;
import pl.probka.glosujonline.entities.OKW;
import pl.probka.glosujonline.repoInterfaces.OkwRepo;

@Service
public class OkwMenager {

    OkwRepo okwRepo;
    CitizenMenager citizenMenager;

    public OkwMenager(OkwRepo okwRepo, CitizenMenager citizenMenager) {
        this.okwRepo = okwRepo; this.citizenMenager= citizenMenager;
    }
    public OKW save(OKW okw){
        return okwRepo.save(okw);
    }
    public OKW findbyUsername(String username){
        return okwRepo.getByUsername(username);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fill(){
        save(new OKW("17_KRAKÓW", "krK17"));
        save(new OKW("2_BYDGOSZCZ", "Byd02"));
        save(new OKW("58_WARSZAWA", "waw58"));
        save(new OKW("8_KATOWICE", "katO08"));

        Citizen ct = new Citizen("Anna", "Nowak", "67040965432", "THM471315");
        Citizen ct2 = new Citizen("Krzysztof", "Zamoyski", "78103045873", "POI741130");
        Citizen ct3 = new Citizen("Halina", "Słowacka", "57042206578", "UST531478");
        Citizen ct4 =new Citizen("Adam", "Kania", "87062564782", "NRK603481");
        Citizen ct5 =new Citizen("Jan", "Kowalski", "89121598765", "KLI753648");
        Citizen ct6 =new Citizen("Maria", "Głuchowska","92080754739","DTO987426" );

        ct.setOkwFK(findbyUsername("17_KRAKÓW"));
        ct2.setOkwFK(findbyUsername("17_KRAKÓW"));
        ct3.setOkwFK(findbyUsername("17_KRAKÓW"));
        ct4.setOkwFK(findbyUsername("17_KRAKÓW"));
        ct5.setOkwFK(findbyUsername("58_WARSZAWA"));
        ct6.setOkwFK(findbyUsername("58_WARSZAWA"));

        citizenMenager.save(ct);
        citizenMenager.save(ct2);
        citizenMenager.save(ct3);
        citizenMenager.save(ct4);
        citizenMenager.save(ct5);
        citizenMenager.save(ct6);
    }

}
