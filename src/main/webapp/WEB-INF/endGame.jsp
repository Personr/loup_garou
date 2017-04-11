<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="fr">
    <head>
        <meta charset="UTF-8" />
        <link rel="stylesheet" type="text/css" href="styles.css" />	
        <title>JEU DU LOUP GAROU - TERMINE</title>
    </head>
    <body>
        <c:choose>
            <c:when test="${isLg=='1'}"> 
                <h1 align="center">VICTOIRE POUR LES LOUPS-GAROUS</h1>
                Felicitations aux atroces loups garous. Aucune pitié pour les fragiles Villageois !
            </c:when> 
            <c:otherwise>
                <h1 align="center">VICTOIRE POUR LES VILLAGEOIS</h1>
                Felicitations a nos Villageois. Ils ont su faire preuve d'ingeniosite et prudence face aux dangereux Loups-Garous!
            </c:otherwise>
        </c:choose>


        <form method="get" action="homecontroleur" accept-charset="UTF-8">
            <input type="hidden" name="action" value="gameLeft" />
            <input type="hidden" name="gameId" value=${gameId} />
            <input type="submit" name="chat" value="Retour a l accueil"/>
        </form>


    </body>
</html>
