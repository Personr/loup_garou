<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head> 
        <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
        <link rel="stylesheet" type="text/css" href="styles.css" />	
        <title>JEU LOUP GAROU</title>
    </head>

    <body>
        <h2> Votre partie est en attente de lancement ! </h2>

        <table>
            <tr>
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
            </tr>
        </table> 
        <form method="get" action="gamecontroleur" accept-charset="UTF-8">
            <input type="hidden" name="action" value="startGame" />
            <input type="hidden" name="game" value=${game} />
            <input type="submit" name="start" value="Lancer la partie"/>
        </form>               
            
        <form method="get" action="homecontroleur" accept-charset="UTF-8">
            <input type="hidden" name="action" value="getWaitingGame" />
            <input type="hidden" name="view" value="aller" />
            <input type="hidden" name="gameId" value=${gameId} />
            <input type="submit" name="chat" value="Rafraichir"/>
        </form>
                
    </body>
</html>