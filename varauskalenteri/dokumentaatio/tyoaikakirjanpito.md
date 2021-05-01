| paiva | aika    | mitä tein								                                                                                |
| ----- | ------- | ----------------------------------------------------------------------------------------------------------------------------------------------------|
| 24.3. | 1h      | tutustuin viikon 2 materiaaleihin, aloitin vaatimusmäärittelyn tekoa ja loin uuden projektin NetBeansissa harjoitustyölleni ja lisäsin se GitHubiin |
| 27.3. | 1h	  | aloitettu koodaamaan tekstikäyttöliittymää. vaatimusmäärittely on lisätty GitHubiin									|												    |
| 29.3. | 0.5h    | tehty lisää ominaisuuksia tekstikäyttöliittymään 													|
| 30.3. | 24min   | Jaettu luokkia paketteihin ja hahmoteltu arkkitehtuurirakenentta                                                                                    |                                              
| 31.3. | 1h      | Tehty lisää ominaisuuksia koskien tietokantaa. Aloitettu myös testien teko.                                                                         |
| 1.4.  | 1h15min | Korjattu virhe pom-tiedostosta, joka esti testien ajamisen. Tämän jälkeen tein ensimmäiset toimivat testit, jotka testasi tietokantaa, siten että  se testaa, että tietokanta on luotu onnistuneesti tauluineen. |
| 2.4.  | 45min   | Tehty lisää testejä luokalle Database 														| 
| 5.4.  | 1h15min | lisää toimintoja lisätty luokkaan database ja dbservice. Testejä tehty myös luokalle user kuten myös luokan user getterit ja setterit. 
| 6.4.  | 1h15min | tehty testejä luokalle reservation. alettu tekemään mahdollisuutta lisätä reservation-olio tietokantaan. tämä ei ole vielä valmis                   |
| 7.4.  | 45min   | jatkettu samasta aiheesta, mistä viimeksi lopetin: reservation-luokan testejä ja lisää ominaisuuksi itse reservation-luokkaan (=metodeja)|
| 8.4.  | 1h      | dbservice-luokka ja sen testit alkaa olla valmiit. aloitin tekemään uutta luokkaa controller. |
| 10.4. | 15min   | contoller-luokan metodeja koodattu lisää. hieman hahmoteltu myös kyseiselle luokalle testejä |
| 11.4. | 10min   | poistettu repositoriosta 'turhia' tiedostoja, jotka eivät kuulu sinne. |
| 12.4. | 2h40min | ohjelmaa muokattu niin, että se toimii yliopiston koneilla. hash-arvo generaattoria alettu tekemään, mutta huomasin juuri että se ei täysin toimi, vaikka testit jotka tein sille toimivat. käyttöliittymästä voi luoda uuden käyttäjän tietokantaan. jouduin tekemään muutaman muutoksen: jotta kuka tahansa voisi ajaa testit, joka kerta kun ohjelma käynnistetään, tietokannan sisältö tuhotaan. jos tietokantaa ei ole olemassa, ajetaan ensin ohjelma ja tämän jälkeen testit. näin varmistutaan, että tietokanta on olemassa ja se sisältää tarvittavat taulut |
| 15.4. | 1h10min | tehty ominaisuus, että voidaan hakea varauksia tietyltä ajanhaksolta. lisäksi uutena ominaisuutena voidaan myös kysyä tietokannalta jokin kysely, mutta se palauttaa määrän vastauksista (COUNT(*)-sql-komento.
| 16.4. | 50min	  | eipä juuri mitään uutta. debuggerilla tutkin ongelmaa, jossa tietokanta on "lukossa" (locked) |
| 17.4. | 35min   | Controller-luokan tesit valmiit, ja tämän luokan testit menävät läpi. Seuraavaksi tekstikäyttöliittymän pariin |
| 18.4. | 1h      | Viety lisää toiminnallisuuksia tekstikäyttöliittymään. Käyttöliittymästä pystyy nyt reksiteröitymään, kirjautumaan, lisäämään varauksen ja etsimään varauksia. Jouduin tekemään lisämetodeja Conroller-luokkaan, kuten esim. metodin, joka tarkistaa käyttäjän syötteet valideiksi kuten. päivämäärät, kellonajat yms. Lisäksi mm. saanti metodit, joilla etsitään tietokannasta käyttäjätunnusta vastaava id ja toisinpäin. |
| 20.4. | 2h40min | tehty ne vaatimuksen harjoitustyöhön, jotka vaaditaan viikon 4 kohdalla |
| 21.4. | 10min   | alettu tekemään käyttöliittymään toimintoa, jossa admin-käyttäjä pystyy poistamaan varauksen |
| 23.4. | 25min   | Tehty lisää metodeja käyttäjätunnuksen tietojen tarkistamisesta Controller-luokassa. Myös testejä lisätty. |
| 24.4. | 1h15min | Korjattu bugi tekstikäyttöliittymässä: ääkösten lukeminen. Korjattu niin, että kuukausi pitää syöttää ilman ä:tä tai ö:tä. Aloitettu tekemään graafista käyttöliittymää. |
| 25.4. | 35min   | Jatkettu graafisen käyttöliittymän koodamista. Nyt admin pystyy poistamaan varauksia. Lisäksi korjattu bugi, joka koski metodia checkInputs. |
| 26.4. | 50min   | Korjattu virheitä, joita checkstyle ilmoitti. Jar-tiedoston luonti onnistuu. Päivitetty edistyminen README-tiedostoon. |
| 27.4. | 50min   | Tehty 1. relase. Päivitin README-tiedostoa, jossa on ohjeet ohjelman kännistämiseen mm. jar-tiedostosta. Testattu, että graafinen käyttöliittymä aukeaa etätyöpöydällä |
| 28.4. | 50min   | Rakennettu lisää graafisen käyttöliittymän kirjautumis- ja rekisteröitymisikkunaa. |  
| 29.4. | 40min   | Nyt pystyy luomaan käyttäjätunnuksen graafista käyttöliittymää käyttäen. |
| 30.4. | 1h5min  | Graafinen käyttäliittymä on lähes valmis. Tosin mitään toiminnallisuutta siinä ei vielä ole, mutta ulkomuto on lähes valmis. |
| 1.5.  | 1h5min  | Graafinen käyttöliittymä on hienosäätöä vaille valmis. |

Yhteensä aikaa on kulunut: 27h 10min.
