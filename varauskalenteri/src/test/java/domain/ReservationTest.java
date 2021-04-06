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
 * Testaa luokkaa ReservationTest.
 * @author Matias
 */
public class ReservationTest {
    
    Reservation reservation;
    
    public ReservationTest() {
        
    }

    @Before
    public void setUp() throws Exception {
        this.reservation = new Reservation(12345678, "11-12", 6, "huhtikuu", 2021, 99999999);
    }
    
    /**
     * Testaa Reservation-oliota niin, että olion attribuutit luodaan konstruktorissa.
     */
    @Test
    public void reservationTestWithArgs() {
        String expected = "12345678|11-12|6|huhtikuu|2021|99999999";
        assertEquals(this.reservation.toString(), expected);
    }
    
    /**
     * Testaa Reservationo-oliota niin, että olion attribuutit määritellään olion luonnin jälkeen.
     */
    @Test
    public void resservationTestWithoutArgs() {
        this.reservation.setId(12345678);
        this.reservation.setDay(6);
        this.reservation.setTime("11-12");
        this.reservation.setMounth("huhtikuu");
        this.reservation.setYear(2021);
        this.reservation.setUserId(99999999);
        
        Integer id = this.reservation.getId();
        Integer day = this.reservation.getDay();
        String time = this.reservation.getTime();
        String mounth = this.reservation.getMounth();
        Integer year = this.reservation.getYear();
        Integer userId = this.reservation.getUserId();
        
        String s = id + "|" + time + "|" + day + "|" + mounth  + "|" + year + "|" + userId; 
        String expected = "12345678|11-12|6|huhtikuu|2021|99999999";
        
        assertEquals(s, expected);
    }
}

