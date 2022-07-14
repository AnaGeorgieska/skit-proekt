package mk.ukim.finki.bazi_proekt.avio_kompanija.repository;

import mk.ukim.finki.bazi_proekt.avio_kompanija.model.Rezervacija;
import mk.ukim.finki.bazi_proekt.avio_kompanija.view.Rezervacii;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RezervaciiRepository extends JpaRepository<Rezervacii, Integer> {

}
