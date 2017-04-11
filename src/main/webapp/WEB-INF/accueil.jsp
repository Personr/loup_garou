<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta charset="UTF-8" />
        <link rel="stylesheet" type="text/css" href="styles.css" />	
        <title>Accueil - Jeu</title>
    </head>
    <body  background="images/village_grand.jpg"        >

        <h2>Identification</h2>




        <form method="get" action="loginControleur" accept-charset="UTF-8">
            <button type="submit">J'ai un compte je m'identifie</button>
            <input type="hidden" name="action" value="login" />
            <input type="hidden" name="message" value="" />
            <!--<input type="submit" name="Login" />-->
        </form>

        <form method="get" action="loginControleur" accept-charset="UTF-8">
            <div class="bouton">
                <button type="submit">Je cree mon compte</button>
            </div>
            <input type="hidden" name="action" value="compte" />
            <input type="hidden" name="message" value="" />
            <!--<input type="submit" name="Account" />-->
        </form>
    </body>
    ${message}
</html>
