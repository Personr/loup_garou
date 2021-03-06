<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="fr">
    <head>
        <meta charset="UTF-8" />
        <link rel="stylesheet" type="text/css" href="styles.css" />	
        <title>T'ES DANS LE CHAT !</title>
    </head>
    <body>

        <h1>T'ES DANS LE CHAT DE LA GAME n°${gameId} !</h1>
        <table>
            <tr>
                <th>Auteur</th>
                <th>Date</th>
                <th>Texte</th>
            </tr>
            <c:forEach items="${messages}" var="message">
                <tr>
                    <td>${message.username}</td>
                    <td>${message.prettyDate}</td>
                    <td>${message.text}</td>
                </tr>
            </c:forEach>
        </table> 


        <c:choose>
            <c:when test="${player.alive == '1'}">
                <c:choose>
                    <c:when test="${isLg == '0' || player.isLg=='1'}"> 
                        <form method="post" action="gamecontroleur" accept-charset="UTF-8">
                            Nouveau message : <input type="text" name="text"/>
                            <input type="hidden" name="action" value="newMessage" />
                            <input type="hidden" name="gameId" value=${gameId} />
                            <input type="hidden" name="username" value=${username} />
                            <input type="hidden" name="isLg" value=${isLg} />
                            <input type="submit" name="Envoyer" value="Envoyer"/>
                        </form> 

                    </c:when>  
                </c:choose>
            </c:when>
        </c:choose>

        <form method="get" action="gamecontroleur" accept-charset="UTF-8">
            <input type="hidden" name="action" value="getChat" />
            <input type="hidden" name="gameId" value=${gameId} />
            <input type="hidden" name="username" value=${username} />
            <input type="hidden" name="isLg" value=${isLg} />
            <input type="submit" name="Rafraichir" Value="Rafraichir"/>
        </form> 

        <form method="get" action="gamecontroleur" accept-charset="UTF-8">
            <input type="hidden" name="action" value="getGame" />
            <input type="hidden" name="view" value="aller" />
            <input type="hidden" name="gameId" value=${gameId} />
            <input type="submit" name="chat" value="Retour partie"/>
        </form>
    </body>
    ${pouvoir}
</html>
