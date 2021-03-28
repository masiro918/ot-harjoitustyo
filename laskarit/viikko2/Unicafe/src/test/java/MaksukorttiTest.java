/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.unicafe.Maksukortti;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author
 */
public class MaksukorttiTest {
    
    Maksukortti maksukortti;
    
    public MaksukorttiTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        maksukortti = new Maksukortti(1000);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void kortinSaldoOnAlussaOikein() {
        assertEquals("saldo: 10.0", maksukortti.toString());
    }
    
    @Test
    public void rahanLataaminenKasvattaaSaldoa() {
        maksukortti.lataaRahaa(550);
        assertEquals("saldo: 15.50", maksukortti.toString());
    }
    
    @Test
    public void rahanOttaminenToimii() {
        maksukortti.otaRahaa(500);
        assertEquals("saldo: 5.0", maksukortti.toString());
    }
    
    @Test
    public void rahaaEiOtetaJosSaldoEiRiita() {
        maksukortti.otaRahaa(1200);
        assertEquals("saldo: 10.0", maksukortti.toString());
    }
    
    @Test
    public void rahaaOnTarpeeksi() {
        boolean rahaaTarpeeksi = maksukortti.otaRahaa(500);
        assertTrue(rahaaTarpeeksi);
    }
    
    @Test
    public void rahaaEiOleTarpeeksi() {
        boolean rahaaTarpeeksi = maksukortti.otaRahaa(1500);
        assertFalse(rahaaTarpeeksi);
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
