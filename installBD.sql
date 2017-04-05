CREATE SEQUENCE playerIdSeq;
CREATE SEQUENCE messageIdSeq;

CREATE TABLE lgUser (
       username varchar(100) PRIMARY KEY NOT NULL,
       password varchar(100) NOT NULL
);

CREATE TABLE game (
       gameID int PRIMARY KEY NOT NULL,
       minPlayer int NOT NULL,
       maxPlayer int NOT NULL,
       nbPlayer int,
       started int CONSTRAINT startedValide
       		   CHECK (started IN(0, 1)) NOT NULL,
       startTime date NOT NULL,
       finished int CONSTRAINT finishedValid 
       		   CHECK (finished IN(0, 1)) NOT NULL,
       creator varchar(100) REFERENCES lgUser (username),
       dayTime date NOT NULL,
       nightTime date NOT NULL,
       pContamination int CONSTRAINT pContaminationValid
       		   CHECK (pContamination IN(0, 1)) NOT NULL,
       pVoyance int CONSTRAINT pVoyanceValid
       		   CHECK (pVoyance IN(0, 1)) NOT NULL,
       pInsomnie int CONSTRAINT pInsomnieValid 
       		   CHECK (pInsomnie IN(0, 1)) NOT NULL,
       pSpiritisme int CONSTRAINT pSpiritismeValid 
       		   CHECK (pSpiritisme IN(0, 1)) NOT NULL,
       lgProp float NOT NULL
);

CREATE TABLE player (
       id number(6) DEFAULT playerIdSeq.nextval PRIMARY KEY,
       username varchar(100) REFERENCES lgUser (username),
       gameID int REFERENCES  Game (gameID),
       isLG int CONSTRAINT isLGValid
       		   CHECK (isLG IN(0, 1)) NOT NULL,
       alive int CONSTRAINT aliveValid
       		   CHECK (alive IN(0, 1)) NOT NULL,
       hasContamination int CONSTRAINT hasContaminationValid
       		   CHECK (hasContamination IN(0, 1)) NOT NULL,
       hasVoyance int CONSTRAINT hasVoyanceValid
       		   CHECK (hasVoyance IN(0, 1)) NOT NULL,
       hasInsomnie int CONSTRAINT hasInsomnieValid
       		   CHECK (hasInsomnie IN(0, 1)) NOT NULL,
       hasSpiritisme int CONSTRAINT hasSpiritisme
       		   CHECK (hasSpiritisme IN(0, 1)) NOT NULL
);

CREATE TABLE message (
       id number(6) DEFAULT messageIdSeq.nextval PRIMARY KEY,
       gameID int REFERENCES  Game (gameID),
       createdAt date,
       text varchar(300),
       isLG int CONSTRAINT messageIsLGValid
       		   CHECK (isLG IN(0, 1)) NOT NULL
);


--------------------------------------------------------------------------------------
DROP TABLE message;
DROP TABLE player;
DROP TABLE game;
DROP TABLE lgUser;
--------------------------------------------------------------------------------------

