package mk.ukim.finki.bazi_proekt.avio_kompanija.service.implementations;

import mk.ukim.finki.bazi_proekt.avio_kompanija.repository.ListanjeLetoviRepository;
import mk.ukim.finki.bazi_proekt.avio_kompanija.repository.SedishteRepository;
import mk.ukim.finki.bazi_proekt.avio_kompanija.service.interfaces.ListanjeLetoviService;
import mk.ukim.finki.bazi_proekt.avio_kompanija.service.interfaces.SedishteService;
import mk.ukim.finki.bazi_proekt.avio_kompanija.view.ListanjeLetovi;
import mk.ukim.finki.bazi_proekt.avio_kompanija.view.SlobodniSedishta;
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

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class ListanjeLetoviServiceImplTest {
    @Mock
    private ListanjeLetoviRepository listanjeLetoviRepository;
    private ListanjeLetoviService service;
    List<ListanjeLetovi> listanjeLetoviList= new ArrayList<>();
    ListanjeLetovi listanjeLetovi;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        listanjeLetovi=new ListanjeLetovi();
        listanjeLetovi.setId_linija(1);
        listanjeLetoviList.add(listanjeLetovi);

        Mockito.when(this.listanjeLetoviRepository.findAll()).thenReturn(listanjeLetoviList);

        this.service = Mockito.spy(new ListanjeLetoviServiceImpl(this.listanjeLetoviRepository));
    }

    @Test
    public void testFindLetoviByLinijaId() {
        Assert.assertEquals(listanjeLetoviList,
                this.service.findLetoviByLinijaId(1));
        Mockito.verify(this.service).findLetoviByLinijaId(1);
    }
}