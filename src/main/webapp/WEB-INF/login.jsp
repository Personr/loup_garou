<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="UTF-8" />
  <link rel="stylesheet" type="text/css" href="styles.css" />	
  <title>JEU LOUP GAROU</title>
</head>
<body>
  <h1>Connexion</h1>
  <form method="post" action="loginControleur" accept-charset="UTF-8">
    <ul>
      <li> Login : <input type="text" name="username"/></li>
      <li> Mot de passe : <input type="password" name="password"/></li>
    </ul>
    
    <input type="hidden" name="action" value="enterlist" />
    <input type="hidden" name="message" value="Identifiants invalides" />
    <a href="loginControleur">Annuler</a>
    <input type="submit" name="Login"/>
  </form>
  ${message}
</body>
</html>
