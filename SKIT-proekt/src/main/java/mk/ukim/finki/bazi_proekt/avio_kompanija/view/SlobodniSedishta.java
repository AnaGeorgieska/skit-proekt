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
@Table(name = "slobodni_sedishta")
@Immutable
public class SlobodniSedishta {
    @Id
    @Column(name = "id_sedishte")
    public Integer idSedishte;

    @Column(name = "id_let")
    public Integer idLet;

    public Integer getIdSedishte() {
        return idSedishte;
    }

    public Integer getIdLet() {
        return idLet;
    }
}
