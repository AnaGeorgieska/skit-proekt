package mk.ukim.finki.bazi_proekt.avio_kompanija.model;

import lombok.Data;

import javax.persistence.*;

//create table patnik
//(
//  id_patnik serial,
//  drzhava varchar(100),
//  broj_pasosh varchar(30) not null ,
//  constraint pk_patnik primary key (id_patnik)
//);
@Data
@Entity
public class Patnik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_patnik;

    private String drzhava;

    @Column(name = "broj_pasosh")
    private String brojPasosh;

    public Patnik() {
    }

    public Patnik(String drzhava, String brojPasosh) {
        this.drzhava = drzhava;
        this.brojPasosh = brojPasosh;
    }

    public Integer getId_patnik() {
        return id_patnik;
    }

    public void setId_patnik(Integer id_patnik) {
        this.id_patnik = id_patnik;
    }

    public String getDrzhava() {
        return drzhava;
    }

    public void setDrzhava(String drzhava) {
        this.drzhava = drzhava;
    }

    public String getBroj_pasosh() {
        return brojPasosh;
    }

    public void setBroj_pasosh(String broj_pasosh) {
        this.brojPasosh = broj_pasosh;
    }

}
