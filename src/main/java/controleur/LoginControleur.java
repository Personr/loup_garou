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
import tools.SessionManager;

/**
 * Le contrôleur de l'application.
 */
@WebServlet(name = "LoginControleur", urlPatterns = {"/loginControleur"})
public class LoginControleur extends HttpServlet {

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

        try {
            if (action == null) {
                actionAfficher(request, response);
            } else if (action.equals("login")) {
                actionLoginAfficher(request, response);
            } else if (action.equals("compte")) {
                actionCompteAfficher(request, response);
            } else {
                invalidParameters(request, response);
            }
        } catch (DAOException e) {
            erreurBD(request, response, e);
        }
    }

    /**
     *
     * Affiche la page d’accueil
     */
    private void actionAfficher(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        if (!response.isCommitted()) {
            request.getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
        }
    }

    private void actionLoginAfficher(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("message", request.getParameter("message"));
        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

    private void actionCompteAfficher(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("message", request.getParameter("message"));
        request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
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
        // le paramètre "action" est obligatoire en POST
        if (action == null) {
            invalidParameters(request, response);
            return;
        }
        UserDAO userDAO = new UserDAO(ds);

        try {
            if (action.equals("enterlist")) {
                actionLogin(request, response, userDAO);
            } else if (action.equals("createaccount")) {
                actionCompte(request, response, userDAO);
            } else {
                invalidParameters(request, response);
                return;
            }
            /* Une fois l’action effectuée, on revient à la page d’accueil */
            actionAfficher(request, response);

        } catch (DAOException e) {
            erreurBD(request, response, e);
        }
    }

    private void actionLogin(HttpServletRequest request,
            HttpServletResponse response,
            UserDAO userDAO) throws ServletException, IOException {
        //tester si le login et mdp sont dans la BDD, si oui acceder à l'accueil !!
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        if (userDAO.verifyUser(username, password)) {
            //create a session for the user
            SessionManager.setUserSession(username, request);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("homecontroleur");
           // if (!response.isCommitted()) {
            dispatcher.forward(request, response);
           // }
            //request.getRequestDispatcher("/WEB-INF/listeParties.jsp").forward(request, response);
            //controleurParties.doGet(afficherlistepartie);
        } else {
            actionLoginAfficher(request, response);
        }
    }

    private void actionCompte(HttpServletRequest request,
            HttpServletResponse response,
            UserDAO userDAO) throws ServletException, IOException {

        if (userDAO.ajouterUser(request.getParameter("username"), request.getParameter("password"))) {
            request.setAttribute("message", "Le compte a été créé");
            actionAfficher(request, response);
        } else {
            actionCompteAfficher(request, response);
        }
    }

}
