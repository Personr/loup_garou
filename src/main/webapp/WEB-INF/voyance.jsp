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
        <h1>T'ES DANS LA GAME n°${gameId}, ${username} - VOYANCE!</h1>

        <table>
            <tr>
                <th>Liste des Joueurs</th>
            </tr>
            <c:forEach items="${mapJoueurs}" var="joueur">
                <tr>
                    <td>${joueur.username}</td>
                    <td><a href="gamecontroleur?action=getVoyance&username=${joueur.username}&gameId=${gameId}">Voir ses pouvoirs</a></td>
                </tr>
            </c:forEach>
        </table>
    </form>
    <form method="get" action="gamecontroleur" accept-charset="UTF-8">
        <input type="hidden" name="action" value="getGame" />
        <input type="submit" name="annuler" value="Retour"/>
    </form>          





</body>
</html>
