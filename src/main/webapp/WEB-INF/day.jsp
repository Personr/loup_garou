<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="fr">
    <head>
        <meta charset="UTF-8" />
        <link rel="stylesheet" type="text/css" href="styles.css" />	
        <title>T'ES DANS LE GAME !</title>
    </head>

    <body>
        <h1>T'ES DANS LA GAME n°${gameId}, ${username} !</h1>
        <c:choose>
            <c:when test="${userPlayer.isLg == '1'}"> 
                Tu es Loup-Garou !
            </c:when>  
            <c:otherwise>
                Tu es Villageois !
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${userPlayer.hasInsomnie == '1'}"> 
                Tu as comme pouvoir : Insomnie !
            </c:when>  
            <c:when test="${userPlayer.hasContamination == '1'}"> 
                Tu as comme pouvoir : Contamination !
            </c:when>  
            <c:when test="${userPlayer.hasVoyance == '1'}"> 
                Tu as comme pouvoir : Voyance !
            </c:when>  
            <c:when test="${userPlayer.hasSpiritisme == '1'}"> 
                Tu as comme pouvoir : Spiritisme !
            </c:when>  
            <c:otherwise>
                Tu n'as pas de pouvoir...
            </c:otherwise>
        </c:choose>
        <h2>C'est le jour, il va falloir voter</h2>

        <h2>Vous pouvez proposer un joueur, en voici la liste:</h2>

        <table>
            <tr>
                <th>Nom</th>
                <th><!-- Modifier --></th>
            </tr>
            <c:forEach items="${proposable}" var="player">
                <tr>
                    <td>${player.username}</td>
                    <td><a href="gamecontroleur?action=proposer&toProposeId=${player.id}">Proposer au vote</a></td>
                </tr>
            </c:forEach>
        </table>


        <h2>Ou voter pour un jour, en voici la liste:</h2>
        <table>
            <tr>
                <th>Nom</th>
                <th><!-- Modifier --></th>
            </tr>
            <c:forEach items="${votable}" var="player">
                <tr>
                    <td>${player.username}</td>
                    <td><a href="gamecontroleur?action=proposer&toProposeId=${player.id}">Voter pour lui</a></td>
                </tr>
            </c:forEach>
        </table>


        <form method="get" action="gamecontroleur" accept-charset="UTF-8">
            <input type="hidden" name="action" value="getChat" />
            <input type="hidden" name="gameId" value=${gameId} />
            <input type="hidden" name="username" value=${username} />
            <input type="hidden" name="isLg" value=0 />
            <input type="submit" name="chat" value="voir le Chat du village"/>
        </form>

        <form method="post" action="gamecontroleur" accept-charset="UTF-8">
            <input type="hidden" name="action" value="changeDayNight" />
            <input type="hidden" name="gameId" value=${gameId} />
            <input type="submit" name="night" value="Passer Ã  la nuit"/>
        </form>
        ${message}
    </body>
</html>
