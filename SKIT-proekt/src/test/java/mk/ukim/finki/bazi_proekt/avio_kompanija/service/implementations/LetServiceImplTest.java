package mk.ukim.finki.bazi_proekt.avio_kompanija.service.implementations;

import mk.ukim.finki.bazi_proekt.avio_kompanija.model.*;
import mk.ukim.finki.bazi_proekt.avio_kompanija.repository.DestinacijaRepository;
import mk.ukim.finki.bazi_proekt.avio_kompanija.repository.LetRepository;
import mk.ukim.finki.bazi_proekt.avio_kompanija.service.interfaces.DestinacijaService;
import mk.ukim.finki.bazi_proekt.avio_kompanija.service.interfaces.LetService;
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
public class LetServiceImplTest {
    @Mock
    private LetRepository letRepository;

    private LetService service;
    List<Let> letList=new ArrayList<>();
    Let let=new Let();


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

        Destinacija destinacija1 = new Destinacija(1, "Skopje");
        Destinacija destinacija2 = new Destinacija(2, "Ohrid");

        Linija linija1=new Linija(destinacija1, destinacija2);


        letList.add(let);

        Mockito.when(this.letRepository.findByLinija(linija1)).thenReturn(letList);
        Mockito.when(this.letRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(let));
        Mockito.when(this.letRepository.save(let)).thenReturn(let);

        this.service = Mockito.spy(new LetServiceImpl(this.letRepository));
    }
    @Test
    public void testFindByLinija() {
        Destinacija destinacija1 = new Destinacija(1, "Skopje");
        Destinacija destinacija2 = new Destinacija(2, "Ohrid");

        Linija linija1=new Linija(destinacija1, destinacija2);

        Assert.assertEquals(letList,
                this.service.findByLinija(linija1));
        Mockito.verify(this.service).findByLinija(linija1);
    }
    @Test
    public void testFindById() {
        Assert.assertEquals(Optional.of(let),
                this.service.findById(1));
        Mockito.verify(this.service).findById(1);
    }
    @Test
    public void testSaveLet() {
        Assert.assertEquals(let, this.service.save(let));
        Mockito.verify(this.service).save(let);
    }
}