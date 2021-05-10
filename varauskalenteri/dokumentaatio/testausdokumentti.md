# testausdokumentti

Sovellusta on testattu yksikkötesteillä JUnit-työkalulla. Ainostaan pakkauksen userinterface luokkia ei ole testattu. Voit luoda testikattavuusraportin komennolla:  
**mvn javadoc:javadoc**  
Tämän jälkeen raportti löytyy hakemistosta target/site/jacoco/ ja avaa sitten webseilaimessa tiedosto index.html

### yksikkötestaus

Jos lasketaan pakkausten main, domain ja database rivikattuvuuksien keskiarvo, saadaan 77%. Samojen pakkausten haarautumakattavuus on 66%.    

![testikattavuusraportti](https://github.com/masiro918/ot-harjoitustyo/blob/master/varauskalenteri/dokumentaatio/testikattavuusraportti.jpg)  

### järjestelmätestaus

Sovelluksen järjestelmätestaus on suoritettu manuaalisesti. Tämä tarkoittaa sitä, että sovelluksen toiminta vastaa sitä, mitä siltä odotetaan. Sovelluksen asentamista on testattu kahdella eri tietokoneella, ja se on suoritettu tavalla, joka kerrotaan käyttöohjeessa.

