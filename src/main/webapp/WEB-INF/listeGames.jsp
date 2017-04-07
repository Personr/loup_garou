<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head> 
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="styles.css" />	
    <title>JEU LOUP GAROU</title>
 </head>
    
    <body>
        <h2> Liste des parties en cours </h2>
        <form method="get" action="homecontroleur" accept-charset="UTF-8">
            <button type="submit">Créer une partie</button>
            <input type="hidden" name="action" value="creategame" />
        </form>

        <form method="get" action="homecontroleur" accept-charset="UTF-8">
            <button type="submit">Voir les règles</button>
            <input type="hidden" name="action" value="seerules" />
        </form>
        
        <form method="get" action="homecontroleur" accept-charset="UTF-8">
            <button type="submit">BOUTONS POUR LES ADMINS</button>
            <input type="hidden" name="action" value="admin" />
        </form>
                
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
            <c:forEach items="${games}" var="game">
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
                    <td><a href="homecontroleur?action=nouveaujoueur&gameId=${game.gameId}">Rejoindre !</a></td>
                </tr>
            </c:forEach>
        </table> 
        ${message}
    </body>
</html>