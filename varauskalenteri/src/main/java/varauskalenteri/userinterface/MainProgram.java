
package varauskalenteri.userinterface;

import java.io.File;
import varauskalenteri.userinterface.*;
import java.nio.file.Files;
import java.nio.file.Path;
import varauskalenteri.userinterface.TextBasedUserInterface;
import varauskalenteri.main.Controller;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Tästä luokasta avataan, joko teksti- tai graafinenkäyttöliittymä.
 * @author Matias Siro
 */
public class MainProgram {
    public static void main(String[] args) {
        
        /*
        try {
            // poistetaan vahna tietokanta
            Path dbPath = Paths.get("database.db");
            Files.delete(dbPath);
        } catch (Exception e) {
            System.err.println("Poikkeus tuhottaessa tietokantaa: " + e.getMessage());
        }
        */
        
        // tarkistetaan, onko tietokanta jo olemassa
        
        File dbFile = new File("database.db");
        
        if (dbFile.exists() == false) {
            try {
                // luodaan uusi tietokanta
                System.out.println("luodaan tietokanta");
                Controller.createDatabase();
            } catch (Exception e) {
                System.err.println("Poikkeus luodessa tietokantaa: " + e.getMessage());
            }
        } else {
            System.out.println("tietokanta on jo olemassa!");
        }
        
        System.out.println("Kirjoita gui, jos haluat käyttää graafista käyttöliittymää.");
        System.out.println("Kirjoita tui, jos haluat käyttää tekstikäyttöliittymää.");
            
        Scanner input = new Scanner(System.in);
        System.out.println(">>");
        String startInput = input.nextLine();
        
        try {
            if (startInput.equals("gui")) {
                LoginWindow.main(args);
            } else if (startInput.equals("tui")) {
            
                TextBasedUserInterface.main(args);
            
            } else {
                System.err.println("Virheellinen valinta!");
            }
        } catch (Exception e) {
            System.err.println("Tapahtui virhe avattaessa ohjelmaa: " + e.getMessage());
        } finally {
            input.close();
        }
    }
}
