package textinterface;

import java.util.Scanner;

/**
 * Tässä luokassa on toiminnallisuudet tekstikäyttöliittymälle.
 * @author user
 */
public class MainClass {
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
        System.out.print(">");
        choice = inputScanner.nextLine();
        
        if (choice.equals("1")) {
            createAccount();
        }
        
        // kirjaudutaan sisään
        
        while (true) {
            System.out.print("käyttäjätunnus: ");
            username = inputScanner.nextLine();

            System.out.print("salasana: ");
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
            System.out.print("> ");
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
    private static boolean checkAccount(String username, String password) {
        return true;
    }

    private static void createAccount() {  
        while (true) {
            System.out.print("käyttäjätunnus:");
            String username = inputScanner.nextLine();

            // tarkistetaan, onko käyttäjätunnus jo olemassa
            if (checkIfUsernameExists(username) == true) {
                System.out.println("Käyttäjätunnus on jo olemassa. Valitse jokin toinen.");
                continue;
            }
            
            while (true) {
                System.out.print("salasana: ");
                String password1, password2;
                password1 = inputScanner.nextLine();

                System.out.print("salasana uudelleen: ");
                password2 = inputScanner.nextLine();

                boolean passwordCheck = checkPasswords(password1, password2);

                if (passwordCheck == true) {
                    // käyttäjätunnus luodaan
                    System.out.println("");
                    System.out.println("Tunnukset luotu! Kirjaudu nyt luomillasi tunnuksilla sisään, ole hyvä.");
                    System.out.println("");
                    break;
                }

                // salasanat eivät täsmänneet
                // TODO: tämä uudelleen
                System.out.println("Salasanat eivät täsmänneet! Yritä uudelleen.");
            }
        }
    }

    /**
     * Tarkistaa, onko käyttäjätunnus jo varattu.
     * @param username käyttäjätunnus
     * @return true, jos on varattu, muulloin false
     */
    private static boolean checkIfUsernameExists(String username) {
        return false;
    }

    /**
     * Tarkistaa, että password1 ja password2 ovat samoja merkkijonoja.
     * @param password1 salasana1
     * @param password2 salasana2
     * @return true, jos ovat samat, muulloin false
     */
    private static boolean checkPasswords(String password1, String password2) {
        if (password1.equals(password2)) return true;
        return false;
    }

    private static boolean checkIfAdmin(String loggedInUsername) {
        return false;
    }
}
