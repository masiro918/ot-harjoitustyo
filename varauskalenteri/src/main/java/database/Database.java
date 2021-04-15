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
        
        String sql2 = "CREATE TABLE reservation (id INTEGER PRIMARY KEY NOT NULL, user_id INTEGER, time TEXT, day INTEGER, mounth TEXT, year INTEGER);";
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
     * @throws Exception 
     */
    public void updateData(String sql) throws Exception {
        try {
            Statement statement = this.connection.createStatement();
            statement.executeUpdate(sql);
            //statement.execute(sql);
            statement.close();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    /**
     * Hakee dataa tietokannan user-taulusta ja antaa tulokset ArrayList-oliona.
     * @param sql sql-lause
     * @return haettu data
     * @throws SQLException 
     */
    public ArrayList<String> getDataUser(String sql) throws SQLException {
        Statement statement = this.connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        
        ArrayList<String> results = new ArrayList<>();
        
        while (resultSet.next()) {
            String row = "";
            
            int id = resultSet.getInt("id");
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            String role = resultSet.getString("role");
            
            row = id + "|" + username + "|" + password + "|" + role;
            
            //System.out.println(row);
            results.add(row);
        }
        
        resultSet.close();
        statement.close();
        
        return results;
    }
    
    /**
     * Hakee dataa User-taulusta ja antaa tulokset ArrayList-oliona.
     * @param sql sql-lause
     * @return haettu data
     * @throws SQLException 
     */
    public ArrayList<String> getDataReservation(String sql) throws SQLException {
        Statement statement = this.connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        
        ArrayList<String> results = new ArrayList<>();
        
        while (resultSet.next()) {
            String row = "";
            
            Integer id = resultSet.getInt("id");
            Integer userId = resultSet.getInt("user_id");
            String time = resultSet.getString("time");
            Integer day = resultSet.getInt("day");
            String mounth = resultSet.getString("mounth");
            Integer year = resultSet.getInt("year");
            
            //TODO: merkkijono
            row = id + "|" + time + "|" + day + "|" + mounth + "|" + year + "|" + userId;
            results.add(row);
        }
        
        resultSet.close();
        statement.close();
        
        return results;
    }
    
    /**
     * Ilmoittaa, montako "löydöstä" haettiin tietokannasta.
     * @param sql sql-lause
     * @return haettujen rivien määrä
     * @throws Exception 
     */
    public int getCount(String sql) throws Exception {
        Statement statement = this.connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        
        int count = Integer.parseInt(resultSet.getString(1));
        
        resultSet.close();
        statement.close();
        
        return count;
    }
    
    /**
     * Tulostaa ArrayListiin koko user-taulun.
     * @return user-taulun sisältö
     * @throws SQLException 
     */
    public ArrayList<String> printTableUser() throws SQLException {
        return getDataUser("select * from user;");
    }
    
    /**
     * Tulostaa ArrayListiin reservation-taulun.
     * @return reservation-taulun sisältö
     * @throws SQLException 
     */
    public ArrayList<String> printTableReservation() throws SQLException {
        return getDataReservation("select * from reservation;");
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
            statement.close();
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
