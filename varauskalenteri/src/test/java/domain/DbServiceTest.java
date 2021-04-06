package domain;

import domain.User;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testaa luokkaa DbService.
 * @author Matias
 */
public class DbServiceTest {
    
    DbService dbService;
    
    public DbServiceTest() {
        
    }

    @Before
    public void setUp() throws Exception {
        this.dbService = new DbService();
        //this.dbService.destroyTables();
        this.dbService.createTablesWithoutChecking();
    }
    
    @Test
    public void addUser() throws Exception {
        User user = new User();
        user.setUsername("kalle");
        user.setPassword("salasana");
        user.setRole("basic");
        
        this.dbService.addUser(user);
        
        ArrayList<String> userTable = this.dbService.printTableUser();
        this.dbService.closeService();
        
        String user1 = userTable.get(0);        
        // poistetaan ensimmäinen tietue, koska se on arvottu id
        
        String[] blocks = user1.split("\\|");
        
        assertEquals("kalle", blocks[1]);
        assertEquals("salasana", blocks[2]);
        assertEquals("basic", blocks[3]);
    }

}

