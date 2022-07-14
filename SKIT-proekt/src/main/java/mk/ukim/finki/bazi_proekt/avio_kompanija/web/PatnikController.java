package mk.ukim.finki.bazi_proekt.avio_kompanija.web;

import mk.ukim.finki.bazi_proekt.avio_kompanija.model.Patnik;
import mk.ukim.finki.bazi_proekt.avio_kompanija.service.interfaces.PatnikService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/patnici")
public class PatnikController {

    private final PatnikService patnikService;

    public PatnikController(PatnikService patnikService) {
        this.patnikService = patnikService;
    }

    @GetMapping
    public String getPatnici(Model model)
    {
      List<Patnik> patnikList= patnikService.findAll();
        model.addAttribute("patnici", patnikList);
        return "patnici";
    }
}
