<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="fr">
<head>
  <meta charset="UTF-8" />
  <link rel="stylesheet" type="text/css" href="styles.css" />	
  <title>JEU LOUP GAROU</title>
</head>
<body>
  <h1>Resultats du vote de la nuit</h1>
    

    
    <form method="get" action="gamecontroleur" accept-charset="UTF-8">
        <input type="hidden" name="action" value="toDay" />
        <input type="hidden" name="gameId" value=${gameId} />
        <input type="submit" name="night" value="Continuer"/>
    </form>

</body>
</html>

