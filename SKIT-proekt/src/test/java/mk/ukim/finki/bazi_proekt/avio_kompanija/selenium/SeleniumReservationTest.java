package mk.ukim.finki.bazi_proekt.avio_kompanija.selenium;

import junit.framework.Assert;
import mk.ukim.finki.bazi_proekt.avio_kompanija.model.*;
import mk.ukim.finki.bazi_proekt.avio_kompanija.repository.*;
import mk.ukim.finki.bazi_proekt.avio_kompanija.service.interfaces.LetService;
import mk.ukim.finki.bazi_proekt.avio_kompanija.service.interfaces.PatnikService;
import mk.ukim.finki.bazi_proekt.avio_kompanija.service.interfaces.RezervacijaService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.time.ZonedDateTime;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SeleniumReservationTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PatnikService patnikService;
    @Autowired
    AvionRepository avionRepository;
    @Autowired
    DestinacijaRepository destinacijaRepository;
    @Autowired
    LinijaRepository linijaRepository;
    @Autowired
    LetService letService;
    @Autowired
    RezervacijaService rezervacijaService;
    @Autowired
    SedisheRep sedishteRepository;
    @Autowired
    RezervacijaRepository rezervaciiRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    private HtmlUnitDriver driver;

    private static Patnik patnik;
    private static Avion avion;
    private static Destinacija d1;
    private static Destinacija d2;
    private static User adminUser;
    private static Linija linija;
    private static Linija linija2;
    private static Let let;
    private static Let let2;
    private static Rezervacija rezervacija;
    private static Sedishte sedishte;
    private static Sedishte sedishte2;
    private static boolean dataInitialized = false;
    public SeleniumReservationTest(){}

    @BeforeEach
    public void setup() {
        this.driver = new HtmlUnitDriver(true);
        initData();
    }

    @AfterEach
    public void destroy() {
        if (this.driver != null) {
            this.driver.close();
        }
    }
    private void initData() {
        if (!dataInitialized) {
            User user=new User("admin", passwordEncoder.encode("admin"),"admin", "admin", Role.ROLE_ADMIN);
            adminUser=userRepository.save(user);

            patnik = patnikService.save(new Patnik("MKD", "11111"));
            d1=destinacijaRepository.save(new Destinacija(1, "Skopje"));
            d2=destinacijaRepository.save(new Destinacija(2, "Ohrid"));

            Linija l1=new Linija(d1, d2);
            l1.setId_linija(1);
            linija = linijaRepository.save(l1);
            Linija l2=new Linija(d2, d1);
            l2.setId_linija(2);
            linija2 = linijaRepository.save(l2);
            Avion avion1=new Avion();
            avion1.setTip_avion("B1");
            avion1.setId_avion(1);
            avion=avionRepository.save(avion1);
            let=letService.save(new Let(ZonedDateTime.now(), linija, avion, (float)100.0));
            let=letService.save(new Let(ZonedDateTime.now(), linija2, avion, (float)80.0));
            Sedishte sedishte1=new Sedishte();
            sedishte1.setIdSedishte(1);
            sedishte1.setLet(let);
            sedishte=sedishteRepository.save(sedishte1);
            Sedishte sedishte2=new Sedishte();
            sedishte2.setIdSedishte(1);
            sedishte2.setLet(let2);
            sedishte=sedishteRepository.save(sedishte2);

            rezervacija=rezervacijaService.save(new Rezervacija((float)100.0, "bag 10kg", sedishte1, let, patnik));

            dataInitialized = true;
        }
    }

    @Test
    public void testScenario() throws Exception {
        Assert.assertEquals(1, rezervaciiRepository.findAll().size());

        HomePage homePage = HomePage.to(this.driver);

        LoginPage loginPage = LoginPage.openLogin(this.driver);

        homePage = LoginPage.doLogin(this.driver, loginPage, adminUser.getUsername(), "admin");

        homePage = homePage.FromAndTo(this.driver);

        PatnikPage patnikPage = homePage.izbiranjeLet(this.driver);

        patnikPage = PatnikPage.patnikPage(this.driver);

        RezervacijaPage rezervacijaPage = RezervacijaPage.rez(this.driver);

        homePage = rezervacijaPage.rezervacijaSubmit(this.driver);

        loginPage = LoginPage.logout(this.driver);

        System.out.println(rezervaciiRepository.findAll());

        Assert.assertEquals(2, rezervaciiRepository.findAll().size());
    }
}
