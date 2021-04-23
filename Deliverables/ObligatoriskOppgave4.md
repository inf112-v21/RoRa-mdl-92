# Oblig 4.
 
### DELOPPGAVE 1: TEAM OG PROSJEKT

Rollene fungerer greit og ingen nye endringer for rollene er blitt gjort


Vi har gjort noen tiltak for å forbedre arbeidsfordelingen som å bytte fra kanban til scrum og å bytte teamlead. disse har fungert til å få en jevnere arbeidsfordeling. 
En ting vi kunne blitt bedre på er å kommunisere hvorfor endringer i koden ble gjort, spesielt til dem som skrev koden i første omgang.


Gruppedynamikken har fungert greit. Vi har jevnlig kontakt på discord og lav terskel for å be om hjelp hvis man sitter fast på sin arbeidsoppgave.

##### Retrospektiv:

Helt i starten gikk det ikke så veldig bra. Vi prøvde å bruke kanban, problemet vi fant ut med det var når vi hadde så vidt forskjellige hverdager, passet det ikke å sitte til faste tider og jobbe sammen. Når vi hadde satt opp arbeidsoppgaver på kanban boarded endte det bare opp med at ingen tok seg av noen av oppgavene.
Vi prøvde så å endre til scrum og fordele oppgavene som skulle gjøres under gruppetimene. Dette fungerte mye bedre, alle gjorde mye mer når de visste hva konkrete oppgaver de skulle gjøre og innen når det skulle bli gjort.
Etter dette skiftet var kommunikasjonen ganske god, vi klarte å fordele oppgavene godt og jobbet jevnt utover prosjektet
Vi klarte også og ta hensyn til de forskjellige kunnskapsnivået på teamet og ga ofte oppgaver basert på hvor mye erfaring teammedlemmene hadde.
Hvis vi skulle ha startet dette prosjektet en gang til, hadde vi for det første brukt scrum fra starten av. Vi burde også ha skrevet ned og blitt enige om hvilke spilleregler vi skulle bruke, siden det ble av og til litt forvirring over hvilke regler som skulle implementeres. Vi skulle også vært bedre på å peer reviewe koden og å gå igjennom koden med alle sånn at alle forsto hvordan koden hang sammen. Til slutt burde vi ha hatt litt mer organiserte møter. Det var noen ganger der et par av oss begynte å jobbe under møtene uten at alle hadde ting de kunne gjøre. Noe positivt er at vi har vært flinke til å identifisere hva som ikke fungerte, og har for det meste klart å forbedre disse punktene.

 
### DELOPPGAVE 2: KRAV

Siden forrige gang har vi fått implementert lasere, conveyor belts osv.
Vi har laget en meny. Man kan nå spille med flere enn to spillere.

*Krav vi har prioritert:*

- Fikse bugs med Online component
- Menu screen
- Kunne lese inn map elements fra tilemappet
- Legge til shut down
- Legge til flaggene
 
###### Menu screen:

**Brukerhistorie:**

* Som spiller/host skal jeg kunne velge om jeg skal spille offline, online eller avslutte ved å trykke på knapper på skjermen.
Akseptansekriterier:
Når programmet starter blir vinduet med menyen presentert. Fra menyen skal man kunne starte selve spillet online/offline, koble til som spiller, hoste for andre og velge antall spillere, samt lukke applikasjonen.
 

**Akseptansekriterier:** 

* Når programmet starter blir vinduet med menyen presentert. Fra menyen skal man kunne starte selve spillet online/offline, koble til som spiller, hoste for andre og velge antall spillere, samt lukke applikasjonen.


**Arbeidsoppgaver:**

* Lage ny klasse MenuScreen.java og ta i bruk LibGDX’s Screen klasse for å opprette et meny vindu.



###### Shut Down:

**Brukerhistorie:**

* Som spiller vil kunne shutte down roboten min for å få tilbake liv.

**Akseptansekriterier:**

* Ha en knapp på skjermen som aktiverer shut down.

**Arbeidsoppgaver:**

* Lage en knapp som du kan trykke på for å shutte down, du kan ikke velge noen kort hvis du velger å shutte down og på slutten av turen din vil roboten din fjerne skade.

###### Flagg:

**Brukerhistorie:**

* Som spiller vil jeg kunne gå på flag for å vinne spillet

**Akseptansekriterier:**

* Ha et nummer av flag på skjermen som robotene må gå på for å vinne spillet, hvis de går på et flag blir spawnpointet deres satt til det flagget

**Arbeidsoppgaver:**

* Lage en flaggklasse som kan plasseres rundt omkring på mappet, flagget skal ha et tall som indikerer i hvilken rekkefølge robotene må besøke flaggene. Robotene må ha en måte å holde rede på hvilken flag de allerede har besøkt. når alle flaggene er besøkt så av en robot så vinner den spilleren.

 
##### BUGS:

Resizing av skjermen ødelegger knappe- og brettelement plasseringer.

### DELOPPGAVE 3: PRODUKTLEVERANSE OG KODEKVALITET

**Forskjellen i commit mengde mellom gruppemedlemmene skyldes at:**

* Hvis vi 2 stykker jobber sammen om å løse en oppgave, så er det kun den ene så committer.
* Vi tok hensyn til de med mindre kode erfaring, så de fikk mindre/enklere oppgaver.
* Noen committer mange mindre endringer (spesielt når bugs skulle fikses), mens andre committer bare når de ble helt ferdig med alle oppgavene sine

Manuell test er beskrevet i \RoRa-mdl-92\documentation\Manual Tests\Manual Test - Visible Wall Collision.pdf. 
Det meste av testing er automatisk. Alle boardelements blir automatisk testet at de gjør som de skal. I tillegg testing for robot hp/respawn/posisjon/bevegelse, kort, andre knapper m.m. 
 
Klassediagram ligger i : \RoRa-mdl-92\documentation\RoboRally Class Diagram.png
