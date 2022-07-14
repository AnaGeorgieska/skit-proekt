package mk.ukim.finki.bazi_proekt.avio_kompanija.service.implementations;


import mk.ukim.finki.bazi_proekt.avio_kompanija.model.User;
import mk.ukim.finki.bazi_proekt.avio_kompanija.model.exceptions.InvalidArgumentsException;
import mk.ukim.finki.bazi_proekt.avio_kompanija.model.exceptions.InvalidUserCredentialsException;
import mk.ukim.finki.bazi_proekt.avio_kompanija.repository.UserRepository;
import mk.ukim.finki.bazi_proekt.avio_kompanija.service.interfaces.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        if (username==null || username.isEmpty() || password==null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        return userRepository.findByUsernameAndPassword(username,
                password).orElseThrow( InvalidUserCredentialsException::new);
    }

}
