/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.unicafe.Kassapaate;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author user
 */
public class KassapaateTest {
    
    Kassapaate kassapaate;
    
    public KassapaateTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        kassapaate = new Kassapaate();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void rahamaaraOikeaJaLounaidenMaaraOikea() {
        int rahaa = kassapaate.kassassaRahaa();
        int edullisiaLounaita = kassapaate.edullisiaLounaitaMyyty();
        int maukkauitaLounaita = kassapaate.maukkaitaLounaitaMyyty();
        
        assertTrue(rahaa == 100000);
        assertTrue(edullisiaLounaita == 0);
        assertTrue(maukkauitaLounaita == 0);
    }
    
    @Test
    public void kateisostoToimiiEdullisilleLounailleTasaraha() {
        kassapaate.syoEdullisesti(240);
        
        int edullisiaLounaita = 1;
        int rahaa = 100000 + 240;
        
        assertTrue(kassapaate.edullisiaLounaitaMyyty() == edullisiaLounaita);
        assertTrue(kassapaate.kassassaRahaa() == rahaa);
    }
    
    @Test
    public void kateisostoToimiiEdullisilleLounailleVaihtoraha() {
        int vaihtoraha = kassapaate.syoEdullisesti(300);
        
        int edullisiaLounaita = 1;
        int rahaa = 100000 + 240;
        
        assertTrue(kassapaate.edullisiaLounaitaMyyty() == edullisiaLounaita);
        assertTrue(kassapaate.kassassaRahaa() == rahaa);
        assertTrue(vaihtoraha == 60);
    }
    
    @Test
    public void kateisostoToimiiMaukkailleLounailleTasaraha() {
        kassapaate.syoMaukkaasti(400);
        
        int maukkaitaLounaita = 1;
        int rahaa = 100000 + 400;
        
        assertTrue(kassapaate.maukkaitaLounaitaMyyty() == maukkaitaLounaita);
        assertTrue(kassapaate.kassassaRahaa() == rahaa);
    }
    
    @Test
    public void kateisostoToimiiMaukkailleLounailleVaihtoraha() {
        int vaihtoraha = kassapaate.syoMaukkaasti(530);
        
        int maukkaitaLounaita = 1;
        int rahaa = 100000 + 400;
        
        assertTrue(kassapaate.maukkaitaLounaitaMyyty() == maukkaitaLounaita);
        assertTrue(kassapaate.kassassaRahaa() == rahaa);
        assertTrue(vaihtoraha == 130);
    }
    
    @Test
    public void kateisostoEiTarpeeksiRahaaSyoEdullisesti() {
        int vaihtoraha = kassapaate.syoEdullisesti(100);
        
        assertTrue(vaihtoraha == 100);
        assertTrue(kassapaate.edullisiaLounaitaMyyty() == 0);
    }
    
    @Test
    public void kateisostaEitarpeeksiRahaaSyoMaukkaasti() {
        int vaihtoraha = kassapaate.syoMaukkaasti(100);
        
        assertTrue(vaihtoraha == 100);
        assertTrue(kassapaate.maukkaitaLounaitaMyyty() == 0);
    }
}
