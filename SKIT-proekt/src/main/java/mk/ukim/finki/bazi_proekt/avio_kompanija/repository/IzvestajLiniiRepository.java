package mk.ukim.finki.bazi_proekt.avio_kompanija.repository;

import mk.ukim.finki.bazi_proekt.avio_kompanija.view.IzvestajNajcestoRezerviraniLinii;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IzvestajLiniiRepository extends JpaRepository<IzvestajNajcestoRezerviraniLinii, Integer> {
}
