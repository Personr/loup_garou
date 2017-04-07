<!DOCTYPE html>
<html lang="fr">
<!--<<<<<<< HEAD
<head>
  <meta charset="UTF-8" />
  <link rel="stylesheet" type="text/css" href="styles.css" />	
  <title>T'ES DANS LE GAME !</title>
</head>
<body>
  <h1>T'ES DANS LA GAME n°${gameId}, ${username} !</h1>
  <form method="get" action="gamecontroleur" accept-charset="UTF-8">
      <input type="hidden" name="action" value="getChat" />
      <input type="hidden" name="gameId" value=${gameId} />
      <input type="hidden" name="username" value=${username} />
      <input type="submit" name="chat" value="voir le Chat"/>
  </form>
     
  <form method="get" action="gamecontroleur" accept-charset="UTF-8">
      <input type="hidden" name="action" value="activatepower" />
      <input type="hidden" name="gameId" value=${gameId} />
      <input type="hidden" name="username" value=${username} />
      <input type="submit" name="power" value="Activez votre pouvoir"/>
  </form>
  
</body>
=======-->
    <head>
        <meta charset="UTF-8" />
        <link rel="stylesheet" type="text/css" href="styles.css" />	
        <title>T'ES DANS LE GAME !</title>
    </head>
    <body>
        <h1>T'ES DANS LA GAME n°${gameId}, ${username} !</h1>
        <form method="get" action="gamecontroleur" accept-charset="UTF-8">
            <input type="hidden" name="action" value="getChat" />
            <input type="hidden" name="gameId" value=${gameId} />
            <input type="hidden" name="username" value=${username} />
            <input type="hidden" name="isLg" value=0 />
            <input type="submit" name="chat" value="voir le Chat du village"/>
        </form>
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
            ${message}
    </body>
</html>
