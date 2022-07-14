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
@Table(name = "izvestaj_patnik")
@Immutable
public class IzvestajPatnik {
    @Id
    private Integer id_patnik;
    private String broj_pasosh;
    private String drzhava;
    private Integer broj_rezervacii;
    private Float suma_uplata;

    public Integer getId_patnik() {
        return id_patnik;
    }

    public String getBroj_pasosh() {
        return broj_pasosh;
    }

    public String getDrzhava() {
        return drzhava;
    }

    public Integer getBroj_rezervacii() {
        return broj_rezervacii;
    }

    public Float getSuma_uplata() {
        return suma_uplata;
    }
}
