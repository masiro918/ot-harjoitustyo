
package varauskalenteri.userinterface;

import varauskalenteri.userinterface.*;
import java.nio.file.Files;
import java.nio.file.Path;
import varauskalenteri.userinterface.TextBasedUserInterface;
import varauskalenteri.main.Controller;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Tästä luokasta avataan, joko teksti- tai graafinenkäyttöliittymä.
 * HUOM! Tietokanta luodaan toistaiseksi joka ohjelman suorittaminen aikana uudelleen.
 * Eli vanhan tietokannan sisältö tuhotaan aina, kun ohjelma käynnistetään.
 * Kun ajat testejä, aja ensin ohjelma ja sitten vasta testit. Näin varmistut, että
 * testejä ajettaessa tietokanta on olmessa.
 * @author Matias Siro
 */
public class MainProgram {
    public static void main(String[] args) {
        
        try {
            // poistetaan vahna tietokanta
            Path dbPath = Paths.get("database.db");
            Files.delete(dbPath);
        } catch (Exception e) {
            System.err.println("Poikkeus tuhottaessa tietokantaa: " + e.getMessage());
        }
        
        try {
            // luodaan uusi tietokanta
            Controller.createDatabase();
        } catch (Exception e) {
            System.err.println("Poikkeus luodessa tietokantaa: " + e.getMessage());
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