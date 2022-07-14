package mk.ukim.finki.bazi_proekt.avio_kompanija.model;

//create table avion(
//        id_avion integer,
//        tip_avion varchar(20) not null ,
//        constraint pk_avion primary key (id_avion)
//        );

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Avion {
    @Id
    private Integer id_avion;

    private String tip_avion;

    public Avion() {
    }

    public Integer getId_avion() {
        return id_avion;
    }

    public void setId_avion(Integer id_avion) {
        this.id_avion = id_avion;
    }

    public String getTip_avion() {
        return tip_avion;
    }

    public void setTip_avion(String tip_avion) {
        this.tip_avion = tip_avion;
    }
}
