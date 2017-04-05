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
import modele.User;

public class UserDAO extends AbstractDataBaseDAO {
    
    public UserDAO(DataSource ds) {
        super(ds);
    }
    
    public void ajouterUser(String username, String password) {
        try (
                Connection conn = getConn();
                PreparedStatement st = conn.prepareStatement("INSERT INTO lgUser (auteur, titre) VALUES (?, ?)");) {
            st.setString(1, username);
            st.setString(2, password);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        }
    }
    
    public User getUser(String username) {
        User user = null;
        try (
                Connection conn = getConn();
                PreparedStatement st = conn.prepareStatement("SELECT * FROM lgUser WHERE username = ?");) {
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            rs.next();
            user = new User(rs.getString("username"), rs.getString("password"));
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        }
        return user;
    }
    
}
