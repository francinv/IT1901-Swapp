# SWAPP
Appen Swap skal gjøre det mulig for brukere å bytte eller gi bort ting. Målgruppen er studenter.
**[Se her for flere detaljer om hva appen handler om.](OM_PROSJEKTET.md)**

# Arkitekturdiagram

![Arkitektur](diagram.png)


# Maven-kommandoer
- mvn clean install 

- mvn test (Kjører alle tester)

- mvn -e javafx:run -rf :fxui (kjører appen, også mulig å skrive cd fxui; mvn javafx:run)

- mvn jacoco:report (test coverage)

- mvn checkstyle:check  (Kjør spotbugs)

- mvn spotbugs:check
- mvn -pl MODULENAME -Dtest=CLASSNAME test  (Test en enkelt klasse)