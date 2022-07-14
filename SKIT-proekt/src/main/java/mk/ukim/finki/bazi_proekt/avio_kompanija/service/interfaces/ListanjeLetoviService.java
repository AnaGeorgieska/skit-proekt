package mk.ukim.finki.bazi_proekt.avio_kompanija.service.interfaces;

import mk.ukim.finki.bazi_proekt.avio_kompanija.view.ListanjeLetovi;

import java.util.List;

public interface ListanjeLetoviService {
    public List<ListanjeLetovi> findLetoviByLinijaId(Integer idLinija);
}
