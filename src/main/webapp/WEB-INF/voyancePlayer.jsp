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
        <h1>VOYANCE de ${spieduser.username}</h1>


        <table>
            <tr>
                <td>Nom du joueur : ${spieduser.username}</td>
            </tr>
            <tr>
                <td>Role : ${spieduser.getRole()}</td>
            </tr>
            <tr>
                <td>Pouvoir : ${spieduser.getPouvoir()}</td>
            </tr>

        </table>
        <a href="gamecontroleur?action=getGame&username=${joueur.username}&gameId=${gameId}">Retour au jeu</a>            





    </body>
</html>
