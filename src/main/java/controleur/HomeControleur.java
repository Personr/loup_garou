package controleur;

import dao.DAOException;
import dao.OuvrageDAO;
import dao.GameDAO;
import dao.PlayerDAO;
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
import modele.Ouvrage;
import modele.Game;
import modele.Player;
import tools.SessionManager;
/**
 *
 * @author cazardn
 */

@WebServlet(name = "HomeControleur", urlPatterns = {"/homecontroleur"})
public class HomeControleur extends HttpServlet {

    @Resource(name = "jdbc/bibliography")
    private DataSource ds;

    /**
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    private void invalidParameters(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/controleurErreur.jsp").forward(request, response);
    }

    /**
     * 
     * @param request
     * @param response
     * @param e
     * @throws ServletException
     * @throws IOException 
     */
    private void erreurBD(HttpServletRequest request,
            HttpServletResponse response, DAOException e)
            throws ServletException, IOException {
        request.setAttribute("erreurMessage", e.getMessage());
        request.getRequestDispatcher("/WEB-INF/bdErreur.jsp").forward(request, response);
    }
    
    /**
     * Actions possibles en GET : afficher (correspond à l’absence du param)
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException 
     */
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
        
        SessionManager.checkUserSession(request, response);

        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        GameDAO gameDAO = new GameDAO(ds);
        PlayerDAO playerDAO = new PlayerDAO(ds);

        try {
            if (action.equals("partie")) {
                actionCreatePartieAfficher(request, response, gameDAO);
            } else if (action.equals("creategame")) {
                actionCreatePartieAfficher(request, response, gameDAO);
             } else if (action.equals("seerules")) {
                actionSeeRules(request, response, gameDAO);
            } else if (action.equals("nouveaujoueur")) {
                actionNouveauJoueur(request, response, gameDAO, playerDAO);
            } else if (action.equals("admin")) {
                actionAdminAfficher(request, response, gameDAO);
            } else if (action.equals("supprimerparties")) {
                actionAdminSupprimer(request, response, gameDAO);
            } else if (action.equals("startGame")) {
                actionAfficher(request, response, gameDAO);
                request.setAttribute("inGame", true);
            } else if (action.equals("getWaitingGame")) {
                request.setAttribute("inGame", true);
                actionAfficher(request, response, gameDAO);  
            } else if (action.equals("gameLeft")) {
                actionGameLeft(request, response, playerDAO, gameDAO);  
            } else {
                invalidParameters(request, response);
            }
        } catch (DAOException e) {
            erreurBD(request, response, e);
        }
    }
    
    /**
     * Affiche la page de creation de partie.
     * @param request
     * @param response
     * @param gameDAO
     * @throws ServletException
     * @throws IOException 
     */
    private void actionCreatePartieAfficher(HttpServletRequest request,
            HttpServletResponse response,
            GameDAO gameDAO) throws ServletException, IOException {
        
        request.getRequestDispatcher("/WEB-INF/creerpartie.jsp").forward(request, response);
        
    }

    private void actionAdminAfficher(HttpServletRequest request,
            HttpServletResponse response,
            GameDAO gameDAO) throws ServletException, IOException {
        
        request.getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
    }
    
    
    private void actionAdminSupprimer(HttpServletRequest request,
        HttpServletResponse response,
        GameDAO gameDAO) throws ServletException, IOException {
        gameDAO.deleteGames();
        request.setAttribute("message", "Parties Supprimees");
        actionAfficher(request,response,gameDAO);
    }
    
    private void actionGameLeft(HttpServletRequest request,
        HttpServletResponse response,
        PlayerDAO playerDAO, GameDAO gameDAO) throws ServletException, IOException {
        
        String myUsername = SessionManager.getUserSession(request);
        int gameId = Integer.parseInt(request.getParameter("gameId"));
        Player myPlayer = playerDAO.getPlayer(myUsername, gameId);
        playerDAO.leaveGame(myPlayer.getId());
        
        actionAfficher(request,response,gameDAO);
    }
    

    
    /**
     * rajoute le joueur dans la game (si possible!)
     * @param request
     * @param response
     * @param gameDAO
     * @param playerDAO
     * @throws ServletException
     * @throws IOException 
     */
    private void actionNouveauJoueur(HttpServletRequest request,
            HttpServletResponse response,
            GameDAO gameDAO, PlayerDAO playerDAO) throws ServletException, IOException {
        
        if (request.getParameter("gameId") != null) {
            
            String username = SessionManager.getUserSession(request);
            int gameID = Integer.parseInt(request.getParameter("gameId"));
            Game game = gameDAO.getGame(gameID);
            
            Player player = playerDAO.getPlayer(username, gameID);
            if (player != null) {
                // le joueur est déjà dans la partie
                request.setAttribute("inGame", true);
                SessionManager.setGameSession(game.getGameId(), request);
                actionAfficher(request,response, gameDAO);
                return;
            }
          
            if (playerDAO.ajouterPlayer(username, gameID) && gameDAO.incrementerNbJoueurs(game)) {
                request.setAttribute("inGame", true);
                game.incrNbPlayers();
                SessionManager.setGameSession(game.getGameId(), request);
                actionAfficher(request, response, gameDAO);
                
                
            } else {
                request.setAttribute("message", "trop de joueurs");
                actionAfficher(request,response, gameDAO);
            }
            
        } else {
            request.setAttribute("message", "mauvais ID de partie");
            actionAfficher(request,response, gameDAO);
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
        
        SessionManager.checkUserSession(request, response);

        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        // le paramètre "action" est obligatoire en POST
        if (action == null) {
            return;
        }
        GameDAO gameDAO = new GameDAO(ds);
        
        try {
            if (action.equals("enterlist")) {
                actionAfficher(request, response, gameDAO);
            } else if (action.equals("createpartie")) {
                actionCreatePartie(request, response, gameDAO);
              
            } else {
                invalidParameters(request, response);
            }           
        } catch (DAOException e) {
            erreurBD(request, response, e);
        }
        
    }

    /**
     * affiche la page d'attente ou de jeu (si le joueur est deja dans une game)
     * @param request
     * @param response
     * @param gameDAO
     * @throws IOException
     * @throws ServletException 
     */
    private void actionAfficher(HttpServletRequest request, HttpServletResponse response, GameDAO gameDAO)
            throws IOException, ServletException {
        if (request.getAttribute("inGame") == null || !(boolean)request.getAttribute("inGame")) {
            /* On interroge la base de données pour obtenir la liste des games en cours */
            List<Game> games = gameDAO.getListGamesNotStarted();
            request.setAttribute("games", games);
            
            request.getRequestDispatcher("/WEB-INF/listeGames.jsp").forward(request, response);
        } else {
            int gameId = SessionManager.getGameSession(request);
            Game game = gameDAO.getGame(gameId);
            if (game.getStarted() == 0) {
                request.setAttribute("game", game);
                request.getRequestDispatcher("/WEB-INF/waitingGame.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("gamecontroleur").forward(request, response);
            }
        }
    }
    
    /**
     * page avec les règles du jeu
     * @param request
     * @param response
     * @param gameDAO
     * @throws ServletException
     * @throws IOException 
     */
    private void actionSeeRules(HttpServletRequest request,
            HttpServletResponse response,
            GameDAO gameDAO) throws ServletException, IOException {
        
        request.getRequestDispatcher("/WEB-INF/rules.html").forward(request, response);     
    }
    
    /**
     * creer la partie! - verifie que tous les champs sont remplis
     * @param request
     * @param response
     * @param gameDAO
     * @throws ServletException
     * @throws IOException 
     */
    private void actionCreatePartie(HttpServletRequest request,
            HttpServletResponse response,
            GameDAO gameDAO) throws ServletException, IOException {
        
        if (request.getParameter("nombre_participants_min").isEmpty()
                || request.getParameter("nombre_participants_max").isEmpty() || request.getParameter("pContamination").isEmpty()
                || request.getParameter("pSpiritisme").isEmpty()    || request.getParameter("pVoyance").isEmpty()
                || request.getParameter("pInsomnie").isEmpty()      || request.getParameter("proportion_loup_garou").isEmpty()){
            
            request.setAttribute("message", "Remplir tous les champs");
            actionCreatePartieAfficher(request,response, gameDAO);
                
        } else {
            int nbJoueursMin = Integer.parseInt(request.getParameter("nombre_participants_min"));
            int nbJoueursMax = Integer.parseInt(request.getParameter("nombre_participants_max"));
            int isManual = Integer.parseInt(request.getParameter("Automatique"));
            
            String dayDebut = request.getParameter("dl"); 
            
            String heureJour = request.getParameter("hd") + ":" + request.getParameter("md");
            String heureNuit = request.getParameter("hn") + ":" + request.getParameter("mn");
            String debut = request.getParameter("hl") + ":" + request.getParameter("ml");
            
            float pContamination = Float.parseFloat(request.getParameter("pContamination"));
            float pSpiritisme = Float.parseFloat(request.getParameter("pSpiritisme"));
            float pVoyance = Float.parseFloat(request.getParameter("pVoyance"));
            float pInsomnie = Float.parseFloat(request.getParameter("pInsomnie"));

            float proportionLoupsGarous = Float.parseFloat(request.getParameter("proportion_loup_garou"));
            String creator = SessionManager.getUserSession(request);
            
            if(gameDAO.creerPartie(nbJoueursMin, nbJoueursMax, heureJour, heureNuit, debut, 
                    creator, pContamination, pSpiritisme, pVoyance, pInsomnie, proportionLoupsGarous, dayDebut, isManual)){
                request.setAttribute("message", "Partie bien crée!");
                actionAfficher(request, response,gameDAO);
            } else {
                request.setAttribute("message", "Attention, respectez les conditions de jeu");
                actionCreatePartieAfficher(request,response, gameDAO);
            }

        }


        
    }

}
