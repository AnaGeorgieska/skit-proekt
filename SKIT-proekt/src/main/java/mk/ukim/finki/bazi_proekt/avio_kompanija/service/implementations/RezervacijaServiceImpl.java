package mk.ukim.finki.bazi_proekt.avio_kompanija.service.implementations;

import mk.ukim.finki.bazi_proekt.avio_kompanija.model.Rezervacija;
import mk.ukim.finki.bazi_proekt.avio_kompanija.repository.RezervacijaRepository;
import mk.ukim.finki.bazi_proekt.avio_kompanija.service.interfaces.RezervacijaService;
import org.springframework.stereotype.Service;

@Service
public class RezervacijaServiceImpl implements RezervacijaService {
    private final RezervacijaRepository rezervacijaRepository;

    public RezervacijaServiceImpl(RezervacijaRepository rezervacijaRepository) {
        this.rezervacijaRepository = rezervacijaRepository;
    }

    @Override
    public Rezervacija save(Rezervacija rezervacija) {
       return rezervacijaRepository.save(rezervacija);
    }
}
