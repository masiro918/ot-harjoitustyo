package varauskalenteri.domain;

import varauskalenteri.domain.*;
import varauskalenteri.database.Database;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
/**
 * Tämä luokka sisältää ne haut ja lisäykset tietokantaa, joita tässä ohjelmassa tarvitaan.
 * Apuja tietokantaoperaatioihin haettu täältä: https://www.tutorialspoint.com/sqlite/sqlite_java.htm
 * @author Matias Siro
 */
public class DbService {
    
    // tietokanta, joka liitetään tähän luokkaan.
    private Database database;  
    
    /**
     * Konstruktori.
     */
    public DbService() throws Exception {
        this.database = new Database();
    }
    
    /**
     * Huhoaa taulut sisältöineen.
     * @throws Exception 
     */
    public void destroyTables() throws Exception {
        this.database.dropTables();
    }
    
    /**
     * Luo sovelluksessa tarvittavat taulut.
     * @throws Exception 
     */
    public void createTables() throws Exception {
        // tarkistetaan, onko tietokantataulut jo olemassa
        boolean exists = Files.exists(Paths.get("database.db"));
        
        if (exists == true) {
            throw new Exception("Tietokanta on jo olemassa");
        }
        
        this.database.createTables();
    }
    
    /**
     * Hakee dataa reservation-taulusta.
     * @param sql sql-lause, jolla dataa haetaan
     * @return haettu data
     * @throws Exception 
     */
    public ArrayList<String> getDataReservation(String sql) throws Exception {
        return this.database.getDataReservation(sql);
    }
    
    /**
     * Hakee dataa user-taulusta
     * @param sql sql-lause, jolla dataa haetaan
     * @return haettu data
     * @throws Exception 
     */
    public ArrayList<String> getDataUser(String sql) throws Exception {
        return this.database.getDataUser(sql);
    }
    
    /**
     * Luo taulut tarkistamatta, että ne ovat olemassa.
     * @throws Exception 
     */
    public void createTablesWithoutChecking() throws Exception {
        this.database.createTables();
    }
    
    /**
     * Lisää uuden User-olion (eli käytännössä uuden käyttäjätunnuksen) tietokantaan. Ei tarkista, sisältääkö jo käyttäjätunnuksen.
     * @param user lisättävä käyttäjä
     * @throws Exception 
     */
    public void addUser(User user) throws Exception {
        String username = user.getUsername();
        String role = user.getRole();
        String password = user.getPassword();
        
        // luodaan tunniste
        Integer id = newId("user");
        
        String sql = "insert into user (id, username, password, role) values (" + id + ", '" + username +  "', '" + password + "', '" + role + "');";
        //System.out.println(sql);
        this.database.updateData(sql);
    }
       
    /**
     * Lisää uuden varauksen, olettaen, että varaus on sallittu 
     * (eli käytännössä kukaan muu ei ole tehnyt samaa varausta aikaisemmin).
     * @param reservation lisättävä varaus (Reservation-olio)
     * @throws Exception 
     */
    public void addReservation(Reservation reservation) throws Exception {
        Integer userId = reservation.getUserId();
        String time = reservation.getTime();
        Integer day = reservation.getDay();
        String mounth = reservation.getMounth();
        Integer year = reservation.getYear();
        
        Integer id = newId("reservation");
        
        String sql = "insert into reservation (id, user_id, time, day, mounth, year) values (" + id + ", " + userId + ", '" + time + "', " + day + ", '" + mounth + "', " + year + ");";
        //System.out.println(sql);
        
        this.database.updateData(sql);
    }
    
    /**
     * Tarkistaa, onko kysyttävä varaus jo olemassa.
     * @param time aika tuntiparina klo 8 -> klo 16 esim. klo 9-10 varaus: "09-10"
     * @param day päivä (jokin luku 1-31 väliltä)
     * @param mounth kuukausi (kirjoitetaan suomeksi esim. tammikuu -> "tammikuu"
     * @param year vuosi
     * @return true, jos varaus on vapaa, muulloin false 
     * @throws Exception 
     */
    public boolean checkIfReservationExist(String time, int day, String mounth, int year) throws Exception {
        String sql = "select * from reservation where time = '" + time + "' and day = " + day + " and mounth = '" + mounth + "' and year = " + year + ";";
        
        ArrayList<String> results = this.database.getDataReservation(sql);
        
        if (results.size() > 0) {
            return false;
        }
        
        return true;
    }
    
    /**
     * Poistaa varauksen Reservation-taulusta id:n perusteella.
     * @param id poistettavan varauksen id
     * @throws Exception 
     */
    public void delReservation(int id) throws Exception {
        String sql = "delete from reservation where id = " + id;
        this.database.updateData(sql);
    }
    
    /**
     * Sulkee tietokantapalvelun.
     * @throws Exception 
     */
    public void closeService() throws Exception {
        this.database.close();
    }
    
    /**
     * Arpoo uuden tunnisteen. Apuja satunnaislukujen tekoon haettu täältä: https://mkyong.com/java/java-generate-random-integers-in-a-range/
     * @param table taulu, johon id luodaan
     * @return arvottu kokonaisluku väliltä [0, 99999999].
     */
    public Integer newId(String table) throws Exception {
        boolean idOk = true;
        Random random = new Random();
        int id = 12345678;
        
        while (idOk) {
            int randomId = random.nextInt((99999999 - 0) + 1) + 0;
            
            if (table.equals("user")) {
                if (idExistsUser(randomId)) {
                    idOk = false;
                    id = randomId;
                }
            }
            
            if (table.equals("reservation")) {
                if (idExistsReservation(randomId)) {
                    idOk = false;
                    id = randomId;
                }
            }
        }
        
        return id;
    }
    
    /**
     * Tarkistaa, onko User-taulun id vapaa.
     * @param id tarkistettava id
     * @return true, jos on vapaa, muulloin false
     */
    public boolean idExistsUser(int id) throws Exception {
        ArrayList<String> results = this.database.getDataUser("select * from user where id = " + id + ";");
        
        if (results.size() > 0) {
            return false;
        }
        
        return true;
    }
    
    /**
     * Tarkistaa, onko Reservation-taulun id vapaa.
     * @param id  tarkistettava id
     * @return true, jos on vapaa, muulloin false
     */
    public boolean idExistsReservation(int id) throws Exception {
        ArrayList<String> results = this.database.getDataReservation("select * from reservation where id = " + id + ";");
        
        if (results.size() > 0) {
            return false;
        }
        
        return true;
    }
    
    /**
     * Tulostaa standarditulostusvirtaan user-taulun sisällön.
     * @return user-taulu
     * @throws Exception 
     */
    public ArrayList<String> printTableUser() throws Exception {
        ArrayList<String> userTable = this.database.printTableUser();
        
        for (String user : userTable) {
            System.out.println(user);
        }
        
        return userTable;
    }
    
    /**
     * Tulostaa standarditulostusvirtaan reservation-taulun sisällön.
     * @return reservation-table
     * @throws Exception 
     */
    public ArrayList<String> printReservationTable() throws Exception {
        ArrayList<String> reservationTable = this.database.printTableReservation();
        
        for (String reservation : reservationTable) {
            System.out.println(reservation);
        }
        
        return reservationTable;
    }
    
    /**
     * Suorittaa kyselyn reservation-tauluun.Kysytään, montako varausta löytyy tietynlaisella haulla.
     * @param day päivä
     * @param year vuosi
     * @param mounth kuukausi
     * @param time aika
     * @return löydettyjen tulosten lukumäärä
     * @throws Exception 
     */
    public int getCountReservation(int day, int year, String mounth, String time) throws Exception {
        String sql = "select count(*) from reservation where day = " + day + " and  year = " + year + " and mounth = '" + mounth + "' and time = '" + time + "';";
        
        int count = this.database.getCount(sql);
        return count;
    }
}
