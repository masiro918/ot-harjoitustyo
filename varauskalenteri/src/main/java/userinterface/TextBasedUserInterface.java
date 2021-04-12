package userinterface;

import java.util.Scanner;
import main.*;
import domain.*;

/**
 * Tässä luokassa on toiminnallisuudet tekstikäyttöliittymälle.
 * @author Matias Siro
 */
public class TextBasedUserInterface {
    public static Scanner inputScanner = new Scanner(System.in);
    public static String loggedInUsername = null;
    
    public static void main(String[] args) {
        // ensin kirjautudaan sisään
        
        System.out.println("================");
        System.out.println("Varausjärjestelmä");
        System.out.println("================");
        
        String choice, username, password;
        
        
        System.out.println("");
        
        System.out.println("Jos haluat luoda uudet tunnuket, kirjoita 1. Jos taas haluat kirjautua sisään, kirjoita 2.");
        //System.out.print(">");
        choice = inputScanner.nextLine();
        
        if (choice.equals("1")) {
            createAccount();
        }
        
        // kirjaudutaan sisään
        
        while (true) {
            System.out.println("käyttäjätunnus: ");
            username = inputScanner.nextLine();

            System.out.println("salasana: ");
            password = inputScanner.nextLine();

            boolean accountCheck = checkAccount(username, password);
            
            if (accountCheck == false) {
                System.out.println("Käyttäjätunnus tai salasana oli väärin! Yritä uudelleen.");
                continue;
            }
            
            loggedInUsername = username;
            break;
        }
        
        
        
        // pääsilmukka
       
        System.out.println("Olet kirjautunut sisään nimellä " + loggedInUsername + ". Voit poistua ohjelmasta kirjoittamalla komentoriville quit.");
        
        String input;
        while (true) {
            //System.out.printl("> ");
            input = inputScanner.nextLine();
            
            if (input.equals("del-r")) {
                boolean isAdmin = checkIfAdmin(loggedInUsername);
                
                if (isAdmin == false) {
                    System.out.println("Vain admin-käyttäjä saa tehdä tämän toimenpiteen.");
                    continue;
                }
            }
            if (input.equals("quit")) break;
        }
    }

    /**
     * Tarkistaa, että tunnukset ovat olemassa tietokannassa.
     * @param username käyttäjätunnus
     * @param password salasana
     * @return true, jos tunnukset löytyvät tietokannasta, muulloin false
     */
    public static boolean checkAccount(String username, String password) {
        return true;
    }

    public static void createAccount() {  
        while (true) {
            System.out.println("käyttäjätunnus:");
            String username = inputScanner.nextLine();

            // tarkistetaan, onko käyttäjätunnus jo olemassa
            if (checkIfUsernameExists(username) == true) {
                System.out.println("Käyttäjätunnus on jo olemassa. Valitse jokin toinen.");
                continue;
            }
            
            while (true) {
                System.out.println("salasana: ");
                String password1, password2;
                password1 = inputScanner.nextLine();

                System.out.println("salasana uudelleen: ");
                password2 = inputScanner.nextLine();

                boolean passwordCheck = checkPasswords(password1, password2);

                if (passwordCheck == true) {
                    try {
                        String hash = createHash(password1);
                        // käyttäjätunnus luodaan
                        User user = new User();
                        user.setPassword(hash);
                        user.setRole("basic");
                        user.setUsername(username);
                    
                    
                        Controller.newUser(user);
                        System.out.println("");
                        System.out.println("Tunnukset luotu! Kirjaudu nyt luomillasi tunnuksilla sisään, ole hyvä.");
                        System.out.println("");
                    } catch (Exception e) {
                        System.out.println("");
                        System.err.println("Tapahtui poikkeus lisätessä uutta käyttäjää: " + e.getMessage());
                        System.out.println("");
                    }
                    
                    break;
                }

                // salasanat eivät täsmänneet
                // TODO: tämä uudelleen
                System.out.println("Salasanat eivät täsmänneet! Yritä uudelleen.");
            }
            break;
        }
    }

    /**
     * Tarkistaa, onko käyttäjätunnus jo varattu.
     * @param username käyttäjätunnus
     * @return true, jos on varattu, muulloin false
     */
    public static boolean checkIfUsernameExists(String username) {
        return false;
    }

    /**
     * Tarkistaa, että password1 ja password2 ovat samoja merkkijonoja.
     * @param password1 salasana1
     * @param password2 salasana2
     * @return true, jos ovat samat, muulloin false
     */
    public static boolean checkPasswords(String password1, String password2) {
        if (password1.equals(password2)) return true;
        return false;
    }

    /**
     * Tarkistaa, onko käyttäjä admin-käyttäjä.
     * @param username käyttäjätunnus
     * @return 
     */
    public static boolean checkIfAdmin(String username) {
        return false;
    }

    /**
     * Luo salasanasta hash-arvon.
     * @param password
     * @return luotu hash-arvo
     * @throws Exception 
     */
    public static String createHash(String password) throws Exception {
        String hash = Controller.createHash(password);
        return hash;
    }
}
