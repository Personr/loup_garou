<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="fr">

    <head>
        <meta charset="UTF-8" />
        <link rel="stylesheet" type="text/css" href="styles.css" />	
        <title>CONTAMINE UN HUMAIN ! </title>
    </head>
    <body>
        <h1>T'ES DANS LA GAME n°${gameId}, ${username} - VOYANCE de ${spieduser.username}</h1>
        

            <table>
                <tr>
                    <th>Liste des Joueurs</th>
                </tr>

                    <tr>
                        
<!--                                    <tr>
                                        <th>Partie n°</th>
                                        <th>Créateur</th>
                                        <th>Nb joueurs</th>
                                        <th>Nb joueurs min</th>
                                        <th>Nb joueurs max</th>
                                        <th>Date lancement</th>
                                        <th>Heure du jour</th>
                                        <th>Heure de la nuit</th>
                                        <th>Proba Contamination</th>
                                        <th>Proba Insomnie</th>
                                        <th>Proba Voyance</th>
                                        <th>Proba Spiritisme</th>
                                        <th>Prop Loups-Garous</th>
                                    </tr>
                                    <tr>
                                        <td>${game.gameId}</td>
                                        <td>${game.creator}</td>
                                        <td>${game.nbPlayers}</td>
                                        <td>${game.minPlayers}</td>
                                        <td>${game.maxPlayers}</td>
                                        <td>${game.startTime}</td>
                                        <td>${game.dayTime}</td>
                                        <td>${game.nightTime}</td>
                                        <td>${game.pContamination}</td>
                                        <td>${game.pInsomnie}</td>
                                        <td>${game.pVoyance}</td>
                                        <td>${game.pSpiritisme}</td>
                                        <td>${game.lgProp}</td>
                                    </tr>-->
                        <td>Nom du joueur : ${spieduser.username}</td>
                        <td>Loup-Garou ? : ${spieduser.isLg}</td>
                        <td>Pouvoir de contamination ? : ${spieduser.hasContamination}</td>
                        <td>Pouvoir d Insomnie ? : ${spieduser.hasInsomnie}</td>
                        <td>Pouvoir de Voyance ? : ${spieduser.hasVoyance}</td>
                        <td>Pouvoir de Spiritisme ? : ${spieduser.hasSpiritisme}</td>
                    </tr>

            </table>
            <a href="gamecontroleur?action=getGame&username=${joueur.username}&gameId=${gameId}">Retour au jeu</a>            
            



            
    </body>
</html>
