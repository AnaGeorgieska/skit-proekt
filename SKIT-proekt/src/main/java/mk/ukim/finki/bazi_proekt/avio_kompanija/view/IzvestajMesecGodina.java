package mk.ukim.finki.bazi_proekt.avio_kompanija.view;

import lombok.Data;
import org.hibernate.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Data
@Entity
@Table(name = "izvestaj_mesec_godina")
@Immutable
public class IzvestajMesecGodina {
    @Id
    private String mesec_godina;
    private Float suma_uplata;
    private Integer broj_rezervacii;

    public String getMesec_godina() {
        return mesec_godina;
    }

    public Float getSuma_uplata() {
        return suma_uplata;
    }

    public Integer getBroj_rezervacii() {
        return broj_rezervacii;
    }
}
