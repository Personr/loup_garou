package controleur;

import dao.DAOException;
import dao.OuvrageDAO;
import dao.GameDAO;
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
import tools.SessionManager;
/**
 *
 * @author cazardn
 */

@WebServlet(name = "HomeControleur", urlPatterns = {"/homecontroleur"})
public class HomeControleur extends HttpServlet {

    @Resource(name = "jdbc/bibliography")
    private DataSource ds;

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

    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
        
        SessionManager.checkUserSession(request, response);

        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        GameDAO gameDAO = new GameDAO(ds);

        try {
            if (action.equals("partie")) {
                actionPartieAfficher(request, response, gameDAO);
            } else {
                invalidParameters(request, response);
            }
        } catch (DAOException e) {
            erreurBD(request, response, e);
        }
    }

    private void actionPartieAfficher(HttpServletRequest request,
            HttpServletResponse response,
            GameDAO gameDAO) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/creerpartie.html").forward(request, response);
    }

    private void actionPartie(HttpServletRequest request,
            HttpServletResponse response,
            GameDAO gameDAO) throws ServletException, IOException {

        //On met a jour la bdd des parties ? Avec les paramètres
        request.getRequestDispatcher("/WEB-INF/login.html").forward(request, response);//A retourner sur ListePartie
        //controleurParties.doGet(afficherlistepartie);
    }

    
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
                actionPartie(request, response, gameDAO);
            } else {
                invalidParameters(request, response);
            }           
        } catch (DAOException e) {
            erreurBD(request, response, e);
        }
        
    }

    private void actionAfficher(HttpServletRequest request, HttpServletResponse response, GameDAO gameDAO)
            throws IOException, ServletException {
        /* On interroge la base de données pour obtenir la liste des games en cours */
        List<Game> games = gameDAO.getListeGames();
        /* On ajoute cette liste à la requête en tant qu’attribut afin de la transférer à la vue
         * Rem. : ne pas confondre attribut (= objet ajouté à la requête par le programme
         * avant un forward, comme ici)
         * et paramètre (= chaîne représentant des données de formulaire envoyées par le client) */
        request.setAttribute("game", games);
        /* Enfin on transfère la requête avec cet attribut supplémentaire vers la vue qui convient */
        request.getRequestDispatcher("/WEB-INF/listeGames.jsp").forward(request, response);
    }

}
