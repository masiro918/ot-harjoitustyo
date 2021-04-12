# ohjelmistotekniikka, harjoitustyö

[linkki työaikakirjanpitoon](https://github.com/masiro918/ot-harjoitustyo/blob/master/tyoaikakirjanpito.md)  
[linkki vaatimusmäärittelyyn](https://github.com/masiro918/ot-harjoitustyo/blob/master/vaatimusmaarittely.md)

## ohjelman tämänhetinen tilanne

Kyseessä on Maven-projekti. Javan versio on 11. Ohjelma toimii toistaiseksi vain siten, että voit luoda uuden
käyttäjätunnuksen, joka tallennetaan tietokantaan. Salasanan hash-arvo ei toimi kaikille salasanoille (täytyy
selvittää miksi). Kun luot testikäytäjän, käytä vaikka salasanaa 'salasana'. Se ainakin toimii.    
Jotta testit toimisivat, käynnistä ensin ohjelma komentoriviltä komennolla mvn compile exec:java -Dexec.mainClass=userinterface.MainProgram  
Aja sitten testit. Näin varmistetaan, että tietokanta on olemassa, kun testejä ajetaan. Toistaiseksi tietokannan sisältö tuhotaan aina, kun ohjelma käynnistetään uudelleen. Tämä ei ole tietenkään lopullinen
ratkaisu, mutta näin siksi, jotta testien ajaminen ei kaadu tilanteeseen, jossa tietokantaa ei ole olemassa
tai se ei sisällä tarvittavia tauluja.    
Testit voit ajaa komennolla: mvn test  
Testikattavuusraportin saat komennolla: mvn test jacoco:report
