<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="fr">
    <head>
        <meta charset="UTF-8" />
        <link href="styles.css" media="all" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" type="text/css" href="styles.css" />	
        <title>T'ES DANS LE GAME !</title>
    </head>

    <body>
        <!--        <embed src="/src/millionday.mp3" autostart="true">-->
        <h1>T'ES DANS LA GAME n°${gameId}, ${username} <c:choose><c:when test="${userPlayer.alive == '0'}"> ... mais tu es mort</c:when></c:choose>!</h1>

                <h2>Resultats de la nuit derniere:</h2>  


        <c:choose>
            <c:when test="${userPlayer.justBitten=='1'}"> 
                <b>${message2}</b><br />

            </c:when>  
            <c:when test="${userPlayer.justBitten=='0' && bitten == '1'}"> 

                <b>${message1}</b><br />

            </c:when> 
            <c:when test="${userPlayer.justContaminated=='1'}"> 

                <b>${message3}</b><br />

            </c:when> 
            <c:when test="${bitten=='0' && contaminated == '0'}"> 

                <b>${message5}</b><br />

            </c:when>  
        </c:choose>  

        <c:choose>
            <c:when test="${userPlayer.isLg == '1'}"> 
                Tu es Loup-Garou !
            </c:when>  
            <c:otherwise>
                Tu es Villageois !
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${userPlayer.hasInsomnie == '1'}"> 
                Tu as comme pouvoir : Insomnie !
            </c:when>  
            <c:when test="${userPlayer.hasContamination == '1'}"> 
                Tu as comme pouvoir : Contamination !
            </c:when>  
            <c:when test="${userPlayer.hasVoyance == '1'}"> 
                Tu as comme pouvoir : Voyance !
            </c:when>  
            <c:when test="${userPlayer.hasSpiritisme == '1'}"> 
                Tu as comme pouvoir : Spiritisme !
            </c:when>  
            <c:otherwise>
                Tu n'as pas de pouvoir...
            </c:otherwise>
        </c:choose>
        <h2>C'est le jour, il va falloir voter</h2>

        <h2>Voici la liste des joueurs :</h2>
        <table>
            <tr>
                <th>Nom</th>
                <th>A vote pour</th>
                <th>Nb de vote contre lui</th>
            </tr>
            <c:forEach items="${players}" var="player">
                <tr>
                    <td>${player.username}</td>
                    <td>${player.voted}</td>
                    <td>${player.nbVotes}</td>
                </tr>
            </c:forEach>
        </table>


        <c:choose>
            <c:when test="${userPlayer.alive == '1'}">
                <h2>Vous pouvez proposer un joueur, en voici la liste :</h2>

                <table>
                    <tr>
                        <th>Nom</th>
                        <th><!-- Modifier --></th>
                    </tr>
                    <c:forEach items="${proposable}" var="player">
                        <tr>
                            <td>${player.username}</td>
                            <td><a href="gamecontroleur?action=proposer&toProposeId=${player.id}">Proposer au vote</a></td>
                        </tr>
                    </c:forEach>
                </table>


                <h2>Ou voter pour un joueur, en voici la liste :</h2>
                <table>
                    <tr>
                        <th>Nom</th>
                        <th><!-- Modifier --></th>
                    </tr>
                    <c:forEach items="${votable}" var="player">
                        <tr>
                            <td>${player.username}</td>
                            <td><a href="gamecontroleur?action=voter&toVoteId=${player.id}">Voter pour lui</a></td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
            <c:when test="${userPlayer.alive == '1'}">
                Vous ne pouvez pas participer aux votes, vous êtes mort...
            </c:when>
        </c:choose>

        <form method="get" action="gamecontroleur" accept-charset="UTF-8">
            <input type="hidden" name="action" value="getChat" />
            <input type="hidden" name="gameId" value=${gameId} />
            <input type="hidden" name="username" value=${username} />
            <input type="hidden" name="isLg" value=0 />
            <input type="submit" name="chat" value="voir le Chat du village"/>
        </form>

        <c:choose>
            <c:when test="${isCreator == 1 || isCreator == 0}">
                <form method="post" action="gamecontroleur" accept-charset="UTF-8">
                    <input type="hidden" name="action" value="changeDayNight" />
                    <input type="hidden" name="gameId" value=${gameId} />
                    <input type="submit" name="night" value="Passer à  la nuit"/>
                </form>
            </c:when>  
        </c:choose>

        <form method="get" action="gamecontroleur" accept-charset="UTF-8">
            <input type="hidden" name="action" value="getGame" />
            <input type="hidden" name="view" value="aller" />
            <input type="hidden" name="gameId" value=${gameId} />
            <input type="submit" name="chat" value="Rafraichir"/>
        </form>


        ${message}


        <h2>Voici la liste des joueurs morts, reposez en paix :</h2>
        <table>
            <tr>
                <th>Nom</th>
                <th>Role</th>
                <th>Pouvoir</th>
            </tr>
            <c:forEach items="${morts}" var="mort">
                <tr>
                    <td>${mort.username}</td>
                    <td>${mort.getRole()}</td>
                    <td>${mort.getPouvoir()}</td>
                </tr>
            </c:forEach>
        </table>


    </body>
</html>
