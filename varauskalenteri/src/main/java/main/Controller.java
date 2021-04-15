
package main;

import domain.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.security.*;
import java.util.Scanner;

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
     * @return haetut varaukset
     * @throws Exception 
     */
    public static ArrayList<Reservation> getReservations(int day, int year, String mounth) throws Exception {
        ArrayList<Reservation> reservations = new ArrayList<>();
        
        String sql = "select * from reservation where day = " + day + " and  year = " + year + " and mounth = '" + mounth + "';";
        
        DbService dbService = new DbService();
        ArrayList<String> results = dbService.getDataReservation(sql);
        dbService.closeService();
        
        for (String row : results) {
            String[] blocks = row.split("\\|");
            
            Integer rowId = Integer.parseInt(blocks[0]);
            String rowTime = blocks[1];
            Integer rowDay = Integer.parseInt(blocks[2]);
            String rowMounth = blocks[3];
            Integer rowYear = Integer.parseInt(blocks[4]);
            Integer rowUserId = Integer.parseInt(blocks[5]);
            
            
            
            Reservation reservation = new Reservation();
            reservation.setDay(rowDay);
            reservation.setId(rowId);
            reservation.setUserId(rowUserId);
            reservation.setYear(year);
            reservation.setMounth(rowMounth);
            reservation.setTime(rowTime);
            
            reservations.add(reservation);
        }
        
        return reservations;
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
     * Luo salasanasta hash-arvon. Algoritmi hash:n generoimiseen on haettu täältä: 
     * https://stackoverflow.com/questions/415953/how-can-i-generate-an-md5-hash
     * @param password salasana, josta hash luodaan
     * @return luotu hash
     * @throws Exception 
     */
    public static String createHash(String password) throws Exception {
        byte[] passwordBytes = password.getBytes("UTF-8");
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(passwordBytes);
                
        return String.format("%032x", new BigInteger(1, messageDigest.digest()));
    }
    
    /**
     * Luo tietokannan, olettaenm, että sitä ei ole olemassa.
     * @throws Exception 
     */
    public static void createDatabase() throws Exception {
        DbService dbService = new DbService();
        dbService.createTablesWithoutChecking();
        dbService.closeService();
    }
    
    /**
     * Tarkistaa, onko haettua varausta olemassa, eli onko varausta ajalle, joka määritellään parametreissa.
     * @param mounth varauksen kuukausi
     * @param year varauksen vuosi
     * @param day varauksen päivä
     * @param time varauksen kellonaika
     * @return true, jos on varaus olemassa, muulloin false
     */
    public static boolean ifReservationExists(String mounth, int year, int day, String time) throws Exception {
        String sql = "select count(*) from reservation where day = " + day + " and  year = " + year + " and mounth = '" + mounth + "' and time = '" + time + "';";
        
        System.out.println(sql);
        DbService dbService = new DbService();
        ArrayList<String> results = dbService.getDataReservation(sql);
        dbService.closeService();
        
        System.out.println("answer: " + results.get(0));
        
        return false;
    }
    
    public static void main(String[] args) throws Exception {
        //ifReservationExists("huhtikuu", 2021, 15, "11-12");
        DbService dbs = new DbService();
        int count = dbs.getCountReservation(15, 2021, "huhtikuu", "11-12");
        System.out.println("count: " + count);
    }
}
