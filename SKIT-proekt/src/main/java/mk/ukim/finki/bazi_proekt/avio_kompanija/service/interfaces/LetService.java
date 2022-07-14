package mk.ukim.finki.bazi_proekt.avio_kompanija.service.interfaces;

import mk.ukim.finki.bazi_proekt.avio_kompanija.model.Let;
import mk.ukim.finki.bazi_proekt.avio_kompanija.model.Linija;

import java.util.List;
import java.util.Optional;

public interface LetService {
    List<Let> findByLinija(Linija linija);
    Optional<Let> findById(Integer id);
    Let save(Let let);
}
