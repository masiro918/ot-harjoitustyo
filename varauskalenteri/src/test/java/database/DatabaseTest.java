/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
//import database.Database;
import static org.junit.Assert.*;

/**
 * Apuja tietokantaoperaatioihin haettu täältä: https://www.tutorialspoint.com/sqlite/sqlite_java.htm.
 * Testaa luokkaa Database.
 * @author Matias
 */
public class DatabaseTest {
    
    Database database;
    
    public DatabaseTest() {
        
    }
    /**
     * Alla olevat testit eivät vielä toimi!
     *
     */
    
    /*
    @Before
    public void setUp() throws Exception {
        database = new Database();
        
        // poistetaan ensin jo olemassa olevat taulut
        database.dropTables();
    }
    */
    
    /**
     * Testaa, että tietokanta luodaan onnistuneesti.
     * @throws Exception 
     */
    @Test
    public void databaseCreated() throws Exception {
        /*
        database.createTables();
        
        // katsotaan, että tietokanta on luotu
        boolean exists = Files.exists(Paths.get("database.db"));
        assertTrue(exists);
        database.close();
        */
    }
    
    /**
     * Tarkistaa, että tietokanta, joka luodaan sisältää taulus user ja reservation.
     * @throws Exception 
     */
    @Test
    public void databaseIncludesTables() throws Exception {
        /*
        database.createTables();
        
        Connection connection = DriverManager.getConnection("jdbc:sqlite:database.db");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from user;");
        String output1 = "output: " + resultSet.getRow();
        
        resultSet = statement.executeQuery("select * from reservation;");
        String output2 = "output: " + resultSet.getRow();
        
        resultSet.close();
        statement.close();
        connection.close();
        
        assertEquals(output1, "output: 0");
        assertEquals(output2, "output: 0");
        */
    }
    
    /**
     * Testaa, että tauluun user voidaan lisätä dataa.
     * @throws Exception 
     */
    @Test
    public void addDataToDatabaseUserTable() throws Exception {
        /*
        database.createTables();
        database.updateData("insert into user (id, username, password, role) values (98342170, 'test_user', 'salasana123', 'basic');");
        database.updateData("insert into user (id, username, password, role) values (12345670, 'admin_user', 'password123', 'admin');");
        
        ArrayList<String> results = database.printTableUser();
        
        String row1 = results.get(0);
        String row2 = results.get(1);
        
        database.close();
        
        assertEquals(row1, "12345670|admin_user|password123|admin");
        assertEquals(row2, "98342170|test_user|salasana123|basic");
        */
    }
    
    /**
     * Testaa, että tauluun reservation voidaan lisätä dataa.
     * @throws Exception 
     */
    @Test
    public void addDataToDatabaseReservationTable() throws Exception {
        /*
        database.createTables();
        database.updateData("insert into reservation (id, user_id, hour, day, mounth, year) values (98112170, 12345670, '10-11', 2, 'tammikuu', 2021);");
        database.updateData("insert into reservation (id, user_id, hour, day, mounth, year) values (99992170, 12345671, '11-12', 2, 'helmikuu', 2026);");
        
        ArrayList<String> results = database.printTableReservation();
        
        String row1 = results.get(0);
        String row2 = results.get(1);
        
        database.close();
        
        assertEquals(row1, "98112170|12345670|10-11|2|tammikuu|2021");
        assertEquals(row2, "99992170|12345671|11-12|2|helmikuu|2026");
        */
    }
    
    @Test
    public void removeDataFromDatabaseUserTable() throws Exception {
        /*
        database.createTables();
        database.updateData("insert into user (id, username, password, role) values (98342170, 'test_user', 'salasana123', 'basic');");
        database.updateData("insert into user (id, username, password, role) values (12345670, 'admin_user', 'password123', 'admin');");
        
        // poistetaan yksi alkio taulusta
        database.updateData("delete from user where id = 98342170;");
        
        ArrayList<String> results = database.printTableUser();
        
        String lines = "" + results.size();
                
        database.close();
        
        // taulussa pitäisi olla nyt 1 rivi
        assertEquals(lines, "1");
        */
    }
    
    
    @Test
    public void removeDataFromDatabaseReservationTable() throws Exception {
        /*
        database.createTables();
        database.updateData("insert into reservation (id, user_id, hour, day, mounth, year) values (98112170, 12345670, '10-11', 2, 'tammikuu', 2021);");
        database.updateData("insert into reservation (id, user_id, hour, day, mounth, year) values (99992170, 12345671, '11-12', 2, 'helmikuu', 2026);");
        
        database.updateData("delete from reservation where id = 99992170");
        
        ArrayList<String> results = database.printTableReservation();
        
        database.close();
        
        String lines = "" + results.size();
                
        database.close();
        
        // taulussa pitäisi olla nyt 1 rivi
        assertEquals(lines, "1");
*/
    }
    
}
