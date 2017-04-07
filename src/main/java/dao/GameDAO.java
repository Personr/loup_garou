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
    
    public void startGame(int gameId) {
        try (
                Connection conn = getConn();
                PreparedStatement st = conn.prepareStatement("INSERT INTO game (started) VALUES (?)");) {
            st.setInt(1, 1);
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
    public boolean creerPartie(int nbJoueursMin, int nbJoueursMax, int dureeJour, int dureeNuit, int horaireDebutPartie, String creator, float pContamination, float pSpiritisme,float pVoyance,float pInsomnie, float proportionLoupsGarous) {
        if (      nbJoueursMin < 2 || nbJoueursMin > 20
                ||  nbJoueursMax > 20 || nbJoueursMax < nbJoueursMin
                || dureeJour < 0 || dureeJour > 23 || dureeNuit < 0 || dureeNuit > 23 || horaireDebutPartie <0 || horaireDebutPartie > 23 || dureeJour > dureeNuit
                || pContamination >1 || pSpiritisme > 1 || pVoyance > 1 || pInsomnie > 1 || proportionLoupsGarous > 1
                ) {
            return false;
        }
        
        try (
                Connection conn = getConn();

// 	VALUES (2, 5, 4, 0, TO_DATE('01-01-2004 13:38:11','DD-MM-YYYY HH24:MI:SS'), 
//                0, 'val', TO_DATE('01-01-2004 13:38:11','DD-MM-YYYY HH24:MI:SS'), 
//                TO_DATE('01-01-2004 13:38:11','DD-MM-YYYY HH24:MI:SS'), 0.5, 0.5, 0.5, 0.5, 0.5);
                //finished = 0  ; started = 0 
                PreparedStatement st = conn.prepareStatement("INSERT INTO game (minPlayer, maxPlayer, nbPlayer, started, startTime, "
                        + " finished, creator, dayTime, nightTime, pContamination, pVoyance, pInsomnie, pSpiritisme, lgProp)"
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
                        + " ?)");) {
            
            
            st.setInt(1, nbJoueursMin);
            st.setInt(2, nbJoueursMax);
            String Date = "01-01-2017 ";
            String horaireDebut= "";
            
            if (horaireDebutPartie < 10){
               horaireDebut = "0"+String.valueOf(horaireDebutPartie);
            }else{
               horaireDebut =String.valueOf(horaireDebutPartie);
            }
            horaireDebut= Date+horaireDebut+":00:00";
           
            System.out.println(horaireDebut);
            
            
            st.setString(3, horaireDebut);
            
            
            //TO_DATE('01-01-2099 ?','DD-MM-YYYY HH24:MI:SS')
            //TO_DATE('?','HH24:MI:SS')
            // TO_DATE('?','HH24:MI:SS')
            
            String dureeJ = "";
            if(dureeJour < 10) {
                dureeJ = dureeJ+"0"+String.valueOf(dureeJour);
            }else{
                dureeJ = dureeJ+String.valueOf(dureeJour);
            }
            
            String dureeN = "";
            if(dureeNuit < 10) {
                dureeN = "0"+String.valueOf(dureeNuit);
            }else{
                dureeN = String.valueOf(dureeNuit);
            }
            
            String horaireJour = Date+dureeJ+":00:00";
            String horaireNuit = Date+dureeN+":00:00";
            
            
            
            st.setString(4, creator);
            
            System.out.println(horaireJour);
            System.out.println(horaireNuit);
            
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
    public boolean incrementerNbJoueurs(int gameID){
        Game gameCourante = getGame(gameID) ;
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
            st.setInt(2, gameID);
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

    public void deleteGames() {
        try (
	    Connection conn = getConn();
	    PreparedStatement st = conn.prepareStatement("DELETE FROM game");) {
   
            st.executeUpdate();
            
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
	}
    }
}
