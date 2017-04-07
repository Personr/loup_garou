/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author personr
 */
public class Game {
    
    private final int gameId;
    private final int minPlayers;
    private final int maxPlayers;
    private final int nbPlayers;
    private final int started;
    private final int finished;
    private final String creator;
    private final java.sql.Time dayTime;
    private final java.sql.Time nightTime;
    private final float pContamination;
    private final float pInsomnie;
    private final float pVoyance;
    private final float pSpiritisme;
    private final float lgProp;
    private final java.sql.Time startTime; 
    
    //liste des objets player de la partie uniquement
    public Map<String, Player> mapJoueurs;
    
    public Game(int gameId, int minPlayers, int maxPlayers, int nbPlayers, int started, 
            Time startTime, int finished,
            String creator, Time dayTime, Time nightTime, float pContamination, 
            float pInsomnie, float pVoyance, float pSpiritisme, float lgProp) {
        this.gameId = gameId;
        this.minPlayers = minPlayers;
        this.maxPlayers = maxPlayers;
        this.nbPlayers = nbPlayers;
        this.started = started;
        this.finished = finished;
        this.creator = creator;
        this.dayTime = dayTime;
        this.nightTime = nightTime;
        this.pContamination = pContamination;
        this.pInsomnie = pInsomnie;
        this.pVoyance = pVoyance;
        this.pSpiritisme = pSpiritisme;
        this.lgProp = lgProp;
        this.startTime = startTime;
        mapJoueurs = new HashMap<>();
    }
    
    
    public void ajouterJoueur(String username, Player joueur) {
        mapJoueurs.put(username, joueur);
    }
    
    public int getFinished() {
        return finished;
    }

    public int getGameId() {
        return gameId;
    }

    public int getMinPlayers() {
        return minPlayers;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public int getNbPlayers() {
        return nbPlayers;
    }

    public int isStarted() {
        return started;
    }

    public String getCreator() {
        return creator;
    }

    public Time getDayTime() {
        return dayTime;
    }

    public Time getNightTime() {
        return nightTime;
    }

    public float getpContamination() {
        return pContamination;
    }

    public float getpInsomnie() {
        return pInsomnie;
    }

    public float getpVoyance() {
        return pVoyance;
    }

    public float getpSpiritisme() {
        return pSpiritisme;
    }

    public float getLgProp() {
        return lgProp;
    }

    public Time getStartTime() {
        return startTime;
    }  

    @Override
    public String toString() {
        return "Game{" + "gameId=" + gameId + ", minPlayers=" + minPlayers + ", maxPlayers=" + maxPlayers + ", nbPlayers=" + nbPlayers + ", started=" + started + ", finished=" + finished + ", creator=" + creator + ", dayTime=" + dayTime + ", nightTime=" + nightTime + ", pContamination=" + pContamination + ", pInsomnie=" + pInsomnie + ", pVoyance=" + pVoyance + ", pSpiritisme=" + pSpiritisme + ", lgProp=" + lgProp + ", startTime=" + startTime + '}';
    }
     
}
