<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta charset="UTF-8" />
        <link rel="stylesheet" type="text/css" href="styles.css" />
        <!--<link rel="stylesheet" type="text/css" href="./src/main/webapp/styles.css" />/>-->	
        <title>Accueil - Jeu</title>
    </head>
    <body>

        <h1>Identification</h1>




        <form method="get" action="loginControleur" accept-charset="UTF-8">
            
                <button type="submit" style="width: 300px; height: 100px ">J'ai un compte je m'identifie</button>
            
            <input type="hidden" name="action" value="login" />
            <input type="hidden" name="message" value="" />
            <!--<input type="submit" name="Login" />-->
        </form>

        <form method="get" action="loginControleur" accept-charset="UTF-8">
            <div class="bouton">
                <button type="submit" style="width: 300px; height: 100px ">Je cree mon compte"</button>
            </div>
            <input type="hidden" name="action" value="compte" />
            <input type="hidden" name="message" value="" />
            <!--<input type="submit" name="Account" />-->
        </form>
    </body>
    ${message}
</html>
