package domain;

import database.Database;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
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
    
    public void createTables() throws Exception {
        // tarkistetaan, onko tietokantataulut jo olemassa
        boolean exists = Files.exists(Paths.get("database.db"));
        if (exists == true) throw new Exception("Tietokanta on jo olemassa");
        
        this.database.createTables();
    }
    
    /**
     * Lisää uuden User-olion (eli käytännössä uuden käyttäjätunnuksen) tietokantaa.
     * @param user lisättävä käyttäjä
     * @throws Exception 
     */
    public void addUser(User user) throws Exception {
        Integer id = user.getId();
        String username = user.getUsername();
        String role = user.getRole();
        
        // luodaan tunniste
        
    }
    
    /**
     * Lisää uuden varauksen, olettaen, että varaus on sallittu 
     * (eli käytännössä kukaan muu ei ole tehnyt samaa varausta aikaisemmin).
     * @param reservation lisättävä varaus (Reservation-olio)
     * @throws Exception 
     */
    public void addReservation(Reservation reservation) throws Exception {
        throw new Exception("metodi ei ole toistaiseksi käytössä!");
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
        throw new Exception("metodi ei ole toistaiseksi käytössä!");
    }
    
    /**
     * Poistaa varauksen Reservation-taulusta id:n perusteella.
     * @param id poistettavan varauksen id
     * @throws Exception 
     */
    public void delReservation(int id) throws Exception {
        throw new Exception("metodi ei ole toistaiseksi käytössä!");
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
     * @return arvottu kokonaisluku väliltä [0, 99999999].
     */
    public Integer newId() {
        Random random = new Random();
        int id = random.nextInt((99999999 - 0) + 1) + 0;
        return id;
    }
    
    /**
     * Tarkistaa, onko id vapaa.
     * @param id tarkistettava id
     * @return true, jos on vapaa, muulloin false
     */
    public boolean idExists(int id) throws Exception {
        // TODO
        throw new Exception("Metodi ei ole vielä käytössä!");
    }
    
    public static void main(String[] args) throws Exception {
        DbService dbs = null;
        try {
            dbs = new DbService();
            dbs.createTables();
        } catch (Exception e) {
            System.err.println("Tapahtui poikkeus: " + e.getMessage());
        } finally {
            dbs.closeService();
        }
    }
}
