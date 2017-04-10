<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="fr">

    <head>
        <meta charset="UTF-8" />
        <link rel="stylesheet" type="text/css" href="styles.css" />	
        <title>CONTAMINE UN HUMAIN ! </title>
    </head>
    <body>
        <h1>T'ES DANS LA GAME n°${gameId}, ${username} - SPIRITISME!</h1>

        <table>
            <tr>
                <th>Liste des Joueurs Morts</th>
            </tr>
            <c:forEach items="${joueursMorts}" var="joueur">
                <tr>
                    <td>${joueur.username}</td>
                    <td><a href="gamecontroleur?action=getSpiritisme&username=${joueur.username}&gameId=${gameId}">Discutez</a></td>
                </tr>
            </c:forEach>
        </table>
        <form method="get" action="gamecontroleur" accept-charset="UTF-8">
            <input type="hidden" name="action" value="getGame" />
            <input type="submit" name="annuler" value="Retour"/>
        </form>  

    </body>
</html>
