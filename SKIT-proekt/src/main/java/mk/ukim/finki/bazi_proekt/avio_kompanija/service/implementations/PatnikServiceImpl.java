package mk.ukim.finki.bazi_proekt.avio_kompanija.service.implementations;

import mk.ukim.finki.bazi_proekt.avio_kompanija.model.Patnik;
import mk.ukim.finki.bazi_proekt.avio_kompanija.repository.PatnikRepository;
import mk.ukim.finki.bazi_proekt.avio_kompanija.service.interfaces.PatnikService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatnikServiceImpl implements PatnikService {

    private final PatnikRepository patnikRepository;

    public PatnikServiceImpl(PatnikRepository patnikRepository) {
        this.patnikRepository = patnikRepository;
    }

    @Override
    public List<Patnik> findAll() {
        List<Patnik> patnikList=patnikRepository.findAll();
        System.out.println(patnikList.toString());
        return patnikList;
    }

    @Override
    public Optional<Patnik> findByBrojPasosh(String brojPasosh) {
        return patnikRepository.findByBrojPasosh(brojPasosh);
    }

    @Override
    public Patnik save(Patnik patnik) {
       return patnikRepository.save(patnik);
    }
}
