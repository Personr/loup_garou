/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import dao.PlayerDAO;

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
    private int usedPower;
    private int proposed;
    private String voted;
    private int nbVotes;
    private int justDied;
    private int justContaminated;
    private int justBitten;
    private int contacted;
    private int gameLeft;
    
    public Player(int id, int gameId, String username, int isLg, int alive, 
            int hasContamination, int hasInsomnie, int hasVoyance, int hasSpiritisme, 
            int usedPower,
            int proposed, String voted, int nbVotes, int justDied, int justContaminated, int justBitten, int contacted, int gameLeft) {
        this.id = id;
        this.gameId = gameId;
        this.username = username;
        this.isLg = isLg;
        this.alive = alive;
        this.hasContamination = hasContamination;
        this.hasInsomnie = hasInsomnie;
        this.hasVoyance = hasVoyance;
        this.hasSpiritisme = hasSpiritisme;
        this.usedPower = usedPower;
        this.proposed = proposed;
        this.voted = voted;
        this.nbVotes = nbVotes;
        this.justDied = justDied;
        this.justContaminated = justContaminated;
        this.justBitten = justBitten;
        this.contacted = contacted;
        this.gameLeft = gameLeft;
    }
    
    public String getPouvoir() {
        if (hasContamination == 1) {
            return "Contamination";
        } else if (hasInsomnie == 1) {
            return "Insomnie";
        } else if (hasVoyance == 1) {
            return "Voyance";
        } else if (hasSpiritisme == 1) {
            return "Spiritisme";
        } else {
            return "Pas de pouvoir";
        }
    }
    
    public String getRole() {
        if (isLg == 1) {
            return "Loup-Garou";
        } else {
            return "Villageois";
        }
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

    public void setProposed(int proposed) {
        this.proposed = proposed;
    }

    public void setVoted(String voted) {
        this.voted = voted;
    }

    public void setNbVotes(int nbVotes) {
        this.nbVotes = nbVotes;
    }

    public void setJustDied(int justDied) {
        this.justDied = justDied;
    }

    public void setJustContaminated(int justContaminated) {
        this.justContaminated = justContaminated;
    }

    public void setJustBitten(int justBitten) {
        this.justBitten = justBitten;
    }

    public int getGameLeft() {
        return gameLeft;
    }

    public void setHasVoyance(int hasVoyance) {
        this.hasVoyance = hasVoyance;
    }

    public void setHasSpiritisme(int hasSpiritisme) {
        this.hasSpiritisme = hasSpiritisme;
    }

    public void setUsedPower(int usedPower) {
        this.usedPower = usedPower;
    }

    public void setContacted(int contacted){
        this.contacted=contacted;
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

    public int getUsedPower() {
        return usedPower;
    }

    public int getProposed() {
        return proposed;
    }

    public String getVoted() {
        return voted;
    }
    
    public int getContacted() {
        return contacted;
    }

    public int getNbVotes() {
        return nbVotes;
    }
    public int getJustDied() {
        return justDied;
    }

    public int getJustContaminated() {
        return justContaminated;
    }

    public int getJustBitten() {
        return justBitten;
    }

    @Override
    public String toString() {
        return "Player{" + "id=" + id + ", gameId=" + gameId + ", username=" + username + ", isLg=" + isLg + ", alive=" + alive + ", hasContamination=" + hasContamination + ", hasInsomnie=" + hasInsomnie + ", hasVoyance=" + hasVoyance + ", hasSpiritisme=" + hasSpiritisme + ", usedPower=" + usedPower + ", proposed=" + proposed + ", voted=" + voted + ", nbVotes=" + nbVotes + ", justDied=" + justDied + ", justContaminated=" + justContaminated + ", justBitten=" + justBitten + ", contacted=" + contacted + ", gameLeft=" + gameLeft + '}';
    }

}
