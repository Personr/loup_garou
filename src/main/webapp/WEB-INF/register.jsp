<!DOCTYPE html>
<html lang="fr">
    <head> 
        <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
        <link rel="stylesheet" type="text/css" href="styles.css" />	
        <title>JEU LOUP GAROU</title>
    </head>
    <body>
        <h1>Creation d'un nouveau compte</h1>
        <p>
        <div class="bouton">

            <form method="post" action="loginControleur" accept-charset="UTF-8">
                Pseudo : <input type="text" name="username"><br/>
                Mdp : <input type="password" name="password"><br/>
                <input type="hidden" name="action" value="createaccount" />
                <input type="hidden" name="message" value="Ce username n'est pas disponible" />
                <a href="loginControleur">Annuler</a>
                <input type="submit" value="Soumettre"/>
            </form>
        </p>
        ${message}
    </div>
</body>
</html>
