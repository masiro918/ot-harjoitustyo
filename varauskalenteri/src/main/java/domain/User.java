
package domain;

/**
 * Sisältää User-olion ominaisuudet. Vastaa tietokantataulua User.
 * @author Matias Siro
 */
public class User {
    private Integer id = null;
    private String kayttajatunnus = null;
    private String password = null;
    private String role = null;
    
    /**
     * Luo uuden User-olion.
     * @param id id-tunnus tietokannassa
     * @param kayttajatunnus käyttjänimi
     * @param password salasana
     * @param role rooli (ylläpitäjä tai normaali)
     */
    public User(Integer id, String kayttajatunnus, String password, String role) {
        this.id = id;
        this.kayttajatunnus = kayttajatunnus;
        this.password = password;
        this.role = role;
    }
    
    /**
     * Tulostaa User-olion attribuutit lukuunottamatta salasanaa.
     * @return 
     */
    @Override
    public String toString() {
        return id + "|" + kayttajatunnus + "|" + role;
    }
}
