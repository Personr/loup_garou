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
            } else if (action.equals("creategame")) {
                actionCreatePartieAfficher(request, response, gameDAO);
             } else if (action.equals("seerules")) {
            actionSeeRules(request, response, gameDAO);
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
        request.getRequestDispatcher("/WEB-INF/listeParties.jsp").forward(request, response);//A retourner sur ListePartie
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
                actionCreatePartie(request, response, gameDAO);
           
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
    
    private void actionCreatePartieAfficher(HttpServletRequest request,
            HttpServletResponse response,
            GameDAO gameDAO) throws ServletException, IOException {
        
        request.getRequestDispatcher("/WEB-INF/creerpartie.jsp").forward(request, response);
        
    }
    
    private void actionSeeRules(HttpServletRequest request,
            HttpServletResponse response,
            GameDAO gameDAO) throws ServletException, IOException {
        
        request.getRequestDispatcher("/WEB-INF/rules.html").forward(request, response);
        
    }
    
    
    private void actionCreatePartie(HttpServletRequest request,
            HttpServletResponse response,
            GameDAO gameDAO) throws ServletException, IOException {
        
        if(request.getParameter("nombre_participants_min").isEmpty()
                || request.getParameter("nombre_participants_max").isEmpty()
                || request.getParameter("duree_jour").isEmpty()     || request.getParameter("duree_nuit").isEmpty()
                || request.getParameter("horaire_debut").isEmpty()  || request.getParameter("pContamination").isEmpty()
                || request.getParameter("pSpiritisme").isEmpty()    || request.getParameter("pVoyance").isEmpty()
                || request.getParameter("pInsomnie").isEmpty()      || request.getParameter("proportion_loup_garou").isEmpty()){
            
            request.setAttribute("message", "Remplir tous les champs");
            actionCreatePartieAfficher(request,response, gameDAO);
                
        }else{
            int nbJoueursMin = Integer.parseInt(request.getParameter("nombre_participants_min"));
            int nbJoueursMax = Integer.parseInt(request.getParameter("nombre_participants_max"));
            int dureeJour = Integer.parseInt(request.getParameter("duree_jour"));
            int dureeNuit = Integer.parseInt(request.getParameter("duree_nuit"));
            

            int horaireDebutPartie = Integer.parseInt(request.getParameter("horaire_debut"));
            float pContamination = Float.parseFloat(request.getParameter("pContamination"));
            float pSpiritisme = Float.parseFloat(request.getParameter("pSpiritisme"));
            float pVoyance = Float.parseFloat(request.getParameter("pVoyance"));
            float pInsomnie = Float.parseFloat(request.getParameter("pInsomnie"));

            float proportionLoupsGarous = Float.parseFloat(request.getParameter("proportion_loup_garou"));
            String creator = SessionManager.getSessionParameter("username", request);
            
            if(gameDAO.creerPartie(nbJoueursMin, nbJoueursMax, dureeJour, dureeNuit, horaireDebutPartie, creator, pContamination,pSpiritisme,pVoyance,pInsomnie, proportionLoupsGarous)){
                List<Game> games = gameDAO.getListeGames();
                request.setAttribute("game", games);
                request.setAttribute("message", "Partie bien crée!");
                actionAfficher(request, response,gameDAO);
            }else{
                request.setAttribute("message", "Attention, respectez les conditions de jeu");
                actionCreatePartieAfficher(request,response, gameDAO);
            }

        }


        
    }

}
