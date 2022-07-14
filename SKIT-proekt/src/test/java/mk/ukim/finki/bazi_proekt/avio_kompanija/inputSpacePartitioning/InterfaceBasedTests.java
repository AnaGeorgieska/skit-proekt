package mk.ukim.finki.bazi_proekt.avio_kompanija.inputSpacePartitioning;

import mk.ukim.finki.bazi_proekt.avio_kompanija.model.exceptions.InvalidArgumentsException;
import mk.ukim.finki.bazi_proekt.avio_kompanija.model.exceptions.InvalidUserCredentialsException;
import mk.ukim.finki.bazi_proekt.avio_kompanija.service.interfaces.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class InterfaceBasedTests {
    @Autowired
    AuthService authService;
    private String username1Null;
    private String passwordNull;
    private String usernameEmpty;
    private String passwordEmpty;
    private String username;
    private String password;

    @BeforeEach
    public void init()
    {
        username1Null=null;
        passwordNull=null;
        usernameEmpty="";
        passwordEmpty="";
        username="username";
        password="password";
    }

    // There are 4 characteristics:
    // 1 - username is null
    // 2 - username is empty
    // 3 - password is null
    // 4 - password is empty

    @Test
    public void test_base_FFFF()
    {
        assertNotNull(username);
        assertNotEquals(username, "");
        assertNotNull(password);
        assertNotEquals(password, "");
        assertThrows(InvalidUserCredentialsException.class,()-> authService.login(username, password));
    }
    @Test
    public void test_TFFF()
    {
        assertNull(username1Null);
        assertNotEquals(username1Null, "");
        assertNotNull(password);
        assertNotEquals(password, "");
        assertThrows(InvalidArgumentsException.class,()-> authService.login(username1Null, password));
    }
    @Test
    public void test_FTFF()
    {
        assertNotNull(usernameEmpty);
        assertEquals(usernameEmpty, "");
        assertNotNull(password);
        assertNotEquals(password, "");
        assertThrows(InvalidArgumentsException.class,()-> authService.login(usernameEmpty, password));
    }
    @Test
    public void test_FFTF()
    {
        assertNotNull(username);
        assertNotEquals(username, "");
        assertNull(passwordNull);
        assertNotEquals(passwordNull, "");
        assertThrows(InvalidArgumentsException.class,()-> authService.login(username, passwordNull));
    }
    @Test
    public void test_FFFT()
    {
        assertNotNull(username);
        assertNotEquals(username, "");
        assertNotNull(passwordEmpty);
        assertEquals(passwordEmpty, "");
        assertThrows(InvalidArgumentsException.class,()-> authService.login(username, passwordEmpty));
    }

}