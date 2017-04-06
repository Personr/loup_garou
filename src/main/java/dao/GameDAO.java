package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import modele.Ouvrage;
import modele.Game;
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
            ResultSet rs = st.executeQuery("SELECT * FROM game");
            while (rs.next()) {
                Game game =
                    new Game(rs.getInt("gameID"), rs.getInt("minPlayer"), rs.getInt("maxPlayer"), rs.getInt("nbPlayer"), 
                            rs.getInt("started"), rs.getTime("startTime"), rs.getInt("finished"),
                            rs.getString("creator"), rs.getTime("dayTime"), rs.getTime("nightTime"),
                            rs.getFloat("pContamination"), rs.getFloat("pVoyance"), rs.getFloat("pInsomnie"),
                            rs.getFloat("pSpiritisme"), rs.getFloat("lgProp"));
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
                            rs.getFloat("pSpiritisme"), rs.getFloat("lgProp"));
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
    public void creerPartie(int nbJoueursMin, int nbJoueursMax, int dureeJour, int dureeNuit, int horaireDebutPartie, String creator, float pContamination, float pSpiritisme,float pVoyance,float pInsomnie, float proportionLoupsGarous) {
        try (
                Connection conn = getConn();

// 	VALUES (2, 5, 4, 0, TO_DATE('01-01-2004 13:38:11','DD-MM-YYYY HH24:MI:SS'), 
//                0, 'val', TO_DATE('01-01-2004 13:38:11','DD-MM-YYYY HH24:MI:SS'), 
//                TO_DATE('01-01-2004 13:38:11','DD-MM-YYYY HH24:MI:SS'), 0.5, 0.5, 0.5, 0.5, 0.5);
                //finished = 0  ; started = 0 
                PreparedStatement st = conn.prepareStatement("INSERT INTO game (minPlayer, maxPlayer, nbPlayer, started, startTime, "
                        + " finished, creator, dayTime, nightTime, pContamination, pVoyance, pInsomnie, pSpiritisme, lgProp)"
                        + " VALUES (?,?, 0, 0, TO_DATE(?,'DD-MM-YYYY HH24:MI:SS'), 0, ? "
                        + ",TO_DATE('01-01-2004 13:38:11','DD-MM-YYYY HH24:MI:SS'), TO_DATE('01-01-2004 13:38:51','DD-MM-YYYY HH24:MI:SS'),"
                        + "?, ?, ?, ?, ?)");) {
            
            
            st.setInt(1, nbJoueursMin);
            st.setInt(2, nbJoueursMax);
            
            
            String horaireDebut = "01-01-2004 08:00:00";
            
//            String horaireDebut = String.valueOf(horaireDebutPartie) + ":00:00";
            st.setString(3, horaireDebut);
            
            
            //TO_DATE('01-01-2099 ?','DD-MM-YYYY HH24:MI:SS')
            //TO_DATE('?','HH24:MI:SS')
            // TO_DATE('?','HH24:MI:SS')
            String horaireJour = String.valueOf(dureeJour) + ":00:00";
            String horaireNuit = String.valueOf(dureeNuit) + ":00:00";
            
            
            
            st.setString(4, creator);
            
            //st.setString(5, horaireJour);
            //st.setString(6, horaireNuit);
            
            st.setFloat(5, pContamination);
            st.setFloat(6, pVoyance);
            st.setFloat(7, pInsomnie);
            st.setFloat(8, pSpiritisme);
            st.setFloat(9, proportionLoupsGarous);
            
            st.executeUpdate();
            
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        }
    }
}
