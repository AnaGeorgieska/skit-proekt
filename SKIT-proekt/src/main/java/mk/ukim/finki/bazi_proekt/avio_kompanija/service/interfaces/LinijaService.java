package mk.ukim.finki.bazi_proekt.avio_kompanija.service.interfaces;

import mk.ukim.finki.bazi_proekt.avio_kompanija.model.Destinacija;
import mk.ukim.finki.bazi_proekt.avio_kompanija.model.Linija;
import mk.ukim.finki.bazi_proekt.avio_kompanija.repository.LinijaRepository;

import java.util.Optional;

public interface LinijaService {

    public Optional<Linija> findByDestinacija_odAndDeestinacija_do(Destinacija dest_od, Destinacija dest_do);
    public Optional<Linija> findById(Integer id);
}
