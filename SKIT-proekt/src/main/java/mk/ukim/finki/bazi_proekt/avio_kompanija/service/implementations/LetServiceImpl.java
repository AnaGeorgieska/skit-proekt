package mk.ukim.finki.bazi_proekt.avio_kompanija.service.implementations;

import mk.ukim.finki.bazi_proekt.avio_kompanija.model.Let;
import mk.ukim.finki.bazi_proekt.avio_kompanija.model.Linija;
import mk.ukim.finki.bazi_proekt.avio_kompanija.repository.LetRepository;
import mk.ukim.finki.bazi_proekt.avio_kompanija.service.interfaces.LetService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LetServiceImpl implements LetService {
    private final LetRepository letRepository;

    public LetServiceImpl(LetRepository letRepository) {
        this.letRepository = letRepository;
    }

    @Override
    public List<Let> findByLinija(Linija linija) {
        return letRepository.findByLinija(linija);
    }

    @Override
    public Optional<Let> findById(Integer id) {
        return letRepository.findById(id);
    }

    @Override
    public Let save(Let let) {
       return letRepository.save(let);
    }
}
