package varauskalenteri.domain;

import varauskalenteri.domain.*;
import varauskalenteri.domain.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testaa luokkaa User.
 * @author Matias Siro
 */
public class UserTest {
    
    User user;

    public UserTest() {
        
    }

    @Before
    public void setUp() {
        user = new User();
    }

    /**
     * Testi, jossa luodaan olion attribuutit konstruktorissa.
     */
    @Test
    public void newUserWithParameters() {
        User userParam = new User(12345678, "testuser", "a89c73nv4wfc", "basic");
        
        assertEquals("a89c73nv4wfc", userParam.getPassword());
        assertEquals("12345678|testuser|basic", userParam.toString());
    }
    
    /**
     * Testi, jossa luodaan olion attribuutit settereillä.
     */
    @Test
    public void newUserWithoutParameters() {
        user.setId(12345678);
        user.setUsername("testuser");
        user.setPassword("a89c73nv4wfc");
        user.setRole("admin");
        
        Integer id = user.getId();
        String username = user.getUsername();
        String password = user.getPassword();
        String role = user.getRole();
        
        String s = id + "|" + username + "|" + role;
        
        assertEquals("a89c73nv4wfc", user.getPassword());
        assertEquals("12345678|testuser|admin", user.toString());
    }
}

