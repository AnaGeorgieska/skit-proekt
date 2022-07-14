package mk.ukim.finki.bazi_proekt.avio_kompanija.repository;

import mk.ukim.finki.bazi_proekt.avio_kompanija.model.Let;
import mk.ukim.finki.bazi_proekt.avio_kompanija.model.Sedishte;
import mk.ukim.finki.bazi_proekt.avio_kompanija.view.SlobodniSedishta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SedishteRepository extends JpaRepository<SlobodniSedishta,Integer> {

    public List<SlobodniSedishta> findAllByIdLet(Integer id_let);

}
