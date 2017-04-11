<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta charset="UTF-8" />
        <link rel="stylesheet" type="text/css" href="styles.css" />	
        <title>JEU LOUP GAROU</title>
    </head>
    <body>
        <h1>Connexion</h1>
        <p>
        <div class="bouton">

            <form method="post" action="loginControleur" accept-charset="UTF-8">
                Pseudo : <input type="text" name="username"/><br/>
                Mdp: <input type="password" name="password"/><br/>
                <input type="hidden" name="action" value="enterlist" />
                <input type="hidden" name="view" value="aller" />
                <input type="hidden" name="message" value="Identifiants invalides" />
                <a href="loginControleur">Annuler</a>
                <input type="submit" name="Login"/>
            </form>
        </p>
        ${message}
    </div>
</body>
</html>

