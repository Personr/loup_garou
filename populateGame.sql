DELETE FROM player WHERE gameID = 81;

INSERT INTO player (username, gameID, isLG, alive, hasContamination, hasVoyance, hasInsomnie, hasSpiritisme, usedContamination, usedVoyance, usedInsomnie, usedSpiritisme, proposed, voted, nbVotes, justDied, justContaminated, justBitten, contacted) VALUES ('y',81,0,1,0,0,0,0,0,0,0,0,0,' ', 0,0,0,0,0);


UPDATE game SET started = 0 WHERE gameID = 81;
UPDATE game SET nbPlayer = 20 WHERE gameID = 81;
