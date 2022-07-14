package mk.ukim.finki.bazi_proekt.avio_kompanija.web;

import mk.ukim.finki.bazi_proekt.avio_kompanija.model.*;
import mk.ukim.finki.bazi_proekt.avio_kompanija.repository.AvionRepository;
import mk.ukim.finki.bazi_proekt.avio_kompanija.repository.RezervaciiRepository;
import mk.ukim.finki.bazi_proekt.avio_kompanija.repository.SedisheRep;
import mk.ukim.finki.bazi_proekt.avio_kompanija.service.interfaces.*;
import mk.ukim.finki.bazi_proekt.avio_kompanija.view.SlobodniSedishta;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Controller
@RequestMapping()
public class HomeController {
    private final DestinacijaService destinacijaService;
    private final LinijaService linijaService;
    private final LetService letService;
    private final PatnikService patnikService;
    private final RezervacijaService rezervacijaService;
    private final SedishteService sedishteService;
    private final SedisheRep  sedisheRep;
    private final AvionRepository avionRepository;


    public HomeController(DestinacijaService destinacijaService, LinijaService linijaService, LetService letService, PatnikService patnikService, RezervacijaService rezervacijaService, SedishteService sedishteService, SedisheRep sedisheRep, AvionRepository avionRepository) {
        this.destinacijaService = destinacijaService;
        this.linijaService = linijaService;
        this.letService = letService;
        this.patnikService = patnikService;
        this.rezervacijaService = rezervacijaService;
        this.sedishteService = sedishteService;
        this.sedisheRep = sedisheRep;
        this.avionRepository = avionRepository;
    }

    @GetMapping
    public String getHomePage(Model model)
    {
        List<Destinacija> destinacii=destinacijaService.findAll();
        model.addAttribute("destinacii", destinacii);
        return "home";
    }

    @PostMapping("/destinacii")
    public String postDestinacii(Model model,
                                 @RequestParam(required = false) String from,
                                 @RequestParam(required = false) String to,
                                 HttpSession session)
    {
        List<Let> letoviSporedLinija;
        if(from!=null && to!=null)
        {
            Destinacija dest_od=destinacijaService.findByIme(from);
            Destinacija dest_do=destinacijaService.findByIme(to);
            System.out.println(dest_od);
            System.out.println(dest_do);
            session.setAttribute("from", from);
            session.setAttribute("to", to);
            if(linijaService.findByDestinacija_odAndDeestinacija_do(dest_od, dest_do).isPresent())
            {
                letoviSporedLinija=letService.findByLinija(linijaService.findByDestinacija_odAndDeestinacija_do(dest_od,dest_do).get());
                System.out.println(letoviSporedLinija);
                model.addAttribute("letovi", letoviSporedLinija);
            }
        }
        List<Destinacija> destinacii=destinacijaService.findAll();
        model.addAttribute("destinacii", destinacii);
        return "home";
    }

    @PostMapping("/let")
    public String postLet(@RequestParam(required = false) Integer letOption, HttpSession session, Model model)
    {
        if(letOption!=null)
        {
            session.setAttribute("let", letService.findById(letOption).get());
            System.out.println(letService.findById(letOption).get()+ "*****************************");
            session.setAttribute("luggage10kg", letService.findById(letOption).get().getCena()*0.05);
            session.setAttribute("luggage20kg", letService.findById(letOption).get().getCena()*0.07);
            session.setAttribute("luggage32kg", letService.findById(letOption).get().getCena()*0.10);
            List<SlobodniSedishta> seats=sedishteService.findByLetAndSlobodno(((Let)session.getAttribute("let")).getId_let());
            model.addAttribute("seats", seats);
            session.setAttribute("seats", seats);
            System.out.println(seats.toString());
            return "/patnik";
        }
        else return "redirect:/";
    }

    @GetMapping("/patnik")
    public String getPatnikForm()
    {
        return "patnik";
    }

    @PostMapping("/patnik")
    public String postPatnikForm(@RequestParam String name, @RequestParam String surename, @RequestParam String passportNumber,
                                 @RequestParam String country, @RequestParam(required = false) String luggage,
                                 @RequestParam Integer seat,
                                 HttpSession session)
    {
        session.setAttribute("name", name);
        session.setAttribute("surename", surename);
        session.setAttribute("passportNumber", passportNumber);
        session.setAttribute("country", country);
        session.setAttribute("seat", seat);
        if(luggage!="")
        {
            if (luggage.equals("bag 10kg"))
            {
                session.setAttribute("price", session.getAttribute("luggage10kg"));
            }
            if(luggage.equals("bag 20kg"))
            {
                session.setAttribute("price", session.getAttribute("luggage20kg"));
            }
            if(luggage.equals("bag 32kg"))
            {
                session.setAttribute("price", session.getAttribute("luggage32kg"));
            }
            session.setAttribute("luggage", luggage);
        }
        else {
            session.setAttribute("price", 0.0);
            session.setAttribute("luggage", luggage);
        }
        return "redirect:/rezervacija";
    }

    @GetMapping("/rezervacija")
    public String getPatnikRezervacija()
    {
        return "rezervacija";
    }

    @PostMapping("/rezervacija")
    public String postPatnikRezervacija(HttpSession session)
    {
        Let let= (Let) session.getAttribute("let");
        Patnik patnik;
        if(patnikService.findByBrojPasosh((String) session.getAttribute("passportNumber")).isPresent())
        {
            patnik=patnikService.findByBrojPasosh((String) session.getAttribute("passportNumber")).get();
        }
        else {
            patnik=new Patnik((String) session.getAttribute("country"), (String) session.getAttribute("passportNumber"));
            patnikService.save(patnik);
        }
        double price;
        if(session.getAttribute("price")!=null)
            price=let.getCena()+(Double)  session.getAttribute("price");
        else price=let.getCena();

        Sedishte sedishte=sedisheRep.findByIdSedishteAndLet((Integer) session.getAttribute("seat"), let);
        Rezervacija rezervacija=new Rezervacija((float) price,(String) session.getAttribute("luggage"),sedishte, let, patnik );
        rezervacijaService.save(rezervacija);
        return "redirect:/";
    }

    @GetMapping("/add-flight/{id}")
    public String getAddFligth(@PathVariable Integer id,  Model model)
    {
        List<Avion> avioni=avionRepository.findAll();
        model.addAttribute("avioni", avioni);
        model.addAttribute("id_linija", id);
        model.addAttribute("header", "Add flight");
        return "add-flight";
    }

    @PostMapping("/add-flight/{id}")
    public String postAddFligth(@PathVariable Integer id,
                                @RequestParam(required = false)Integer id_let,
                                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime datum_vreme,
                                @RequestParam Float cena,
                                @RequestParam Integer avion)
    {
        Linija linija=linijaService.findById(id).get();
        Avion avion1=avionRepository.getById(avion);
        LocalDateTime ldt = datum_vreme;            //Local date time
        ZoneId zoneId = ZoneId.of( "Europe/Skopje" );        //Zone information
        ZonedDateTime zdt = ldt.atZone( zoneId );
        if(id_let!=null)
        {
            Let let=letService.findById(id_let).get();
            let.setAvion(avion1);
            let.setCena(cena);
            let.setDatum_vreme(zdt);
            letService.save(let);
        }
        else {
            Let let=new Let(zdt, linija, avion1, cena);
            let.setId_let(2);
            letService.save(let);
        }
        return "redirect:/view/let/"+id;
    }

    @GetMapping("/edit-flight/{id}/{id_let}")
    public String getEditFligth(@PathVariable Integer id, @PathVariable Integer id_let,  Model model)
    {
        Let let=letService.findById(id_let).get();
        List<Avion> avioni=avionRepository.findAll();
        model.addAttribute("avioni", avioni);
        model.addAttribute("id_linija", id);
        model.addAttribute("let", let);
        model.addAttribute("header", "Edit flight");
        return "add-flight";
    }
    @GetMapping("/edit-rezervacija")
    public String getEditRezervacija(Model model)
    {
        return "edit-rezervacija";
    }
}
