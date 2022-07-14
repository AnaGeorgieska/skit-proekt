package mk.ukim.finki.bazi_proekt.avio_kompanija.repository;


import mk.ukim.finki.bazi_proekt.avio_kompanija.model.Destinacija;
import mk.ukim.finki.bazi_proekt.avio_kompanija.model.Linija;
import mk.ukim.finki.bazi_proekt.avio_kompanija.model.Patnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LinijaRepository extends JpaRepository<Linija, Integer> {
    Optional<Linija> findByDestinacijaOdAndDestinacijaDo(Destinacija od_dest, Destinacija do_dest);

}