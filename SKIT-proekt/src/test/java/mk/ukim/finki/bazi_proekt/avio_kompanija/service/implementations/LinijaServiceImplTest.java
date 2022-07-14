package mk.ukim.finki.bazi_proekt.avio_kompanija.service.implementations;

import mk.ukim.finki.bazi_proekt.avio_kompanija.model.Destinacija;
import mk.ukim.finki.bazi_proekt.avio_kompanija.model.Let;
import mk.ukim.finki.bazi_proekt.avio_kompanija.model.Linija;
import mk.ukim.finki.bazi_proekt.avio_kompanija.repository.LetRepository;
import mk.ukim.finki.bazi_proekt.avio_kompanija.repository.LinijaRepository;
import mk.ukim.finki.bazi_proekt.avio_kompanija.service.interfaces.LetService;
import mk.ukim.finki.bazi_proekt.avio_kompanija.service.interfaces.LinijaService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(MockitoJUnitRunner.class)
public class LinijaServiceImplTest {
    @Mock
    private LinijaRepository linijaRepository;
    private LinijaService service;
    Destinacija destinacija1 ;
    Destinacija destinacija2 ;
    Linija linija1;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

         destinacija1 = new Destinacija(1, "Skopje");
         destinacija2 = new Destinacija(2, "Ohrid");
         linija1=new Linija(destinacija1, destinacija2);

        Mockito.when(this.linijaRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(linija1));
        Mockito.when(this.linijaRepository.findByDestinacijaOdAndDestinacijaDo(destinacija1, destinacija2)).thenReturn(Optional.of(linija1));

        this.service = Mockito.spy(new LinijaServiceImpl(this.linijaRepository));
    }
    @Test
    public void testFindById() {
        Assert.assertEquals(Optional.of(linija1), this.service.findById(1));
        Mockito.verify(this.service).findById(1);
    }

    @Test
    public void testFindByDestinacija_odAndDeestinacija_do() {
        Assert.assertEquals(Optional.of(linija1),
                this.service.findByDestinacija_odAndDeestinacija_do(destinacija1, destinacija2));
        Mockito.verify(this.service).findByDestinacija_odAndDeestinacija_do(destinacija1, destinacija2);
    }
}