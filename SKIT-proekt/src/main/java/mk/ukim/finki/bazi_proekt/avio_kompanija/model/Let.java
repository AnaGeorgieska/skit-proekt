package mk.ukim.finki.bazi_proekt.avio_kompanija.model;

//create table let(
//  id_let serial,
//  datum_vreme timestamp not null ,
//  id_linija integer not null ,
//  id_avion integer not null ,
//  cena float not null ,
//  constraint pk_let primary key (id_let),
//  constraint fk_linija foreign key (id_linija) references linija(id_linija),
//  constraint fk_avion foreign key (id_avion) references avion(id_avion)
//);

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Data
@Entity
public class Let {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_let;

    @DateTimeFormat(pattern = "dd.mm.yyyy HH:mm")
    private ZonedDateTime datum_vreme;

    @ManyToOne
    @JoinColumn(name = "id_linija")
    private Linija linija;

    @ManyToOne
    @JoinColumn(name = "id_avion")
    private Avion avion;

    private Float cena;

    public Let() {
    }

    public Let(ZonedDateTime datum_vreme, Linija linija, Avion avion, Float cena) {
        this.datum_vreme = datum_vreme;
        this.linija = linija;
        this.avion = avion;
        this.cena = cena;
    }

    public Integer getId_let() {
        return id_let;
    }

    public void setId_let(Integer id_let) {
        this.id_let = id_let;
    }

    public ZonedDateTime getDatum_vreme() {
        return datum_vreme;
    }

    public void setDatum_vreme(ZonedDateTime datum_vreme) {
        this.datum_vreme = datum_vreme;
    }

    public Linija getLinija() {
        return linija;
    }

    public void setLinija(Linija linija) {
        this.linija = linija;
    }

    public Avion getAvion() {
        return avion;
    }

    public void setAvion(Avion avion) {
        this.avion = avion;
    }

    public Float getCena() {
        return cena;
    }

    public void setCena(Float cena) {
        this.cena = cena;
    }
}
