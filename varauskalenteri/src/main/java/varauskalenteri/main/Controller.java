
package varauskalenteri.main;

import varauskalenteri.main.*;
import varauskalenteri.domain.*;
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
            
            if (results.size() > 0) {
                throw new Exception("Käyttäjätunnus on jo olemassa! Valitse uusi.");
            }
            
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
        if (Controller.ifReservationExists(reservation.getMounth(), reservation.getYear(), reservation.getDay(), reservation.getTime())) {
            throw new Exception("Tapahtui poikkeus tehdessä varausta tietokantaan: varaus on jo olemassa!");
        }
        
        DbService dbService = new DbService();
        
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
        if (userType.equals("admin") == false) {
            throw new Exception("Vain admin voi poistaa varauksen!");
        }
        
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
     * Poistaa varauksen tietokannasta
     * @param year vuosi
     * @param mounth kuukausi
     * @param day päivä
     * @param time aika
     * @throws Exception
     */
    public static void delReservations(String userType, int year, String mounth, int day, String time) throws Exception {
        // haetaan poistettavan varauksen id
        try {
            int id = Controller.getReservationId(year, mounth, day, time);
            Controller.delReservation(userType, id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
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
        
        DbService dbService = new DbService();
        int results = dbService.getCountReservation(day, year, mounth, time);
        dbService.closeService();
        
        
        int reservations = results;
        
        if (reservations > 0) {
            return true;
        }
        
        return false;
    }

    /**
     * Tarkistaa, että varauksen syöte on oikean kaltainen.
     * @param day päivä
     * @param year vuosi
     * @param mounth kuukausi
     * @param time aika
     * @throws java.lang.Exception
     */
    public static void checkInputs(int day, int year, String mounth, String time) throws Exception {
        // päivä
        if (day < 0 || day > 31) {
            throw new Exception("Päivämäärä ei ole sallittu!");
        }
        
        // vuosi
        if (year < 2021) {
            throw new Exception("Vuosi ei ole sallittu!");
        }
        
        // kuukausi
        while (true) {
            
            if (mounth.equals("tammikuu")) {
                break;
            }
            
            if (mounth.equals("helmikuu")) {
                break;
            }
            
            if (mounth.equals("maaliskuu")) {
                break;
            }
            
            if (mounth.equals("huhtikuu")) {
                break;
            }
            
            if (mounth.equals("toukokuu")) {
                break;
            }
            
            if (mounth.equals("kesakuu")) {
                break;
            }
            
            if (mounth.equals("heinakuu")) {
                break;
            }
            
            if (mounth.equals("elokuu")) {
                break;
            }
            
            if (mounth.equals("syyskuu")) {
                break;
            }
            
            if (mounth.equals("lokakuu")) {
                break;
            }
            
            if (mounth.equals("marraskuu")) {
                break;
            }
            
            if (mounth.equals("joulukuu")) {
                break;
            }
            
            throw new Exception("Kuukausi ei ole sallittu!");
        }
        
        // kellonaika
        while (true) {
            if (time.equals("08-09")) {
                break;
            }
            
            if (time.equals("09-10")) {
                break;
            }
            
            if (time.equals("10-11")) {
                break;
            }
            
            if (time.equals("11-12")) {
                break;
            }
            
            if (time.equals("12-13")) {
                break;
            }
            
            if (time.equals("13-14")) {
                break;
            }
            
            if (time.equals("14-15")) {
                break;
            }
            
            if (time.equals("15-16")) {
                break;
            }
            
            throw new Exception("Kellonaika ei ole sallittu!");
        }
    }
    
    /**
     * Tarkistaa, että varauksen syöte on oikean kaltainen.Kellonaika ei ole mukana.
     * @param day päivä
     * @param year vuosi
     * @param mounth kuukausi
     * @throws java.lang.Exception
     */
    public static void checkInputs(int day, int year, String mounth) throws Exception {
        // päivä
        if (day < 0 && day > 31) {
            throw new Exception("Päivämäärä ei ole sallittu!");
        }
        
        // vuosi
        if (year < 2021) {
            throw new Exception("Vuosi ei ole sallittu!");
        }
        
        // kuukausi
        while (true) {
            if (mounth.equals("tammikuu")) {
                break;
            }
            if (mounth.equals("helmikuu")) {
                break;
            }
            if (mounth.equals("maaliskuu")) {
                break;
            }
            if (mounth.equals("huhtikuu")) {
                break;
            }
            if (mounth.equals("toukokuu")) {
                break;
            }
            if (mounth.equals("kesakuu")) {
                break;
            }
            if (mounth.equals("heinakuu")) {
                break;
            }
            if (mounth.equals("elokuu")) {
                break;
            }
            if (mounth.equals("syyskuu")) {
                break;
            }
            if (mounth.equals("lokakuu")) {
                break;
            }
            if (mounth.equals("marraskuu")) {
                break;
            }
            if (mounth.equals("joulukuu")) {
                break;
            }
            
            throw new Exception("Kuukausi ei ole sallittu!");
        }
    }
    
    /**
     * Etsii käyttäjätunnusta vastaavan id:n.
     * @param username käyttäjätunnus, jonka id haetaan
     * @return haettu id
     * @throws Exception 
     */
    public static int getUserId(String username) throws Exception {
        ArrayList<User> users = Controller.getUsers();
        
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user.getId();
            }
        }
        
        throw new Exception("Ei löydetty käyttäjätunnuksen id:tä!");
    }
    
    /**
     * Hakee userId:tä vastaavan käyttäjätunnuksen käyttäjänimen.
     * @param id haettavan käyttäjätunnuksen id
     * @return haettu käyttäjänimi
     * @throws Exception 
     */
    public static String getUsername(int id) throws Exception {
        ArrayList<User> users = Controller.getUsers();
        
        for (User user : users) {
            if (user.getId() == id) {
                return user.getUsername();
            }
        }
        
        throw new Exception("Ei löydetty id:tä vastaavaa käyttäjätunnusta!");
    }
    
    /**
     * Hakee tietyn varauksen id:n.
     * @param year varauksen vuosi
     * @param mounth varauksen kuukausu
     * @param day varauksen päivä
     * @param time varauksen kellonaika
     * @return haettu id
     * @throws Exception 
     */
    public static int getReservationId(int year, String mounth, int day, String time) throws Exception {
        // haetaan kyseisen päivän kaikki varaukset
        ArrayList<Reservation> reservations = Controller.getReservations(day, year, mounth);
        
        // etsitään oikea kellonaika
        for (Reservation reservation : reservations) {
            if (reservation.getTime().equals(time)) {
                return reservation.getId();
            }
        }
        
        throw new Exception("Ei löytynyt haettavaa varausta!");
    }
    
    /**
     * Hakee käyttäjän roolin.
     * @param username käyttäjätunnus, jonka rooli haetaan
     * @return käyttäjätunnuksen rooli
     * @throws Exception 
     */
    public static String getUserType(String username) throws Exception {
        ArrayList<User> users = Controller.getUsers();
        
        for (User user : users) {
            String userUsername = user.getUsername();
            
            if (userUsername.equals(username)) {
                return user.getRole();
            }
        }
        throw new Exception("Epäonnistuttiin käyttäjätunnuksen tyypin haussa!");
    }

    /**
     * Tarkistaa, onko käyttäjätunnusta tietokannassa.
     * @param username käyttäjätunnus
     * @return true, jos löytyi, muulloin false
     * @throws Exception 
     */
    public static boolean ifUserExists(String username) throws Exception {
        ArrayList<User> users = Controller.getUsers();
        
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Tarkistaa, onko käyttäjätunnus admin.
     * @param username käyttäjätunnuksen käyttäjänimi
     * @return true, jos on admin, muulloin false
     * @throws Exception 
     */
    public static boolean ifAdmin(String username) throws Exception {
        String userType = Controller.getUserType(username);
        
        if (userType.equals("admin")) {
            return true;
        }
        return false;
    }
}
