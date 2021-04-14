Meeting Reports
05/02/2021 Time (16:30 - 18:00)
Members present: Herman Fleischer, Ola M. Johannessen, Pål a H Bentsen, Sassan Zomorodi, Thomas Hillesland.
1. Made sure that everyone had succesfully set up GIT and IntelliJ and was ready to work, should it be needed.
2. Discussed the overall structure of the project, identifying the objects that make up the games and the class structure.
3. Assigned tasks to work on until 11/02/2021, with a general emphasis on Architecture over practical code.

Set Tasks:
1. Thomas Hilleland: set up a surface level interpretation of the project that meets the minumum requirements including a GUI board and basic controllable robot.
2.  Herman Fleischer: begin working on the Board object, including setting up a Direction enum.
3. Sassan Zomorodi: begin workign on the Game object that would handle the order of events in the game.
4. Pål A H Bentsen: start working on the Robot object.
5. Ola M. Johannessen: Identify game elements, get rules in more practical reference form, and setup UML diagram.


10/02/2021 Time (12:15 – 14:00)

Members present: Herman Fleischer, Pål A H Bentsen, Sassan Zomorodi, Thomas Hillesland.

Wrote the following User Histories:
1. jeg som spiller vil kunne se brettet, for å kunne orientere meg for hvor ting er
 - få et bilde av brettet til å vise i et vindu på skjermen
2. jeg som spiller vil kunne se brikker på spillebrettet, sånn at jeg kan vite hvor de er på brettet og i forhold til andre ting på brettet
 - få spillerens brikke til å vises på skjermen i korrekt grid lokasjon
 - vise klart hvilken direksjon brikken står
3. jeg som spiller vil kunne bevege brikken min, for å få progresjon i spillet
 - få roboten til å bevege seg frem og bakover med ↑ og ↓ piltastestene, og snu seg 90 grader med  ← og  → piltastene  
4. jeg som spiller vil ha et flagg på brettet, sånn at jeg har et mål å bevege meg mot
 - få vist et flag på brettet på samme måte så roboten blir vist
5. jeg som spiller vil kunne vinne spillet med å flytte roboten min på flagget
 - hvis roboten går oppå flagget, så må et eller annet skje. f.eks. spillet lukker seg.

Accomplished the first 2 user histories.

Everyone had accomplished their goals from previous meeting.
11/02/2021 Time (10:15  – 12:00)
Members Present: Herman Fleisher, Ola M. Johannessen, Pål A H Bentsen, Sassan Zomorodi, Thomas Hillesland.
1. Coordinated on getting the robot to move.
2. Got the robot to win by visiting the flags.
3. Herman found and started with Tilemaps.


17/02/2021
Members Present: Herman Fleischer, Pål A H Bentsen, Thomas Hillesland, Ola Meling Johannessen.

Sassan has been reallocated to another group.
Ola Meling Johannessen will take over Sassan's responsibilities.
Agreed to meetup 20/02/2021 12:00 to go through retrospective and prepare for next sprint.


20/02/2021 Time (12:20 - 13:20)
Members Present: Herman Fleischer, Pål A H Bentsen, Thomas Hillesland, Ola Meling Johannessen.

Communicated and did a retrospective. (See file Retrospective 20022021)

Made the following User Histories:

ARD - As the rules demand.
brukerhistorier 2:
1. jeg som spiller vil kunne gi kort til roboten for å bevege den ARD.
- implementer kort klassen
- implementer forskjellige kort
2. jeg som robot vil kunne utføre kommandoer basert på programkort ARD.
- roboten skal kunne lagre kommando kort
- roboten skal kunne utføre et kort når den er bedt om det
3. jeg som spiller vil kunne spille med andre spillere over nettet
4. jeg som spiller vil kunne kommandere roboten til å reparere seg ARD.
- det må være en knapp eller keyboard tast som vil få roboten til å reparere seg
5. jeg som robot vil kunne reparere seg selv ARD.
- roboten må ha en funksjon som healer den selv
6. jeg som robot vil kunne skru av for en runde ARD.
- hvis roboten ikke blir tildelt noen kort, er den skrudd av.
7. jeg som robot vil kunne ta skade ARD.
8. jeg som robot vil kunne låse program kort på plass ARD.
9. jeg som robot vil kunne bli ødelagt ARD.
10. jeg som robot vil kunne plasseres tilbake på brettet ARD.
11. jeg som robot vil kunne lagre et ‘respawn point’ ARD.

Board Elements
12. jeg som belte vil kunne flytte på roboter som står på meg ARD.
13. jeg som tannhjul vil kunne rotere på roboter som står på meg ARD.
14. jeg som pusher vil kunne skyve roboter som står ved meg ARD.
15. jeg som laser vil kunne skyte roboter ARD.

Spill
16. jeg som spill vil kunne avgjøre hvilken rekkefølge roboter gjør ting ARD.
17. jeg som spill vil kunne be roboter og utføre kommandoer ARD.
18. jeg som spill vil kunne be belter å operere ARD.
19. jeg som spill vil kunne be tannhjul å operere ARD.
20. jeg som spill vil kunne be pushere å operere ARD.
21. jeg som spill vil kunne be lasere å skyte ARD.

Detemined Workflow for the coming sprint.
1. See what User Histories are being worked on or finished in the project board.
2. Pick an available User History, and add it to the project board as in progress.
3. Work on said project.
4. Mark as finished when done.
5. Return to step 1.
Sidenote on step 3: Use project board to announce which files are in use to prevent Git issues.
If possible, try to work with others.


10/03/2021 Time (12:15 - 13:00)
Members Present: Herman Fleischer, Pål A H Bentsen, Thomas Hillesland, Ola Meling Johannessen.

assigned tasks for everyone

went over the current code so that everyone could get up to speed on how it works

17/03/2021 Time (12:15 - 13:45)
checked up on what everyone have done the past week

reviewed the feedback from obligitory assignment 2

checked out obligitory assignment 3 and wrote some begining points for it

assigned new tasks

Ola - finetune and fix the robot movements. create belts, pushers and walls

Herman - creat a gui startscreen

Pål - create classes for the holes, gears

Thomas - implement robot respawn



24/03/2021 Time (12:15 - 13:45)
checked up on what everyone have done the past week

helped Pål with some of the problems he had with his task

finished up a couple of features, like the damage, lasers and respawn

reviewed what featers we want to have done for the 3. obligatory assignment and what can wait

agreed to meet again tomorrow to write up all the points on the 3. obligatory assignment


24/03/2021 Time (12:15 - 13:00) && (14:00 - 16:00)
went trough and wrote some points on the obligatory assignment 3

began writing some tests and better comments on the code

tried to eliminate some of the new bugs


07/04/2021 Time (12:15 - 13:35)
started looking at obligatory assignment 4

wrote down some tasks we wanted to have completed for the final assignment

devided up some of those tasks that we should work on for next week



07/04/2021 Time (12:15 - 13:20)
catched up on what everyone have gotten done the last week

finalized the last tasks that needed to get done for the final assignment 
- add more commentsk, docstrings and tess
- add a main menu screen
- make it so all map data gets loaded from the tilemap
- make it so flags function how they are supposed to

devided up those remaining tasks between us

ola was not present at this meeting
