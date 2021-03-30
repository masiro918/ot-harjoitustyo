/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.util.ArrayList;

/**
 * Tässä luokassa käsitellään tietokanta-operaatioita.
 * @author Matias Siro
 */
public class Database {
    
    /**
     * Konstruktori.
     */
    public Database() {
        
    }
    
    /**
     * Lisää/päivittää/poistaa dataa tietokannasta.
     * @param sql sql-lause
     * @return true, jos onnistui, muulloin false
     */
    public boolean updateData(String sql) {
        return false;
    }
    
    /**
     * Hakee dataa tietokannasta ja antaa tulokset ArrayList-oliona.
     * @param sql sql lause
     * @return haettu data
     */
    public ArrayList<String> getData(String sql) {
        return null;
    }
}
