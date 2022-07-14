package mk.ukim.finki.bazi_proekt.avio_kompanija.service.implementations;

import mk.ukim.finki.bazi_proekt.avio_kompanija.model.Destinacija;
import mk.ukim.finki.bazi_proekt.avio_kompanija.model.Role;
import mk.ukim.finki.bazi_proekt.avio_kompanija.model.User;
import mk.ukim.finki.bazi_proekt.avio_kompanija.model.exceptions.InvalidArgumentsException;
import mk.ukim.finki.bazi_proekt.avio_kompanija.repository.DestinacijaRepository;
import mk.ukim.finki.bazi_proekt.avio_kompanija.repository.UserRepository;
import mk.ukim.finki.bazi_proekt.avio_kompanija.service.interfaces.AuthService;
import mk.ukim.finki.bazi_proekt.avio_kompanija.service.interfaces.DestinacijaService;
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
public class DestinacijaServiceImplTest {
    @Mock
    private DestinacijaRepository destinacijaRepository;

    private DestinacijaService service;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        List<Destinacija> destinacijas = new ArrayList<>();
        Destinacija destinacija1 = new Destinacija(1, "Skopje");
        Destinacija destinacija2 = new Destinacija(2, "Ohrid");
        destinacijas.add(destinacija1);
        destinacijas.add(destinacija2);

        Mockito.when(this.destinacijaRepository.findAll()).thenReturn(destinacijas);
        Mockito.when(this.destinacijaRepository.findByIme("Skopje")).thenReturn(destinacija1);

        this.service = Mockito.spy(new DestinacijaServiceImpl(this.destinacijaRepository));
    }
    @Test
    public void testFindAll() {
        List<Destinacija> destinacijas = new ArrayList<>();
        Destinacija destinacija1 = new Destinacija(1, "Skopje");
        Destinacija destinacija2 = new Destinacija(2, "Ohrid");
        destinacijas.add(destinacija1);
        destinacijas.add(destinacija2);
        Assert.assertEquals(destinacijas,
                 this.service.findAll());
        Mockito.verify(this.service).findAll();
    }

    @Test
    public void testFindByIme() {
        Destinacija destinacija1 = new Destinacija(1, "Skopje");

        Assert.assertEquals(destinacija1,
                this.service.findByIme("Skopje"));
        Mockito.verify(this.service).findByIme("Skopje");
    }
}