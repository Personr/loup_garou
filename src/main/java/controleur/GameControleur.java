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
                actionAfficherChat(request, response, messageDAO);
            } else if (action.equals("getGame")) {
                actionAfficher(request, response);
            } else if (action.equals("startGame")) {
                actionStartGame(request, response, playerDAO, gameDAO);
                
//             } else if (action.equals("getGame")) {
//                actionPouvoir(request, response, gameDAO, userDAO);
                   
            } else {
                invalidParameters(request, response);
            }
        } catch (DAOException e) {
            erreurBD(request, response, e);
        }
    }

    /**
     *
     * Affiche la page d’accueil d'une game
     */
    private void actionAfficher(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        //Game game = gameDAO.getGame(Integer.parseInt(request.getParameter("id")));
        //request.setAttribute("game", game);
        String username = SessionManager.getUserSession(request);
        Game userGame = (Game) SessionManager.getGameSession(request);

        request.setAttribute("gameId", userGame.getGameId());
        request.setAttribute("username", username);
        request.getRequestDispatcher("/WEB-INF/game.jsp").forward(request, response);

    }
    
    /**
     *
     * Active le pouvoir du joueur (si possible)
     */
//    private void actionPouvoir(HttpServletRequest request,
//            HttpServletResponse response, GameDAO gameDAO, UserDAO userDAO) throws ServletException, IOException {
//        
//        /* On interroge la base de données pour obtenir le player et les caractéristiques de son pouvoir */
//        
//        if()
//        
//        
//        
//    }
    
    
    private void actionAfficherChat(HttpServletRequest request,
            HttpServletResponse response,
            MessageDAO messageDAO) throws ServletException, IOException {
        
        /* On interroge la base de données pour obtenir la liste des messages */
        int gameId = Integer.parseInt(request.getParameter("gameId"));
        int isLg = Integer.parseInt(request.getParameter("isLg"));
        List<Message> messages = messageDAO.getListeMessages(isLg, gameId);
        
        request.setAttribute("gameId", gameId);
        request.setAttribute("messages", messages);
        request.setAttribute("isLg", isLg);
        request.getRequestDispatcher("/WEB-INF/gameChat.jsp").forward(request, response);
    }
    
    private void actionStartGame(HttpServletRequest request,
            HttpServletResponse response,
            PlayerDAO playerDAO, GameDAO gameDAO) throws ServletException, IOException {
        
        Game game = (Game) request.getAttribute("game");
        game.startGame(playerDAO, gameDAO);
        SessionManager.setGameSession(game, request);
        
        actionAfficher(request, response);
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
        

        try {
            
            if (action.equals("enterlist")) {
                actionAfficher(request, response);
            } else if (action.equals("newMessage")) {
                actionNewMessage(request, response, messageDAO);
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
            MessageDAO messageDAO) throws ServletException, IOException {
        String username = request.getParameter("username");
        String text = request.getParameter("text");
        int gameId = Integer.parseInt(request.getParameter("gameId"));
        int isLg = Integer.parseInt(request.getParameter("isLg"));
        java.sql.Date currentDate = new Date(Calendar.getInstance().getTimeInMillis());
        
        Message message = new Message(0, gameId, currentDate, username, text, isLg);
        messageDAO.ajouterMessage(message);
        
        actionAfficherChat(request, response, messageDAO);
    }

}
