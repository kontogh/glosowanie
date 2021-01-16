package pl.probka.glosujonline.Entities;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.*;


@RestController
@Service
@Entity
public class Citizen  {

    public Citizen() {
    }

    @Id
    @Column(name = "id")
    String nrDowodu;
    String imie;
    String nazwisko;
    String pesel;
    @OneToOne(mappedBy = "citizen")
    private Voters isvoted;
    @ManyToOne(fetch = FetchType.LAZY)
    private OKW okwFK;


    public Citizen(String imie, String nazwisko, String pesel, String nrDowodu) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pesel = pesel;
        this.nrDowodu = nrDowodu;

    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getPesel() {
        return pesel;
    }
    /*public Voters getIsvoted() {
        return isvoted;
    }

    public void setIsvoted(Voters isvoted) {
        this.isvoted = isvoted;
    }*/
    public OKW getOkwFK() {
        return okwFK;
    }

    public void setOkwFK(OKW okwFK) {
        this.okwFK = okwFK;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    @RequestMapping(value = "getIdNumber")
    @ResponseBody
    public String getNrDowodu() {
        return nrDowodu;
    }

    public void setNrDowodu(String nrDowodu) {
        this.nrDowodu = nrDowodu;
    }

    public boolean compare(Citizen citizen){
        if(imie.equalsIgnoreCase(citizen.getImie())  && nazwisko.equalsIgnoreCase(citizen.getNazwisko())
        && pesel.equalsIgnoreCase(citizen.getPesel()) && nrDowodu.equalsIgnoreCase(citizen.getNrDowodu())
        ) return true;
        else return false;
    }
//nie  bylo tych 2 ostatnich wczesniej
    public Voters getIsvoted() {
        return isvoted;
    }

    public void setIsvoted(Voters isvoted) {
        this.isvoted = isvoted;
    }
}
