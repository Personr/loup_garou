<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta charset="UTF-8" />
        <link rel="stylesheet" type="text/css" href="styles.css" />	
        <title>T'ES DANS LE GAME !</title>
    </head>
    <body>
        <h1>T'ES DANS LA GAME n°${gameId}, ${username} !</h1>
        <h2>Et la c'est la nuit</h2>

        <form method="get" action="gamecontroleur" accept-charset="UTF-8">
            <input type="hidden" name="action" value="getChat" />
            <input type="hidden" name="gameId" value=${gameId} />
            <input type="hidden" name="username" value=${username} />
            <input type="hidden" name="isLg" value=1 />
            <input type="submit" name="chat" value="voir le Chat des loups-garous"/>
        </form> 
            
        <form method="get" action="gamecontroleur" accept-charset="UTF-8">
            <input type="hidden" name="action" value="activatePower" />
            <input type="hidden" name="gameId" value=${gameId} />
            <input type="hidden" name="username" value=${username} />
            <input type="submit" name="power" value="Activez votre pouvoir"/>
        </form>
            
        <form method="post" action="gamecontroleur" accept-charset="UTF-8">
            <input type="hidden" name="action" value="changeDayNight" />
            <input type="hidden" name="gameId" value=${gameId} />
            <input type="submit" name="night" value="Passer au jour"/>
        </form>
            ${message}
    </body>
</html>
