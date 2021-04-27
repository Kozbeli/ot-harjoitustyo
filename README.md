# **Ordermanager** 
Sovelluksen avulla käyttäjä voi luoda käyttäjätunnuksen ja valita tuotevalikoimasta tuotteita ostoskoriin. Sovellukseen voi luoda uuden käyttäjän, mikäli kyseinen käyttäjätunnus on vielä vapaana. Ylläpitäjän tunnuksilla pystyy tarkastelemaan kaikkien tilausten kokonaistilannetta.
Sovellus toimii Helsingin yliopiston Tietojenkäsittelytieteen Ohjelmistotekniika harjoitustyönä. Sovelluksen tarkoituksena on harjoitella laajemman projektin toteuttamista sekä siihen liittyvää dokumentointia ja testausta.
## **Dokumentaatio**

* [Vaatimusmaarittely](./dokumentaatio/vaatimusmaarittely.md)  
* [Arkkitehtuurikuvaus](./dokumentaatio/arkkitehtuurikuvaus.md)  
* [Testausdokumentti](./dokumentaatio/testausdokumentti.md)  
* [Työaikakirjanpito](./dokumentaatio/tuntikirjanpito.md)  

## **Komentorivitoiminnot** 
### **Testaus** 
Ohjelman testaus onnistuu komennolla 
``` 
mvn test 
``` 
Testikattavuusraportin luominen onnistuu komennolla 
```
mvn test jacoco:report
```
Raportti luodaan hakemistoon target/site/jacoco/index.html 

### **Suorittaminen**
Ohjelman suorittaminen onnistuu jar-tiedostolla.
Tiedosto luodaan komennolla:
``` 
mvn package 
``` 
Näin luotu jar-tiedosto löytyy hakemistosta target/Ordermanager-1.0-SNAPSHOT.jar 
Ohjelman voi nyt suorittaa komentoriviltä komennolla 
``` 
java -jar target/Ordermanager-1.0-SNAPSHOT.jar
``` 

### **Checkstyle** 
Ohjelman siisteyden voi tarkistaa komennolla 
``` 
mvn jxr:jxr checkstyle:checkstyle
``` 
Käyttöliittymä on jätetty tarkistusten ulkopuolelle, mutta muuten mahdolliset virheilmoitukset löytyy hakemistosta target/site/checkstyle.html

