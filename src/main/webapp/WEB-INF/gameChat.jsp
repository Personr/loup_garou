<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="UTF-8" />
  <link rel="stylesheet" type="text/css" href="styles.css" />	
  <title>T'ES DANS LE CHAT !</title>
</head>
<body>
  <h1>T'ES DANS LE CHAT DE LA GAME n°${gameId} !</h1>
  <form method="get" action="gamecontroleur" accept-charset="UTF-8">
      <input type="hidden" name="action" value="getGame" />
      <input type="hidden" name="view" value="aller" />
      <input type="hidden" name="gameId" value=${gameId} />
      <input type="submit" name="chat" value="Retour à la partie"/>
  </form>
</body>
</html>
