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
        <h2>Et la c'est la nuit</h2>

        <c:choose>
            <c:when test="${userPlayer.isLg=='1' || userPlayer.hasInsomnie=='1'}"> 
                <form method="get" action="gamecontroleur" accept-charset="UTF-8">
                    <input type="hidden" name="action" value="getChat" />
                    <input type="hidden" name="gameId" value=${gameId} />
                    <input type="hidden" name="username" value=${username} />
                    <input type="hidden" name="isLg" value=1 />
                    <input type="submit" name="chat" value="voir le Chat des loups-garous"/>
                </form> 
            </c:when>  
        </c:choose>


    <c:choose>
        <c:when test="${userPlayer.hasVoyance=='1' || userPlayer.hasContamination=='1' || 
                        userPlayer.hasSpiritisme=='1'}"> 
            <form method="get" action="gamecontroleur" accept-charset="UTF-8">
                <input type="hidden" name="action" value="activatePower" />
                <input type="hidden" name="gameId" value=${gameId} />
                <input type="hidden" name="username" value=${username} />
                <input type="submit" name="power" value="Activez votre pouvoir"/>
            </form>
        </c:when>  
    </c:choose>
        
 <!--   <c:choose>
        <c:when test="${userPlayer.alive=='0'}"> 
            <form method="get" action="gamecontroleur" accept-charset="UTF-8">
                <input type="hidden" name="action" value="testSpiritisme" />
                <input type="hidden" name="gameId" value=${gameId} />
                <input type="hidden" name="username" value=${username} />
                <input type="submit" name="power" value="Un spiritisme pourrait vous parler!!"/>
            </form>
        </c:when>  
    </c:choose> -->

    <c:choose>
        <c:when test="${userPlayer.contacted=='1'}"> 
            <form method="get" action="gamecontroleur" accept-charset="UTF-8">
                <input type="hidden" name="action" value="testSpiritisme" />
                <input type="hidden" name="gameId" value=${gameId} />
                <input type="hidden" name="username" value=${username} />
                <input type="submit" name="power" value="Un spiritisme vous parle!"/>
            </form>
        </c:when>  
    </c:choose>
        
        <form method="post" action="gamecontroleur" accept-charset="UTF-8">
            <input type="hidden" name="action" value="changeDayNight" />
            <input type="hidden" name="gameId" value=${gameId} />
            <input type="submit" name="night" value="Passer au jour"/>
        </form>
          
        </form>
            <form method="get" action="gamecontroleur" accept-charset="UTF-8">
            <input type="hidden" name="action" value="getGame" />
            <input type="hidden" name="view" value="aller" />
            <input type="hidden" name="gameId" value=${gameId} />
            <input type="submit" name="chat" value="Rafraichir"/>
        </form>
        ${message}
    </body>
</html>
