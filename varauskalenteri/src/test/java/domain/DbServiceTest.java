package domain;

import domain.User;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testaa luokkaa DbService.
 * @author Matias
 */
public class DbServiceTest {
    
    DbService dbService;
    
    public DbServiceTest() {
        
    }

    @Before
    public void setUp() throws Exception {
        this.dbService = new DbService();
        this.dbService.destroyTables();
        this.dbService.createTablesWithoutChecking();
    }
    
    /**
     * Testaa, että user-olion tallennus tietokantaan onnistuu.
     * @throws Exception 
     */
    @Test
    public void addUserTest() throws Exception {
        User user = new User();
        user.setUsername("kalle");
        user.setPassword("salasana");
        user.setRole("basic");
        
        this.dbService.addUser(user);
        
        ArrayList<String> userTable = this.dbService.printTableUser();
        this.dbService.closeService();
        
        String user1 = userTable.get(0);        
        // poistetaan ensimmäinen tietue, koska se on arvottu id
        
        String[] blocks = user1.split("\\|");
        
        assertEquals("kalle", blocks[1]);
        assertEquals("salasana", blocks[2]);
        assertEquals("basic", blocks[3]);
    }
    
    /**
     * Testaa, että id-generaattori tuottaa luvun väliltä [0, 99999999].
     * @throws Exception 
     */
    @Test
    public void newIdUserTest() throws Exception {
        Integer id = this.dbService.newId("user");
        
        if (id >= 0 && id <= 99999999) {
            this.dbService.closeService();
            assertTrue(true);
        } else {
            this.dbService.closeService();
            assertTrue(false);
        }
    }
    
    /**
     * Testaa, id-generaattori tuottaa luvun väliltä [0, 99999999].
     * @throws Exception 
     */
    @Test
    public void newIdReservationTest() throws Exception {
        Integer id = this.dbService.newId("reservation");
        
        if (id >= 0 && id <= 99999999) {
            this.dbService.closeService();
            assertTrue(true);
        } else {
            this.dbService.closeService();
            assertTrue(false);
        }
    }

    @Test
    public void addReservationTest() throws Exception  {
        Reservation reservation = new Reservation();
        reservation.setDay(7);
        reservation.setMounth("huhtikuu");
        reservation.setUserId(12345678);
        reservation.setTime("11-12");
        reservation.setYear(2021);
        
        this.dbService.addReservation(reservation);
        
        ArrayList<String> reservationTable = this.dbService.printReservationTable();
        this.dbService.closeService();
        
        String reservation1 = reservationTable.get(0);
        
        // poistetaan ensimmäinen tietue, koska se on arvottu id
        
        String[] blocks = reservation1.split("\\|");
        
        //for (String s : blocks) System.out.println(s);
        
        assertEquals("11-12", blocks[1]);
        assertEquals("7", blocks[2]);
        assertEquals("huhtikuu", blocks[3]);
        assertEquals("2021", blocks[4]);
        assertEquals("12345678", blocks[5]);
    }
    
    /**
     * Testaa metodin idExistsUser toimintaa.
     * @throws Exception 
     */
    @Test
    public void idExistsUserTest() throws Exception {
        // luodaan uusi user-olio
        User user = new User(null, "testitimo", "salasana", "basic");
        this.dbService.addUser(user);
        
        ArrayList<String> results = this.dbService.getDataUser("select * from user;");
        
        String user1 = results.get(0);
        
        String[] blocks = user1.split("\\|");
        
        Integer id = Integer.parseInt(blocks[0]);
        
        assertFalse(this.dbService.idExistsUser(id));
        assertTrue(this.dbService.idExistsUser(1234569));
        
        this.dbService.closeService();
    }
    
    /**
     * Testaa metodin idExistsReservation toimintaa.
     * @throws Exception 
     */
    @Test
    public void idExistsReservationTest() throws Exception {
        // luodaan uusi reservation-olio
        Reservation reservation = new Reservation();
        reservation.setDay(7);
        reservation.setMounth("huhtikuu");
        reservation.setUserId(12345678);
        reservation.setTime("11-12");
        reservation.setYear(2021);
        
        this.dbService.addReservation(reservation);
        
        ArrayList<String> results = this.dbService.getDataReservation("select * from reservation;");
        
        String reservation1 = results.get(0);
        
        String[] blocks = reservation1.split("\\|");
        
        Integer id = Integer.parseInt(blocks[0]);
        
        assertFalse(this.dbService.idExistsReservation(id));
        assertTrue(this.dbService.idExistsReservation(1234569));
        
        this.dbService.closeService();
    }
    
    @Test
    public void delReservationTest() throws Exception {
        // luodaan uusi reservation-olio
        Reservation reservation = new Reservation();
        reservation.setDay(7);
        reservation.setMounth("huhtikuu");
        reservation.setUserId(12345678);
        reservation.setTime("11-12");
        reservation.setYear(2021);
        
        this.dbService.addReservation(reservation);
        
        ArrayList<String> results = this.dbService.getDataReservation("select * from reservation;");
        
        String reservation1 = results.get(0);
        
        String[] blocks = reservation1.split("\\|");
        
        
        // poistetaan luotu alkio id:n perusteella
        Integer id = Integer.parseInt(blocks[0]);
        this.dbService.delReservation(id);
        
        results = this.dbService.getDataReservation("select * from reservation;");
        
        assertEquals("0", results.size() + "");
    }
}

