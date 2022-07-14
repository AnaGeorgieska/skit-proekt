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
@Table(name = "listanje_linii")
@Immutable
public class ListanjeLinii {
    @Id
    @Column(name = "id_linija")
    public Integer idLinija;
    private Integer id_destinacija_od;
    private String destinacija_od;
    private Integer id_destinacija_do;
    private String destinacija_do;
    private Integer broj_letovi;

    public Integer getIdLinija() {
        return idLinija;
    }

    public Integer getId_destinacija_od() {
        return id_destinacija_od;
    }

    public String getDestinacija_od() {
        return destinacija_od;
    }

    public Integer getId_destinacija_do() {
        return id_destinacija_do;
    }

    public String getDestinacija_do() {
        return destinacija_do;
    }

    public Integer getBroj_letovi() {
        return broj_letovi;
    }
}
