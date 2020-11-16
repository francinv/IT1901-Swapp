# Kjernemodulen (core)

Dette laget har domenelogikk og persistens.
## Domenelaget

Sidens logikk er definert her. User-objektet, Ad-objektet, Transaction og Swapp, samt Lister for alle instansene av disse
 klassene. Swapp objektet er selve appen. dette har en liste med registrerte brukere, som legges til, fjernes og aksesseres etter hva appen skal gjøre.
 Swapp har også et AdList objekt definert, som er listen over ads man kan interagere med.
 Laget finnes her **[her](src/main/java/swapp/core/README.md)**.

## Persistenslaget

Vi bruker JSON for lagring av objekter. Laget finnes **[her](src/main/java/swapp/json/README.md)**.

