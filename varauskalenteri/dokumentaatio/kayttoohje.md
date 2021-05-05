# Sovelluksen käyttöohje

## 1. asentaminen

Lataa ohjelman uusin release GitHubista. Pura ladattu paketti johonkin hakemistoon. Tämän jälkeen siirry komentorivillä alihakemistoon varauskalenteri/. Tämän jälkeen aja komento:    

mvn compile exec:java -Dexec.mainClass=varauskalenteri.userinterface.MainProgram    

Tämä äskeinen komento kääntää ohjelman. Käännöksen jälkeen ohjelma käynnistyy. Kannattaa kuitenkin tässä kohtaan lopettaa ohjelman suoritus painamalla CTRL+D.  

Aja sitten testit komennolla:    

mvn test    

Tämän jälkeen voit luoda testikattavuusraportin komennolla:    

mvn test jacoco:report    

Tämän jälkeen voit luoda JavaDocin komennolla:    

mvn javadoc:javadoc    

Sitten siirrytään itse jar-tiedoston luontiin. Saat generoitua jar-tiedoston, joka luodaan alihakemistoon target/, komennolla:    

mav package    

Siirry sitten target-hakemistoon. Siellä pitäisi olla nyt tiedosto **varauskalenteri-1.0-SNAPSHOT.jar**. Tämä on nyt pysyvä ohjelmatiedosto. Saat nyt käynnistettyä milloin tahansa ohjelman, siirtymällä
tähän hakemistoon ja ajamalla komomennon:    

java -jar varauskalenteri-1.0-SNAPSHOT.jar    

Ohjelma on nyt asennettu.    

## 2. graafisen käyttöliittymän käyttö    

Kun avaat ohjelman komentoriviltä, sinulta kysytään ensimmäiseksi, haluatko käyttää graafista vai tekstikäyttöliittymää. Ainoa toiminnallinen ero näissä on se, että admin-käyttäjiä pystyy luomaan vain tekstikäyttöliittymällä. Kuitenkin tässä osiossa käsitellään ainostaa graafisen käyttöliittymän käyttämistä.  
Oletetaan, että olet nyt valinnut graafisen käyttöliittymän. Ensimmäisellä kerralla sinun on luotava tunnukset.

## 2.1 tavallisen käyttäjätunnuksen luonti

Valitse itsellesi käyttäjätunnus. Se voi olla mikä tahansa merkkijono. Valitse sitten salasana. Jos käyttäjätunnus on varattu, ohjelma ilmoittaa siitä sinulle. Tämän jälkeen voit kirjautua sisään luomillasi tunnuksilla.

## 2.2 sisään kirjautuminen ja ohjelman perusnäkymä

Kun olet kirjaunut sisään, avauttu sinulle alla oleva näkymä. Alla olevassa kuvassa on kerrottu, mitä mitkin elementit ovat kyseisessä ikkunassa.

**kuva_perusnäkymästä**




Avaa ohjelma komentoriviltä komennolla:    

java -jar varauskalenteri-1.0-SNAPSHOT.jar    

Sen jälkeen ohjelma kysyy, käytätkö graafista vai tekstipohjaista käyttöliittymää. Ota huomioon, että voit luoda admin-käyttäjän ainostaan
tekstipohjaisesta käyttöliittymästä.
