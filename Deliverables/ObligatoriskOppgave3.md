Oblig 3
	oppgave 1 (Team og prosjekt)
vi har byttet teamlead fra Ola til Thomas fordi, etter obligen hvor vi opplevde en veldig stor skjevfordeling i fordelingen av arbeidet,
ble vi enige om at noe måtte gjøres for å prøve å få en forbedring
ellers fungerer rollene greit nok

vi valgte originalt å bruke kanban, men etter som vi jobbet litt så fant vi ut at måten vi jobbet på passet bedre med scrum.
vi har gjort noen tiltak for å forbedre arbeidsfordelingen som å bytte fra kanban til scrum og å bytte teamlead. disse har fungert 
til å få en jevnere arbeidsfordeling. Fra tidligere snakket vi om å få bedre oversikt over hva alle skal gjøre mellom møtene, og dette har vi klart å bli bedre på.
vi prioriterte til denne innleveringen å få et spill som er spillbart med alle reglene på plass og å få vekk en del bugs

forbedringspunkter:
 1. bli flinkere te å oppdatera project boardet
 2. bli flinkere å bruke forskjellige branches når vi jobber på prosjektet
 3. skrive mer tester og kommentarer for koden vår

gruppedynamikken har fungert greit. Vi har jevnlig kontakt på discord og lav terskel for å be om hjelp hvis man sitter fast på sin arbeidsoppgave.
 
	oppgave 2 (Krav)
Nye krav:
Conveyor belts, holes, lasers, walls, cogs skal fungere som de er ment til å fungere.
Lage en main menu screen, lobby osv.
Kunne spille med fler enn 2 spillere.

Brukerhistorier, Akseptansekriterier og arbeidsoppgaver:
Robot
Brukerhistorie:
 - jeg som robot vil kunne ta skade ARD.
Akseptansekriterier:
 - roboten må kunne ta skade
Arbeidsoppgave:
 - lag en metode i robot klassen som trekker fra livene til roboten

Brukerhistorie:
 - jeg som robot vil kunne låse program kort på plass ARD.
Akseptansekriterier:
 - basert på hvor mye skade roboten har, må spilleren trekke mindre kort
Arbeidsoppgave:
 - endre trekk kort funksjonen i spilleren sånn at du trekker mindre kort hvis roboten din har tatt skade

Brukerhistorie:
 - jeg som robot vil kunne bli ødelagt ARD.
Akseptansekriterier:
 - roboten må sjekke når den har mindre enn 0 liv igjen
Arbeidsoppgave:
 - endre ta damage metoden slik at den skjekker om du har mindre enn 0 liv


Brukerhistorie:
 - jeg som robot vil kunne plasseres tilbake på brettet ARD.
Akseptansekriterier:
 - hvis en robot dør skal de gå tilbake til forrige respawn point
Arbeidsoppgave:
 - lage en respawn metode som plaserer roboten på respawn punktet


Brukerhistorie:
 - jeg som robot vil kunne lagre et ‘respawn point’ ARD.
Akseptansekriterier:
 - en robot må ha en måte å lagre hvor på mappet de skal respawne
Arbeidsoppgave:
 - ha 2 variabler som lagrer et respawn punkt i roboten

Spill

Brukerhistorie:
 - jeg som spill vil kunne avgjøre hvilken rekkefølge roboter gjør ting og bestemme når 
   det skal skje ARD.
Akseptansekriterier:
 - Spillet sjekker prioriteten til kortene så skal bli spilt og spiller dem i rett rekkefølge.
 - spillet må kunne be roboten utføre en kommando ARD.
Arbeidsoppgave:
 - lage en metode i game så sorterer kortene så skal bli spilt sånn at de blir spilt i rett rekkefølge

Brukerhistorie:
 - jeg som spill vil styre når brettelementer gjør ting ARD.
Akseptansekriterier:
 - hver tur skal spillet sjekke om en spiller står på et belte, hvis de gjør det skal de bli 
   beveget i retningen til belte
 - hver tur skal spillet sjekke om en spiller står på et tannhjul, hvis de gjør det skal de bli 
   rotert rundt
 - hver tur skal spillet sjekke om en spiller står ved en pusher, hvis de gjør det skal de bli skubbet
 - hver tur skal spillet sjekke om en spiller står i en laser, hvis de gjør det skal de ta 
   skade
 - hver tur skal spillet sjekke om en spiller står på et hull, hvis de gjør det skal de dø
Arbeidsoppgave:
|- lage en metode som skjekker om en robot står på et belte og beveger dem fram hvis de gjør det.
 - lage en metode som skjekker om en robot står på et tannhjul og roterer dem i rett retning hvis de gjør det
 - lage en metode som skjekker om en robot står på en pusher, og skyver dem hvis de gjør det
 - lage en metode som skjekker om en robot blir truffet av en laser og skader roboten hvis de gjør det
 - lage en metode som skjekker om en robot spår på et hull og dreper roboten hvis de gjør det


	oppgave 3 (Produktleveranse og kodekvalitet)
  To use this program: when opening the window, enter in the console window if you want to play multiplayer or singleplayer, and if you want to host or not.
  if you are host you must also enter how many players you want to play with
  
  click on cards to program your robot and click the "P" keyboard button to execute the commands. Lower left robot is player 1 and upper right is player 2 Host is always player 1.
  To be able to play with others over the internet the host player must have port forwarded.
  
  known bugs: 
  sometimes the game stops responding if a robot takes damage
	The game stops responding if you play with more 
  then 1 other player over the internet
  (works fine if you just have multiple instances on the same
  computer)
