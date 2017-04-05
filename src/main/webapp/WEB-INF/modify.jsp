<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <body>
        <h2> Modification d'ouvrage </h2>


        <form action="controleur" method="post" accept-charset="UTF-8">
            <label>Auteur :</label><input type="text" name="auteur" value = "${ouvrage.auteur}" /><br/>
            <label>Titre :</label><input type="text" name="titre" value = "${ouvrage.titre}"/> <br/>
            <!-- Annuler est un simple lien car il ne soumet pas le formulaire -->
            <a href="controleur">Annuler</a>
            <input type="submit" value="Valider" />
            <!-- Pour indiquer au contrôleur quelle action faire, on utilise un champ caché -->
            <input type="hidden" name="id" value="${ouvrage.id}" />
            <input type="hidden" name="action" value="modifier" />
        </form>
        
    </body>
</html>