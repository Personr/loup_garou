/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

/**
 *
 * @author personr
 */
public class Player {
    
    private final int id;
    private final int gameId;
    private final String username;
    private int isLg;
    private final int alive;
    private int hasContamination;
    private int hasInsomnie;
    private int hasVoyance;
    private int hasSpiritisme;

    public Player(int id, int gameId, String username, int isLg, int alive, int hasContamination, int hasInsomnie, int hasVoyance, int hasSpiritisme) {
        this.id = id;
        this.gameId = gameId;
        this.username = username;
        this.isLg = isLg;
        this.alive = alive;
        this.hasContamination = hasContamination;
        this.hasInsomnie = hasInsomnie;
        this.hasVoyance = hasVoyance;
        this.hasSpiritisme = hasSpiritisme;
    }


    public void setIsLg(int isLg) {
        this.isLg = isLg;
    }

    public void setHasContamination(int hasContamination) {
        this.hasContamination = hasContamination;
    }

    public void setHasInsomnie(int hasInsomnie) {
        this.hasInsomnie = hasInsomnie;
    }

    public void setHasVoyance(int hasVoyance) {
        this.hasVoyance = hasVoyance;
    }

    public void setHasSpiritisme(int hasSpiritisme) {
        this.hasSpiritisme = hasSpiritisme;
    }
    
    public int getId() {
        return id;
    }
    
    public int getGameId() {
        return gameId;
    }

    public String getUsername() {
        return username;
    }

    public int getIsLg() {
        return isLg;
    }

    public int getAlive() {
        return alive;
    }

    public int getHasContamination() {
        return hasContamination;
    }

    public int getHasInsomnie() {
        return hasInsomnie;
    }

    public int getHasVoyance() {
        return hasVoyance;
    }

    public int getHasSpiritisme() {
        return hasSpiritisme;
    }

    @Override
    public String toString() {
        return "Player{" + "id=" + id + ", gameId=" + gameId + ", username=" + username + ", isLg=" + isLg + ", alive=" + alive + ", hasContamination=" + hasContamination + ", hasInsomnie=" + hasInsomnie + ", hasVoyance=" + hasVoyance + ", hasSpiritisme=" + hasSpiritisme + '}';
    }
    
}
