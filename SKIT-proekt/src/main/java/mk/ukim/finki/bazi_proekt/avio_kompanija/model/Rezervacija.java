package mk.ukim.finki.bazi_proekt.avio_kompanija.model;

import lombok.Data;

import javax.persistence.*;

//create table rezervacija(
//  id_rezervacija serial,
//  uplata float not null ,
//  tip_bagazh varchar(100),
//  id_sedishte integer,
//  id_let integer not null ,
//  id_patnik integer not null ,
//  constraint pk_rezervacija primary key (id_rezervacija),
//  constraint fk_rezervacija_sedishte foreign key (id_let, id_sedishte) references sedishte(id_let, id_sedishte),
//  constraint fk_rezervacija_let foreign key (id_let) references let(id_let),
//  constraint fk_rezervacija_patnik foreign key (id_patnik) references patnik(id_patnik)
//);
@Data
@Entity
public class Rezervacija {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_rezervacija;

    private Float uplata;

    private String tip_bagazh;

    @JoinColumn(name = "id_sedishte")
    @ManyToOne
    private Sedishte sedishte;

    @JoinColumn(name = "id_let")
    @ManyToOne
    private Let let;

    @JoinColumn(name = "id_patnik")
    @ManyToOne
    private Patnik patnik;

    public Rezervacija() {
    }

    public Rezervacija(Float uplata, String tip_bagazh, Sedishte sedishte, Let let, Patnik patnik) {
        this.uplata = uplata;
        this.tip_bagazh = tip_bagazh;
        this.sedishte = sedishte;
        this.let = let;
        this.patnik = patnik;
    }
}
