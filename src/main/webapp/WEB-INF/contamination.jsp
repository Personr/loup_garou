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
        <h1>T'ES DANS LA GAME n°${gameId}, ${username} - CONTAMINATION!</h1>
        
            <table>
                
            <c:forEach items="${mapHumains}" var="joueur">
                
                <tr>
                    <td>${joueur.username}</td>
                    
                    <td>
                        <form method="get" action="gamecontroleur" accept-charset="UTF-8">
<!-- ATTENTION AFFICHER QUE LES HUMAINS                   
                        
                        <input type="hidden" name="view" value="aller" />-->
                        <input type="hidden" name="action" value="getContamination" />
                        <input type="hidden" name="username" value=${joueur.username} />
                        <input type="hidden" name="gameId" value=${gameId} />
                        <input type="submit" name="contamine" value="Contaminer!"/>

                        </form>
                    </td>
                </tr>
                
            </c:forEach>
                
            </table>
            
    </body>
</html>
