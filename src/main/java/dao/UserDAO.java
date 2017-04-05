/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import modele.Ouvrage;

public class UserDAO extends AbstractDataBaseDAO {
    
    public UserDAO(DataSource ds) {
        super(ds);
    }
    
    public void ajouterUser(String username, String password) {
        try (
                Connection conn = getConn();
                PreparedStatement st = conn.prepareStatement("INSERT INTO bibliographie (auteur, titre) VALUES (?, ?)");) {
            st.setString(1, auteur);
            st.setString(2, titre);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        }
    }
    
}
