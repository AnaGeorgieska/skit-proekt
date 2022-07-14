package mk.ukim.finki.bazi_proekt.avio_kompanija.service.implementations;

import mk.ukim.finki.bazi_proekt.avio_kompanija.repository.SedishteRepository;
import mk.ukim.finki.bazi_proekt.avio_kompanija.service.interfaces.SedishteService;
import mk.ukim.finki.bazi_proekt.avio_kompanija.view.SlobodniSedishta;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SedishteServiceImpl implements SedishteService {
    private final SedishteRepository sedishteRepository;

    public SedishteServiceImpl(SedishteRepository sedishteRepository) {
        this.sedishteRepository = sedishteRepository;
    }

    @Override
    public List<SlobodniSedishta> findByLetAndSlobodno(Integer id_let) {
        return sedishteRepository.findAllByIdLet(id_let);
    }
}
