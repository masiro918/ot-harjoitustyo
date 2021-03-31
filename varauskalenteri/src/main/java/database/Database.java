/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.util.ArrayList;
import java.sql.*;

/**
 * Tässä luokassa käsitellään tietokanta-operaatioita.
 * @author Matias Siro
 */
public class Database {
    
    private Connection connection = null;
    
    /**
     * Konstruktori.
     */
    public Database() throws Exception { 
        connection = DriverManager.getConnection("jdbc:sqlite:database.db");
    }
    
    /**
     * Luo sovelluksen tarvitsemat tietokantataulut. Huom! Ei tarkista, onko tietokantataulut jo olemassa.
     * @throws Exception 
     */
    public void createTables() throws Exception {
        String sql1 = "CREATE TABLE user (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, username TEXT NOT NULL, password TEXT NOT NULL, role TEXT NOT NULL);";
        boolean table1 = updateData(sql1);
        
        if (table1 == false) throw new Exception("Epäonnistuttiin luodessa tietokantataulua USER!");
        
        String sql2 = "CREATE TABLE reservation (id INTEGER PRIMARY KEY NOT NULL, user_id INTEGER FOREIGN KEY(user_id) REFERENCES user(id), time TEXT, day INTEGER, mounth TEXT, year INTEGER);";
        boolean table2 = updateData(sql2);
        
        if (table2 == false) throw new Exception("Epäonnistuttiin luodessa tietokantataulua RESERVATION!");
    }
    
    /**
     * Lisää/päivittää/poistaa dataa tietokannasta.
     * @param sql sql-lause
     * @return true, jos onnistui, muulloin false
     */
    public boolean updateData(String sql) {
        try {
            Statement statement = this.connection.createStatement();
            statement.executeUpdate(sql);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
    /**
     * Hakee dataa tietokannasta ja antaa tulokset ArrayList-oliona.
     * @param sql sql lause
     * @return haettu data
     */
    public ArrayList<String> getData(String sql) {
        return null;
    }
    
    /**
     * Sulkee tietokantayhteyden.
     */
    public void close() throws SQLException {
        this.connection.close();
    }
}
