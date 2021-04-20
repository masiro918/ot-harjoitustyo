# Vaatimusmäärittely harjoitystyölle - ohjelmistotekniikka  

### Matias Siro 2021 

 

## Toiminnallisuuksia 

Ideana on, että jossain päin on olemassa jokin tila (esim. Kokoustila tai liikuntasali), johon voi
tehdä varauksia tunniksi tai pidemmäksi aikaa. Tilan voi varata mille päivälle tahansa, siis
maanantaista sunnuntaihin. Tilan voi varata klo 8 -> klo 16. Jotta voi tehdä varauksia, täytyy luoda
käyttäjätunnus. Käyttäjätunnus liitetään aina varaukseen. Luonnollisesti päällekäisiä varauksia
ei voi tehdä. Jos haluaa varata tilan useammaksi tunniksi, on jokainen tunti varattava erikeen.
Ylläpitäjä käyttäjä voi poistaa minkä tahansa varauksen. “Normaalikäyttäjä” ei siis voi tehdä tätä.  

## projektin nykyinen tilanne

Toiminnot, jotka ohjelmalta vaaditaan:    

(toistaiseksi vain tekstikäyttöliittymässä)    

-varauksen tekeminen (sis tarkistuksen, kuten ei päällekkäisiä varauksia ja varauksia ennen klo 8 tai jälkeen klo 16)  [tehty]
-käyttäjätunnuksen luonti (vain basic-käyttäjä) [tehty]
-kaikkien varausten tarkistaminen tietyltä päivältä [tehty]
-käyttäjätunnus liitetään varaukseen [tehty]
-admin-käyttäjä pystyy poistamaan varauksen [ei ole tehty]    

Lisäksi tekstikäyttöliittymässä on vielä sellainen bugi, että jos rekisteröityessä
kirjoittaa tarkistussalasanan väärin tai haluttu käyttäjänimi on jo olemassa, tekstikäyttöliittymä
ei toimi oikein. Tämä täytyy siis myös korjata.    

Edelleenkin (testien toimiviiden takia) aina kun ohjelma käynnistetään, kaikki aikaisempi sisältö tietokannasta tuhotaan.  

## luonnos käyttöliittymästä

![kuvaluonnos käyttöliittymästä](https://github.com/masiro918/ot-harjoitustyo/blob/master/luonnos_kayttoliittymasta.jpg)  
