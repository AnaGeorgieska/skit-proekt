package mk.ukim.finki.bazi_proekt.avio_kompanija.service.implementations;

import mk.ukim.finki.bazi_proekt.avio_kompanija.repository.ListanjeLetoviRepository;
import mk.ukim.finki.bazi_proekt.avio_kompanija.service.interfaces.ListanjeLetoviService;
import mk.ukim.finki.bazi_proekt.avio_kompanija.view.ListanjeLetovi;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListanjeLetoviServiceImpl implements ListanjeLetoviService {
   private final ListanjeLetoviRepository listanjeLetoviRepository;

    public ListanjeLetoviServiceImpl(ListanjeLetoviRepository listanjeLetoviRepository) {
        this.listanjeLetoviRepository = listanjeLetoviRepository;
    }

    @Override
    public List<ListanjeLetovi> findLetoviByLinijaId(Integer idLinija) {
        List<ListanjeLetovi> letovi=listanjeLetoviRepository.findAll();
        List<ListanjeLetovi> temp=letovi.stream().filter(l->l.getId_linija().equals(idLinija)).collect(Collectors.toList());
        return temp;
    }
}
