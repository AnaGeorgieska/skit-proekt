package mk.ukim.finki.bazi_proekt.avio_kompanija.integration;

import mk.ukim.finki.bazi_proekt.avio_kompanija.model.*;
import mk.ukim.finki.bazi_proekt.avio_kompanija.model.exceptions.InvalidUserCredentialsException;
import mk.ukim.finki.bazi_proekt.avio_kompanija.repository.*;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class IntegrationTests {
    MockMvc mockMvc;
    MockHttpSession session;
    @Autowired
    DestinacijaRepository destinacijaRepository;
    @Autowired
    LinijaRepository linijaRepository;
    @Autowired
    AvionRepository avionRepository;
    @Autowired
    LetRepository letRepository;
    @Autowired
    RezervacijaRepository rezervaciiRepository;
    @Autowired
    UserRepository userRepository;
    private static Destinacija d1;
    private static Destinacija d2;
    private static Linija linija;
    private static Let let;
    private static Avion avion;
    private static boolean dataInitialized = false;

    @BeforeEach
    public void setup(WebApplicationContext wac) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        session = new MockHttpSession();
        initData();
    }

    private void initData() {
        if (!dataInitialized) {
            d1 = destinacijaRepository.save(new Destinacija(1, "Skopje"));
            d2 = destinacijaRepository.save(new Destinacija(2, "Ohrid"));
            Linija l1 = new Linija(d1, d2);
            l1.setId_linija(1);
            linija = linijaRepository.save(l1);
            Avion avion1 = new Avion();
            avion1.setId_avion(1);
            avion1.setTip_avion("B1");
            avion = avionRepository.save(avion1);
            let = letRepository.save(new Let(ZonedDateTime.now(), linija, avion, (float) 100.0));
            System.out.println(linija.getId_linija());
            System.out.println(let.getId_let());
            dataInitialized = true;
        }
    }

    @Test
    public void testGetHomePage() throws Exception {
        MockHttpServletRequestBuilder homeRequest = MockMvcRequestBuilders
                .get("http://localhost");
        this.mockMvc.perform(homeRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("destinacii"))
                .andExpect(MockMvcResultMatchers.view().name("home"));
    }

    @Test
    public void testDestinaciiPage() throws Exception {
        MockHttpServletRequestBuilder destinaciiRequest = MockMvcRequestBuilders
                .post("http://localhost:9999/destinacii")
                .param("from", "Skopje").param("to", "Ohrid");
        this.mockMvc.perform(destinaciiRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("destinacii"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("letovi"))
                .andExpect(MockMvcResultMatchers.view().name("home"));
    }

    @Test
    public void testLetPage() throws Exception {
        MockHttpServletRequestBuilder letRequest = MockMvcRequestBuilders
                .post("http://localhost:9999/let")
                .param("letOption", "1");
        this.mockMvc.perform(letRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("seats"));
    }

    @Test
    public void testLetPageWithoutLetOption() throws Exception {
        MockHttpServletRequestBuilder letRequest = MockMvcRequestBuilders
                .post("http://localhost:9999/let");
        this.mockMvc.perform(letRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"));
    }

    @Test
    public void testPatnikPage() throws Exception {
        MockHttpServletRequestBuilder patnikRequest = MockMvcRequestBuilders.get("http://localhost/patnik");
        this.mockMvc.perform(patnikRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("patnik"));
    }

    @Test
    public void testPatnikPostPage() throws Exception {
        MockHttpServletRequestBuilder patnikRequest = MockMvcRequestBuilders
                .post("http://localhost/patnik")
                .param("name", "Ana")
                .param("surename", "Georgieska")
                .param("passportNumber", "MKD6677")
                .param("country", "Macedonia")
                .param("luggage", "")
                .param("seat", "1");
        this.mockMvc.perform(patnikRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/rezervacija"));
    }

    @Test
    public void testRezervacijaPage() throws Exception {
        this.session.setAttribute("let", letRepository.findById(1).get());
        System.out.println(session.getAttribute("let"));
        MockHttpServletRequestBuilder rezervacijaRequest = MockMvcRequestBuilders
                .get("http://localhost/rezervacija")
                .session(session);
        this.mockMvc.perform(rezervacijaRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("rezervacija"));
    }

    @Test
    public void testAddRezervacijaPostPage() throws Exception {
        session.setAttribute("let", letRepository.findById(1).get());
        session.setAttribute("country", "Macedonia");
        session.setAttribute("passportNumber", "MKD5677");
        session.setAttribute("price", (double) 40.0);
        session.setAttribute("seat", 1);
        session.setAttribute("luggage", "");
        MockHttpServletRequestBuilder addFlightRequest = MockMvcRequestBuilders
                .post("http://localhost/rezervacija")
                .session(session);
        Assert.assertEquals(0, rezervaciiRepository.findAll().size());
        this.mockMvc.perform(addFlightRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"));
        Assert.assertEquals(1, rezervaciiRepository.findAll().size());
    }

    @Test
    public void testAddFlightPage() throws Exception {
        MockHttpServletRequestBuilder rezervacijaRequest = MockMvcRequestBuilders
                .get("http://localhost/add-flight/1");
        System.out.println(LocalDateTime.now());
        this.mockMvc.perform(rezervacijaRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("avioni"))
                .andExpect(MockMvcResultMatchers.model().attribute("id_linija", 1))
                .andExpect(MockMvcResultMatchers.view().name("add-flight"));
    }

    @Test
    public void testAddFlightPostPage() throws Exception {
        MockHttpServletRequestBuilder addFlightRequest = MockMvcRequestBuilders.post("http://localhost/add-flight/1")
                .param("id", "1")
                .param("datum_vreme", "2022-07-13T21:17:11.080874100")
                .param("cena", "90.0")
                .param("avion", "1");
        Assert.assertEquals(1, letRepository.findAll().size());
        this.mockMvc.perform(addFlightRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/view/let/1"));
        Assert.assertEquals(2, letRepository.findAll().size());
    }

    @Test
    public void testEditFlightPage() throws Exception {
        this.session.setAttribute("let", letRepository.findById(1).get());
        System.out.println(session.getAttribute("let"));
        MockHttpServletRequestBuilder flightRequest = MockMvcRequestBuilders.get("http://localhost/edit-flight/1/1")
                .session(session);
        this.mockMvc.perform(flightRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("avioni"))
                .andExpect(MockMvcResultMatchers.model().attribute("id_linija", 1))
                .andExpect(MockMvcResultMatchers.model().attribute("let", letRepository.findById(1).get()))
                .andExpect(MockMvcResultMatchers.view().name("add-flight"));
    }

    @Test
    public void testGetEditRezervacijaPage() throws Exception {
        this.session.setAttribute("let", letRepository.findById(1).get());
        MockHttpServletRequestBuilder editRezervacijaRequest = MockMvcRequestBuilders
                .get("http://localhost/edit-rezervacija")
                .session(session);
        this.mockMvc.perform(editRezervacijaRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("edit-rezervacija"));
    }

    @Test
    public void testGetLoginPage() throws Exception {
        MockHttpServletRequestBuilder patnikRequest = MockMvcRequestBuilders.get("http://localhost/login");
        this.mockMvc.perform(patnikRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("login"));
    }

    @Test
    public void testLoginPostPage() throws Exception {
        User user = new User("admin", "admin", "admin", "admin", Role.ROLE_ADMIN);
        userRepository.save(user);
        MockHttpServletRequestBuilder loginRequest = MockMvcRequestBuilders.post("http://localhost/login")
                .param("username", "admin")
                .param("password", "admin");
        this.mockMvc.perform(loginRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"));
    }

    @Test
    public void testGetLogoutPage() throws Exception {
        MockHttpServletRequestBuilder logoutRequest = MockMvcRequestBuilders.get("http://localhost/logout");
        this.mockMvc.perform(logoutRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/login"));
    }

    @Test
    public void testPatniciPage() throws Exception {
        MockHttpServletRequestBuilder patnikRequest = MockMvcRequestBuilders.get("http://localhost/patnici");
        this.mockMvc.perform(patnikRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.model().attributeExists("patnici"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("patnici"));
    }

    @Test
    public void testRegisterPage() throws Exception {
        MockHttpServletRequestBuilder registerRequest = MockMvcRequestBuilders.get("http://localhost/register");
        this.mockMvc.perform(registerRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("register"));
    }

    @Test
    public void testRegisterErrorPage() throws Exception {
        MockHttpServletRequestBuilder registerRequest = MockMvcRequestBuilders.get("http://localhost/register")
                .param("error", "error");
        this.mockMvc.perform(registerRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.model().attribute("hasError", true))
                .andExpect(MockMvcResultMatchers.model().attribute("error", "error"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("register"));
    }

    @Test
    public void testRegisterPostPage() throws Exception {
        MockHttpServletRequestBuilder loginRequest = MockMvcRequestBuilders.post("http://localhost/register")
                .param("username", "admin")
                .param("password", "admin")
                .param("repeatedPassword", "admin")
                .param("name", "admin")
                .param("surname", "admin");
        this.mockMvc.perform(loginRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/login"));
    }

}
