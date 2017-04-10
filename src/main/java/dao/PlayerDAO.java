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
    
    public Map<String, Player> getMapPlayers(int gameId) {
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
                                rs.getInt("hasVoyance"), rs.getInt("hasInsomnie"), rs.getInt("hasSpiritisme"), 
                                rs.getInt("usedSpiritisme"), rs.getInt("usedVoyance"), rs.getInt("usedInsomnie"), 
                                rs.getInt("usedContamination"), rs.getInt("proposed"), rs.getString("voted"), rs.getInt("nbVotes"),
                                rs.getInt("justDied"), rs.getInt("justContaminated"), rs.getInt("justBitten"), rs.getInt("contacted"));
                result.put(rs.getString("username"), player);
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        }
        return result;
    }


    public List<Player> getListPlayers(int gameId) {
        List<Player> result = new ArrayList<Player>();
        try (
                Connection conn = getConn();
                PreparedStatement st = conn.prepareStatement("SELECT * FROM player WHERE gameID = ?");) {
            st.setInt(1, gameId);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Player player
                        = new Player(rs.getInt("id"), rs.getInt("gameID"), rs.getString("username"),
                                rs.getInt("isLG"), rs.getInt("alive"), rs.getInt("hasContamination"),
                                rs.getInt("hasInsomnie"), rs.getInt("hasVoyance"), rs.getInt("hasSpiritisme"), 
                                rs.getInt("usedSpiritisme"), rs.getInt("usedVoyance"), 
                                rs.getInt("usedInsomnie"), rs.getInt("usedContamination"), rs.getInt("proposed"), rs.getString("voted"), rs.getInt("nbVotes"),
                                rs.getInt("justDied"), rs.getInt("justContaminated"), rs.getInt("justBitten"), rs.getInt("contacted"));


                result.add(player);
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        }
        return result;
    }
    
    
    public List<Player> getListPlayersMorts(int gameId) {
        List<Player> result = new ArrayList<Player>();
        try (
                Connection conn = getConn();
                PreparedStatement st = conn.prepareStatement("SELECT * FROM player WHERE gameID = ? and alive = 0");) {
            st.setInt(1, gameId);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Player player
                        = new Player(rs.getInt("id"), rs.getInt("gameID"), rs.getString("username"),
                                rs.getInt("isLG"), rs.getInt("alive"), rs.getInt("hasContamination"),
                                rs.getInt("hasInsomnie"), rs.getInt("hasVoyance"), rs.getInt("hasSpiritisme"),
                                rs.getInt("usedSpiritisme"), rs.getInt("usedVoyance"),
                                rs.getInt("usedInsomnie"), rs.getInt("usedContamination"), rs.getInt("proposed"), rs.getString("voted"), rs.getInt("nbVotes"),
                                rs.getInt("justDied"), rs.getInt("justContaminated"), rs.getInt("justBitten"), rs.getInt("contacted"));

                result.add(player);
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        }
        return result;
    }

    public List<Player> getListPlayersRole(int gameId, int isLg) {
        List<Player> result = new ArrayList<Player>();
        try (
                Connection conn = getConn();
                PreparedStatement st = conn.prepareStatement("SELECT * FROM player WHERE gameID = ? AND isLG = ?");) {
            st.setInt(1, gameId);
            st.setInt(2, isLg);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Player player
                        = new Player(rs.getInt("id"), rs.getInt("gameID"), rs.getString("username"),
                                rs.getInt("isLG"), rs.getInt("alive"), rs.getInt("hasContamination"),
                                rs.getInt("hasVoyance"), rs.getInt("hasInsomnie"), rs.getInt("hasSpiritisme"), 
                                rs.getInt("usedSpiritisme"), rs.getInt("usedVoyance"), rs.getInt("usedInsomnie"), 
                                rs.getInt("usedContamination"), rs.getInt("proposed"), rs.getString("voted"), rs.getInt("nbVotes"),
                                rs.getInt("justDied"), rs.getInt("justContaminated"), rs.getInt("justBitten"), rs.getInt("contacted"));
                result.add(player);
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        }
        return result;
    }

    public List<Player> getListPlayersProposable(int gameId) {
        List<Player> result = new ArrayList<Player>();
        try (
                Connection conn = getConn();
                PreparedStatement st = conn.prepareStatement("SELECT * FROM player WHERE gameID = ? AND proposed = 0 AND alive = 1");) {
            st.setInt(1, gameId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Player player
                        = new Player(rs.getInt("id"), rs.getInt("gameID"), rs.getString("username"),
                                rs.getInt("isLG"), rs.getInt("alive"), rs.getInt("hasContamination"),
                                rs.getInt("hasVoyance"), rs.getInt("hasInsomnie"), rs.getInt("hasSpiritisme"), 
                                rs.getInt("usedSpiritisme"), rs.getInt("usedVoyance"), rs.getInt("usedInsomnie"), 
                                rs.getInt("usedContamination"), rs.getInt("proposed"), rs.getString("voted"), rs.getInt("nbVotes"),
                                rs.getInt("justDied"), rs.getInt("justContaminated"), rs.getInt("justBitten"), rs.getInt("contacted"));
                result.add(player);
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        }
        return result;
    }
    
    public List<Player> getListHumansProposable(int gameId) {
        List<Player> result = new ArrayList<Player>();
        try (
                Connection conn = getConn();
                PreparedStatement st = conn.prepareStatement("SELECT * FROM player WHERE gameID = ? AND proposed = 0 AND alive = 1 AND isLG = 0");) {
            st.setInt(1, gameId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Player player
                        = new Player(rs.getInt("id"), rs.getInt("gameID"), rs.getString("username"),
                                rs.getInt("isLG"), rs.getInt("alive"), rs.getInt("hasContamination"),
                                rs.getInt("hasVoyance"), rs.getInt("hasInsomnie"), rs.getInt("hasSpiritisme"), 
                                rs.getInt("usedSpiritisme"), rs.getInt("usedVoyance"), rs.getInt("usedInsomnie"), 
                                rs.getInt("usedContamination"), rs.getInt("proposed"), rs.getString("voted"), rs.getInt("nbVotes"),
                                rs.getInt("justDied"), rs.getInt("justContaminated"), rs.getInt("justBitten"), rs.getInt("contacted"));
                result.add(player);
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        }
        return result;
    }
    
    public List<Player> getListPlayersVotable(int gameId) {
        List<Player> result = new ArrayList<Player>();
        try (
                Connection conn = getConn();
                PreparedStatement st = conn.prepareStatement("SELECT * FROM player WHERE gameID = ? AND proposed = 1");) {
            st.setInt(1, gameId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Player player
                        = new Player(rs.getInt("id"), rs.getInt("gameID"), rs.getString("username"),
                                rs.getInt("isLG"), rs.getInt("alive"), rs.getInt("hasContamination"),
                                rs.getInt("hasVoyance"), rs.getInt("hasInsomnie"), rs.getInt("hasSpiritisme"), 
                                rs.getInt("usedSpiritisme"), rs.getInt("usedVoyance"), rs.getInt("usedInsomnie"), 
                                rs.getInt("usedContamination"), rs.getInt("proposed"), rs.getString("voted"), rs.getInt("nbVotes"),
                                rs.getInt("justDied"), rs.getInt("justContaminated"), rs.getInt("justBitten"), rs.getInt("contacted"));
                result.add(player);
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        }
        return result;
    }
    
    public List<Player> getListHumansVotable(int gameId) {
        List<Player> result = new ArrayList<Player>();
        try (
                Connection conn = getConn();
                PreparedStatement st = conn.prepareStatement("SELECT * FROM player WHERE gameID = ? AND proposed = 1 AND isLG = 0");) {
            st.setInt(1, gameId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Player player
                        = new Player(rs.getInt("id"), rs.getInt("gameID"), rs.getString("username"),
                                rs.getInt("isLG"), rs.getInt("alive"), rs.getInt("hasContamination"),
                                rs.getInt("hasVoyance"), rs.getInt("hasInsomnie"), rs.getInt("hasSpiritisme"), 
                                rs.getInt("usedSpiritisme"), rs.getInt("usedVoyance"), rs.getInt("usedInsomnie"), 
                                rs.getInt("usedContamination"), rs.getInt("proposed"), rs.getString("voted"), rs.getInt("nbVotes"),
                                rs.getInt("justDied"), rs.getInt("justContaminated"), rs.getInt("justBitten"), rs.getInt("contacted"));
                result.add(player);
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
                     + "alive, hasContamination, hasVoyance, hasInsomnie, hasSpiritisme, usedContamination, "
                     + "usedSpiritisme, usedVoyance, usedInsomnie, proposed, voted, nbVotes, justDied, justContaminated, justBitten, contacted) "
                     + "VALUES (?,?,0,1,0,0,0,0,0,0,0,0,0,' ', 0, 0, 0, 0, 0)");) {   
            st.setString(1, username);
            st.setInt(2, gameID);
            st.executeUpdate(); 
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
	}
        
        
        return true;
    }

    public Player getPlayer(String username, int gameId) {
        Player player = null;
        try (
                Connection conn = getConn();
                PreparedStatement st = conn.prepareStatement("SELECT * FROM player WHERE username = ? AND gameID = ?");) {
            st.setString(1, username);
            st.setInt(2, gameId);
            ResultSet rs = st.executeQuery();
            rs.next();
            player = new Player(rs.getInt("id"), rs.getInt("gameID"), rs.getString("username"),
                    rs.getInt("isLG"), rs.getInt("alive"), rs.getInt("hasContamination"),
                    rs.getInt("hasInsomnie"), rs.getInt("hasVoyance"), rs.getInt("hasSpiritisme"), 
                    rs.getInt("usedSpiritisme"), rs.getInt("usedVoyance"), rs.getInt("usedInsomnie"), 
                    rs.getInt("usedContamination"), rs.getInt("proposed"), rs.getString("voted"), rs.getInt("nbVotes"),
                                rs.getInt("justDied"), rs.getInt("justContaminated"), rs.getInt("justBitten"), rs.getInt("contacted"));
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        }
        return player;
    }
    
    public Player getPlayerFromId(int userId) {
        Player player = null;
        try (
                Connection conn = getConn();
                PreparedStatement st = conn.prepareStatement("SELECT * FROM player WHERE id = ?");) {
            st.setInt(1, userId);
            ResultSet rs = st.executeQuery();
            rs.next();
            player = new Player(rs.getInt("id"), rs.getInt("gameID"), rs.getString("username"),
                    rs.getInt("isLG"), rs.getInt("alive"), rs.getInt("hasContamination"),
                    rs.getInt("hasInsomnie"), rs.getInt("hasVoyance"), rs.getInt("hasSpiritisme"), 
                    rs.getInt("usedSpiritisme"), rs.getInt("usedVoyance"), rs.getInt("usedInsomnie"), 
                    rs.getInt("usedContamination"), rs.getInt("proposed"), rs.getString("voted"), rs.getInt("nbVotes"),
                                rs.getInt("justDied"), rs.getInt("justContaminated"), rs.getInt("justBitten"), rs.getInt("contacted"));
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        }
        return player;
    }

    public void modifierPlayer(int id, int isLG, int alive, int hasContamination,
            int hasVoyance, int hasInsomnie, int hasSpiritisme, int usedContamination,
            int usedVoyance, int usedInsomnie, int usedSpiritisme) {
        try (
                Connection conn = getConn();
                PreparedStatement st = conn.prepareStatement("UPDATE player SET isLG = ?, "
                        + "hasContamination = ?, hasVoyance = ?, hasInsomnie = ?, hasSpiritisme = ?, usedContamination = ?, usedVoyance = ?, usedInsomnie = ?, usedSpiritisme = ? WHERE id = ?");) {
            st.setInt(1, isLG);
            st.setInt(2, hasContamination);
            st.setInt(3, hasVoyance);
            st.setInt(4, hasInsomnie);
            st.setInt(5, hasSpiritisme);
            st.setInt(6, usedContamination);
            st.setInt(7, usedVoyance);
            st.setInt(8, usedInsomnie);
            st.setInt(9, usedSpiritisme);
            st.setInt(10, id);

            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        }
    }
    
    public void proposer(int userId) {
        try (
                Connection conn = getConn();
                PreparedStatement st = conn.prepareStatement("UPDATE player SET proposed = 1 WHERE id = ?");) {
            
            st.setInt(1, userId);

            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        }
    }
    
    public void voter(int userId, int cibleId) {
        Player player = getPlayerFromId(cibleId);
        try (
                Connection conn = getConn();
                PreparedStatement st = conn.prepareStatement("UPDATE player SET voted = ? WHERE id = ?");) {
            
            st.setString(1, player.getUsername());
            st.setInt(2, userId);

            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        }
        try (
                Connection conn = getConn();
                PreparedStatement st = conn.prepareStatement("UPDATE player SET nbVotes = nbVotes + 1 WHERE id = ?");) {
            
            st.setInt(1, cibleId);

            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        }
    }
    
    public void changeVote(int userId, int ancienVoteId) {
        try (
                Connection conn = getConn();
                PreparedStatement st = conn.prepareStatement("UPDATE player SET nbVotes = nbVotes - 1 WHERE id = ?");) {
            
            st.setInt(1, ancienVoteId);

            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        }
    }
    
    //0 : pas utilise -> valable pour la nuit
    //1 : utilise la nuit et tte la journee
    public void pouvoirContaminationUtilise(int userId, int utilise) {
        try (
                Connection conn = getConn();
                PreparedStatement st = conn.prepareStatement("UPDATE player SET usedContamination = ? WHERE id = ?");) {

            st.setInt(1, utilise);
            st.setInt(2, userId);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        }
    }
    
        //0 : pas utilise -> valable pour la nuit
    //1 : utilise la nuit et tte la journee
    public void pouvoirVoyanceUtilise(int userId, int utilise) {
        try (
                Connection conn = getConn();
                PreparedStatement st = conn.prepareStatement("UPDATE player SET usedVoyance = ? WHERE id = ?");) {

            st.setInt(1, utilise);
            st.setInt(2, userId);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        }
    }
    
            //0 : pas utilise -> valable pour la nuit
    //1 : utilise la nuit et tte la journee
    public void pouvoirSpiritismeUtilise(int userId, int utilise) {
        try (
                Connection conn = getConn();
                PreparedStatement st = conn.prepareStatement("UPDATE player SET usedSpiritisme = ? WHERE id = ?");) {

            st.setInt(1, utilise);
            st.setInt(2, userId);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        }
    }
    
                //0 : pas utilise -> valable pour la nuit
    //1 : utilise la nuit et tte la journee
    public void pouvoirInsomnieUtilise(int userId, int utilise) {
        try (
                Connection conn = getConn();
                PreparedStatement st = conn.prepareStatement("UPDATE player SET usedInsomnie = ? WHERE id = ?");) {

            st.setInt(1, utilise);
            st.setInt(2, userId);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        }
    }

    /**
     * transforme en loup garou l'user id
     * @param userId 
     */
    public void transformerLoupGarou(int userId) {
        try (
                Connection conn = getConn();
                PreparedStatement st = conn.prepareStatement("UPDATE player SET isLG = ? WHERE id = ?");) {

            st.setInt(1, 1);
            st.setInt(2, userId);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        }
    }
    
    /**
     * enleve les joueurs de la game
     * @param gameId 
     */
    public void removePlayersFromGame(int gameId) {
        try (
	    Connection conn = getConn();
	    PreparedStatement st = conn.prepareStatement("DELETE FROM player WHERE gameID = ?");) {
            st.setInt(1, gameId);
            st.executeUpdate();
            
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
	}
    }
    
    /**
     * informe le joueur qu'il a été contacté
     * @param userId 
     */
    public void playerContacted(int userId){
        try (
                Connection conn = getConn();
                PreparedStatement st = conn.prepareStatement("UPDATE player SET contacted = 1 WHERE id = ?");) {

            st.setInt(1, userId);
            
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        }        
    }
    

}
