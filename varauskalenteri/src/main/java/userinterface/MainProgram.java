
package userinterface;

import java.nio.file.Files;
import java.nio.file.Path;
import userinterface.TextBasedUserInterface;
import main.Controller;
import java.nio.file.Paths;

/**
 * Tästä luokasta avataan, joko teksti- tai graafinenkäyttöliittymä.
 * @author Matias Siro
 */
public class MainProgram {
    public static void main(String[] args) {
        try {
            TextBasedUserInterface.main(args);
        } catch (Exception e) {
            System.err.println("Tapahtui virhe avattaessa ohjelmaa: " + e.getMessage());
        }
    }
}
