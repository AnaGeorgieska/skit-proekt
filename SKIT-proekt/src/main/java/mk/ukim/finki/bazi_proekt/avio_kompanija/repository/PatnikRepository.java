package mk.ukim.finki.bazi_proekt.avio_kompanija.repository;

import mk.ukim.finki.bazi_proekt.avio_kompanija.model.Patnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PatnikRepository extends JpaRepository<Patnik, Integer> {
    Optional<Patnik> findByBrojPasosh(String brojPasosh);

}
