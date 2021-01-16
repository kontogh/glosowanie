package pl.probka.glosujonline.Menagers;

import org.springframework.stereotype.Service;
import pl.probka.glosujonline.Entities.Citizen;
import pl.probka.glosujonline.RepoInterfaces.CitizenRepository;

import java.util.Optional;

@Service
public class CitizenMenager {

    private CitizenRepository citizenRepository;
   // private BCryptPasswordEncoder bCryptPasswordEncoder;


    public CitizenMenager(CitizenRepository citizenRepository/*,
                          BCryptPasswordEncoder bCryptPasswordEncoder*/){
        this.citizenRepository=citizenRepository;
       // this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public Optional<Citizen> find(String nrdowodu){
        return citizenRepository.findById(nrdowodu);
    }

    //public Citizen findbyImie(String s){ return citizenRepository.findbyImie(s);}

    public Iterable<Citizen> findAll(){
        return citizenRepository.findAll();
    }

    public Citizen save(Citizen citizen){
        return citizenRepository.save(citizen);
    }
    public void deletebyid(String nrdowodu){
        citizenRepository.deleteById(nrdowodu);
    }

   /* @EventListener(ApplicationReadyEvent.class)
    public void fill(){
//            save(new Citizen("Jan", "Kowalski", "89121598765", "KLI753648"));
//        save(new Citizen("Anna", "Nowak", "67040965432", "THM471315"));
//        save(new Citizen("Krzysztof", "Zamoyski", "78103045873", "POI741130"));
//        save(new Citizen("Halina", "Słowacka", "57042206578", "UST531478"));
//        save(new Citizen("Adam", "Kania", "87062564782", "NRK603481"));
    }*/

}
