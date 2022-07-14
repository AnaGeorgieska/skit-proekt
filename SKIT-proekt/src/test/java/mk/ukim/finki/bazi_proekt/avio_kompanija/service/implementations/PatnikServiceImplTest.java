package mk.ukim.finki.bazi_proekt.avio_kompanija.service.implementations;

import mk.ukim.finki.bazi_proekt.avio_kompanija.model.Destinacija;
import mk.ukim.finki.bazi_proekt.avio_kompanija.model.Linija;
import mk.ukim.finki.bazi_proekt.avio_kompanija.model.Patnik;
import mk.ukim.finki.bazi_proekt.avio_kompanija.repository.LinijaRepository;
import mk.ukim.finki.bazi_proekt.avio_kompanija.repository.PatnikRepository;
import mk.ukim.finki.bazi_proekt.avio_kompanija.service.interfaces.LinijaService;
import mk.ukim.finki.bazi_proekt.avio_kompanija.service.interfaces.PatnikService;
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
public class PatnikServiceImplTest {
    @Mock
    private PatnikRepository patnikRepository;
    private PatnikService service;
    List<Patnik> patnikList=new ArrayList<>();
    Patnik patnik1;
    Patnik patnik2;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

        patnik1=new Patnik("MKD", "11111");
        patnik2=new Patnik("MKD", "22222");
        patnikList.add(patnik1);
        patnikList.add(patnik2);

        Mockito.when(this.patnikRepository.findAll()).thenReturn(patnikList);
        Mockito.when(this.patnikRepository.findByBrojPasosh(Mockito.anyString())).thenReturn(Optional.of(patnik1));
        Mockito.when(this.patnikRepository.save(Mockito.any())).thenReturn(patnik1);

        this.service = Mockito.spy(new PatnikServiceImpl(this.patnikRepository));
    }
    @Test
    public void testFindAll() {
        Assert.assertEquals(patnikList,
                this.service.findAll());
        Mockito.verify(this.service).findAll();
    }

    @Test
    public void testFindByBrojPasosh() {
        Assert.assertEquals(Optional.of(patnik1),
                this.service.findByBrojPasosh("11111"));
        Mockito.verify(this.service).findByBrojPasosh("11111");
    }

    @Test
    public void testSavePatnik() {
        Patnik patnik=this.service.save(patnik1);
        Assert.assertEquals(patnik1, patnik);
        Mockito.verify(this.service).save(patnik1);
    }
}