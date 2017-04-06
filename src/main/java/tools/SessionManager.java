/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author personr
 */
public class SessionManager {
    
    public static void setUserSession(String username, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("username", username);
    }
    
    /**
     *
     * @param username
     * @param request
     * @return username
     */
    public static String getUserSession(String username, HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (String) session.getAttribute(username);
        
    }
    
    public static void checkUserSession(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HttpSession session = request.getSession();
        String name = null;
        if (session != null) {
            name = (String) session.getAttribute("username");
        }             
        if (session == null || name == null) {
            response.sendRedirect("accueil.jsp");
        }
    }

}
