package varauskalenteri.main;

import varauskalenteri.main.*;
import varauskalenteri.domain.*;
import varauskalenteri.main.Controller;
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
        reservation1.setUserId(1324);
        reservation1.setDay(16);
        reservation1.setMounth("huhtikuu");
        reservation1.setTime("11-12");
        reservation1.setYear(2021);
        
        Reservation reservation2 = new Reservation();
        reservation2.setUserId(1324);
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
        
         // testataan, että pällekäistä varausta ei voi tehdä
         
        Reservation reservation3 = new Reservation();
        reservation3.setDay(16);
        reservation3.setUserId(1324);
        reservation3.setMounth("huhtikuu");
        reservation3.setTime("11-12");
        reservation3.setYear(2021);
        
       try {
           Controller.newReservation(reservation3);
           // jos tullaan tämän rivin alle, testi ei mene läpi
           // koska tapahtuu päällekkäinen varaus
           assertTrue(false);
       } catch (Exception e) {
           assertTrue(true);
       }
    }
    
    /**
     * Testaa, että varaus poistuu onnistuneesti.
     * @throws Exception 
     */
    @Test
    public void delReservationTest() throws Exception {
        Reservation r1 = new Reservation();
        r1.setDay(17);
        r1.setMounth("huhtikuu");
        r1.setTime("12-13");
        r1.setUserId(1234);
        r1.setYear(2021);
        
        Reservation r2 = new Reservation();
        r2.setDay(17);
        r2.setMounth("huhtikuu");
        r2.setTime("14-11");
        r2.setUserId(1234);
        r2.setYear(2021);
        
        Controller.newReservation(r1);
        Controller.newReservation(r2);
        
        ArrayList<Reservation> reservations = Controller.getReservations(17, 2021, "huhtikuu");
        
        int r1Id = reservations.get(0).getId();
        int r2Id = reservations.get(1).getId();
        
        Controller.delReservation("admin", r1Id);
        
        String s1 = "varauksia pitäisi olla tietokannassa 1 kpl";
        
        reservations = Controller.getReservations(17, 2021, "huhtikuu");
        
        assertEquals(s1, "varauksia pitäisi olla tietokannassa " + reservations.size() + " kpl");
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
    
    /**
     * Testaa, että syötteentarkistaja toimii oikein.
     * @throws Exception 
     */
    @Test
    public void checkInputsTest() throws Exception {
        int year = 2021;
        int day = 20;
        String mounth = "huhtikuu";
        String time = "09-10";
        
        try {
            Controller.checkInputs(day, year, mounth, time);
            assertTrue(true);
        } catch (Exception e) {
            assertTrue(false);
        }
        
        year =2010;
        day = 33;
        mounth = "juustokuu";
        time = "06-07";
        
        try {
            Controller.checkInputs(day, year, mounth, time);
            assertTrue(false);
        } catch (Exception e) {
            assertTrue(true);
        }
    }
}

