
package domain;

/**
 * Sisältää User-olion ominaisuudet. Vastaa tietokantataulua User.
 * @author Matias Siro
 */
public class User {
    private Integer id = null;
    private String username = null;
    private String password = null;
    private String role = null;
    
    /**
     * Luo uuden User-olion.
     * @param id id-tunnus tietokannassa
     * @param kayttajatunnus käyttjänimi
     * @param password salasana
     * @param role rooli (ylläpitäjä tai normaali)
     */
    public User(Integer id, String username, String password, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }
    
    /**
     * Luo uuden User-olion, jonka attribuutteihin ei ole asetettu muut kuin null-arvot.
     */
    public User() {
        
    }
    
    /*
    =============================================================
    =TÄSTÄ ALASPÄIN OLEVAT METODIT OVAT GETTEREITÄ JA SETTEREITÄ=
    ============================================================= 
    */
    public Integer getId() {
        return this.id;
    }
    
    public String getUsername() {
        return this.username;
    }
    
    /*
    Huom! Ei palauta "todellista" salasanaa vaan hash-arvon siitä.
    */
    public String getPassword() {
        return this.password;
    }
    
    public String getRole() {
        return this.role;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    /*
    HUOM! Annettavan salasanan tulee olla hash-muotoinen, ei siis selkokielinen salasana.
    */
    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * Tulostaa User-olion attribuutit lukuunottamatta salasanaa.
     * @return 
     */
    @Override
    public String toString() {
        return id + "|" + username + "|" + role;
    }
}
