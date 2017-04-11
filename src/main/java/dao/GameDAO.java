package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import modele.Ouvrage;
import modele.Game;
import modele.Player;
import tools.SessionManager;

public class GameDAO extends AbstractDataBaseDAO {

    public GameDAO(DataSource ds) {
        super(ds);
    }
    
    /**
     * Renvoie la liste des parties de la table game.
     */
    public List<Game> getListeGames() {
        List<Game> result = new ArrayList<Game>();
        try (
	     Connection conn = getConn();
	     Statement st = conn.createStatement();
	     ) {
            ResultSet rs = st.executeQuery("SELECT * FROM game ORDER BY gameID");
            while (rs.next()) {
                Game game =
                    new Game(rs.getInt("gameID"), rs.getInt("minPlayer"), rs.getInt("maxPlayer"), rs.getInt("nbPlayer"), 
                            rs.getInt("started"), rs.getTime("startTime"), rs.getInt("finished"),
                            rs.getString("creator"), rs.getTime("dayTime"), rs.getTime("nightTime"),
                            rs.getFloat("pContamination"), rs.getFloat("pVoyance"), rs.getFloat("pInsomnie"),
                            rs.getFloat("pSpiritisme"), rs.getFloat("lgProp"), rs.getInt("isDay"), rs.getInt("dayNb"));
                result.add(game);
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
	}
	return result;
    }
    
    /**
     * Renvoie la liste des parties de la table game.
     */
    public List<Game> getListGamesNotStarted() {
        List<Game> result = new ArrayList<Game>();
        try (
	     Connection conn = getConn();
	     Statement st = conn.createStatement();
	     ) {
            ResultSet rs = st.executeQuery("SELECT * FROM game WHERE started = 0 ORDER BY gameID");
            while (rs.next()) {
                Game game =
                    new Game(rs.getInt("gameID"), rs.getInt("minPlayer"), rs.getInt("maxPlayer"), rs.getInt("nbPlayer"), 
                            rs.getInt("started"), rs.getTime("startTime"), rs.getInt("finished"),
                            rs.getString("creator"), rs.getTime("dayTime"), rs.getTime("nightTime"),
                            rs.getFloat("pContamination"), rs.getFloat("pVoyance"), rs.getFloat("pInsomnie"),
                            rs.getFloat("pSpiritisme"), rs.getFloat("lgProp"), rs.getInt("isDay"), rs.getInt("dayNb"));
                result.add(game);
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
	}
	return result;
    }
    
    public Game getGame(int gameId) {
        Game game;
        try (
	     Connection conn = getConn();
	     PreparedStatement st = conn.prepareStatement("SELECT * FROM game WHERE gameID = ?");
	     ) {
            st.setInt(1, gameId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                game = new Game(rs.getInt("gameID"), rs.getInt("minPlayer"), rs.getInt("maxPlayer"), rs.getInt("nbPlayer"), 
                            rs.getInt("started"), rs.getTime("startTime"), rs.getInt("finished"),
                            rs.getString("creator"), rs.getTime("dayTime"), rs.getTime("nightTime"),
                            rs.getFloat("pContamination"), rs.getFloat("pVoyance"), rs.getFloat("pInsomnie"),
                            rs.getFloat("pSpiritisme"), rs.getFloat("lgProp"), rs.getInt("isDay"), rs.getInt("dayNb"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
	}
	return game;
    }
    
    public boolean isStarted(int gameId) {
        try (
	     Connection conn = getConn();
	     PreparedStatement st = conn.prepareStatement("SELECT started FROM game WHERE gameID = ?");) {
            st.setInt(1, gameId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("started") == 1;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
	}
    }
    
    public void startGame(int gameId) {
        try (
                Connection conn = getConn();
                PreparedStatement st = conn.prepareStatement("UPDATE game SET started = 1 WHERE gameID = ?");) {
            st.setInt(1, gameId);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        }
    }
    
    /**
     *
     * @param nbJoueursMin
     * @param nbJoueursMax
     * @param dureeJour
     * @param dureeNuit
     * @param horaireDebutPartie
     * @param creator
     * @param pContamination
     * @param pSpiritisme
     * @param pVoyance
     * @param pInsomnie
     * @param proportionLoupsGarous
     */
    public boolean creerPartie(int nbJoueursMin, int nbJoueursMax, String heureJour, String heureNuit, String horaireDebutPartie, 
            String creator, float pContamination, float pSpiritisme,float pVoyance,float pInsomnie, float proportionLoupsGarous) {
        if (      nbJoueursMin < 2 || nbJoueursMin > 20
                ||  nbJoueursMax > 20 || nbJoueursMax < nbJoueursMin
                || pContamination >1 || pSpiritisme > 1 || pVoyance > 1 || pInsomnie > 1 || proportionLoupsGarous > 1
                ) {
            return false;
        }
        
        try (
                Connection conn = getConn();
                PreparedStatement st = conn.prepareStatement("INSERT INTO game (minPlayer, maxPlayer, nbPlayer, started, startTime, "
                        + " finished, creator, dayTime, nightTime, pContamination, pVoyance, pInsomnie, pSpiritisme, lgProp, isDay, dayNb)"
                        + " VALUES ("
                        + " ?,"
                        + " ?, "
                        + " 0, "
                        + " 0, "
                        + " TO_DATE(?,'DD-MM-YYYY HH24:MI:SS'), "
                        + " 0, "
                        + " ?, "
                        + " TO_DATE(?,'DD-MM-YYYY HH24:MI:SS'), "
                        + " TO_DATE(?,'DD-MM-YYYY HH24:MI:SS'),"
                        + " ?, "
                        + " ?, "
                        + " ?, "
                        + " ?, "
                        + " ?, 1, 1)");) {
            
            
            st.setInt(1, nbJoueursMin);
            st.setInt(2, nbJoueursMax);
            String Date = "01-01-2017 ";
            String horaireDebut= "";

            horaireDebut= Date + horaireDebutPartie + ":00";
            System.out.println("Horaire début: " + horaireDebut);
            st.setString(3, horaireDebut);
            
            String horaireJour = Date + heureJour +":00";
            String horaireNuit = Date + heureNuit + ":00";
            
            st.setString(4, creator);
            st.setString(5, horaireJour);
            st.setString(6, horaireNuit);
            
            st.setFloat(7, pContamination);
            st.setFloat(8, pVoyance);
            st.setFloat(9, pInsomnie);
            st.setFloat(10, pSpiritisme);
            st.setFloat(11, proportionLoupsGarous);
            
            st.executeUpdate();
            
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        }
        return true;
    }
    
    public boolean incrementerNbJoueurs(Game gameCourante){
        int nbJoueurs = gameCourante.getNbPlayers();
        int nbJoueursMax = gameCourante.getMaxPlayers();
         
        if (nbJoueurs >= nbJoueursMax){
            return false;
        }else{
            try (
	     Connection conn = getConn();
	     PreparedStatement st = conn.prepareStatement("UPDATE game SET nbPlayer = ? where gameID=?");) {
            
            
//            UPDATE table
//SET nom_colonne_1 = 'nouvelle valeur'
//WHERE condition
            
            st.setInt(1, nbJoueurs+1);
            st.setInt(2, gameCourante.getGameId());
            st.executeUpdate();
            
            } catch (SQLException e) {
                throw new DAOException("Erreur BD " + e.getMessage(), e);
            }
        }
        
        return true;
    }
    
    public boolean nouveauJoueur(String username, int gameID){
        
        
        if (username.isEmpty() || gameID <0 ){
            return false;
        }
        
        try (
	     Connection conn = getConn();
	     PreparedStatement st = conn.prepareStatement("INSERT INTO player (username, gameID, isLG, "
                     + "alive, hasContamination, hasVoyance, hasInsomnie, hasSpiritisme) "
                     + "VALUES (?,?,0,0,0,0,0,0)");) {
            
            
            
            st.setString(1, username);
            st.setInt(2, gameID);
            st.executeUpdate();
            
            
                
            
            
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
	}
        
        
        return true;
    }
    
    public void startDay(int gameID) {
        Game gameCourante = getGame(gameID);
        int dayNb = gameCourante.getDayNb();
        try (
                Connection conn = getConn();
                PreparedStatement st = conn.prepareStatement("UPDATE game SET isDay = 1, dayNb = ? where gameID = ? ");) {
            st.setInt(1, dayNb+1);
            st.setInt(2, gameID);           
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        }
        // Le jour reprend, on remet à 0 les bons paramètres 
        try (
                Connection conn = getConn();
                PreparedStatement st = conn.prepareStatement("UPDATE player SET proposed = 0, voted = ' ',"
                        + "usedContamination = 0, usedVoyance = 0, usedSpiritisme = 0, usedInsomnie = 0,"
                        + "contacted = 0, nbVotes = 0, justDied = 0 WHERE gameID = ? ");) {

            st.setInt(1, gameID);           
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        }

    }
    
    public void startNight(int gameID) {
        try (
                Connection conn = getConn();
                PreparedStatement st = conn.prepareStatement("UPDATE game SET isDay = 0 where gameID = ? ");) {

            st.setInt(1, gameID);
            st.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        }
        // La nuit reprend, on remet à 0 les bons paramètres 
        try (
                Connection conn = getConn();
                PreparedStatement st = conn.prepareStatement("UPDATE player SET proposed = 0, voted = ' ',"
                        + "nbVotes = 0, justBitten = 0, justContaminated = 0 where gameID = ? ");) {

            st.setInt(1, gameID);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        }
    }
    
    public List<Integer> depouiller(int gameID) {
        List<Integer> result = new ArrayList<>();
        try (
                Connection conn = getConn();
                PreparedStatement st = conn.prepareStatement("SELECT id FROM player "
                        + "WHERE gameId = ? "
                        + "AND nbVotes = (SELECT max(nbVotes) from player where gameId = ?)");) {
            st.setInt(1, gameID);
            st.setInt(2, gameID);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int playerID = rs.getInt("id");
                result.add(playerID);
            }
            return result;
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        }
    }

    public void deleteGames() {
        try (
	    Connection conn = getConn();
	    PreparedStatement st = conn.prepareStatement("DELETE FROM game");) {
   
            st.executeUpdate();
            
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
	}
    }
    
    public void deleteGame(int gameId) {
        try (
	    Connection conn = getConn();
	    PreparedStatement st = conn.prepareStatement("DELETE FROM game WHERE gameID = ?");) {
            st.setInt(1, gameId);
            st.executeUpdate();
            
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
	}
    }
    
        public void endGame(int gameId) {
        try (
	    Connection conn = getConn();
	    PreparedStatement st = conn.prepareStatement("UPDATE game SET finished = 1 WHERE gameID = ?");) {
            st.setInt(1, gameId);
            st.executeUpdate();
            
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
	}
    }
}
