CREATE SEQUENCE playerIdSeq;
CREATE SEQUENCE messageIdSeq;
CREATE SEQUENCE gameIdSeq;

CREATE TABLE lgUser (
       usCREATE SEQUENCE playerIdSeq;ername varchar(100) PRIMARY KEY NOT NULL,
       password varchar(100) NOT NULL
);

CREATE TABLE game (
       gameID int DEFAULT gameIdSeq.nextval PRIMARY KEY,
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
       pContamination float CONSTRAINT pContaminationValid
       		   CHECK (pContamination >= 0.0 AND pContamination <= 1.0) NOT NULL,
       pVoyance float CONSTRAINT pVoyanceValid
       		   CHECK (pVoyance >= 0.0 AND pVoyance <= 1.0) NOT NULL,
       pInsomnie float CONSTRAINT pInsomnieValid 
       		   CHECK (pInsomnie >= 0.0 AND pInsomnie <= 1.0) NOT NULL,
       pSpiritisme float CONSTRAINT pSpiritismeValid 
       		   CHECK (pSpiritisme >= 0.0 AND pSpiritisme <= 1.0) NOT NULL,
       lgProp float NOT NULL,
       isDay int CONSTRAINT isDayValid 
       		   CHECK (isDay IN(0, 1)) NOT NULL,
       CONSTRAINT maxSuperieurToMin CHECK (maxPlayer >= minPlayer) 
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
       		   CHECK (hasSpiritisme IN(0, 1)) NOT NULL,
       usedContamination int CONSTRAINT usedContaminationValid
       		   CHECK (usedContamination IN(0, 1)) NOT NULL,
       usedVoyance int CONSTRAINT usedVoyanceValid
       		   CHECK (usedVoyance IN(0, 1)) NOT NULL,
       usedInsomnie int CONSTRAINT usedInsomnieValid
       		   CHECK (usedInsomnie IN(0, 1)) NOT NULL,
       usedSpiritisme int CONSTRAINT usedSpiritismeValid
       		   CHECK (usedSpiritisme IN(0, 1)) NOT NULL,
       proposed int CONSTRAINT proposedValid
       		   CHECK (proposed IN(0, 1)) NOT NULL,
       voted varchar(100) NOT NULL
);

CREATE TABLE message (
       id number(6) DEFAULT messageIdSeq.nextval PRIMARY KEY,
       gameID int REFERENCES  Game (gameID),
       createdAt date,
       username varchar(100) REFERENCES lgUser (username),
       text varchar(300),
       isLG int CONSTRAINT messageIsLGValid
       		   CHECK (isLG IN(0, 1, 2)) NOT NULL
);


--------------------------------------------------------------------------------------
DROP TABLE message;
DROP TABLE player;
DROP TABLE game;
DROP TABLE lgUser;
--------------------------------------------------------------------------------------


INSERT INTO lgUser (username, password) VALUES ('val', 'val');

INSERT INTO game (minPlayer, maxPlayer, nbPlayer, 
       	    	 started, startTime, finished, creator, dayTime, 
		 nightTime, pContamination, pVoyance, pInsomnie, pSpiritisme, lgProp) 
 	VALUES (2, 5, 4, 0, TO_DATE('01-01-2004 13:38:11','DD-MM-YYYY HH24:MI:SS'), 0, 'val', TO_DATE('01-01-2004 13:38:11','DD-MM-YYYY HH24:MI:SS'), TO_DATE('01-01-2004 13:38:11','DD-MM-YYYY HH24:MI:SS'), 0.5, 0.5, 0.5, 0.5, 0.5);

INSERT INTO message (username, text, isLG) VALUES ('val', 'mon premier message', 0);

INSERT INTO player (username, gameID, isLG, alive, hasContamination, hasVoyance, hasInsomnie, hasSpiritisme) VALUES ('val', 6, 0, 1, 0, 0, 0, 0);


