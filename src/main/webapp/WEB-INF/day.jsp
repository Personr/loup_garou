<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta charset="UTF-8" />
        <link rel="stylesheet" type="text/css" href="styles.css" />	
        <title>T'ES DANS LE GAME !</title>
    </head>
    
    <body>
        <h1>T'ES DANS LA GAME n°${gameId}, ${username} !</h1>
        <h2>C'est le jour, il va falloir voter</h2>
        
        <table>
            <tr>
                <th>Joueur</th>
                <th><!-- Voter pour lui wallah --></th>
            </tr>
            <c:forEach items="${players}" var="players">
                <tr>
                    <td>${players.string}</td><td>${players.string}</td>
                    <td><a href="controleur?action=getOuvrage&view=modifier&id=${ouvrage.id}">voter pour lui</a></td>
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
            <input type="submit" name="night" value="Passer à la nuit"/>
        </form>
        ${message}
    </body>
</html>
