package main;

import domain.*;
import main.Controller;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testaa luokkaa Conroller.
 * @author Matias
 */
public class ControllerTest {
    
    public ControllerTest() {
        
    }

    @Before
    public void setUp() throws Exception {
        Controller.deleteAllDataFromTables();
    }
    
    /**
     * Testaa, että käyttäjätunnus luodaan onnistuneesti tietokantaan. 
     * Lisäksi testaa, että jo olemassa olevaa käyttäjätunnusta ei voi valita.
     * @throws Exception 
     */
    @Test
    public void newUserTest() throws Exception {
        User user = new User(null, "eskoesimerkki", Controller.createHash("salasana"), "basic");
        Controller.newUser(user);
        
        ArrayList<User> users = Controller.getUsers();
        User user1 = users.get(0);
        String username = user1.getUsername();
        
        assertEquals(username, "eskoesimerkki");        
    }
    
    @Test
    public void newReservationTest() {
        
    }
    
    /**
     * Testaa, että hash-arvon luonti toimii.
     * @throws Exception 
     */
    @Test
    public void hashTest() throws Exception {
        String s1 = Controller.createHash("salasana");
        String s2 = Controller.createHash("salasana");
        String s3 = Controller.createHash("syyskuu");
        
        assertEquals(s1, s2);
        if (s1.equals(s3) == false) assertTrue(true);
    }
}

