package mk.ukim.finki.bazi_proekt.avio_kompanija.service.implementations;

import mk.ukim.finki.bazi_proekt.avio_kompanija.model.Patnik;
import mk.ukim.finki.bazi_proekt.avio_kompanija.model.Rezervacija;
import mk.ukim.finki.bazi_proekt.avio_kompanija.repository.PatnikRepository;
import mk.ukim.finki.bazi_proekt.avio_kompanija.repository.RezervacijaRepository;
import mk.ukim.finki.bazi_proekt.avio_kompanija.service.interfaces.PatnikService;
import mk.ukim.finki.bazi_proekt.avio_kompanija.service.interfaces.RezervacijaService;
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
public class RezervacijaServiceImplTest {
    @Mock
    private RezervacijaRepository rezervacijaRepository;
    private RezervacijaService service;
    Rezervacija rezervacija;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        rezervacija=new Rezervacija();

        Mockito.when(this.rezervacijaRepository.save(Mockito.any())).thenReturn(rezervacija);

        this.service = Mockito.spy(new RezervacijaServiceImpl(this.rezervacijaRepository));
    }

    @Test
    public void testSavePatnik() {
        Assert.assertEquals(rezervacija, this.service.save(rezervacija));
        Mockito.verify(this.service).save(rezervacija);
    }
}