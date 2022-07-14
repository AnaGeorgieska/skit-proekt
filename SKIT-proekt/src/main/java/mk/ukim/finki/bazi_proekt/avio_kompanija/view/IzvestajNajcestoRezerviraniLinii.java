package mk.ukim.finki.bazi_proekt.avio_kompanija.view;

import lombok.Data;
import org.hibernate.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "izvestaj_najcesto_rezervirani_linii")
@Immutable
public class IzvestajNajcestoRezerviraniLinii {
    @Id
    private Integer id_linija;
    private String destinacija_od;
    private String destinacija_do;
    private Integer broj_rezervacii;
    private Float suma_uplata;
    public Integer getId_linija() {
        return id_linija;
    }

    public String getDestinacija_od() {
        return destinacija_od;
    }

    public String getDestinacija_do() {
        return destinacija_do;
    }

    public Integer getBroj_rezervacii() {
        return broj_rezervacii;
    }

    public Float getSuma_uplata() {
        return suma_uplata;
    }


}
