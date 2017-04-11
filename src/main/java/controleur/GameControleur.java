package controleur;

import dao.DAOException;
import dao.UserDAO;
import java.io.*;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;
import dao.MessageDAO;
import dao.GameDAO;
import dao.PlayerDAO;
import modele.Game;
import tools.SessionManager;
import modele.Message;
import java.sql.Date;
import java.util.Calendar;
import modele.Player;

/**
 * Le contrôleur de l'application.
 */
@WebServlet(name = "GameControleur", urlPatterns = {"/gamecontroleur"})
public class GameControleur extends HttpServlet {

    @Resource(name = "jdbc/bibliography")
    private DataSource ds;

    /* pages d’erreurs */
    private void invalidParameters(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/controleurErreur.jsp").forward(request, response);
    }

    private void erreurBD(HttpServletRequest request,
            HttpServletResponse response, DAOException e)
            throws ServletException, IOException {
        request.setAttribute("erreurMessage", e.getMessage());
        request.getRequestDispatcher("/WEB-INF/bdErreur.jsp").forward(request, response);
    }

    /**
     * Actions possibles en GET : afficher (correspond à l’absence du param)
     */
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        MessageDAO messageDAO = new MessageDAO(ds);
        GameDAO gameDAO = new GameDAO(ds);
        UserDAO userDAO = new UserDAO(ds);
        PlayerDAO playerDAO = new PlayerDAO(ds);

        try {
            if (action.equals("getChat")) {
                actionAfficherChat(request, response, messageDAO, playerDAO, gameDAO);
            } else if (action.equals("getGame")) {
                actionAfficher(request, response, gameDAO, playerDAO);
            } else if (action.equals("proposer")) {
                actionProposer(request, response, gameDAO, playerDAO);
            } else if (action.equals("voter")) {
                actionVoter(request, response, gameDAO, playerDAO, -1);
            } else if (action.equals("startGame")) {
                actionStartGame(request, response, playerDAO, gameDAO);
            } else if (action.equals("activatePower")) {
                actionPouvoir(request, response, playerDAO);
            } else if (action.equals("getContamination")) {
                actionPouvoirContamination(request, response, gameDAO, userDAO, playerDAO, messageDAO);
            } else if (action.equals("getVoyance")) {
                actionPouvoirVoyance(request, response, gameDAO, userDAO, playerDAO, messageDAO);
            } else if (action.equals("getSpiritisme")) {
                actionPouvoirSpiritisme(request, response, gameDAO, userDAO, playerDAO, messageDAO);
            } else if (action.equals("toNight")) {
                actionToNight(request, response, gameDAO, userDAO, playerDAO, messageDAO);    
            } else if (action.equals("toDay")) {
                actionToDay(request, response, gameDAO, userDAO, playerDAO, messageDAO);    
            } else {
                invalidParameters(request, response);
            }
        } catch (DAOException e) {
            erreurBD(request, response, e);
        }
    }

    private void actionToNight(HttpServletRequest request, HttpServletResponse response, GameDAO gameDAO, UserDAO userDAO, PlayerDAO playerDAO, MessageDAO messageDAO) throws ServletException, IOException {
        actionAfficher(request,response, gameDAO, playerDAO);


        //request.getRequestDispatcher("/WEB-INF/night.jsp").forward(request, response);
    }
    
    private void actionToDay(HttpServletRequest request, HttpServletResponse response, GameDAO gameDAO, UserDAO userDAO, PlayerDAO playerDAO, MessageDAO messageDAO) throws ServletException, IOException {
        //actionAfficher(request,response, gameDAO, playerDAO);
        int gameId = Integer.parseInt(request.getParameter("gameId"));
//        gameDAO.startDay(gameId);
        actionAfficher(request,response, gameDAO, playerDAO);
        //request.getRequestDispatcher("/WEB-INF/night.jsp").forward(request, response);
    }
    
    /**
     *
     * Affiche la page d’accueil d'une game
     */
    private void actionAfficher(HttpServletRequest request,
            HttpServletResponse response, GameDAO gameDAO, PlayerDAO playerDAO) 
            throws ServletException, IOException {
        String username = SessionManager.getUserSession(request);
        int gameID = SessionManager.getGameSession(request);
        Game userGame = gameDAO.getGame(gameID);
        Player userPlayer = playerDAO.getPlayer(username, gameID);
              
        request.setAttribute("userPlayer", userPlayer);
        request.setAttribute("gameId", userGame.getGameId());
        request.setAttribute("username", username);
        List<Player> morts = playerDAO.getListPlayers(gameID, 0); //dead okayers
        request.setAttribute("morts", morts);
        request.setAttribute("isCreator", '1');
        
        List<Player> players = playerDAO.getListPlayers(gameID, 1); //alive players
        request.setAttribute("players", players);
        
        actionCheckerFinPartie(request, response, gameDAO, playerDAO);

        if (userGame.getIsDay() == 1) {
            List<Player> proposable = playerDAO.getListPlayersProposable(gameID);
            request.setAttribute("proposable", proposable);

            List<Player> votable = playerDAO.getListPlayersVotable(gameID);
            request.setAttribute("votable", votable);
            request.getRequestDispatcher("/WEB-INF/day.jsp").forward(request, response);
        } else {
            List<Player> lg = playerDAO.getListPlayersRole(gameID, 1, 1); //alive lg
            request.setAttribute("lg", lg);

            List<Player> proposable = playerDAO.getListHumansProposable(gameID);
            request.setAttribute("proposable", proposable);

            List<Player> votable = playerDAO.getListPlayersVotable(gameID); //proposed players
            request.setAttribute("votable", votable);
            request.getRequestDispatcher("/WEB-INF/night.jsp").forward(request, response);
        }
        
    }
    
   
    /**
     *
     * Propose un joueur au vote
     */
    private void actionProposer(HttpServletRequest request,
            HttpServletResponse response, GameDAO gameDAO, PlayerDAO playerDAO)
            throws ServletException, IOException {

        int userId = Integer.parseInt(request.getParameter("toProposeId"));
        playerDAO.proposer(userId);
        actionVoter(request, response, gameDAO, playerDAO, userId);

    }
    
    /**
     *
     * Vote pour un joueur
     */
    private void actionVoter(HttpServletRequest request,
            HttpServletResponse response, GameDAO gameDAO, PlayerDAO playerDAO, int cibleId)
            throws ServletException, IOException {

        if (cibleId == -1) {
            cibleId = Integer.parseInt(request.getParameter("toVoteId"));
        }
        String username = SessionManager.getUserSession(request);
        int gameId = SessionManager.getGameSession(request);
        Player player = playerDAO.getPlayer(username, gameId);
        int userId = player.getId();
        
        // On récupère l'ancien vote
        String voted = player.getVoted();
        if (voted.equals(" ")) {
            // n'avait pas voté
            playerDAO.voter(userId, cibleId);
        } else {
            int ancienVoteId = playerDAO.getPlayer(voted, gameId).getId();
            playerDAO.changeVote(userId, ancienVoteId);
            playerDAO.voter(userId, cibleId);
        }
        actionAfficher(request, response, gameDAO, playerDAO);

    }
    
    /*
        lancement d'un chat parallele entre le 'player' et le joueur au pouvoir de spiritisme.
        le 'player' est notifié sur la page 'night.jsp' du message.
    */
    private void actionPouvoirSpiritisme(HttpServletRequest request, HttpServletResponse response, 
            GameDAO gameDAO, UserDAO userDAO, PlayerDAO playerDAO, MessageDAO messageDAO) throws ServletException, IOException {

        String myUsername = SessionManager.getUserSession(request);
        String calledUsername = request.getParameter("username");
        int gameId = Integer.parseInt(request.getParameter("gameId"));
        Player myPlayer = playerDAO.getPlayer(myUsername, gameId);
        Player calledPlayer = playerDAO.getPlayer(calledUsername, gameId);
        Game game = gameDAO.getGame(gameId);
        
        playerDAO.pouvoirUtilise(myPlayer.getId(),1);
        
        //on informe le mort qu'il est contacté, et on le "lie" à l'appelant
        playerDAO.playerContacted(calledPlayer.getId());
        playerDAO.playerContacted(myPlayer.getId());
        
        List<Message> messages = messageDAO.getListeMessages(2, gameId, game.getDayNb());

        request.setAttribute("gameId", gameId);
        request.setAttribute("messages", messages);
        request.getRequestDispatcher("/WEB-INF/spiritisme.jsp").forward(request, response);
    }
    
    /**
     * 
     * @param request
     * @param response
     * @param gameDAO
     * @param userDAO
     * @param playerDAO
     * @param messageDAO
     * @throws ServletException
     * @throws IOException 
     */
    private void actionPouvoirContamination(HttpServletRequest request,
            HttpServletResponse response, GameDAO gameDAO, UserDAO userDAO, PlayerDAO playerDAO, MessageDAO messageDAO) throws ServletException, IOException {

        String myUsername = SessionManager.getUserSession(request);
        String contUsername = request.getParameter("username");
        int gameId = Integer.parseInt(request.getParameter("gameId"));
        Player myPlayer = playerDAO.getPlayer(myUsername, gameId);
        Player contPlayer = playerDAO.getPlayer(contUsername, gameId);
        
        playerDAO.transformerLoupGarou(contPlayer.getId());
        playerDAO.pouvoirUtilise(myPlayer.getId(), 1);
        request.setAttribute("message", "vous avez contamine : " + contUsername);
        actionAfficher(request, response, gameDAO, playerDAO);
        
    }
    
    /**
     * 
     * @param request
     * @param response
     * @param gameDAO
     * @param userDAO
     * @param playerDAO
     * @param messageDAO
     * @throws ServletException
     * @throws IOException 
     */
    private void actionPouvoirVoyance(HttpServletRequest request,
            HttpServletResponse response, GameDAO gameDAO, UserDAO userDAO, PlayerDAO playerDAO, MessageDAO messageDAO) throws ServletException, IOException {
        
        String myUsername = SessionManager.getUserSession(request);
        String spiedUsername = request.getParameter("username");
        int gameId = Integer.parseInt(request.getParameter("gameId"));
        Player myPlayer = playerDAO.getPlayer(myUsername, gameId);
        Player spiedPlayer = playerDAO.getPlayer(spiedUsername, gameId);
        
        playerDAO.pouvoirUtilise(myPlayer.getId(),1);
        request.setAttribute("spieduser", spiedPlayer);
        request.getRequestDispatcher("/WEB-INF/voyancePlayer.jsp").forward(request, response);
    }

    
    /**
     *
     * Active le pouvoir du joueur (si possible). Chaque cas redirige vers des action de vues ou d'autres méhodes du gameControler.
     * 
     */
    private void actionPouvoir(HttpServletRequest request,
            HttpServletResponse response, PlayerDAO playerDAO) throws ServletException, IOException {

        /* On interroge la base de données pour obtenir le player et les caractéristiques de son pouvoir */
        String username = request.getParameter("username");
        int gameId = Integer.parseInt(request.getParameter("gameId"));
        Player joueur = playerDAO.getPlayer(username, gameId);
        
        request.setAttribute("gameId", gameId);
        request.setAttribute("username", username);
        
        if (joueur.getHasContamination() == 1) {
            List<Player> mapHumains = playerDAO.getListPlayersRole(gameId, 0, 1); //alive villagers
            request.setAttribute("mapHumains", mapHumains);
            request.getRequestDispatcher("/WEB-INF/contamination.jsp").forward(request, response);
        } else if (joueur.getHasVoyance() == 1) {
            gameId = Integer.parseInt(request.getParameter("gameId"));
            List<Player> mapJoueurs = playerDAO.getListPlayers(gameId, 1); //alive players
            request.setAttribute("mapJoueurs", mapJoueurs);
            request.getRequestDispatcher("/WEB-INF/voyance.jsp").forward(request, response);
        } else if (joueur.getHasSpiritisme() == 1) {
            gameId = Integer.parseInt(request.getParameter("gameId"));
            List<Player> joueursMorts = playerDAO.getListPlayers(gameId, 0); //dead players
            request.setAttribute("joueursMorts", joueursMorts);
            request.getRequestDispatcher("/WEB-INF/choisirSpiritisme.jsp").forward(request, response);
        }
    }
    
    /**
     * Affiche le chat (de base ou loups garous).
     * @param request
     * @param response
     * @param messageDAO
     * @param playerDAO
     * @throws ServletException
     * @throws IOException 
     */
    private void actionAfficherChat(HttpServletRequest request,
            HttpServletResponse response,
            MessageDAO messageDAO, PlayerDAO playerDAO, GameDAO gameDAO) throws ServletException, IOException {

        /* On interroge la base de données pour obtenir la liste des messages */
        int gameId = Integer.parseInt(request.getParameter("gameId"));
        int isLg = Integer.parseInt(request.getParameter("isLg"));
        String username = request.getParameter("username");
        Player player = playerDAO.getPlayer(username, gameId);
        Game game = gameDAO.getGame(gameId);
        
        request.setAttribute("player", player);

        List<Message> messages = messageDAO.getListeMessages(isLg, gameId, game.getDayNb());

        request.setAttribute("gameId", gameId);
        request.setAttribute("messages", messages);

        request.setAttribute("isLg", isLg);
        if (isLg == 2) {
            request.getRequestDispatcher("/WEB-INF/spiritisme.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/WEB-INF/gameChat.jsp").forward(request, response);
        }
    }
    
    /**
     * Lance la game!
     * @param request
     * @param response
     * @param playerDAO
     * @param gameDAO
     * @throws ServletException
     * @throws IOException 
     */

    private void actionStartGame(HttpServletRequest request,
            HttpServletResponse response,
            PlayerDAO playerDAO, GameDAO gameDAO) throws ServletException, IOException {

        int gameID = SessionManager.getGameSession(request);
        Game game = gameDAO.getGame(gameID);
        if (game != null && game.startGame(playerDAO, gameDAO)) {
            SessionManager.setGameSession(game.getGameId(), request);
            actionAfficher(request, response, gameDAO, playerDAO);
        } else {
            request.setAttribute("message", "Nombre de joueurs insuffisant");
            request.getRequestDispatcher("homecontroleur").forward(request, response);
        }
    }

    /**
     * Actions possibles en POST : ajouter, supprimer, modifier. Une fois
     * l’action demandée effectuée, on retourne à la page d’accueil avec
     * actionAfficher(...)
     */
    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        MessageDAO messageDAO = new MessageDAO(ds);
        GameDAO gameDAO = new GameDAO(ds);
        UserDAO userDAO = new UserDAO(ds);
        PlayerDAO playerDAO = new PlayerDAO(ds);

        try {

            if (action.equals("enterlist")) {
                actionAfficher(request, response, gameDAO, playerDAO);
            } else if (action.equals("newMessage")) {
                actionNewMessage(request, response, messageDAO, playerDAO, gameDAO);
            } else if (action.equals("changeDayNight")) {
                actionChangeDayNight(request, response, gameDAO, playerDAO);
            } else {
                invalidParameters(request, response);
            }
            //Une fois l’action effectuée, on revient à la page d’accueil
            //request.setAttribute("view", "aller");
            //actionAfficher(request, response, gameDAO);

        } catch (DAOException e) {
            erreurBD(request, response, e);
        }

    }
   
    /**
     * Ajoute le message au chat correspondant!
     * @param request
     * @param response
     * @param messageDAO
     * @param playerDAO
     * @throws ServletException
     * @throws IOException 
     */
    private void actionNewMessage(HttpServletRequest request,
            HttpServletResponse response,
            MessageDAO messageDAO, PlayerDAO playerDAO, GameDAO gameDAO) throws ServletException, IOException {
        String username = request.getParameter("username");
        String text = request.getParameter("text");
        int gameId = Integer.parseInt(request.getParameter("gameId"));
        int isLg = Integer.parseInt(request.getParameter("isLg"));
        
        Game game = gameDAO.getGame(gameId);
        java.sql.Date currentDate = new Date(Calendar.getInstance().getTimeInMillis());

        Message message = new Message(0, gameId, currentDate, username, text, isLg, game.getDayNb());   //On considère un insomniaque comme un LG qui ne peut pas écrire
        messageDAO.ajouterMessage(message);

        actionAfficherChat(request, response, messageDAO, playerDAO, gameDAO);
    }

    /**
     * change du jour a la nuit
     * @param request
     * @param response
     * @param gameDAO
     * @param playerDAO
     * @throws ServletException
     * @throws IOException 
     */
    private void actionChangeDayNight(HttpServletRequest request,
            HttpServletResponse response,
            GameDAO gameDAO, PlayerDAO playerDAO) throws ServletException, IOException {

        int gameId = Integer.parseInt(request.getParameter("gameId"));
        Game gameCourante = gameDAO.getGame(gameId);
        int isDay = gameCourante.getIsDay();
        
        // On crée le résultat du vote (que ce soit vote de jour où de nuit)
        List<Integer> resultat = gameDAO.depouiller(gameId);
        
        if (isDay == 1) {
            int elim = 0;
            // On commence une nuit
            if (resultat.size() == 1) {
                // 1 chosen, there is a dead
                playerDAO.kill(resultat.get(0));
                
                request.setAttribute("message1", "Vous avez elimine : " + playerDAO.getPlayerFromId(resultat.get(0)).getUsername()  );
                request.setAttribute("message2",  playerDAO.getPlayerFromId(resultat.get(0)).getUsername() +", vous venez de vous faire eliminer...");
                //playerdead = playerDAO.getPlayerFromId(resultat.get(0));
                elim = 1;
            } else {
                // nobody chosen or equality -> no dead 
                request.setAttribute("message3", "Personne n a ete elimine");
            }
            String userplaying = SessionManager.getUserSession(request);
            Player playerplaying = playerDAO.getPlayer(userplaying, gameId);
            request.setAttribute("elim", elim);
            request.setAttribute("userPlayer", playerplaying);
            gameDAO.startNight(gameId);
            actionAfficher(request, response, gameDAO, playerDAO);
            

        } else {
            // We start a day
                        // On commence une journée
            int bitten = 0; int contaminated = 0;
            if (resultat.size() == 1) {
                // 1 chosen, there is a person who is now loup-garou
                playerDAO.mordre(resultat.get(0));
                request.setAttribute("message1",  playerDAO.getPlayerFromId(resultat.get(0)).getUsername() + " a ete mordu");
                request.setAttribute("message2", "vous venez de vous faire mordre... Dommage tes mort");
                bitten = 1;
            } else {
                // nobody chosen or equality -> no dead 

            }
            
            List<Player> listeJoueurs = playerDAO.getListPlayers(gameId, 1); //alive players
            for (Player play : listeJoueurs) {
                if (play.getJustContaminated() == 1) {
                    request.setAttribute("message3", " vous venez de vous faire contaminer...");
                    contaminated = 1;
                }
            }
            if(contaminated == 0 && bitten == 0){
                request.setAttribute("message5", "Personne de mordu, bien calme tout ça!");
            }

            
            //set les attributs sur les morts , ecrire les messages possibles
            String userplaying2 = SessionManager.getUserSession(request);
            Player playerplaying2 = playerDAO.getPlayer(userplaying2, gameId);
            
            
            request.setAttribute("bitten", bitten);
            request.setAttribute("contaminated", contaminated);
            request.setAttribute("userPlayer", playerplaying2);
            gameDAO.startDay(gameId);
            actionAfficher(request, response, gameDAO, playerDAO);
        }
    }
    
    private void actionCheckerFinPartie(HttpServletRequest request,
        HttpServletResponse response,
        GameDAO gameDAO, PlayerDAO playerDAO) throws ServletException, IOException {
        
        int gameId = SessionManager.getGameSession(request);
        List<Player> humans = playerDAO.getListPlayersRole(gameId, 0, 1); //alive humans
        if(humans.isEmpty()){
            request.getRequestDispatcher("/WEB-INF/lgwin.jsp").forward(request, response);
            gameDAO.endGame(gameId);
        }else if (playerDAO.getListPlayersRole(gameId, 1, 1).isEmpty()) { //alive lg
            request.getRequestDispatcher("/WEB-INF/villageoiswin.jsp").forward(request, response);
            gameDAO.endGame(gameId);
        }
        
    }


}
