<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="fr">
<head>
  <meta charset="UTF-8" />
  <link rel="stylesheet" type="text/css" href="styles.css" />	
  <title>JEU DU LOUP GAROU - TERMINE</title>
</head>
<body>
  <h1 align="center">VICTOIRE POUR LES VILLAGEOIS</h1>
     
    Felicitations a nos Villageois. Ils ont su faire preuve d'ingeniosite et prudence face aux dangereux Loups-Garous!
    
    <form method="post" action="loginControleur" accept-charset="UTF-8">
            <input type="hidden" name="action" value="enterlist" />
            <input type="hidden" name="view" value="aller" />
            <input type="hidden" name="gameId" value=${gameId} />
            <input type="submit" name="chat" value="Retour a l accueil"/>
    </form>

    
</body>
</html>
