[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitpod.idi.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2020/gr2069/gr2069)

# Swapp
Prosjektet swapp skal gjøre det mulig for brukere å bytte ting. 
**[Se her for flere detaljer om hva appen handler om.](swapp/OM_PROSJEKTET.md)**

# Organisering av koden:

- swapp/
    - core/              &nbsp;&nbsp;&nbsp;&nbsp;   &nbsp;&nbsp;&nbsp;&nbsp;              Kjernemodulen, domenelogikk og persistens
        - src/main/java/swapp/core  &nbsp;&nbsp;&nbsp;&nbsp;    som utgjør domenelogikk
        - src/main/java/swapp/core   &nbsp;&nbsp;&nbsp;&nbsp;   som utgjør persistenslaget
    - fxui/                         &nbsp;&nbsp;&nbsp;&nbsp;   &nbsp;&nbsp;&nbsp;&nbsp;   Modulen håndtere brukegrensesnittet
        - src/main/java/swapp/ui/    &nbsp;&nbsp;&nbsp;&nbsp;   javafx-filer
        - src/main/resources/swapp/ui/ &nbsp;&nbsp;&nbsp;&nbsp; FXML-filer



## Test-lokasjoner:
- src/test/java/swapp                   Testing av domenelogikk og persistens
- src/test/java/swapp/ui/               Testing av javafx

## Domenelaget
Appens interne logikk vil inneholde objekter som Bruker, Annonse, Melding og relasjonene mellom dem.

## Brukergrensesnittlaget
Brukergrensesnittlaget vil vise brukere annonser, melding og mulighet for å logge inn og registrere seg. 

## Persistenslaget
Prosjektet brukerer json til å lagre data. **[Se her for refleksjon over lagringsmetode](swapp/OM_PROSJEKTET.md)**