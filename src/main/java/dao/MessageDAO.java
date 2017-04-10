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
import modele.Message;

public class MessageDAO extends AbstractDataBaseDAO {

    public MessageDAO(DataSource ds) {
        super(ds);
    }
    
    /**
     * recupere la liste des messages
     * @param isLG
     * @param gameId
     * @return 
     */
    public List<Message> getListeMessages(int isLG, int gameId, int dayNb) {
        List<Message> result = new ArrayList<Message>();
        try (
                Connection conn = getConn();
                PreparedStatement st = conn.prepareStatement("SELECT * FROM message WHERE isLG = ? AND gameID = ? AND dayNb = ? ORDER BY createdAt");) {
            st.setInt(1, isLG);
            st.setInt(2, gameId);
            st.setInt(3, dayNb);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Message message
                        = new Message(rs.getInt("id"), rs.getInt("gameID"), rs.getDate("createdAt"),
                                rs.getString("username"), rs.getString("text"), rs.getInt("isLG"), rs.getInt("dayNb"));
                result.add(message);
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        }
        return result;
    }

    /**
     * ajoute un message a la conversation
     * @param message 
     */
    public void ajouterMessage(Message message) {
        try (
                Connection conn = getConn();
                PreparedStatement st = conn.prepareStatement("INSERT INTO message "
                        + "(gameID, createdAt, username, text, isLG, dayNb) VALUES (?, ?, ?, ? ,?, ?)");) {
            st.setInt(1, message.getGameId());
            st.setDate(2, message.getDate());
            st.setString(3, message.getUsername());
            st.setString(4, message.getText());
            st.setInt(5, message.getIsLg());
            st.setInt(6, message.getDayNb());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        }
    }
    
    /**
     * recupere les messages d'un id correspondant
     * @param id
     * @return 
     */
    public Message getMessage(int id) {
        Message message = null;
        try (
                Connection conn = getConn();
                PreparedStatement st = conn.prepareStatement("SELECT * FROM message WHERE id = ?");) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            rs.next();
            message = new Message(rs.getInt("id"), rs.getInt("gameID"), rs.getDate("createdAt"), 
                            rs.getString("username"), rs.getString("text"), rs.getInt("isLG"), rs.getInt("dayNb"));
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        }
        return message;
    }
    
}
