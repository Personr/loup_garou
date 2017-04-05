/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.sql.*;

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
    private final String creator;
    private final java.sql.Time dayTime;
    private final java.sql.Time nightTime;
    private final float pContamination;
    private final float pInsomnie;
    private final float pVoyance;
    private final float pSpiritisme;
    private final float lgProp;
    private final java.sql.Time startTime;  

    public Game(int gameId, int minPlayers, int maxPlayers, int nbPlayers, int started, 
            String creator, Time dayTime, Time nightTime, float pContamination, 
            float pInsomnie, float pVoyance, float pSpiritisme, float lgProp, Time startTime) {
        this.gameId = gameId;
        this.minPlayers = minPlayers;
        this.maxPlayers = maxPlayers;
        this.nbPlayers = nbPlayers;
        this.started = started;
        this.creator = creator;
        this.dayTime = dayTime;
        this.nightTime = nightTime;
        this.pContamination = pContamination;
        this.pInsomnie = pInsomnie;
        this.pVoyance = pVoyance;
        this.pSpiritisme = pSpiritisme;
        this.lgProp = lgProp;
        this.startTime = startTime;
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
        return "Game{" + "gameId=" + gameId + ", minPlayers=" + minPlayers + ", maxPlayers=" + maxPlayers + ", nbPlayers=" + nbPlayers + ", started=" + started + ", creator=" + creator + ", dayTime=" + dayTime + ", nightTime=" + nightTime + ", pContamination=" + pContamination + ", pInsomnie=" + pInsomnie + ", pVoyance=" + pVoyance + ", pSpiritisme=" + pSpiritisme + ", lgProp=" + lgProp + ", startTime=" + startTime + '}';
    }
     
}