package varauskalenteri.userinterface;

import varauskalenteri.userinterface.*;
import java.util.Scanner;
import varauskalenteri.main.*;
import varauskalenteri.domain.*;
import java.util.ArrayList;

/**
 * Tässä luokassa on toiminnallisuudet tekstikäyttöliittymälle.
 * @author Matias Siro
 */
public class TextBasedUserInterface {
    public static Scanner inputScanner = new Scanner(System.in);
    public static String loggedInUsername = null;
    public static String loggedInUserType = null;
    public static int loggedInUserId = -1;
    
    public static void main(String[] args) {
        // ensin kirjautudaan sisään
        
        System.out.println("================");
        System.out.println("Varausjärjestelmä");
        System.out.println("================");
        
        String choice, username, password;
        
        
        System.out.println("");
        
        while (true) {
            System.out.println("Jos haluat luoda uudet tunnuket, kirjoita 1. Jos taas haluat kirjautua sisään, kirjoita 2. ");
            System.out.println("Luodaksesi admin-käyttäjän, kirjoita 3.");
            //System.out.print(">");
            choice = inputScanner.nextLine();

            if (choice.equals("1")) {
                createAccount("basic");
                continue;
            }
            
            if (choice.equals("2")) {
                break;
            }
            
            if (choice.equals("3")) {
                createAccount("admin");
            }
        }
        
        
        // kirjaudutaan sisään
        
        while (true) {
            System.out.println("käyttäjätunnus: ");
            username = inputScanner.nextLine();

            System.out.println("salasana: ");
            password = inputScanner.nextLine();
            
            try {
                boolean accountCheck = checkAccount(username, password);
                
                if (accountCheck == false) {
                    System.out.println("Käyttäjätunnus tai salasana oli väärin! Yritä uudelleen.");
                    continue;
                }
            
                loggedInUsername = username;
                loggedInUserType = Controller.getUserType(username);
                loggedInUserId = Controller.getUserId(username);
                
                break;
            } catch (Exception e) {
                System.err.println("Tapahtui poikkeus kirjautuessa sisään: " + e.getMessage());
            }
            
            continue;
        }
        
        
        
        // pääsilmukka
       
        System.out.println("");
        System.out.println("Olet kirjautunut sisään nimellä " + loggedInUsername + ". Voit poistua ohjelmasta kirjoittamalla komentoriville quit.");
        System.out.println("Käyttäjätunnuksen rooli: " + loggedInUserType);
        System.out.println("Käyttäjätunnuksen id on " + loggedInUserId);
        System.out.println("");
        
        String input;
        while (true) {
            System.out.println("Syötä komento, ole hyvä. Apuja saat kirjoittamalla 'help'.");
            System.out.println(">>");
            input = inputScanner.nextLine();
            
            /**
             * Varauksen poisto.
             */
            if (input.equals("del-r")) {
               
                boolean isAdmin = false;
                
                try {
                    isAdmin = checkIfAdmin(loggedInUsername);
                } catch (Exception e) {
                    System.err.println("Epäonnistuttiin tarkistaessa käyttäjäntyyppiä.");
                }
                
                if (isAdmin == false) {
                    System.out.println("Vain admin-käyttäjä saa tehdä tämän toimenpiteen.");
                    continue;
                }
                
                
                
                try {
                    System.out.println("varauksen vuosi");
                    int year = Integer.parseInt(inputScanner.nextLine());

                    System.out.println("varauksen kuukausi");
                    String mounth = inputScanner.nextLine();

                    System.out.println("varauksen päivä");
                    int day = Integer.parseInt(inputScanner.nextLine());

                    System.out.println("varauksen kellonaika");
                    String time = inputScanner.nextLine();
                
                    // tarkistetaan ensin, että syötteet ovat valideja
                    Controller.checkInputs(day, year, mounth);
                    
                    // haetaan ensin varauksen id
                    int reservationId = Controller.getReservationId(year, mounth, day, time);
                    Controller.delReservation(loggedInUserType, reservationId);
                } catch (Exception e) {
                    System.err.println("Epäonnistuttiin varauksen poistossa: " + e.getMessage());
                }
            }
            
            /**
             * Manuaali ohjelman käyttöön.
             */
            if (input.equals("help")) {
                viewHelp();
                continue;
            }
            
            /**
             * Näyttää kaikki varaukset.
             */
            if (input.equals("all-r")) {
                try {
                    String mounth;
                    int day, year;

                    System.out.println("päivä (luku)");
                    day = Integer.parseInt(inputScanner.nextLine());

                    System.out.println("vuosi (luku)");
                    year = Integer.parseInt(inputScanner.nextLine());

                    System.out.println("kuukausi (muotoa esim. 'huhtikuu' ILAMN ÄÄKKÖSIÄ eli eli esim. kesäkuu -> kesakuu)");
                    mounth = inputScanner.nextLine();
                    
                    Controller.checkInputs(day, year, mounth);
                    
                    viewAllReservations(day, year, mounth);
                } catch (Exception e) {
                    System.err.println("Epäonnistuttiin haettaessa varauksia. Luultavasti syötteet eivät olleet sallittuja.");
                    System.err.println("Annoitko esimerkiksi vuosiluvun tai kuukauden väärin? Esim. kuukausi ei voi olla '2021'.");
                    System.err.println(e.getMessage());
                }
            }
            
            /**
             * Uusi varaus.
             */
            if (input.equals("new-r")) {
                String mounth, time;
                int day, year;
                
                
                
                try {
                    System.out.println("päivä (luku)");
                    day = Integer.parseInt(inputScanner.nextLine());

                    System.out.println("vuosi (luku)");
                    year = Integer.parseInt(inputScanner.nextLine());

                    System.out.println("kuukausi (muotoa esim. 'huhtikuu' ILMAN ÄÄKKÖSIÄ eli eli esim. kesäkuu -> kesakuu)");
                    mounth = inputScanner.nextLine();

                    System.out.println("kellonaika (muotoa '11-12' tai '08-09')");
                    time =  inputScanner.nextLine();
                
                    Controller.checkInputs(day, year, mounth, time);
                    
                    Reservation newReserv = new Reservation();
                    newReserv.setDay(day);
                    newReserv.setMounth(mounth);
                    newReserv.setTime(time);
                    newReserv.setUserId(loggedInUserId);
                    newReserv.setYear(year);
                    
                    //System.out.println(newReserv.toString());
                    
                    Controller.newReservation(newReserv);
                    System.out.println("Uusi varaus lisätty onnistuneesti!");
                } catch (Exception e) {
                    System.err.println("Epäonnistuttiin varauksen lisäämisessä! Todennäköisesti syötteesi eivät olleet oikean kaltaisia.");
                    System.err.println("Annoitko esimerkiksi vuosiluvun tai kuukauden väärin? Esim. kuukausi ei voi olla '2021'.");
                    System.out.println(e.getMessage());
                }
                
                continue;
            }
            
            if (input.equals("quit")) {
                break;
            }
        }
    }

    /**
     * Tarkistaa, että tunnukset ovat olemassa tietokannassa.
     * @param username käyttäjätunnus
     * @param password salasana
     * @return true, jos tunnukset löytyvät tietokannasta, muulloin false
     */
    public static boolean checkAccount(String username, String password) throws Exception {
        ArrayList<User> users = Controller.getUsers();
        
        for (User user : users) {
            String userUsername = user.getUsername();
            String userPassword = user.getPassword();
            
            if (userUsername.equals(username) && userPassword.equals(Controller.createHash(password))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Luodaan uusi käyttäjä.
     * @param type käyttäjätunnuksen tyyppi: joko 'basic' tai 'admin'
     */
    public static void createAccount(String type) {  
        while (true) {
            System.out.println("");
            System.out.println("Huom! Heittomerkit eivät ole sallittuja käyttäjätunnukessa tai salasanss.");
            System.out.println("käyttäjätunnus:");
            String username = inputScanner.nextLine();

            // tarkistetaan, onko käyttäjätunnus jo olemassa
            try {
                if (checkIfUsernameExists(username) == true) {
                    System.out.println("Käyttäjätunnus on jo olemassa. Valitse jokin toinen.");
                    continue;
                }
            } catch (Exception e) {
                System.err.println("Epäonnistuttiin luotaessa käyttäjätunnusta: " + e.getMessage());
                break;
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
                        user.setRole(type);
                        user.setUsername(username);
                    
                    
                        Controller.newUser(user);
                        System.out.println("");
                        System.out.println("Tunnukset luotu! Kirjaudu nyt luomillasi tunnuksilla sisään, ole hyvä.");
                        System.out.println("");
                        return;
                    } catch (Exception e) {
                        System.out.println("");
                        System.err.println(e.getMessage());
                        System.out.println("");
                    }
                    
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
    public static boolean checkIfUsernameExists(String username) throws Exception {
        boolean userExists = Controller.ifUserExists(username);
        return userExists;
    }

    /**
     * Tarkistaa, että password1 ja password2 ovat samoja merkkijonoja.
     * @param password1 salasana1
     * @param password2 salasana2
     * @return true, jos ovat samat, muulloin false
     */
    public static boolean checkPasswords(String password1, String password2) {
        if (password1.equals(password2)) {
            return true;
        }
        
        return false;
    }

    /**
     * Tarkistaa, onko käyttäjä admin-käyttäjä.
     * @param username käyttäjätunnus
     * @return true, jos on admin, muulloin false
     * @throws java.lang.Exception
     */
    public static boolean checkIfAdmin(String username) throws Exception {
        boolean isAdmin = Controller.ifAdmin(username);
        return isAdmin;
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

    /**
     * Näyttää komentokäyttöliittymän käyttöohjeen.
     */
    public static void viewHelp() {
        System.out.println("");
        System.out.println("Kirjoittamalla 'new-r', voit tehdä uuden varauksen.");
        System.out.println("Kirjoittamalla 'all-r', voit tarkistaa tietyn päivän kaikki varaukset.");
        System.out.println("Jos olet admin, voit poistaa varauksen kirjoittamalla 'del-r'.");
        System.out.println("Kirjoittamalla 'quit', pääset pois sovelluksesta.");
        System.out.println("");
    }

    /**
     * Näyttää kaikki varaukset ja niiden varaajat kyseiseltä päivältä.
     * @param day päivä
     * @param year vuosi 
     * @param mounth kuukausi
     * @throws Exception 
     */
    public static void viewAllReservations(int day, int year, String mounth) throws Exception {
        ArrayList<Reservation> reservations = Controller.getReservations(day, year, mounth);
        
        System.out.println("Kyseisellä päivälle (" + day + "." + mounth + "." + year + ") olevat varaukset:");
        System.out.println("");
        
        for (Reservation reservation : reservations) {
            String ownerOfReservation = null;
            int userId = reservation.getUserId();
            ownerOfReservation = Controller.getUsername(userId);
            
            String time = reservation.getTime();
            
            System.out.println(time + " varaajalta: " + ownerOfReservation);
        }
        System.out.println("");
    }
    /*
    private static void createAdminAccount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    */
}
