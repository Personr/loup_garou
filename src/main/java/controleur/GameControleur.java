package controleur;

import dao.DAOException;
import dao.UserDAO;
import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;
import modele.User;
import dao.MessageDAO;
import dao.GameDAO;
import dao.PlayerDAO;
import modele.Game;
import tools.SessionManager;
import modele.Message;
import java.sql.Date;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
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
     * Actions possibles en GET : afficher (correspond à l’absence du param),
     * getOuvrage.
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
                actionAfficherChat(request, response, messageDAO, playerDAO);
            } else if (action.equals("getGame")) {
                actionAfficher(request, response, gameDAO, playerDAO);
            } else if (action.equals("proposer")) {
                actionProposer(request, response, gameDAO, playerDAO);
            } else if (action.equals("voter")) {
                actionVoter(request, response, gameDAO, playerDAO);
            } else if (action.equals("startGame")) {
                actionStartGame(request, response, playerDAO, gameDAO);
            } else if (action.equals("activatePower")) {
                actionPouvoir(request, response, gameDAO, userDAO, playerDAO, messageDAO);
            } else if (action.equals("getContamination")) {
                actionPouvoirContamination(request, response, gameDAO, userDAO, playerDAO, messageDAO);
            } else if (action.equals("getNoContamination")) {
                actionPouvoirNoContamination(request, response, gameDAO, userDAO, playerDAO, messageDAO);
            } else if (action.equals("getVoyance")) {
                actionPouvoirVoyance(request, response, gameDAO, userDAO, playerDAO, messageDAO);
            } else if (action.equals("getNoVoyance")) {
                actionPouvoirNoVoyance(request, response, gameDAO, userDAO, playerDAO, messageDAO);
            } else if (action.equals("getSpiritisme")) {
                actionPouvoirSpiritisme(request, response, gameDAO, userDAO, playerDAO, messageDAO);
            } else if (action.equals("getNoSpiritisme")) {
                actionPouvoirNoSpiritisme(request, response, gameDAO, userDAO, playerDAO, messageDAO);
            } else if (action.equals("testSpiritisme")) {
                actionPouvoirSpiritisme(request, response, gameDAO, userDAO, playerDAO, messageDAO);
            } else {
                invalidParameters(request, response);
            }
        } catch (DAOException e) {
            erreurBD(request, response, e);
        }
    }

    
    private void actionPouvoirSpiritisme(HttpServletRequest request, HttpServletResponse response, GameDAO gameDAO, UserDAO userDAO, PlayerDAO playerDAO, MessageDAO messageDAO) throws ServletException, IOException {
//        String username = request.getParameter("username");
//        int gameId = Integer.parseInt(request.getParameter("gameId"));
//        Player joueur = playerDAO.getPlayer(username, gameId);

        int gameId = Integer.parseInt(request.getParameter("gameId"));
        String username = request.getParameter("username");
        Player player = playerDAO.getPlayer(username, gameId);
        
        request.setAttribute("player", player);

        List<Message> messages = messageDAO.getListeMessages(2, gameId);

        request.setAttribute("gameId", gameId);
        request.setAttribute("messages", messages);
        request.getRequestDispatcher("/WEB-INF/spiritisme.jsp").forward(request, response);
    }

    private void actionPouvoirNoSpiritisme(HttpServletRequest request, HttpServletResponse response, GameDAO gameDAO, UserDAO userDAO, PlayerDAO playerDAO, MessageDAO messageDAO) throws ServletException, IOException {
        String username = request.getParameter("username");
        int gameId = Integer.parseInt(request.getParameter("gameId"));
        Player joueur = playerDAO.getPlayer(username, gameId);
        playerDAO.pouvoirSpiritismeUtilise(joueur.getId(),0);
        request.setAttribute("message", "vous n avez pas utilise le spiritisme");
        actionAfficher(request, response, gameDAO, playerDAO);
    }
    
    /**
     *
     * Affiche la page d’accueil d'une game
     */
    private void actionAfficher(HttpServletRequest request,
            HttpServletResponse response, GameDAO gameDAO, PlayerDAO playerDAO) 
            throws ServletException, IOException {
        //Game game = gameDAO.getGame(Integer.parseInt(request.getParameter("id")));
        //request.setAttribute("game", game);
        String username = SessionManager.getUserSession(request);
        int gameID = SessionManager.getGameSession(request);
        Game userGame = gameDAO.getGame(gameID);
        Player userPlayer = playerDAO.getPlayer(username, gameID);
        
        List<Player> players= playerDAO.getListPlayers(gameID);
        request.setAttribute("players", players);
        
        List<Player> proposable = playerDAO.getListPlayersProposable(gameID);
        request.setAttribute("proposable", proposable);
        
        List<Player> votable = playerDAO.getListPlayersVotable(gameID);
        request.setAttribute("votable", votable);

        request.setAttribute("userPlayer", userPlayer);
        request.setAttribute("gameId", userGame.getGameId());
        request.setAttribute("username", username);
        if (userGame.getIsDay() == 1) {
            request.getRequestDispatcher("/WEB-INF/night.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/WEB-INF/day.jsp").forward(request, response);
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
        actionAfficher(request, response, gameDAO, playerDAO);

    }
    
    /**
     *
     * Vote pour un joueur
     */
    private void actionVoter(HttpServletRequest request,
            HttpServletResponse response, GameDAO gameDAO, PlayerDAO playerDAO)
            throws ServletException, IOException {

        int cibleId = Integer.parseInt(request.getParameter("toVoteId"));
        String username = SessionManager.getUserSession(request);
        int gameId = SessionManager.getGameSession(request);
        Player player = playerDAO.getPlayer(username, gameId);
        int userId = player.getId();
        
        
        playerDAO.voter(userId, cibleId);
        actionAfficher(request, response, gameDAO, playerDAO);

    }
    

    private void actionPouvoirContamination(HttpServletRequest request,
            HttpServletResponse response, GameDAO gameDAO, UserDAO userDAO, PlayerDAO playerDAO, MessageDAO messageDAO) throws ServletException, IOException {

        String username = request.getParameter("username");
        int gameId = Integer.parseInt(request.getParameter("gameId"));
        Player joueur = playerDAO.getPlayer(username, gameId);
        playerDAO.transformerLoupGarou(joueur.getId());
        request.setAttribute("message", "vous avez contamine : " + username);
        actionAfficher(request, response, gameDAO, playerDAO);
        
    }

    private void actionPouvoirNoContamination(HttpServletRequest request,
            HttpServletResponse response, GameDAO gameDAO, UserDAO userDAO, PlayerDAO playerDAO, MessageDAO messageDAO) throws ServletException, IOException {

        String username = request.getParameter("username");
        int gameId = Integer.parseInt(request.getParameter("gameId"));
        Player joueur = playerDAO.getPlayer(username, gameId);
        playerDAO.pouvoirContaminationUtilise(joueur.getId(),0);
        request.setAttribute("message", "vous n avez contamine personne");
        actionAfficher(request, response, gameDAO, playerDAO);
        
    }    
    
    private void actionPouvoirVoyance(HttpServletRequest request,
            HttpServletResponse response, GameDAO gameDAO, UserDAO userDAO, PlayerDAO playerDAO, MessageDAO messageDAO) throws ServletException, IOException {
        
        String username = request.getParameter("username");
        int gameId = Integer.parseInt(request.getParameter("gameId"));
        Player joueur = playerDAO.getPlayer(username, gameId);
        request.setAttribute("spieduser", joueur);
        request.getRequestDispatcher("/WEB-INF/voyancePlayer.jsp").forward(request, response);
    }
    private void actionPouvoirNoVoyance(HttpServletRequest request,
            HttpServletResponse response, GameDAO gameDAO, UserDAO userDAO, PlayerDAO playerDAO, MessageDAO messageDAO) throws ServletException, IOException {

        String username = request.getParameter("username");
        int gameId = Integer.parseInt(request.getParameter("gameId"));
        Player joueur = playerDAO.getPlayer(username, gameId);
        //joueur.setUsedVoyance(0);
        playerDAO.pouvoirVoyanceUtilise(joueur.getId(),0);
        request.setAttribute("message", "vous n avez espionne personne");
        actionAfficher(request, response, gameDAO, playerDAO);
    }

    
    /**
     *
     * Active le pouvoir du joueur (si possible)
     */
    private void actionPouvoir(HttpServletRequest request,
            HttpServletResponse response, GameDAO gameDAO, UserDAO userDAO, PlayerDAO playerDAO, MessageDAO messageDAO) throws ServletException, IOException {

        /* On interroge la base de données pour obtenir le player et les caractéristiques de son pouvoir */
        String username = request.getParameter("username");
        int gameId = Integer.parseInt(request.getParameter("gameId"));
        Player joueur = playerDAO.getPlayer(username, gameId);
        //modele.Game.ajouterJoueur(username, joueur);

        if (joueur.getHasContamination() == 1) {
            if (joueur.getUsedContamination() == 0) {
                List<Player> mapHumains = playerDAO.getListPlayersVillageois(gameId);
                System.out.println(mapHumains);
                request.setAttribute("gameId", gameId);
                request.setAttribute("mapHumains", mapHumains);
                request.setAttribute("username", username);
                playerDAO.pouvoirContaminationUtilise(joueur.getId(),1);
                request.getRequestDispatcher("/WEB-INF/contamination.jsp").forward(request, response);

            } else {
                request.setAttribute("message", "Vous avez deja active votre pouvoir de contamination");
                actionAfficher(request, response, gameDAO, playerDAO);
            }

            //request.setAttribute("message", "Vous avez activer votre pouvoir de contamination");
            //actionAfficher(request,response);            
        } else if (joueur.getHasInsomnie() == 1) {

            if (joueur.getUsedInsomnie() == 0) {
                //Rejoint chatLG2    une copie de chatLG sans barre de chat
                //request.setAttribute("insomnie", "1");
                request.setAttribute("pouvoir", "Vous etes invisibles, en mode insomnie");
                actionAfficherChat(request, response, messageDAO, playerDAO);

            } else {

                request.setAttribute("message", "Il fait jour, impossible d utiliser votre pouvoir");
                actionAfficher(request, response, gameDAO, playerDAO);
            }
        } else if (joueur.getHasVoyance() == 1) {
            if (joueur.getUsedVoyance() == 0) {
                
                gameId = Integer.parseInt(request.getParameter("gameId")); 
                List<Player> mapJoueurs = playerDAO.getListPlayers(gameId);
                //Map<String, Player> mapJoueurs = gameDAO.getGame(gameId).getMapJoueurs();
                System.out.println(mapJoueurs);
                request.setAttribute("gameId", gameId);
                request.setAttribute("mapJoueurs", mapJoueurs);
                request.setAttribute("username", username);
                playerDAO.pouvoirVoyanceUtilise(joueur.getId(),1);
                request.getRequestDispatcher("/WEB-INF/voyance.jsp").forward(request, response);
            } else {
                request.setAttribute("message", "Vous avez deja active votre pouvoir de voyance");
                actionAfficher(request, response, gameDAO, playerDAO);
            }
        } else if (joueur.getHasSpiritisme() == 1) {
            if (joueur.getUsedSpiritisme() == 0) {
                gameId = Integer.parseInt(request.getParameter("gameId")); 
                List<Player> joueursMorts = playerDAO.getListPlayersMorts(gameId);
                playerDAO.pouvoirSpiritismeUtilise(joueur.getId(),1);
                request.setAttribute("gameId", gameId);
                request.setAttribute("joueursMorts", joueursMorts);
                request.setAttribute("username", username);
                request.getRequestDispatcher("/WEB-INF/choisirSpiritisme.jsp").forward(request, response);
            } else {
                request.setAttribute("message", "Vous avez deja active votre pouvoir de spiritisme");
                actionAfficher(request, response, gameDAO, playerDAO);
            }
        } else {

            request.setAttribute("message", "hey vous n avez pas de pouvoir!!!");
            actionAfficher(request, response, gameDAO, playerDAO);
        }

    }

    private void actionAfficherChat(HttpServletRequest request,
            HttpServletResponse response,
            MessageDAO messageDAO, PlayerDAO playerDAO) throws ServletException, IOException {

        /* On interroge la base de données pour obtenir la liste des messages */
        int gameId = Integer.parseInt(request.getParameter("gameId"));
        int isLg = Integer.parseInt(request.getParameter("isLg"));
        String username = request.getParameter("username");
        Player player = playerDAO.getPlayer(username, gameId);
        
        request.setAttribute("player", player);

        List<Message> messages = messageDAO.getListeMessages(isLg, gameId);

        request.setAttribute("gameId", gameId);
        request.setAttribute("messages", messages);

        request.setAttribute("isLg", isLg);
        request.getRequestDispatcher("/WEB-INF/gameChat.jsp").forward(request, response);
    }

    private void actionStartGame(HttpServletRequest request,
            HttpServletResponse response,
            PlayerDAO playerDAO, GameDAO gameDAO) throws ServletException, IOException {

        int gameID = SessionManager.getGameSession(request);
        Game game = gameDAO.getGame(gameID);
        if (game != null && game.startGame(playerDAO, gameDAO)) {
            SessionManager.setGameSession(game.getGameId(), request);
            actionAfficher(request, response, gameDAO, playerDAO);
        } else {
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
                actionNewMessage(request, response, messageDAO, playerDAO);
            } else if (action.equals("newMessageSpiritisme")) {
                actionNewMessageSpiritisme(request, response, messageDAO, playerDAO, gameDAO, userDAO);
            } else if (action.equals("changeDayNight")) {
                actionChangeDayNight(request, response, gameDAO, playerDAO);
                actionNewMessage(request, response, messageDAO, playerDAO);
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

    private void actionNewMessage(HttpServletRequest request,
            HttpServletResponse response,
            MessageDAO messageDAO, PlayerDAO playerDAO) throws ServletException, IOException {
        String username = request.getParameter("username");
        String text = request.getParameter("text");
        int gameId = Integer.parseInt(request.getParameter("gameId"));
        int isLg = Integer.parseInt(request.getParameter("isLg"));
        // int insomnie = Integer.parseInt(request.getParameter("insomnie"));
        java.sql.Date currentDate = new Date(Calendar.getInstance().getTimeInMillis());

        Message message = new Message(0, gameId, currentDate, username, text, isLg);//+ insomnie));    //On considère un insomniaque comme un LG qui ne peut pas écrire
        messageDAO.ajouterMessage(message);

        actionAfficherChat(request, response, messageDAO, playerDAO);
    }
    
    private void actionNewMessageSpiritisme(HttpServletRequest request,
            HttpServletResponse response,
            MessageDAO messageDAO, PlayerDAO playerDAO, GameDAO gameDAO, UserDAO userDAO) throws ServletException, IOException {
        String username = request.getParameter("username");
        String text = request.getParameter("text");
        int gameId = Integer.parseInt(request.getParameter("gameId"));
        //int isLg = Integer.parseInt(request.getParameter("isLg"));
        // int insomnie = Integer.parseInt(request.getParameter("insomnie"));
        java.sql.Date currentDate = new Date(Calendar.getInstance().getTimeInMillis());

        Message message = new Message(0, gameId, currentDate, username, text, 2);//+ insomnie));    //On considère un insomniaque comme un LG qui ne peut pas écrire
        messageDAO.ajouterMessage(message);

        actionPouvoirSpiritisme(request, response, 
            gameDAO, userDAO, playerDAO, messageDAO);
    }

    private void actionChangeDayNight(HttpServletRequest request,
            HttpServletResponse response,
            GameDAO gameDAO, PlayerDAO playerDAO) throws ServletException, IOException {

        int gameId = Integer.parseInt(request.getParameter("gameId"));
        gameDAO.changeDayNight(gameId);
        actionAfficher(request, response, gameDAO, playerDAO);
    }



}
