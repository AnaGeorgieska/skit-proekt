package mk.ukim.finki.bazi_proekt.avio_kompanija.repository;


import mk.ukim.finki.bazi_proekt.avio_kompanija.model.Patnik;
import mk.ukim.finki.bazi_proekt.avio_kompanija.model.Rezervacija;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RezervacijaRepository extends JpaRepository<Rezervacija, Integer> {

}
