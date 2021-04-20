
package varauskalenteri.domain;

import varauskalenteri.domain.*;

/**
 * Sisältää yhden varauksen tiedot. Vastaa tietokantataulua Reservation. 
 * Apuja tietokantaoperaatioihin haettu täältä: https://www.tutorialspoint.com/sqlite/sqlite_java.htm
 * @author Matias Siro 
 */
public class Reservation {
    private Integer id = null;
    private String time = null;
    private Integer day = null;
    private String mounth = null;
    private Integer year = null;
    private Integer userId = null;
    
    /**
     * Konstruktori, joka luo Uuden Reservation olion. 
     * @param time aika, jolloin varaus on
     * @param day päivä, jolloin varaus on
     * @param mounth kuukausi, jolloin varaus on
     * @param year vuosi, jolloin varaus on
     * @param userId varaajan id user-taulussa
     */
    public Reservation(Integer id, String time, Integer day, String mounth, Integer year, Integer userId) {
        this.id = id;
        this.time = time;
        this.day = day;
        this.mounth = mounth;
        this.year = year;
        this.userId = userId;
    }
    
    /**
     * Parametriton konstruktori.
     */
    public Reservation() {
        
    }
    
    /*
    =============================================================
    =TÄSTÄ ALASPÄIN OLEVAT METODIT OVAT GETTEREITÄ JA SETTEREITÄ=
    ============================================================= 
    */
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public void setTime(String time) {
        this.time = time;
    }
    
    public void setDay(Integer day) {
        this.day = day;
    }
    
    public void setYear(Integer year) {
        this.year = year;
    }
    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
    public void setMounth(String mounth) {
        this.mounth = mounth;
    }
    
    public int getId() {
        return this.id;
    } 
    
    public String getTime() {
        return this.time;
    }
    
    public int getDay() {
        return this.day;
    }
    
    public int getYear() {
        return this.year;
    }
    
    public int getUserId() {
        return this.userId;
    }
    
    public String getMounth() {
        return this.mounth;
    }
    
    @Override
    public String toString() {
        return id + "|" + time + "|" + day + "|" + mounth + "|" + year + "|" + userId;
    }
}
