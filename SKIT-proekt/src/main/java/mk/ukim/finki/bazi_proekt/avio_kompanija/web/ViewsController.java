package mk.ukim.finki.bazi_proekt.avio_kompanija.web;

import mk.ukim.finki.bazi_proekt.avio_kompanija.repository.*;
import mk.ukim.finki.bazi_proekt.avio_kompanija.service.interfaces.ListanjeLetoviService;
import mk.ukim.finki.bazi_proekt.avio_kompanija.view.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/view")
public class ViewsController {

    private final RezervaciiRepository rezervaciiRepository;
    private final ListanjeLiniiRepository listanjeLiniiRepository;
    private final ListanjeLetoviService listanjeLetoviService;
    private final IzvestajMesecGodinaRepository izvestajMesecGodinaRepository;
    private final IzvestajLiniiRepository izvestajLiniiRepository;
    private final IzvestajPatnikRepository izvestajPatnikRepository;

    public ViewsController(RezervaciiRepository rezervaciiRepository, ListanjeLiniiRepository listanjeLiniiRepository, ListanjeLetoviService listanjeLetoviService, IzvestajMesecGodinaRepository izvestajMesecGodinaRepository, IzvestajLiniiRepository izvestajLiniiRepository, IzvestajPatnikRepository izvestajPatnikRepository) {
        this.rezervaciiRepository = rezervaciiRepository;
        this.listanjeLiniiRepository = listanjeLiniiRepository;
        this.listanjeLetoviService = listanjeLetoviService;
        this.izvestajMesecGodinaRepository = izvestajMesecGodinaRepository;
        this.izvestajLiniiRepository = izvestajLiniiRepository;
        this.izvestajPatnikRepository = izvestajPatnikRepository;
    }

    @GetMapping("/rezervacii")
    public String getRezervaciiPage(Model model)
    {
        List<Rezervacii> rezervacii=rezervaciiRepository.findAll();
        model.addAttribute("rezervacii", rezervacii);
        return "rezervacii";
    }

    @GetMapping("/linii")
    public String getLiniiPage(Model model)
    {
        List<ListanjeLinii> linii=listanjeLiniiRepository.findAll();
        model.addAttribute("linii", linii);
        return "linii";
    }

    @GetMapping("/let/{id_linija}")
    public String getLetoviPage(Model model, @PathVariable Integer id_linija)
    {
        List<ListanjeLetovi> letovi=listanjeLetoviService.findLetoviByLinijaId(id_linija);
        model.addAttribute("letovi", letovi);
        return "letovi";
    }

    @GetMapping("/mesec_godina")
    public String getMesecPage(Model model)
    {
        List<IzvestajMesecGodina> izvestai=izvestajMesecGodinaRepository.findAll();
        model.addAttribute("izvestai", izvestai);
        return "mesec_godina";
    }
    @GetMapping("/najcesti_linii")
    public String getnajccestiLiniiPage(Model model)
    {
        List<IzvestajNajcestoRezerviraniLinii> izvestai=izvestajLiniiRepository.findAll();
        model.addAttribute("izvestai", izvestai);
        return "najcesti_linii";
    }
    @GetMapping("/patnici")
    public String getPatnikPage(Model model)
    {
        List<IzvestajPatnik> izvestai=izvestajPatnikRepository.findAll();
        model.addAttribute("izvestai", izvestai);
        return "patnici";
    }
}
