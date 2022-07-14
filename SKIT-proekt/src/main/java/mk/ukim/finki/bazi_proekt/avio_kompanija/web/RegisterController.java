package mk.ukim.finki.bazi_proekt.avio_kompanija.web;


import mk.ukim.finki.bazi_proekt.avio_kompanija.model.Role;
import mk.ukim.finki.bazi_proekt.avio_kompanija.model.exceptions.InvalidArgumentsException;
import mk.ukim.finki.bazi_proekt.avio_kompanija.model.exceptions.PasswordsDoNotMatchException;
import mk.ukim.finki.bazi_proekt.avio_kompanija.service.interfaces.AuthService;
import mk.ukim.finki.bazi_proekt.avio_kompanija.service.interfaces.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final AuthService authService;
    private final UserService userService;

    public RegisterController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @GetMapping
    public String getRegisterPage(@RequestParam(required = false) String error, Model model) {
        if(error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        return "register";
    }

    @PostMapping
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String repeatedPassword,
                           @RequestParam String name,
                           @RequestParam String surname) {
        try{
            this.userService.register(username, password, repeatedPassword, name, surname, Role.ROLE_USER);
            return "redirect:/login";
        } catch (InvalidArgumentsException | PasswordsDoNotMatchException exception) {
            return "redirect:/register?error=" + exception.getMessage();
        }
    }
}
