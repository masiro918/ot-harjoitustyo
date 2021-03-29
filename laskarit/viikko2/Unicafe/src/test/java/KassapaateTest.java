/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.unicafe.Kassapaate;
import com.mycompany.unicafe.Maksukortti;
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
    Maksukortti maksukortti;
    
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
        maksukortti = new Maksukortti(10000);
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
    
    @Test
    public void korttiostoSyoEdullisestiRahaaOnKortilla() {
        // pitäisi palauttaa true eli oston pitäisi onnistua, koska kortilla on tarpeeksi rahaa
        boolean b = kassapaate.syoEdullisesti(maksukortti);
        
        // koska osto onnituu, myytyjen lounaiden määrän pitäisi kasvaa
        int lounaita = kassapaate.edullisiaLounaitaMyyty();
        int rahaaKortilla = maksukortti.saldo();
        
        // kortilla pitäisi olla nyt rahaa 10000 - 240
        assertTrue(b);
        assertTrue(lounaita == 1);
        assertTrue(rahaaKortilla == 9760);
    }
    
    @Test
    public void korttiostoSyoMaukkaastiRahaaOnKortilla() {
        // pitäisi palauttaa true eli oston pitäisi onnistua, koska kortilla on tarpeeksi rahaa
        boolean b = kassapaate.syoMaukkaasti(maksukortti);
        
        // koska osto onnituu, myytyjen lounaiden määrän pitäisi kasvaa
        int lounaita = kassapaate.maukkaitaLounaitaMyyty();
        int rahaaKortilla = maksukortti.saldo();
        
        // kortilla pitäisi olla nyt rahaa 10000 - 400
        assertTrue(b);
        assertTrue(lounaita == 1);
        assertTrue(rahaaKortilla == 9600);
    }
    
    @Test
    public void korttiOstoSyoEdullisestiRahaaEiOleTarpeeksi() {
        // pitäisi palautta false eli osto ei onnistua, koska rahaa ei ole kortilla tarpeeksi
        // luodaan uusi Maksukortti-olio
        maksukortti = new Maksukortti(1000);
        
        kassapaate.syoMaukkaasti(maksukortti);
        kassapaate.syoMaukkaasti(maksukortti);
        
        // pitäisi olla false
        boolean b = kassapaate.syoMaukkaasti(maksukortti);
        
        // tarkistetaan vielä, että kassassa on myytynä 2kpl maukkaita lounaita
        int lounaita = kassapaate.maukkaitaLounaitaMyyty();
        
        // kassassa pitäisi olla 100000 rahaa, koska ollaan tehty vain korttiostoja
        
        assertTrue(kassapaate.kassassaRahaa() == 100000);
        assertTrue(b == false);
        assertTrue(lounaita == 2);
    }
    
    @Test
    public void lataaRahaaKortilleToimii() {
        kassapaate.lataaRahaaKortille(maksukortti, 500);
        assertTrue(kassapaate.kassassaRahaa() == 100500);
    }
    
    @Test
    public void lataaRahaaKortilleNollalla() {
        kassapaate.lataaRahaaKortille(maksukortti, 0);
        assertTrue(kassapaate.kassassaRahaa() == 100000);
    }
    
    @Test
    public void lataaRahaaKortilleNegSumma() {
        kassapaate.lataaRahaaKortille(maksukortti, -2);
        assertTrue(kassapaate.kassassaRahaa() == 100000);
    }
}
