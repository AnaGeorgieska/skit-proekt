package mk.ukim.finki.bazi_proekt.avio_kompanija.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

//create table destinacija(
//        id_destinacija integer,
//        ime varchar(100) not null unique,
//        constraint pk_destinacija primary key (id_destinacija)
//        );

@Data
@Entity
public class Destinacija {
    @Id
    private Integer id_destinacija;

    private String ime;

    public Destinacija() {
    }

    public Destinacija(Integer id_destinacija, String ime) {
        this.id_destinacija = id_destinacija;
        this.ime = ime;
    }

    public Integer getId_destinacija() {
        return id_destinacija;
    }

    public String getIme() {
        return ime;
    }
}
