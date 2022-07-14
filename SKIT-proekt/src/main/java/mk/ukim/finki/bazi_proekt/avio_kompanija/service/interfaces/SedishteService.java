package mk.ukim.finki.bazi_proekt.avio_kompanija.service.interfaces;

import mk.ukim.finki.bazi_proekt.avio_kompanija.model.Let;
import mk.ukim.finki.bazi_proekt.avio_kompanija.model.Sedishte;
import mk.ukim.finki.bazi_proekt.avio_kompanija.view.SlobodniSedishta;

import java.util.List;

public interface SedishteService {
    public List<SlobodniSedishta> findByLetAndSlobodno(Integer id_let);

}
