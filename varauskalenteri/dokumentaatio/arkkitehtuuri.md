# Arkkitehtuurikuvaus  
 
## rakenne 

Ohjelmistossa on 4 kerrosta. Jokainen kerros on yksi Javan pakkaus (=package).  
**kuva kerrosarkkitehtuurista.  

## käyttöliittymä

Ohjelmassa on kaksi käyttöliittymää: graafinen ja tekstipohajainen. Ainut toiminnallinen ero näissä on, että jos käyttäjä haluaa luoda admin-käyttäjätunnuksen, on hänen luotava se käyttämällä graafista käyttöliittymää.  

Kun ohjelma käynnistetään, käyttäjältä kysytään komentorivillä, kumpaako käyttöliittymää hän haluaa käyttää.  

Käyttöliittymä on rakennettu erilleen sovelluslogiikasta. Näin uusia käyttöliittymiä (esim. Java Swing) voidaan melko helposti toteuttaa.  

Graafinen käyttöliittymä sisältää 3 erilaista ikkunaa: kirjautumisikkuna, rekisteröitymisikkuna ja varausikkuna. Ensimmäiseksi aukeaa kirjautumisikkuna (luokka LoginWindow). Tämän jälkeen käyttäjä voi kirjautua sisään tai luoda uuden käyttäjätunnuksen. Jos käyttäjä kirjautuu sisään, aukeaa varausikkuna (luokka ReservationWindow). Jos hän taas valitsee rekisteröitymisen, avautuu rekisteröitymisikkuna (luokka RegisterWindow). Rekisteröitymisen jälkeen palataan kirjautumisikkunaan (luokka LoginWindow). Kun poistutaan varausikkunasta, palataan kirjautumisikkunaan. Jos käyttäjä haluaa lopettaa käytön, hän painaa rastia kirjautumisikkunassa.  

## sovelluslogiikka

Sovelluksen kaksi olennaista luokaa ovat User ja Reservation. Niiden määrittely on pakkauksessa varauskalenteri.domain.  

Luokkien suhde toisiinsa:    

** kuva user-reservation **
Ohjelman rakenteesta. Alimmassa pakkauksessa on toiminnallisuudet, jossa käsitellään tietokantaa sql-kielen tasolla. Kerrosta ylemässä pakkauksessa tietokantaa käsitellään luokkien (User ja Reservation) avulla. Näitä operaatioita tehdään luokan DbService avulla. Eli esimerkiksi, jos halutaan tehdä uusi varaus, muodostetaan ensiksi uusi Reservation-olio. Tämän jälkeen annetaan luotu Reservation-olio DbService-oliolle, joka antaa tiedon uudesta Reservation-oliosta kerrostaalemmalle Database-oliolle, joka suorittaa oikeanlaisen SQL-lauseen tietokannalle.  

Yhtä ylempänä on main-pakkaus. Se sisältää vain yhden luokan. Tästä luokasta ei luoda oliota vaan kaikki tämän luokan metodit ovat staattisia. Tämä luokka tarjoaa metodeja, joita tarvitaan juuri tässä ohjelmassa (esim. uuden käyttäjätunnuksen luonti, varauksen lisäys ja poisto, varausten haku ja salasanan hash-arvon selvittäminen). Niissä tapauksissa kun käytetään tietokantaa, Controller-luokka tekee ne operaatiot antamalla ne DbService-oliolle.  

Kaikkein ylempänä on userinterface-pakkaus. Tämä pakkaus sisältää kolme luokkaa: MainProgram, TextBasedUserInterface ja 3 muuta luokkaa, jotka kuuluvat graafiseen käyttöliittymään. Sovelluslogiikkaa käytetään näissä luokissa Controller-luokan avulla.  


## tietojen tallentaminen

Tiedot tallennetaan tietokantaa kahteen eri tauluun: Reservation ja User. Näitä vastaavat luokat ovat domain-pallauksessa, jotka ovat saman nimiset.  

## ohjelman heikkoudet/puutteet

Ohjelman graafinen käyttöliittymä on huono. Se on aika kankea käytettävä. Lisäksi ohjelmassa käytetty MD5-kryptausta ei kuulemma enää pidetä kovin turvallisena. Myöskään SQL-inketion tukkimista ei ole tehty.  

 
### kuva pakkausarkkitehtuurista    
:![picture alt](https://github.com/masiro918/ot-harjoitustyo/blob/master/varauskalenteri/dokumentaatio/kaavio.jpg)  

