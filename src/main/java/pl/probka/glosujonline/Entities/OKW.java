package pl.probka.glosujonline.Entities;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.List;

@Service
@Entity
public class OKW {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;
    private String username;
    private String password;
    private String passwordHash;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "okw")
    private List<ElectionResults> wyniki;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "okwFK")
    private List<Citizen> obywatele;

    public OKW(String username, String password) {
        this.username = username;
        this.passwordHash = RandomStringUtils.random(32);
        this.password = DigestUtils.sha1Hex(password + passwordHash );
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public boolean checkpassword(String givenpassword){
        return DigestUtils.sha1Hex(givenpassword + passwordHash).equals(password);
    }

    public OKW() {
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public List<ElectionResults> getWyniki() {
        return wyniki;
    }
    public List<Citizen> getObywatele() { return obywatele; }
    public void setObywatele(List<Citizen> obywatele) { this.obywatele = obywatele; }

    public void setWyniki(List<ElectionResults> wyniki) {
        this.wyniki = wyniki;
    }
}
