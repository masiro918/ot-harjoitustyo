package domain;

import database.Database;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
/**
 * Tämä luokka sisältää ne haut ja lisäykset tietokantaa, joita tässä ohjelmassa tarvitaan.
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
        throw new Exception("metodi ei ole toistaiseksi käytössä!");
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
}
