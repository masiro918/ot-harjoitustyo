
package userinterface;

import userinterface.TextBasedUserInterface;

/**
 * Tästä luokasta avataan, joko teksti- tai graafinenkäyttöliittymä.
 * @author Matias Siro
 */
public class MainProgram {
    public static void main(String[] args) {
        // käynnistetään tekstikäyttöliittymä
        try {
            TextBasedUserInterface.main(args);
        } catch (Exception e) {
            System.err.println("Tapahtui virhe avattaessa ohjelmaa: " + e.getMessage());
        }
    }
}
