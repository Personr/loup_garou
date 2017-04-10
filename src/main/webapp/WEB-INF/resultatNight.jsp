<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="fr">
<head>
  <meta charset="UTF-8" />
  <link rel="stylesheet" type="text/css" href="styles.css" />	
  <title>JEU LOUP GAROU</title>
</head>
<body>
  <h1>Resultats du vote</h1>
    
    <c:choose>
        <c:when test="${userPlayer.justDied=='1'}"> 
            ${message2}
<!--            <form method="get" action="gamecontroleur" accept-charset="UTF-8">
                <input type="hidden" name="action" value="testSpiritisme" />
                <input type="hidden" name="gameId" value=${gameId} />
                <input type="hidden" name="username" value=${username} />
                <input type="submit" name="power" value="Un spiritisme vous parle!"/>
            </form>-->
        </c:when>  
        <c:when test="${userPlayer.justDied=='0' && elim == '1'}"> 
            
            ${message1}
<!--            <form method="get" action="gamecontroleur" accept-charset="UTF-8">
                <input type="hidden" name="action" value="testSpiritisme" />
                <input type="hidden" name="gameId" value=${gameId} />
                <input type="hidden" name="username" value=${username} />
                <input type="submit" name="power" value="Un spiritisme vous parle!"/>
            </form>-->
        </c:when> 
        <c:when test="${userPlayer.justDied=='0' && elim == '0'}"> 
            
            ${message3}
<!--            <form method="get" action="gamecontroleur" accept-charset="UTF-8">
                <input type="hidden" name="action" value="testSpiritisme" />
                <input type="hidden" name="gameId" value=${gameId} />
                <input type="hidden" name="username" value=${username} />
                <input type="submit" name="power" value="Un spiritisme vous parle!"/>
            </form>-->
        </c:when>  
    </c:choose>
    
    <form method="get" action="gamecontroleur" accept-charset="UTF-8">
        <input type="hidden" name="action" value="toNight" />
        <input type="hidden" name="gameId" value=${gameId} />
        <input type="submit" name="night" value="Continuer"/>
    </form>

</body>
</html>

