package mk.ukim.finki.bazi_proekt.avio_kompanija.service.interfaces;

import mk.ukim.finki.bazi_proekt.avio_kompanija.model.User;

public interface AuthService {
    User login(String username, String password);
}
