# Arkkitehtuurikuvaus  
 
## rakenne 

Ohjelmistossa on 4 kerrosta. Jokainen kerros on yksi Javan pakkaus (=package).  
:![kuva_pakkausarkkitehtuuri](https://github.com/masiro918/ot-harjoitustyo/blob/master/varauskalenteri/dokumentaatio/pakkausarkkitehtuuri.jpg)  

## käyttöliittymä

Ohjelmassa on kaksi käyttöliittymää: graafinen ja tekstipohajainen. Ainut toiminnallinen ero näissä on se, että jos käyttäjä haluaa luoda admin-käyttäjätunnuksen, on hänen luotava se käyttämällä tekstipohjaista käyttöliittymää.  

Kun ohjelma käynnistetään, käyttäjältä kysytään komentorivillä, kumpaako käyttöliittymää hän haluaa käyttää.  

Käyttöliittymä on rakennettu erilleen sovelluslogiikasta. Näin uusia käyttöliittymiä (esim. Java Swing) voidaan melko helposti toteuttaa.  

Graafinen käyttöliittymä sisältää 4 erilaista ikkunaa: kirjautumisikkuna, rekisteröitymisikkuna, viesti-ikkuna ja varausikkuna. Ensimmäiseksi aukeaa kirjautumisikkuna (luokka LoginWindow). Tämän jälkeen käyttäjä voi kirjautua sisään tai luoda uuden käyttäjätunnuksen. Jos käyttäjä kirjautuu sisään, aukeaa varausikkuna (luokka ReservationWindow). Jos hän taas valitsee rekisteröitymisen, avautuu rekisteröitymisikkuna (luokka RegisterWindow). Rekisteröitymisen jälkeen palataan kirjautumisikkunaan (luokka LoginWindow). Kun poistutaan varausikkunasta, palataan kirjautumisikkunaan. Jos käyttäjä haluaa lopettaa käytön, hän painaa rastia kirjautumisikkunassa. Ohjelman mahdolliset viestit käyttäjälle kerrotaan viesti-ikkunassa (luokka MessageWindow).  

## sovelluslogiikka

Sovelluksen kaksi olennaista luokaa ovat User ja Reservation. Niiden määrittely on pakkauksessa varauskalenteri.domain.  

Luokkien suhde toisiinsa:    

:![kuva_luokka](https://github.com/masiro918/ot-harjoitustyo/blob/master/varauskalenteri/dokumentaatio/user-reservation.jpg)  
Ohjelman rakenteesta. Alimmassa pakkauksessa ovat toiminnallisuudet, jossa käsitellään tietokantaa sql-kielen tasolla. Kerrosta ylemässä pakkauksessa tietokantaa käsitellään luokkien (User ja Reservation) avulla. Näitä operaatioita tehdään luokan DbService avulla. Eli esimerkiksi, jos halutaan tehdä uusi varaus, muodostetaan ensiksi uusi Reservation-olio. Tämän jälkeen annetaan luotu Reservation-olio DbService-oliolle, joka antaa tiedon uudesta Reservation-oliosta kerrosta alemmalle Database-oliolle, joka suorittaa oikeanlaisen SQL-lauseen tietokannalle.  

Yhtä ylempänä on main-pakkaus. Se sisältää vain yhden luokan. Tästä luokasta ei luoda oliota vaan kaikki tämän luokan metodit ovat staattisia. Tämä luokka tarjoaa metodeja, joita tarvitaan juuri tässä ohjelmassa (esim. uuden käyttäjätunnuksen luonti, varauksen lisäys ja poisto, varausten haku ja salasanan hash-arvon selvittäminen). Niissä tapauksissa kun käytetään tietokantaa, Controller-luokka tekee ne operaatiot antamalla ne DbService-oliolle hoidettavaksi, joka antaa sitten puolestaan Database-luokalle.  

Kaikkein ylempänä on userinterface-pakkaus. Tämä pakkaus sisältää 6 luokkaa: MainProgram, TextBasedUserInterface ja 4 muuta luokkaa, jotka kuuluvat graafiseen käyttöliittymään. Sovelluslogiikkaa käytetään näissä luokissa Controller-luokan avulla. MainProgram on se luokka, joka käynnistyy ensimmäisenä, kun ohjelmaa aletaan ajaa. Se kysyy ainostaan, käytetäänkö graafista vai tekstipohjaista käyttöliittymää. Kun käyttäjä on tehnyt tämän valinnan, käynnistyy joko TextBasedUserInterface-luokka tai LoginWindow-luokka.    

Pääset lukemaan luokkien dokumentaatiota, kun suoritat komennon, jolla generoidaan JavaDoc. Siirry ensin hakemistoon varauskalenteri/ ja suorita sitten komento:  

**mvn javadoc:javadoc**    

Tämän jälkeen dokumentti löytyy hakemistosta: target/site/apidocs/, josta avaa tiedosto index.html webselaimessa.    

Testiraportin saat puolestaan komennolla:  

**mvn test jacoco:report**    

Tämän jälkeen testiraportti löytyy hakemistosta target/site/jacoco/, josta avaa tiedosto index.html webselaimessa.    


## tietojen tallentaminen

Tiedot tallennetaan tietokantaan kahteen eri tauluun: Reservation ja User. Näitä vastaavat luokat ovat domain-pallauksessa, jotka ovat saman nimiset. Tässä ohjelmassa käytetään SQLite-tietokantaa.  

## ohjelman heikkoudet/puutteet

Ohjelman graafinen käyttöliittymä on huono. Kun käyttäjä hakee varauksia tietyltä päivältä, olisi hyvä, jos varaukset näkyisivät listassa aikajärjestyksessä. Nyt varaukset eivät välttämättä näy listassa aikajärjestyksessä. Lisäksi graafinen käyttöliittymä on melko kankea käytettävä. Ohjelmassa käytetty MD5-kryptausta ei kuulemma enää pidetä kovin turvallisena. Myöskään SQL-injektion tukkimista ei ole tehty. Myös jotkut virheilmoitukset ovat huonot (esim. jos poistaa varausta jota ei ole olemassa, ohjelma väittää, että haku oli väärä).  

 
### kuva pakkausarkkitehtuurista    
:![kuva_kaavio](https://github.com/masiro918/ot-harjoitustyo/blob/master/varauskalenteri/dokumentaatio/kaavio.jpg)  

