# ohjelmistotekniikka, harjoitustyö

[linkki työaikakirjanpitoon](https://github.com/masiro918/ot-harjoitustyo/blob/master/varauskalenteri/tyoaikakirjanpito.md)  
[linkki vaatimusmäärittelyyn](https://github.com/masiro918/ot-harjoitustyo/blob/master/varauskalenteri/vaatimusmaarittely.md)

## ohjelman tämänhetinen tilanne

viikon 4 tilanne:    

## projektin nykyinen tilanne

### viikon 4 tilanne:
Toiminnot, jotka ohjelmalta vaaditaan:    

(toistaiseksi vain tekstikäyttöliittymässä)    

-varauksen tekeminen (sis tarkistuksen, kuten ei päällekkäisiä varauksia ja varauksia ennen klo 8 tai jälkeen klo 16)  [tehty]  
-käyttäjätunnuksen luonti (vain basic-käyttäjä) [tehty]  
-kaikkien varausten tarkistaminen tietyltä päivältä [tehty]  
-käyttäjätunnus liitetään varaukseen [tehty]  
-admin-käyttäjä pystyy poistamaan varauksen [ei ole tehty]    

Myös hash-arvon muodostaminen onnistuu nyt mille tahansa salasanalle.  
Lisäksi tekstikäyttöliittymässä on vielä sellainen bugi, että jos rekisteröityessä
kirjoittaa tarkistussalasanan väärin tai haluttu käyttäjänimi on jo olemassa, tekstikäyttöliittymä
ei toimi oikein. Tämä täytyy siis myös korjata.    

Edelleenkin (testien toimiviiden takia) aina kun ohjelma käynnistetään, kaikki aikaisempi sisältö tietokannasta tuhotaan.    

### viikon 3 tilanne:
    
Kyseessä on Maven-projekti. Javan versio on 11. Ohjelma toimii toistaiseksi vain siten, että voit luoda uuden
käyttäjätunnuksen, joka tallennetaan tietokantaan. Salasanan hash-arvo ei toimi kaikille salasanoille (täytyy
selvittää miksi). Kun luot testikäytäjän, käytä vaikka salasanaa 'salasana'. Se ainakin toimii.    
Jotta testit toimisivat, käynnistä ensin ohjelma komentoriviltä komennolla  
**mvn compile exec:java -Dexec.mainClass=userinterface.MainProgram**  
Aja sitten testit. Näin varmistetaan, että tietokanta on olemassa, kun testejä ajetaan. Toistaiseksi tietokannan sisältö tuhotaan aina, kun ohjelma käynnistetään uudelleen. Tämä ei ole tietenkään lopullinen
ratkaisu, mutta näin siksi, jotta testien ajaminen ei kaadu tilanteeseen, jossa tietokantaa ei ole olemassa
tai se ei sisällä tarvittavia tauluja.    
Testit voit ajaa komennolla:  
**mvn test**  
Testikattavuusraportin saat komennolla:  
**mvn test jacoco:report**
