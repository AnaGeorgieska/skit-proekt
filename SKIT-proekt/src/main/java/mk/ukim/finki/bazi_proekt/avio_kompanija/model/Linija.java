package mk.ukim.finki.bazi_proekt.avio_kompanija.model;


//create table linija(
//        id_linija integer,
//        id_destinacija_od integer not null ,
//        id_destinacija_do integer not null ,
//        constraint pk_linija primary key (id_linija),
//        constraint fk_destinacija_od foreign key (id_destinacija_od) references destinacija(id_destinacija),
//        constraint fk_destinacija_do foreign key (id_destinacija_do) references destinacija(id_destinacija)
//        );

import lombok.Data;

import javax.persistence.*;

import mk.ukim.finki.bazi_proekt.avio_kompanija.model.Destinacija;


@Data
@Entity
public class Linija {
    @Id
    private Integer id_linija;

    @ManyToOne
    @JoinColumn(name = "id_destinacija_od")
    private Destinacija destinacijaOd;



    @ManyToOne
    @JoinColumn(name = "id_destinacija_do")
    private Destinacija destinacijaDo;

    public Linija() {
    }

    public Linija(Destinacija destinacijaOd, Destinacija destinacijaDo) {
        this.destinacijaOd = destinacijaOd;
        this.destinacijaDo = destinacijaDo;
    }
}
