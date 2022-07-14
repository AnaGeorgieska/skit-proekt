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
@Table(name = "rezervacii")
@Immutable
public class Rezervacii {
    @Id
    @Column(name = "id_rezervacija")
    public Integer idRezervacija;
    private Float uplata;
    private String tip_bagazh;
    private Integer id_sedishte;
    private String broj_pasosh;
    private ZonedDateTime datum_vreme;
    private Float cena;
    private String destinacija_od;
    private String destinacija_do;
    private String tip_avion;

    public Integer getIdRezervacija() {
        return idRezervacija;
    }

    public void setIdRezervacija(Integer idRezervacija) {
        this.idRezervacija = idRezervacija;
    }

    public Float getUplata() {
        return uplata;
    }

    public void setUplata(Float uplata) {
        this.uplata = uplata;
    }

    public String getTip_bagazh() {
        return tip_bagazh;
    }

    public void setTip_bagazh(String tip_bagazh) {
        this.tip_bagazh = tip_bagazh;
    }

    public Integer getId_sedishte() {
        return id_sedishte;
    }

    public void setId_sedishte(Integer id_sedishte) {
        this.id_sedishte = id_sedishte;
    }

    public String getBroj_pasosh() {
        return broj_pasosh;
    }

    public void setBroj_pasosh(String broj_pasosh) {
        this.broj_pasosh = broj_pasosh;
    }

    public ZonedDateTime getDatum_vreme() {
        return datum_vreme;
    }

    public void setDatum_vreme(ZonedDateTime datum_vreme) {
        this.datum_vreme = datum_vreme;
    }

    public Float getCena() {
        return cena;
    }

    public void setCena(Float cena) {
        this.cena = cena;
    }

    public String getDestinacija_od() {
        return destinacija_od;
    }

    public void setDestinacija_od(String destinacija_od) {
        this.destinacija_od = destinacija_od;
    }

    public String getDestinacija_do() {
        return destinacija_do;
    }

    public void setDestinacija_do(String destinacija_do) {
        this.destinacija_do = destinacija_do;
    }

    public String getTip_avion() {
        return tip_avion;
    }

    public void setTip_avion(String tip_avion) {
        this.tip_avion = tip_avion;
    }
}
