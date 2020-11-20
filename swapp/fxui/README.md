# Brukergrensesnittmodulen (fxui)

Dette laget viser dataen fra core i en GUI. det er 6 forskjellige fxml filer med hver sin kontroller, som representerer de forskjellige delene av appen.
Når man kjører FXApp, som starter applikasjonen, begynner man på innlogginssiden. derfra kan man logge inn eller registrere seg i en ny side,
før man går videre til hovedsiden, fxml filen ListOfAds. Her vises alle ads regisrert i appen, og man kan interragere med dem ved å gå inn i AdDetail,hvor man får beskrivelse av gjenstanden mm.
man kan også gå inn på sin profilside og se sine tidligere transaksjoner. sekvensdiagram viser hva som skjer når en bruker vil gå inn på en spesiell ad i listen.
## Brukergrensesnittlaget
Modulen bruker javafx og FXML-filer. 
 **[Javafx-koden befinner seg i ](src/main/java/swapp/ui/README.md)**

    src/main/java/swapp/ui/

   FXML befinner seg i 

    src/main/resources/swapp/ui/

## Sequence diagram

![Sequence diagram](sequenceD.png)

g