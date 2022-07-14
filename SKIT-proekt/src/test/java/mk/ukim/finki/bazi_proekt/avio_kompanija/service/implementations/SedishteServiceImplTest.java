package mk.ukim.finki.bazi_proekt.avio_kompanija.service.implementations;

import mk.ukim.finki.bazi_proekt.avio_kompanija.model.Rezervacija;
import mk.ukim.finki.bazi_proekt.avio_kompanija.model.Sedishte;
import mk.ukim.finki.bazi_proekt.avio_kompanija.repository.RezervacijaRepository;
import mk.ukim.finki.bazi_proekt.avio_kompanija.repository.SedishteRepository;
import mk.ukim.finki.bazi_proekt.avio_kompanija.service.interfaces.RezervacijaService;
import mk.ukim.finki.bazi_proekt.avio_kompanija.service.interfaces.SedishteService;
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
public class SedishteServiceImplTest {
    @Mock
    private SedishteRepository sedishteRepository;
    private SedishteService service;
    List<SlobodniSedishta> sedishteList= new ArrayList<>();
    SlobodniSedishta sedishte;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        sedishte=new SlobodniSedishta();
        sedishteList.add(sedishte);

        Mockito.when(this.sedishteRepository.findAllByIdLet(Mockito.anyInt())).thenReturn(sedishteList);

        this.service = Mockito.spy(new SedishteServiceImpl(this.sedishteRepository));
    }

    @Test
    public void testFindByLetAndSlobodno() {
        Assert.assertEquals(sedishteList, this.service.findByLetAndSlobodno(1));
        Mockito.verify(this.service).findByLetAndSlobodno(1);
    }
}