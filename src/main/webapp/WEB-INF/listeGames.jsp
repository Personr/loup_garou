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
        
        
        <form method="post" action="homecontroleur" accept-charset="UTF-8">
            <button type="submit">Créer une partie</button>
            <input type="hidden" name="action" value="creategame" />
        </form>

        <table>
            <tr>
                <th>Partie n°</th>
                <th>Créateur</th>
                <th>Nb joueurs</th>
                <th>Date lancement</th>
                <th><!-- Participer --></th>
            </tr>
            <c:forEach items="${game}" var="game">
                <tr>
                    <td>${game.gameId}</td>
                    <td>${game.creator}</td>
                    <td>${game.nbPlayers}</td>
                    <td>${game.startTime}</td>
                    <td><a href="gamecontroleur?action=getGame&view=aller&id=${game.gameId}">Rejoindre !</a></td>
                </tr>
            </c:forEach>
        </table> 
        
    </body>
</html>