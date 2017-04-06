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
import java.io.*;

public class UserDAO extends AbstractDataBaseDAO {
    
    public UserDAO(DataSource ds) {
        super(ds);
    }
    
    //return false if user already exists
    public boolean ajouterUser(String username, String password) {
        if (getUser(username) != null || username.length() == 0 || password.length() == 0) {
            return false;
        }
        try (
                Connection conn = getConn();
                PreparedStatement st = conn.prepareStatement("INSERT INTO lgUser (username, password) VALUES (?, ?)");) {
            st.setString(1, username);
            st.setString(2, password);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        }
        return true;
    }
    
    //return null if user doesn't exist
    public User getUser(String username) {
        User user = null;
        try (
                Connection conn = getConn();
                PreparedStatement st = conn.prepareStatement("SELECT * FROM lgUser WHERE username = ?");) {
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            
            if (rs.next()) {
                user = new User(rs.getString("username"), rs.getString("password"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        }
        return user;
    }
    
    //return true if good creditentials
    public boolean verifyUser(String username, String password) {
        try (
                Connection conn = getConn();
                PreparedStatement st = conn.prepareStatement("SELECT * FROM lgUser WHERE username = ? AND password = ?");) {
            st.setString(1, username);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        }
    }
    
    public int getUserGameId(String username) {
        int gameId;
        try (
                Connection conn = getConn();
                PreparedStatement st = conn.prepareStatement("SELECT p.gameID FROM lgUser u, player p, game g WHERE u.username = ? AND p.username = ? AND p.gameID = g.gameID AND g.finished = 0");) {
            st.setString(1, username);
            st.setString(2, username);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                gameId = rs.getInt("gameID");
            } else {
                gameId = -1;
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        }
        return gameId;
    }

}
