[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitpod.idi.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2020/gr2069/gr2069)

# Swapp
Prosjektet swapp skal gjøre det mulig for brukere å bytte ting. 
**[Se her for flere detaljer om hva appen handler om.](swapp/OM_PROSJEKTET.md)**

# Organisering av koden:

- swapp/
    - core/              &nbsp;&nbsp;&nbsp;&nbsp;   &nbsp;&nbsp;&nbsp;&nbsp;              Kjernemodulen, domenelogikk og persistens
        - src/main/java/swapp/core  &nbsp;&nbsp;&nbsp;&nbsp;    som utgjør domenelogikk
        - src/main/java/swapp/json   &nbsp;&nbsp;&nbsp;&nbsp;   som utgjør persistenslaget
    - fxui/                         &nbsp;&nbsp;&nbsp;&nbsp;   &nbsp;&nbsp;&nbsp;&nbsp;   Modulen håndtere brukegrensesnittet
        - src/main/java/swapp/ui/    &nbsp;&nbsp;&nbsp;&nbsp;   javafx-filer
        - src/main/resources/swapp/ui/ &nbsp;&nbsp;&nbsp;&nbsp; FXML-filer



## Test-lokasjoner:
- swapp/core/src/test/java/swapp/                Testing av domenelogikk og persistens
- swapp/fxui/src/test/java/swapp/ui/             Testing av javafx

## Domenelaget
Appens interne logikk inneholder objekter som Swapp(appen), User, Ad, Transaction og relasjonene mellom dem.

## Brukergrensesnittlaget
Brukergrensesnittlaget begynner på en innloggingsside, med mulighet for en registreringsside. videre vises brukeres annonser, profilside, annonsebeskrivelser, opretting av annonser mm.

## Persistenslaget
Prosjektet bruker json til å lagre data. **[Se her for refleksjon over lagringsmetode](swapp/OM_PROSJEKTET.md)**

# Restserver
**[Se her for informasjon om hvordan man kjører restserver](swapp/restserver/README.md)**

## Nyttige Maven kommandoer
- mvn clean install
går igjennom modulene og utfører clean og install så du kompilerer prosjektet fra scratch
- mvn test
kjører testene i prosjektet
- mvn javafx:run
kjører javafx appen
- mvn jacoco:report
gir rapport om testdekningsgrad i prosjektet
- mvn checkstyle:check
sjekker prosjektet utifra checkstyle pluginen og gir tilbakemelding
- mvn spotbugs:check
sjekker prosjektet for bugs i koden med spotbugs plugin