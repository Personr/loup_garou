/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import modele.Player;

public class PlayerDAO extends AbstractDataBaseDAO {
    
    public PlayerDAO(DataSource ds) {
        super(ds);
    }
    
    public Map<String, Player> getListePlayers(int gameId) {
        Map<String, Player> result = new HashMap<String, Player>();
        try (
                Connection conn = getConn();
                PreparedStatement st = conn.prepareStatement("SELECT * FROM player WHERE gameID = ?");) {
            st.setInt(1, gameId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Player player
                        = new Player(rs.getInt("id"), rs.getInt("gameID"), rs.getString("username"),
                                rs.getInt("isLG"), rs.getInt("alive"), rs.getInt("hasContamination"),
                                rs.getInt("hasVoyance"), rs.getInt("hasInsomnie"), rs.getInt("hasSpiritisme"));
                result.put(rs.getString("username"), player);
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
	}
	return result;
    }
    
    public boolean ajouterPlayer(String username, int gameID){
        
        if (username.isEmpty() || gameID <0 ){
            return false;
        }
        try (
	     Connection conn = getConn();
	     PreparedStatement st = conn.prepareStatement("INSERT INTO player (username, gameID, isLG, "
                     + "alive, hasContamination, hasVoyance, hasInsomnie, hasSpiritisme) "
                     + "VALUES (?,?,0,1,0,0,0,0)");) {   
            st.setString(1, username);
            st.setInt(2, gameID);
            st.executeUpdate();    
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
	}
        
        
        return true;
    }
    
    public Player getPlayer(int id) {
        Player player = null;
        try (
                Connection conn = getConn();
                PreparedStatement st = conn.prepareStatement("SELECT * FROM player WHERE id = ?");) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            rs.next();
            player = new Player(rs.getInt("id"), rs.getInt("gameID"), rs.getString("username"),
                            rs.getInt("isLG"), rs.getInt("alive"), rs.getInt("hasContamination"),
                            rs.getInt("hasVoyance"), rs.getInt("hasInsomnie"), rs.getInt("hasSpiritisme"));
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        }
        return player;
    }
    
    public void modifierPlayer(int id, int isLG, int alive, int hasContamination, 
            int hasVoyance, int hasInsomnie, int hasSpiritisme) {
        try (
                Connection conn = getConn();
                PreparedStatement st = conn.prepareStatement("UPDATE player SET isLG = ?, alive = ?, hasContamination = ?, hasVoyance = ?, hasInsomnie = ?, hasSpiritisme = ? WHERE id = ?");) {
            st.setInt(1, isLG);
            st.setInt(2, alive);
            st.setInt(3, hasContamination);
            st.setInt(4, hasVoyance);
            st.setInt(5, hasInsomnie);
            st.setInt(6, hasSpiritisme);
            st.setInt(7, id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        }
    }
    
}
