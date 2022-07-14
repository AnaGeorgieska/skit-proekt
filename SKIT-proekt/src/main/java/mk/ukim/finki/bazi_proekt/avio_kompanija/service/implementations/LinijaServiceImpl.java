package mk.ukim.finki.bazi_proekt.avio_kompanija.service.implementations;

import mk.ukim.finki.bazi_proekt.avio_kompanija.model.Destinacija;
import mk.ukim.finki.bazi_proekt.avio_kompanija.model.Linija;
import mk.ukim.finki.bazi_proekt.avio_kompanija.repository.LinijaRepository;
import mk.ukim.finki.bazi_proekt.avio_kompanija.service.interfaces.LinijaService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LinijaServiceImpl implements LinijaService {
    private final LinijaRepository linijaRepository;

    public LinijaServiceImpl(LinijaRepository linijaRepository) {
        this.linijaRepository = linijaRepository;
    }

    @Override
    public Optional<Linija> findByDestinacija_odAndDeestinacija_do(Destinacija dest_od, Destinacija dest_do) {
        return linijaRepository.findByDestinacijaOdAndDestinacijaDo(dest_od,dest_do);
    }

    @Override
    public Optional<Linija> findById(Integer id) {
        return linijaRepository.findById(id);
    }
}
