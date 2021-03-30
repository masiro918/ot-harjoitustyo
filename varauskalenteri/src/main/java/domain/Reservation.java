
package domain;

/**
 * Sisältää yhden varauksen tiedot. Vastaa tietokantataulua Reservation.
 * @author Matias Siro 
 */
public class Reservation {
    private String time = null;
    private Integer day = null;
    private String mounth = null;
    private Integer year = null;
    private Integer user_id = null;
    
    public Reservation(String time, Integer day, String mounth, Integer year, Integer user_id) {
        this.time = time;
        this.day = day;
        this.mounth = mounth;
        this.year = year;
        this.user_id = user_id;
    }
    
    @Override
    public String toString() {
        return time + "|" + day + "|" + mounth + "|" + year + "|" + user_id;
    }
}
