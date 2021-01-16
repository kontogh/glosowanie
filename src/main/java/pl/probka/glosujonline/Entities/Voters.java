package pl.probka.glosujonline.Entities;

import org.springframework.stereotype.Service;

import javax.persistence.*;

@Service
@Entity
@Table(name = "Voters")
public class Voters {

    @Id
    @Column(name = "id")
    String id;
    String candidate;
    @OneToOne
    @JoinColumn(name = "nrDowodu")
    private Citizen citizen;

    public String getnrdowodu() {
        return id;
    }

    public void senNrdowodu(String nrDowodu) {
        this.id = nrDowodu;
    }

    public Voters() {
    }

    public Voters(String nrDowodu) {
        this.id = nrDowodu;
    }

    public void setCandidate(String candidate) {
        this.candidate = candidate;
    }

    public Citizen getCitizen() {
        return citizen;
    }

    public void setCitizen(Citizen citizen) {
        this.citizen = citizen;
    }


}
