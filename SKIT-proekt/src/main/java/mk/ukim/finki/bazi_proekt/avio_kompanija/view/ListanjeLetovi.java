package mk.ukim.finki.bazi_proekt.avio_kompanija.view;

import lombok.Data;
import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.ZonedDateTime;
@Data
@Entity
@Table(name = "listanje_letovi")
@Immutable
public class ListanjeLetovi {
    @Id
    @Column(name = "id_let")
    public Integer idLet;
    private Integer id_linija;
    private ZonedDateTime datum_vreme;
    private Float cena;
    private String destinacija_od;
    private String destinacija_do;
    private String tip_avion;
    public Integer getIdLet() {
        return idLet;
    }

    public Integer getId_linija() {
        return id_linija;
    }

    public ZonedDateTime getDatum_vreme() {
        return datum_vreme;
    }

    public Float getCena() {
        return cena;
    }

    public String getDestinacija_od() {
        return destinacija_od;
    }

    public String getDestinacija_do() {
        return destinacija_do;
    }

    public String getTip_avion() {
        return tip_avion;
    }
}
