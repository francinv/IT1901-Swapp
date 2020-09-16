[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitpod.idi.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2020/gr2069/gr2069)

# Swapp
Prosjektet swapp skal gjøre det mulig for brukere å bytte ting. 
[Se her for flere detaljer om hva appen handler om.](OM_PROSJEKTET.md)

# Organisering av koden:

- src/main/java
    - /swapp/core som utgjør domenelogikk
    - /swapp/ui som håndterer brukergrensesnittet
    - /swapp/json for persistens. 

FXML-filen befinner seg i: 

- src/main/resources/swapp/ui

## Test-lokasjoner:
- src/test/java/swapp/core Testing av domenelogikk
- src/main/java/swapp/ui Testing av javafx

## Domenelaget
Appens interne logikk vil inneholde objekter som Bruker, Annonse, Melding og relasjonene mellom dem.

## Brukergrensesnittlaget
Brukergrensesnittlaget vil vise brukere annonser, melding og mulighet for å logge inn og registrere seg. 

## Persistenslaget
Prosjektet brukerer json til å lagre data. 