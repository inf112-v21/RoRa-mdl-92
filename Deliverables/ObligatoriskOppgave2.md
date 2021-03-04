Deloppgave 1:

"Hvordan fungerer rollene i teamet? Trenger dere å oppdatere hvem som er teamlead eller kundekontakt?"

* Everyone is fulfilling their roles sufficiently. The ‘Sekretær’ role has been integrated into the ‘teamlead’ role.

"Trenger dere andre roller? Skriv ned noen linjer om hva de ulike rollene faktisk innebærer for dere."

* No, nothing comes to mind.

"Er det noen erfaringer enten team-messig eller mtp prosjektmetodikk som er verdt å nevne? "

og

"Synes teamet at de valgene dere har tatt er gode? Hvis ikke, hva kan dere gjøre annerledes for å forbedre måten teamet fungerer på?"


* In terms of Project methodology, we have no project methodology. The workload is unbalanced, the tasks/priorities unclear/undetermined, but our communication is good.
If we better specify goals using tools like User Histories then we may be better able to organise, focus, and delegate.
Perhaps doing some coordinated programming may allow us to counteract some of the differences in experiences.

"Hvordan er gruppedynamikken?"

* Get along decently with no noticeable boons or problems resulting from our group dynamic.

"Hvordan fungerer kommunikasjonen for dere?"

* We are keeping each other pretty well updated, and meet often.

"Under vurdering vil det vektlegges at alle bidrar til kodebasen. Hvis det er stor forskjell i hvem som committer, må dere legge ved en kort forklaring for hvorfor det er sånn. Husk å committe alt. (Også designfiler)

* The workload has been unbalanced for two primary reasons.
Firstly, due to the lack of structure in a new project we haven’t been able to delegate enough tasks that could be worked on simultaneously.
Secondly, the difference in experience levels means that some of us are simply more able to output work than others, and we have yet to take any measures to balance that.

"Bli enige om maks tre forbedringspunkter fra retrospektivet, som skal følges opp under neste sprint."

* Establish clear and obvious goals, and distribute them appropriately.
Do some  coordinated programming.



Deloppgave 2:

* Krav

***1. Spille fra flere maskiner***

**Brukerhistorie:**

jeg som spiller vil kunne velge om jeg vil hoste et spill eller koble meg til et hostet spill

**Akseptansekriterier:**

Singleplayer/Multiplayer option
Kun når begge spillere har valgt kort, flyttes robotene

**Arbeidsoppgaver:**

Gjøre slik at en kan hoste et spill
gjøre slik at en kan bli med i et hostet spill
som host kunne få andres valg av kort
som host kunne sende alles valg av kort til resten av spillerene
kunne sende valg av kort til host
kunne få andres valg av kort fra host

***2. Dele ut kort***

**Brukerhistorie:**

jeg som spiller vil kunne få kort som jeg kan spille

**Akseptansekriterier:**

kunne gi kort til en player som de skal kunne spille

**Arbeidsoppgaver:**

legge til forskjellige kort til spillernes hånd

***3. Velge 5 kort***

**Brukerhistorie:**

jeg som spiller vil kunne velge 5 programkort som skal utføres

**Akseptansekriterier:**

vise kort
kunne velge 5 kort
kunne utføre de 5 kortene

**Arbeidsoppgaver:**

lage en metode for kortene som viser et bildet basert på hva kort det er
lage en måte å kunne trykke på kortene for å velge dem
kunne trykke på kortene igjen for å fjerne valget
kunne trykke på en knapp for å utføre valget av kort

***4. Bevege robot ut fra valgte kort***

**Brukerhistorie:**

jeg som robot vil kunne utføre kommandoer basert på programkort

**Akseptansekriterier:**

når et kort blir spilt skal roboten utføre den oppgaven som kortet sier

**Arbeidsoppgaver:**

legge til alle de forskjellige kortene som kan bli spilt 
legge til en metode for kortene som påvirker roboten basert på hvilket kort det er


***-Siden forrige gang har vi fått til å:***

Tegne kartet på skjermen (nå et tmx tilemap, ikke bare en png)
Tegne roboten til både den som hoster og den som er client
Tegne kortene på skjermen og gi feedback til bruker om hvilke kort som er valgt og i hvilken rekkefølge de spilles
Få robotene til å bevege seg ved bruk av kortene
Spiller “vinner” når roboten går på samme tile som flagget

***-Prioritering for oppgaver fremover:***

Utvide logikken rundt kartet
Dvs.Implementere kart elementer som hull, laser, conveyor belts osv.
Organisere klassene og koden bedre
Dvs. Rydde vekk unødvendig kode, skrive bedre (og mer) kommentarer, flytte kode fra en klasse til en annen der den kanskje passer bedre inn.
 
Deloppgave 3:

To use this program: when opening the window, enter in the console window if you want to play multiplayer or singleplayer, and if you want to host or not.
click on cards to program your robot and click the "P" keyboard button to execute the commands. lower left robot is player 1 and upper right is player 2 Host is always player 1.
To be able to play with others over the internet the host player must have port forwarded.
