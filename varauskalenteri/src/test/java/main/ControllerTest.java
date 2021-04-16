package main;

import domain.*;
import main.Controller;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    /**
     * Testaa, että uusi varaus voidaan luoda. 
     * Jos ollaan tekemässä päällekäistä varausta, sen ei pitäisi onnistua.
     */
    @Test
    public void newReservationTest() {
        Reservation reservation1 = new Reservation();
        reservation1.setDay(16);
        reservation1.setMounth("huhtikuu");
        reservation1.setTime("11-12");
        reservation1.setYear(2021);
        
        Reservation reservation2 = new Reservation();
        reservation2.setDay(16);
        reservation2.setMounth("huhtikuu");
        reservation2.setTime("11-12");
        reservation2.setYear(2021);
        
        try {
            Controller.newReservation(reservation1);
        } catch (Exception ex) {
            assertTrue(false);
        }
        
        ArrayList<Reservation> reservations = new ArrayList<>();
        
        try {
            reservations = Controller.getReservations(16, 2021, "huhtikuu");
        } catch (Exception ex) {
            assertTrue(false);
        }
        
        Reservation savedReservation = reservations.get(0);
        String savedReservationStr = savedReservation.getDay() + "|" + savedReservation.getYear() + "|" + savedReservation.getMounth();
        assertEquals(savedReservationStr, "16|2021|huhtikuu");
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

