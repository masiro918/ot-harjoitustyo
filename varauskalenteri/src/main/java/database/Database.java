/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.util.ArrayList;
import java.sql.*;

/**
 * Tässä luokassa käsitellään tietokanta-operaatioita. Apuja tietokantaoperaatioihin haettu täältä: https://www.tutorialspoint.com/sqlite/sqlite_java.htm
 * ja täältä: https://www.sqlitetutorial.net/sqlite-java/create-table/
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
        String sql1 = "CREATE TABLE user (id INTEGER PRIMARY KEY NOT NULL, username TEXT NOT NULL, password TEXT NOT NULL, role TEXT NOT NULL);";
        try {
            createTable(sql1);
        } catch (Exception e) {
            throw new Exception("Epäonnistuttiin luodessa tietokantataulua USER: " + e.getMessage());
        }
        
        String sql2 = "CREATE TABLE reservation (id INTEGER PRIMARY KEY NOT NULL, user_id INTEGER, hour TEXT, day INTEGER, mounth TEXT, year INTEGER);";
        try {
            createTable(sql2);
        } catch (Exception e) {
            throw new Exception("Epäonnistuttiin luodessa tietokantataulua RESERVATION: " + e.getMessage());
        }
    }
    
    /**
     * Luo uuden tietokantataulun. 
     * @param sql luontilauseke
     * @throws Exception 
     */
    public void createTable(String sql) throws Exception {
        try {
            Statement statement = this.connection.createStatement();
            statement.execute(sql);
            statement.close();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    /**
     * Lisää/päivittää/poistaa dataa tietokannasta.
     * @param sql sql-lause
     * @return true, jos onnistui, muulloin false
     */
    public void updateData(String sql) throws Exception {
        try {
            Statement statement = this.connection.createStatement();
            statement.executeUpdate(sql);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
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
     * Poistaa molemmat taulut.
     * @throws Exception 
     */
    public void dropTables() throws Exception {
        try {
            Statement statement = this.connection.createStatement();
            statement.executeUpdate("drop table user;");
            statement.close();
            
            statement = this.connection.createStatement();
            statement.executeUpdate("drop table reservation;");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    /**
     * Sulkee tietokantayhteyden.
     */
    public void close() throws SQLException {
        this.connection.close();
    }
}
