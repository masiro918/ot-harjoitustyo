/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import database.Database;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Apuja tietokantaoperaatioihin haettu täältä: https://www.tutorialspoint.com/sqlite/sqlite_java.htm.
 * @author user
 */
public class DatabaseTest {
    
    Database database;
    
    public DatabaseTest() {
        
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        try {
            database = new Database();
        } catch (Exception ex) {
            Logger.getLogger(DatabaseTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @AfterEach
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
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
        System.out.println(resultSet.toString());
        
        resultSet = statement.executeQuery("select * from reservation;");
        System.out.println(resultSet.toString());
        
        resultSet.close();
        statement.close();
        connection.close();
    }
}
