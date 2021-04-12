
package main;

import domain.*;
import java.util.ArrayList;
import java.security.*;

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
            dbService.addUser(user);
        } catch (Exception e) {
            throw new Exception("Tapahtui poikkeus luodessa uutta käyttäjätunnusta: " + e.getMessage());
        } finally {
            dbService.closeService();
        }
    }
    
    /**
     * Lisää uuden varauksen tietokantaan.
     * @param reservation reservation olio, jossa id-arvo on null
     */
    public static void newReservation(Reservation reservation) throws Exception {
        DbService dbService = new DbService();
        
        // TODO: tarkista, että varausta ei ole jo tietokannassa kyseiseltä ajalta
        
        try {
            dbService.addReservation(reservation);
        } catch (Exception e) {
            throw new Exception("Tapahtui poikkeus tehdessä varausta tietokantaan: " + e.getMessage());
        } finally {
            dbService.closeService();
        }
    }
    
    /**
     * Poistaa varauksen tietokannasta.
     * @param userType käyttäjätyyppi (vain admin voi tehdä tämän toimenpiteen)
     * @param id poistettavan varauksen id
     * @throws Exception 
     */
    public static void delReservation(String userType, int id) throws Exception {
        if (userType.equals("admin") == false) throw new Exception("Vain admin voi poistaa varauksen!");
        
        DbService dbService = new DbService();
        
        try {
            dbService.delReservation(id);
        } catch (Exception e) {
            throw new Exception("Tapahtui poikkeus poistettaessa varausta tietokannasta: " + e.getMessage());
        } finally {
            dbService.closeService();
        }
    }
    
    /**
     * Hakee tietyn päivän kaikki varauksen.
     * @param day päivä, jolta varaukset haetaan
     * @param year vuosi, jolta varaukset haetaan
     * @param mounth kuukausi, jolta varaukset haketaan
     * @throws Exception 
     */
    public static ArrayList<Reservation> getReservations(int day, int year, String mounth) throws Exception {
        throw new Exception("Ei toimi vielä!");
    }
    
    /**
     * Hakee kaikki käyttäjät tietokannasta ja palauttaa ne User-oliona.
     * @return kaikki käyttäjät
     * @throws Exception 
     */
    public static ArrayList<User> getUsers() throws Exception {
        DbService dbService = new DbService();
        
        ArrayList<String> userList = dbService.getDataUser("select * from user;");
        ArrayList<User> users = new ArrayList<>();
        
        for (String userStr : userList) {
            String[] blocks = userStr.split("\\|");
            Integer id = Integer.parseInt(blocks[0]);
            String username = blocks[1];
            String password = blocks[2];
            String role = blocks[3];
            
            User user = new User();
            user.setId(id);
            user.setUsername(username);
            user.setPassword(password);
            user.setRole(role);
            
            users.add(user);
        }
        
        dbService.closeService();
        
        return users;
    }
    
    /**
     * Tyjentää tietokannan kaikesta sisällöstä.
     */
    public static void deleteAllDataFromTables() throws Exception {
        DbService dbService = new DbService();
        dbService.destroyTables();
        dbService.createTablesWithoutChecking();
        dbService.closeService();
    }
    
    /**
     * Luo salasanasta hash-arvon. Apuja hash:n generoimiseen on haettu täältä: 
     * https://stackoverflow.com/questions/415953/how-can-i-generate-an-md5-hash
     * @param password salasana, josta hash luodaan
     * @return luotu hash
     * @throws Exception 
     */
    public static String createHash(String password) throws Exception {
        byte[] passwordBytes = password.getBytes("UTF-8");
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        byte[] hashBytes = messageDigest.digest(passwordBytes);
        
        return new String(hashBytes);
    }
    
    public static void main(String[] args) {
    }
}
