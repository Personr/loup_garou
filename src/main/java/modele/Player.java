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
    private int usedSpiritisme;
    private int usedVoyance;
    private int usedContamination;
    private int usedInsomnie;
    private int proposed;
    private String voted;
    private int nbVotes;
    private int justDied;
    private int justContaminated;
    private int justBitten;
    private int contacted;
    
    public Player(int id, int gameId, String username, int isLg, int alive, 
            int hasContamination, int hasInsomnie, int hasVoyance, int hasSpiritisme, 
            int usedSpiritisme, int usedVoyance, int usedInsomnie, int usedContamination,
            int proposed, String voted, int nbVotes, int justDied, int justContaminated, int justBitten, int contacted) {
        this.id = id;
        this.gameId = gameId;
        this.username = username;
        this.isLg = isLg;
        this.alive = alive;
        this.hasContamination = hasContamination;
        this.hasInsomnie = hasInsomnie;
        this.hasVoyance = hasVoyance;
        this.hasSpiritisme = hasSpiritisme;
        this.usedContamination = usedContamination;
        this.usedVoyance = usedVoyance;
        this.usedSpiritisme = usedSpiritisme;
        this.usedInsomnie = usedInsomnie;
        this.proposed = proposed;
        this.voted = voted;
        this.nbVotes = nbVotes;
        this.justDied = justDied;
        this.justContaminated = justContaminated;
        this.justBitten = justBitten;
        this.contacted = contacted;
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



    public void setHasVoyance(int hasVoyance) {
        this.hasVoyance = hasVoyance;
    }

    public void setHasSpiritisme(int hasSpiritisme) {
        this.hasSpiritisme = hasSpiritisme;
    }

    public void setUsedContamination(int usedContamination) {
        this.usedContamination = usedContamination;
    }

    public void setUsedVoyance(int usedVoyance) {
        this.usedVoyance = usedVoyance;
    }

    public void setUsedSpiritisme(int usedSpiritisme) {
        this.usedSpiritisme = usedSpiritisme;
    }

    public void setUsedInsomnie(int usedInsomnie) {
        this.usedInsomnie = usedInsomnie;
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

    public int getUsedContamination() {
        return usedContamination;
    }

    public int getUsedVoyance() {
        return usedVoyance;
    }

    public int getUsedSpiritisme() {
        return usedSpiritisme;
    }

    public int getUsedInsomnie() {
        return usedInsomnie;
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
        return "Player{" + "id=" + id + ", gameId=" + gameId + ", username=" + username + ", isLg=" + isLg + ", alive=" + alive + ", hasContamination=" + hasContamination + ", hasInsomnie=" + hasInsomnie + ", hasVoyance=" + hasVoyance + ", hasSpiritisme=" + hasSpiritisme + '}';
    }

}
