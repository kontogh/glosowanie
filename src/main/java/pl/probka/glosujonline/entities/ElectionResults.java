package pl.probka.glosujonline.entities;

import javax.persistence.*;

@Entity
public class ElectionResults {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String kandydat;
    private long glosy;
    @ManyToOne(fetch = FetchType.LAZY)
    private OKW okw;

    public ElectionResults(String kandydat, long glosy) {
        this.kandydat = kandydat;
        this.glosy = glosy;
    }

    public ElectionResults() {
    }

    public String getKandydat() {
        return kandydat;
    }

    public void setKandydat(String kandydat) {
        kandydat = kandydat;
    }

    public long getglosy() {
        return glosy;
    }

    public void setglosy(long glosy) {
        glosy = glosy;
    }
    public OKW getOkw() {
        return okw;
    }

    public void setOkw(OKW okw) {
        this.okw = okw;
    }
}   
