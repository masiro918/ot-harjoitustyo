# vaatimusmäärittely harjoitystyölle - ohjelmistotekniikka  

### Matias Siro 2021 

 

## toiminnallisuuksia 

Ideana on, että jossain päin on olemassa jokin tila (esim. kokoustila tai liikuntasali), johon voi
tehdä varauksia tunniksi tai pidemmäksi aikaa. Tilan voi varata mille päivälle tahansa, siis
maanantaista sunnuntaihin. Tilan voi varata klo 8 -> klo 16. Jotta voi tehdä varauksia, täytyy luoda
käyttäjätunnus. Käyttäjätunnus liitetään aina varaukseen. Luonnollisesti päällekäisiä varauksia
ei voi tehdä. Jos haluaa varata tilan useammaksi tunniksi, on jokainen tunti varattava erikeen.
Admin-käyttäjä voi poistaa minkä tahansa varauksen. “Normaalikäyttäjä” ei siis voi tehdä tätä. 
Tiedot käyttäjistä ja varauksista tallennetaan tietokantaan. Kaikki muut tiedot paitsi salasana
tallennetaan tietokantaan selkokielisenä. Salasanasta tehdään MD5-tiiviste.    

## käyttöliittymät

Sovelluksella on kaksi eri käyttöliittymää: tekstipohjainen ja graafinen. Ainut toiminnallinen ero 
näissä on se, että admin-käyttäjän pystyy luomaan vain tekstipohjaista käyttöliittymää käyttäen. Kun
ohjelma käynnistetään, käyttäjältä kysytään, kumpaako käyttöliittymää hän haluaa käyttää.    

## luonnos käyttöliittymästä

![kuvaluonnos käyttöliittymästä](https://github.com/masiro918/ot-harjoitustyo/blob/master/varauskalenteri/dokumentaatio/luonnos_kayttoliittymasta.jpg)  
