package mk.ukim.finki.bazi_proekt.avio_kompanija.service.interfaces;

import mk.ukim.finki.bazi_proekt.avio_kompanija.model.Destinacija;
import mk.ukim.finki.bazi_proekt.avio_kompanija.model.Linija;

import java.util.List;
import java.util.Optional;

public interface DestinacijaService {
    public List<Destinacija> findAll();
    public Destinacija findByIme(String  ime);
}
