package mk.ukim.finki.bazi_proekt.avio_kompanija.service.interfaces;

import mk.ukim.finki.bazi_proekt.avio_kompanija.model.Patnik;

import java.util.List;
import java.util.Optional;

public interface PatnikService {
    public List<Patnik> findAll();
    Optional<Patnik> findByBrojPasosh(String brojPasosh);
    public Patnik save(Patnik patnik);
}
