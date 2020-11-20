# Restserver

For å kjøre server må du gjøre dette i riktig rekkefølge:

1. For at SwappApp skal bruke restserver må du endre denne linjen i `fxui/pom.xml`
    ```xml
       <configuration>
            <mainClass>swapp.ui.SwappApp</mainClass>
       </configuration>
    ```
   til: 
   ```xml
        <configuration>
            <mainClass>swapp.ui.RemoteSwappApp</mainClass>
        </configuration>
   ```
   Da vil `mvn javafx:run` bruke `RemoteSwappApp.java`. 
2. `cd restserver`
3. `mvn org.springframework.boot:spring-boot-maven-plugin:run` vil starte `SwappServer.java` i `restserver/`
4. MERK: Du vil måtte opprette en ny terminal for å kjøre nye kommandoer, det kan gjøres via CTRL+SHIFT+Ø
5. `mvn javafx:run` vil starte opp RemoteSwappApp dersom du gjorde steg 1, ellers vil den bare bruke SwappApp \
    og vil da heller ikke gjøre noen kall til server

For å skru av serveren i gitpod kan du bruke CTRL+C, merk at du vil bli nødt til å åpne en ny terminal for å få kjørt 
nye kommandoer