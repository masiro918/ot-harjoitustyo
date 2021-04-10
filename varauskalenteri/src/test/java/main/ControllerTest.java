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
        User user = new User(null, "eskoesimerkki", "salasana", "basic");
        Controller.newUser(user);
        
        
    }
}

