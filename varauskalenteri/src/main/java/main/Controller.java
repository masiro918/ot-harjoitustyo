
package main;

import domain.*;
import java.util.ArrayList;

/**
 * Tällä luokalla tehdään ne toimenpiteet, joita kutsutaan käyttöliittymästä.
 * @author Matias Siro
 */
public class Controller {
    
    /**
     * Luo uuden käyttäjän tietokantaan.
     * @param user user-olio, jossa id-arvo on null
     */
    public static void newUser(User user) throws Exception {
        DbService dbService = new DbService();
        
        // tarkistetaan, että käyttäjätunnus on vapaa
        try {
            ArrayList<String> results = dbService.getDataUser("select * from user where username = '" + user.getUsername() + "';");
            if (results.size() > 0) throw new Exception("Käyttäjätunnus on jo olemassa! Valitse uusi.");
        } catch (Exception e) {
            throw new Exception("Tapahtui poikkeus luodessa uutta käyttäjätunnusta: " + e.getMessage());
        } finally {
            dbService.closeService();
        }
        
        
        dbService.addUser(user);
        dbService.closeService();
    }
    
    /**
     * Lisää uuden varauksen tietokantaan.
     * @param reservation reservation olio, jossa id-arvo on null
     */
    public static void newReservation(Reservation reservation) {
        
    }
    
    /**
     * Poistaa varauksen tietokannasta.
     * @param userType käyttäjätyyppi (vain admin voi tehdä tämän toimenpiteen)
     * @param id poistettavan varauksen id
     * @throws Exception 
     */
    public static void delReservation(String userType, int id) throws Exception {
        if (userType.equals("admin") == false) throw new Exception("Vain admin voi poistaa varauksen!");
    }
    
    /**
     * Hakee tietyn päivän kaikki varauksen.
     * @param day päivä, jolta varaukset haetaan
     * @param year vuosi, jolta varaukset haetaan
     * @param mounth kuukausi, jolta varaukset haketaan
     * @throws Exception 
     */
    public static void getReservations(int day, int year, String mounth) throws Exception {
        
    }
}
