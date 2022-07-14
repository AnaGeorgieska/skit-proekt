package mk.ukim.finki.bazi_proekt.avio_kompanija.repository;

import mk.ukim.finki.bazi_proekt.avio_kompanija.model.Avion;
import mk.ukim.finki.bazi_proekt.avio_kompanija.model.Patnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AvionRepository extends JpaRepository<Avion, Integer> {

}