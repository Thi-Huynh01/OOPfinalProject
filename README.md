IMPORTANT

In order to run this project, you need to set up PostgreSQL and the Database. The tutorial is in a PDF file in the repository.


How the game works:

The player must guess the movie, and each guess lists attributes about the movie. If a "(C)" is appended (and is green), that means that the answer shares that attribute with the player's guess.

NOTE #1- Pressing the "Hint" button will reveal one random attribute of the answer (except the movie name). Pressing this button will take one life.
NOTE #2 - The database is quite limited with only 17 movies in it. Here is the list of the movies in the Database that the player is free to guess:

Goodfellas
Inception
Gladiator
Avatar
Superman
Spiderman
Deadpool
Frozen
Barbie
Interstellar
Tenet
Dune
Joker
Scarface
Jaws
Titanic
Transformers

NOTE #3 - The list of attributes will be displayed in the following order: 

Movie title, Lead Actor, Supporting Actor, Director, Release Year, Streaming Service that the movie is on, Production Company.

TROUBLESHOOTING

"ResultSet not positioned properly" - This means that the Database is not properly set up. Please refer to the PDF file, section "Setting up PostgreSQL"
"No suitable driver found" - This means that the JAR file was not properly inserted into the project. Please refer to the section in the PDF file labeled "Setting up the JAR file"


ADDITIONAL COMMENT - This game is similar to "Wordle", except instead of letters being revealed, it is movie attributes.
