<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="UTF-8" />
  <link rel="stylesheet" type="text/css" href="styles.css" />	
  <title>Accueil - Jeu</title>
</head>
<body>
  <h1>Identification</h1>
<!--  <ul>
      
  <li> <a href="/login.html">J'ai un compte je m'identifie </a></li>
  <li> <a href="/register_get.html"> Je crée mon compte </a></li>
  </ul>-->
  <form method="get" action="loginControleur" accept-charset="UTF-8">
      <button type="submit">J'ai un compte je m'identifie</button>
      <input type="hidden" name="action" value="login" />
      <input type="hidden" name="message" value="" />
      <!--<input type="submit" name="Login" />-->
  </form>
  
  <form method="get" action="loginControleur" accept-charset="UTF-8">
      <button type="submit">Je crée mon compte</button>
      <input type="hidden" name="action" value="compte" />
      <input type="hidden" name="message" value="" />
      <!--<input type="submit" name="Account" />-->
  </form>
  ${message}
</html>