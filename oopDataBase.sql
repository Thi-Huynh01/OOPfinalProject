DROP TABLE Movie CASCADE;
DROP TABLE Games CASCADE;

CREATE TABLE Movie(
Mname                   VARCHAR(50),
Streaming_service       VARCHAR(20),
Rating                  VARCHAR(6),
Production_company      VARCHAR(20),
Mdirector               VARCHAR(20),
LeadActor               VARCHAR(20),
SuppActor               VARCHAR(50),
ReleaseYear		INTEGER,
CONSTRAINT Movie_Mname_pk PRIMARY KEY(Mname));

CREATE TABLE Games(
Gname					VARCHAR(50),
Consoles				VARCHAR(50),
Rating					VARCHAR(6),
Developer				VARCHAR(50),
Genre					VARCHAR(50),
ReleaseYear				INTEGER,
CONSTRAINT Game_Gname_pk PRIMARY KEY (Gname));

INSERT INTO Movie VALUES ('Goodfellas', 'Max', 'R', 'Warner Brothers', 'Martin Scorsese', 'Robert De Niro', 'Ray Liotta', 1990);
INSERT INTO Movie VALUES ('Inception', 'Peacock', 'PG-13', 'Warner Brothers', 'Christopher Nolan', 'Leonardo Di Caprio', 'Cillian Murphy', 2010);
INSERT INTO Movie VALUES ('Gladiator', 'Pluto-TV', 'R', 'Universal Pictures', 'Ridley Scott', 'Russell Crowe', 'Joaquin Phoenix', 2000);
INSERT INTO Movie VALUES ('Avatar', 'Prime Video', 'PG-13', '20th Century Studios', 'James Cameron', 'Sam Worthington', 'Zoe Saldana', 2009);
INSERT INTO Movie VALUES ('Titanic', 'Pluto-TV', 'PG-13', '20th Century Studios', 'James Cameron', 'Leonardo Di Caprio', 'Kate Winslett', 1997);
INSERT INTO Movie VALUES ('Superman', 'Max', 'PG-13', 'Warner Brothers', 'Richard Donner', 'Christopher Reeves', 'Gene Hackman', 1978);
INSERT INTO Movie VALUES ('Spiderman', 'Disney Plus', 'PG-13', 'Sony Pictures', 'Sam Raimi', 'Tobey Macguire', 'Kirstin Dunst', 2002);
INSERT INTO Movie VALUES ('Deadpool', 'Disney Plus', 'R', '20th Century Studios', 'Tim Miller', 'Ryan Reynolds', 'Morena Baccarin', 2016);
INSERT INTO Movie VALUES ('Frozen', 'Disney Plus', 'PG', 'Walt Disney Pictures', 'Jennifer Lee', 'Idinia Menzel','Kristen Bell', 2013);
INSERT INTO Movie VALUES ('Barbie', 'Max', 'PG-13', 'Mattel', 'Greta Gerwig', 'Margot Robbie', 'Ryan Gosling', 2023);



INSERT INTO Games VALUES ('Sekiro Shadows Die Twice', 'Xbox, Playstation, PC', 'M', 'FromSoft', 'Action-Adventure', 2019); 
INSERT INTO Games VALUES ('ELDEN RING', 'Xbox, Playstation, PC', 'M', 'FromSoft', 'Action-RPG', 2022);
INSERT INTO Games VALUES ('Halo 3', 'Xbox 360, Microsoft Windows', 'M', 'Bungie Studios', 'First-Person Shooter', 2007);