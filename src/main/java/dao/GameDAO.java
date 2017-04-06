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

    /**
     * Ajoute l'ouvrage d'auteur et de titre spécifiés dans la table
     * bibliographie.
     */
    public void ajouterOuvrage(String auteur, String titre) {
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
    

    /**
     * Récupère l'ouvrage d'identifiant id dans la table bibliographie.
     */
    public Ouvrage getOuvrage(int id) {
        Ouvrage ouvrage = null;
        try (
                Connection conn = getConn();
                PreparedStatement st = conn.prepareStatement("SELECT * FROM bibliographie WHERE id = ?");) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            rs.next();
            ouvrage = new Ouvrage(rs.getInt("id"), rs.getString("auteur"), rs.getString("titre"));
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        }
        return ouvrage;
    }

    /**
     * Modifie l'ouvrage d'identifiant id avec le nouvel auteur et le nouveau
     * titre spécifiés dans la table bibliographie.
     */
    public void modifierOuvrage(int id, String auteur, String titre) {
        try (
                Connection conn = getConn();
                PreparedStatement st = conn.prepareStatement("UPDATE bibliographie SET auteur = ?, titre = ? WHERE id = ?");) {
            st.setString(1, auteur);
            st.setString(2, titre);
            st.setInt(3, id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        }
    }

    /**
     * Supprime l'ouvrage d'identifiant id dans la table bibliographie.
     */
    public void supprimerOuvrage(int id) {
        try (
                Connection conn = getConn();
                PreparedStatement st = conn.prepareStatement("DELETE FROM bibliographie WHERE id = ?");) {
            st.setInt(1, id);
            st.executeQuery();
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        }
    }
}
