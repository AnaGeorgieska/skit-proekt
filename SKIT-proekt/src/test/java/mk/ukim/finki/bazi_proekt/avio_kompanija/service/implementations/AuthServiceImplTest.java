package mk.ukim.finki.bazi_proekt.avio_kompanija.service.implementations;

import mk.ukim.finki.bazi_proekt.avio_kompanija.model.Role;
import mk.ukim.finki.bazi_proekt.avio_kompanija.model.User;
import mk.ukim.finki.bazi_proekt.avio_kompanija.model.exceptions.InvalidArgumentsException;
import mk.ukim.finki.bazi_proekt.avio_kompanija.model.exceptions.InvalidUserCredentialsException;
import mk.ukim.finki.bazi_proekt.avio_kompanija.model.exceptions.InvalidUsernameOrPasswordException;
import mk.ukim.finki.bazi_proekt.avio_kompanija.repository.UserRepository;
import mk.ukim.finki.bazi_proekt.avio_kompanija.service.interfaces.AuthService;
import mk.ukim.finki.bazi_proekt.avio_kompanija.service.interfaces.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class AuthServiceImplTest {
    @Mock
    private UserRepository userRepository;
    private AuthService service;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        User user = new User("username", "password", "name", "surname", Role.ROLE_USER);

        this.service = Mockito.spy(new AuthServiceImpl(this.userRepository));
    }

    @org.junit.Test
    public void testNullUsername() {
        Assert.assertThrows("InvalidArgumentException expected",
                InvalidArgumentsException.class,
                () -> this.service.login(null, "password"));
        Mockito.verify(this.service).login(null, "password");
    }

    @org.junit.Test
    public void testEmptyUsername() {
        String username = "";
        Assert.assertThrows("InvalidArgumentException expected",
                InvalidArgumentsException.class,
                () -> this.service.login(username, "password"));
        Mockito.verify(this.service).login(username, "password");
    }

    @org.junit.Test
    public void testEmptyPassword() {
        String username = "username";
        String password = "";
        Assert.assertThrows("InvalidArgumentException expected",
                InvalidArgumentsException.class,
                () -> this.service.login(username, password));
        Mockito.verify(this.service).login(username, password);
    }

    @Test
    public void testNullPassword() {
        String username = "username";
        String password = null;
        Assert.assertThrows("InvalidArgumentException expected",
                InvalidArgumentsException.class,
                () -> this.service.login(username, password));
        Mockito.verify(this.service).login(username, password);
    }

    @Test
    public void testFindByUsernameAndPassword()
    {
        User user = new User("username", "password", "name", "surname", Role.ROLE_USER);
        Mockito.when(this.userRepository.findByUsernameAndPassword("username", "password")).thenReturn(Optional.of(user));

        Assert.assertEquals(user, this.service.login("username", "password"));
        Mockito.verify(this.service).login("username", "password");
    }

    @Test
    public void testInvalidUserCredentials()
    {
        User user = new User("username", "password", "name", "surname", Role.ROLE_USER);

        Assert.assertThrows("InvalidUserCredentialsException expected",
                InvalidUserCredentialsException.class,
                ()-> this.service.login("username1", "password"));
        Mockito.verify(this.service).login("username1", "password");
    }


}