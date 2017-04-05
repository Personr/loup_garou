package controleur;

import dao.DAOException;
import dao.OuvrageDAO;
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

        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        OuvrageDAO ouvrageDAO = new OuvrageDAO(ds);

        try {
            if (action.equals("enterlist")) {
                actionPartieAfficher(request, response, ouvrageDAO);
            } else if (action.equals("partie")) {
                actionPartieAfficher(request, response, ouvrageDAO);
            } else {
                invalidParameters(request, response);
            }
        } catch (DAOException e) {
            erreurBD(request, response, e);
        }
    }

    private void actionPartieAfficher(HttpServletRequest request,
            HttpServletResponse response,
            OuvrageDAO ouvrageDAO) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/creerpartie.html").forward(request, response);
    }

    private void actionPartie(HttpServletRequest request,
            HttpServletResponse response,
            OuvrageDAO ouvrageDAO) throws ServletException, IOException {

        //On met a jour la bdd des parties ? Avec les paramètres
        request.getRequestDispatcher("/WEB-INF/login.html").forward(request, response);//A retourner sur ListePartie
        //controleurParties.doGet(afficherlistepartie);
    }

    
    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        // le paramètre "action" est obligatoire en POST
        if (action == null) {
            return;
        }
        OuvrageDAO ouvrageDAO = new OuvrageDAO(ds);
        
        try {
            if (action.equals("createpartie")) {
                actionPartie(request, response, ouvrageDAO);
            }            
        } catch (DAOException e) {
            erreurBD(request, response, e);
        }
        
    }
}
