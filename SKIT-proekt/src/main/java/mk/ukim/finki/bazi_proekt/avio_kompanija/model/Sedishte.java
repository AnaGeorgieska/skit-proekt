package mk.ukim.finki.bazi_proekt.avio_kompanija.model;


//create table sedishte(
//  id_let integer,
//  id_sedishte integer,
//  constraint pk_sedishte primary key (id_let, id_sedishte),
//  constraint fk_let foreign key (id_let) references let(id_let)
//);

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Sedishte {
    @Id
    @Column(name = "id_sedishte")
    private Integer idSedishte;

    @JoinColumn(name="id_let")
    @ManyToOne
    private Let let;

    public Sedishte() {
    }

    public Integer getId_sedishte() {
        return idSedishte;
    }

    public Let getLet() {
        return let;
    }



    public void setIdSedishte(Integer idSedishte) {
        this.idSedishte = idSedishte;
    }

    public void setLet(Let let) {
        this.let = let;
    }


}
