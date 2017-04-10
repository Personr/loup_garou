<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="fr">

    <head>
        <meta charset="UTF-8" />
        <link rel="stylesheet" type="text/css" href="styles.css" />	
        <title>CONTAMINE UN HUMAIN ! </title>
    </head>
    <body>
        <h1>T'ES DANS LA GAME n°${gameId}, ${username} - CONTAMINATION!</h1>
        <table>
            <tr>
                <th>Liste des Villageois</th>
            </tr>
            <c:forEach items="${mapHumains}" var="joueur">
                <tr>
                    <td>${joueur.username}</td>
                    <td><a href="gamecontroleur?action=getContamination&username=${joueur.username}&gameId=${gameId}">Contamine Le! (il se transformera en Loup-Garou)</a></td>
                </tr>
            </c:forEach>
        </table>
        <form method="get" action="gamecontroleur" accept-charset="UTF-8">
            <input type="hidden" name="action" value="getGame" />
            <input type="submit" name="annuler" value="Retour"/>
        </form>  

    </body>
</html>
