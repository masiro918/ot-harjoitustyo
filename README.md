# ohjelmistotekniikka, harjoitustyö

[linkki työaikakirjanpitoon](https://github.com/masiro918/ot-harjoitustyo/blob/master/varauskalenteri/dokumentaatio/tyoaikakirjanpito.md)  
[linkki vaatimusmäärittelyyn](https://github.com/masiro918/ot-harjoitustyo/blob/master/varauskalenteri/dokumentaatio/vaatimusmaarittely.md)  
[linkki arkkitehtuurikuvaukseen](https://github.com/masiro918/ot-harjoitustyo/blob/master/varauskalenteri/dokumentaatio/arkkitehtuuri.md)  

## ohjelman tämänhetinen tilanne

Jotta voisit suorittaa alla olevat toimenpiteet, siirry ensin hakemistoon varauskalenteri/  

HUOM! Käännä ensin ohjelma, aja sitten vasta testit. Näin saadaan luotua tietokanta, joita myös testit käyttävät. Muuten testit eivät men läpi, koska oikeankaltaista tietokantaa ei ole luotu. Jos haluat luoda jar-tiedoston, sinun täytyy ensin kääntää ohjelma alla olevalla komennolla ja sen jälkeen voit luoda jar-tiedoston komennolla 'mvn package'. Huomio sekin, että testit kannattaa ajaa läpi vain kerran, eli heti sen jälkeen, kun ohjelma on käännetty. Tämä siksi, koska testien ajaminen tuhoaa tietokannassa jo olevan sisällön.   

ohjelma käännetään ja ajetaan komennolla:  
**mvn compile exec:java -Dexec.mainClass=varauskalenteri.userinterface.MainProgram**  

testit ajetaan komennolla:  
**mvn test**  

testikattavuusraportti saadaan komennolla:  
**mvn test jacoco:report**  

tämän jälkeen voit luoda jar-tiedoston komennolla  

**mvn package**   

ja ajaa jar-tiedoston, siirtymällä target-hakemistoon ja suorittamalla komennon:  

**java -jar varauskalenteri-1.0-SNAPSHOT.jar**

***HUOM! Toistaiseksi aina kun ohjelma käynnistetään, aikaisempi sisältö tietokannassa tuhotaan. Tämä siksi, jotta testit toimisivat.***

## projektin nykyinen tilanne (viikko 6)
Graafinen käyttöliittymä on hienosäätöä vaille valmis. Ainut toiminnallinen ero tekstikäyttöliittymään on se, että tekstikäyttöliittymästä voidaan luoda admin-käyttäjä.
Graafisesta käyttöliittymästä voidaan luoda siis vain tavallisia käyttäjiä.

Toiminnot, jotka ohjelmalta vaaditaan:    

(toistaiseksi vain tekstikäyttöliittymässä)    

-varauksen tekeminen (sis tarkistuksen, kuten ei päällekkäisiä varauksia ja varauksia ennen klo 8 tai jälkeen klo 16)  **[tehty]**  
-käyttäjätunnuksen luonti (vain basic-käyttäjä) **[tehty]**  
-kaikkien varausten tarkistaminen tietyltä päivältä **[tehty]**  
-käyttäjätunnus liitetään varaukseen **[tehty]**  
-admin-käyttäjä pystyy poistamaan varauksen **[tehty]**  
-graafinen käyttöliittymä **[tehty (tarvitsee vielä kuitenkin hinosäätöä)]**
-käyttöohje **[aloitettu, kesken]**  
-dokumentaatio **[aloitettu, kesken]**  
-bugi: jotkut virhe ilmoituksen ovat vääriä (esim. jos kuukauden kohdalle kirjoittaa 1234, väittää että vuosi olisi väärä) **[korjattu]**    


## projektin nykyinen tilanne (viikko 5)
Toiminnot, jotka ohjelmalta vaaditaan:    

(toistaiseksi vain tekstikäyttöliittymässä)    

-varauksen tekeminen (sis tarkistuksen, kuten ei päällekkäisiä varauksia ja varauksia ennen klo 8 tai jälkeen klo 16)  **[tehty]**  
-käyttäjätunnuksen luonti (vain basic-käyttäjä) **[tehty]**  
-kaikkien varausten tarkistaminen tietyltä päivältä **[tehty]**  
-käyttäjätunnus liitetään varaukseen **[tehty]**  
-admin-käyttäjä pystyy poistamaan varauksen **[tehty]**  
-graafinen käyttöliittymä **[ei ole tehty]**    
-bugi: jotkut virhe ilmoituksen ovat vääriä (esim. jos kuukauden kohdalle kirjoittaa 1234, väittää että vuosi olisi väärä) **[korjattu]**    

Edelleenkin (testien toimivuuden takia) aina kun ohjelma käynnistetään, kaikki aikaisempi sisältö tietokannasta tuhotaan.  

### viikon 4 tilanne:
Toiminnot, jotka ohjelmalta vaaditaan:    

(toistaiseksi vain tekstikäyttöliittymässä)    

-varauksen tekeminen (sis tarkistuksen, kuten ei päällekkäisiä varauksia ja varauksia ennen klo 8 tai jälkeen klo 16)  **[tehty]**  
-käyttäjätunnuksen luonti (vain basic-käyttäjä) **[tehty]**  
-kaikkien varausten tarkistaminen tietyltä päivältä **[tehty]**  
-käyttäjätunnus liitetään varaukseen **[tehty]**  
-admin-käyttäjä pystyy poistamaan varauksen **[ei ole tehty]**  
-graafinen käyttöliittymä **[ei ole tehty]**    
-bugi: jotkut virhe ilmoituksen ovat vääriä (esim. jos kuukauden kohdalle kirjoittaa 1234, väittää että vuosi olisi väärä)  

Myös hash-arvon muodostaminen onnistuu nyt mille tahansa salasanalle.  
Lisäksi tekstikäyttöliittymässä on vielä sellainen bugi, että jos rekisteröityessä
kirjoittaa tarkistussalasanan väärin tai haluttu käyttäjänimi on jo olemassa, tekstikäyttöliittymä
ei toimi oikein. Tämä täytyy siis myös korjata.    

Edelleenkin (testien toimivuuden takia) aina kun ohjelma käynnistetään, kaikki aikaisempi sisältö tietokannasta tuhotaan.    

### viikon 3 tilanne:
    
Kyseessä on Maven-projekti. Javan versio on 11. Ohjelma toimii toistaiseksi vain siten, että voit luoda uuden
käyttäjätunnuksen, joka tallennetaan tietokantaan. Salasanan hash-arvo ei toimi kaikille salasanoille (täytyy
selvittää miksi). Kun luot testikäytäjän, käytä vaikka salasanaa 'salasana'. Se ainakin toimii.    
Jotta testit toimisivat, käynnistä ensin ohjelma komentoriviltä komennolla    
**mvn compile exec:java -Dexec.mainClass=varauskalenteri.userinterface.MainProgram**    
Aja sitten testit. Näin varmistetaan, että tietokanta on olemassa, kun testejä ajetaan. Toistaiseksi tietokannan sisältö tuhotaan aina, kun ohjelma käynnistetään uudelleen. Tämä ei ole tietenkään lopullinen
ratkaisu, mutta näin siksi, jotta testien ajaminen ei kaadu tilanteeseen, jossa tietokantaa ei ole olemassa
tai se ei sisällä tarvittavia tauluja.    
Testit voit ajaa komennolla:    
**mvn test**    
Testikattavuusraportin saat komennolla:    
**mvn test jacoco:report**
