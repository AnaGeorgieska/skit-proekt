package mk.ukim.finki.bazi_proekt.avio_kompanija.service.implementations;

import mk.ukim.finki.bazi_proekt.avio_kompanija.model.Destinacija;
import mk.ukim.finki.bazi_proekt.avio_kompanija.repository.DestinacijaRepository;
import mk.ukim.finki.bazi_proekt.avio_kompanija.service.interfaces.DestinacijaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DestinacijaServiceImpl implements DestinacijaService {

    private final DestinacijaRepository destinacijaRepository;

    public DestinacijaServiceImpl(DestinacijaRepository destinacijaRepository) {
        this.destinacijaRepository = destinacijaRepository;
    }

    @Override
    public List<Destinacija> findAll() {
        return destinacijaRepository.findAll();
    }

    @Override
    public Destinacija findByIme(String ime) {
        return destinacijaRepository.findByIme(ime);
    }


}
