/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import org.junit.Before;
import org.junit.Test;
//import database.Database;
import static org.junit.Assert.*;

/**
 * Apuja tietokantaoperaatioihin haettu täältä: https://www.tutorialspoint.com/sqlite/sqlite_java.htm.
 * @author user
 */
public class DatabaseTest {
    
    Database database;
    
    public DatabaseTest() {
        
    }
    
    @Before
    public void setUp() throws Exception {
        database = new Database();
        
        // poistetaan ensin jo olemassa olevat taulut
        database.dropTables();
        /*
        try {
            database = new Database();
        } catch (Exception ex) {
            Logger.getLogger(DatabaseTest.class.getName()).log(Level.SEVERE, null, ex);
        }
*/
    }
    
    @Test
    public void databaseCreated() throws Exception {
        database.createTables();
        
        // katsotaan, että tietokanta on luotu
        boolean exists = Files.exists(Paths.get("database.db"));
        assertTrue(exists);
    }
    
    @Test
    public void databaseIncludesTables() throws Exception {
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
    }
}
