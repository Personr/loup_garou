/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import dao.GameDAO;
import dao.PlayerDAO;
import static java.lang.Math.ceil;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Random;

/**
 *
 * @author personr
 */
public class Game {
    
    private final int gameId;
    private final int minPlayers;
    private final int maxPlayers;
    private int nbPlayers;
    private int started;
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
    private int isDay;
    
    //liste des objets player de la partie uniquement
    private Map<String, Player> mapJoueurs;
    private List<Player> villageois;
    private List<Player> lg;
    private List<Player> sansPouvoir;
    
    public Game(int gameId, int minPlayers, int maxPlayers, int nbPlayers, int started, 
            Time startTime, int finished,
            String creator, Time dayTime, Time nightTime, float pContamination, 
            float pInsomnie, float pVoyance, float pSpiritisme, float lgProp, int isDay) {
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
        this.mapJoueurs = new HashMap<String, Player>();
        this.villageois = new ArrayList<Player>();
        this.lg = new ArrayList<Player>();
        this.sansPouvoir = new ArrayList<Player>();
        this.isDay = isDay;
    }
    
    
//    public void ajouterJoueur(String username, Player joueur) {
//        mapJoueurs.put(username, joueur);
//    }
    
    public void startGame(PlayerDAO playerDAO, GameDAO gameDAO) {
        mapJoueurs = playerDAO.getListePlayers(gameId);
        
        setRoles(playerDAO);
        setContamination(playerDAO);
        setInsomnie(playerDAO);
        setVoyance(playerDAO);
        setSpiritisme(playerDAO);
        
        started = 1;
        gameDAO.startGame(gameId);
    }
    
    private int randIntBetween(int min, int max) {
	Random rand = new Random();
	int randNb = rand.nextInt(max - min + 1) + min;
	return randNb;
    }
    
    
    private float randFloat0_1() {
	Random rand = new Random();
	float randNb = (float)rand.nextDouble();
	return randNb;
    }
    
    private int getLgNb() {
        int res = (int) ceil(lgProp * nbPlayers);
        if (res == 0) {
            res = 1;
        } else if (res == nbPlayers) {
            res--;
        }
        return res;
    }
    
    private void setContamination(PlayerDAO playerDAO) {
        float seuil = randFloat0_1();
        if (seuil <= pContamination) {
            int indice = randIntBetween(0, lg.size()-1);
            Player player = lg.get(indice);
            sansPouvoir.remove(player);
            player.setHasContamination(1);
            playerDAO.modifierPlayer(player.getId(), player.getIsLg(), player.getAlive(), 
                    1, player.getHasVoyance(), player.getHasInsomnie(), player.getHasSpiritisme());
        }
    }
    
    private void setInsomnie(PlayerDAO playerDAO) {
        float seuil = randFloat0_1();
        if (seuil <= pInsomnie) {
            int indice = randIntBetween(0, villageois.size()-1);
            Player player = villageois.get(indice);
            sansPouvoir.remove(player);
            player.setHasInsomnie(1);
            playerDAO.modifierPlayer(player.getId(), player.getIsLg(), player.getAlive(), 
                    player.getHasContamination(), player.getHasVoyance(), 1, player.getHasSpiritisme());
        }
    }
    
    private void setVoyance(PlayerDAO playerDAO) {
        float seuil = randFloat0_1();
        if (seuil <= pVoyance) {
            int indice = randIntBetween(0, sansPouvoir.size() - 1);
            Player player = sansPouvoir.get(indice);
            sansPouvoir.remove(indice);
            player.setHasVoyance(1);
            playerDAO.modifierPlayer(player.getId(), player.getIsLg(), player.getAlive(), 
                    player.getHasContamination(), 1, player.getHasInsomnie(), player.getHasSpiritisme());
        }
    }
    
    private void setSpiritisme(PlayerDAO playerDAO) {
        float seuil = randFloat0_1();
        if (seuil <= pSpiritisme) {
            int indice = randIntBetween(0, sansPouvoir.size() - 1);
            Player player = sansPouvoir.get(indice);
            sansPouvoir.remove(indice);
            player.setHasSpiritisme(1);
            playerDAO.modifierPlayer(player.getId(), player.getIsLg(), player.getAlive(), 
                    player.getHasContamination(), player.getHasVoyance(), player.getHasInsomnie(), 1);
        }
    }
    
    private void setRoles(PlayerDAO playerDAO) {
        int lgNb = getLgNb();
        HashSet<Integer> indices = new HashSet<Integer>();
        while (indices.size() < lgNb) {
            indices.add(randIntBetween(1, nbPlayers));
        }
        
        Set keys = mapJoueurs.keySet();
        Iterator it = keys.iterator();
        int i = 1;
        while (it.hasNext()) {
            String username = (String) it.next(); 
            Player player = mapJoueurs.get(username); 
            if (indices.contains(i)) {
                player.setIsLg(1);
                lg.add(player);
                playerDAO.modifierPlayer(player.getId(), 1, 1, 0, 0, 0, 0);
            } else {
                villageois.add(player);
            }
            sansPouvoir.add(player);
            i++;
        }
    }
    
    public void incrNbPlayers() {
        nbPlayers++;
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

    public int getIsDay() {
        return isDay;
    }

    @Override
    public String toString() {
        return "Game{" + "gameId=" + gameId + ", minPlayers=" + minPlayers + ", maxPlayers=" + maxPlayers + ", nbPlayers=" + nbPlayers + ", started=" + started + ", finished=" + finished + ", creator=" + creator + ", dayTime=" + dayTime + ", nightTime=" + nightTime + ", pContamination=" + pContamination + ", pInsomnie=" + pInsomnie + ", pVoyance=" + pVoyance + ", pSpiritisme=" + pSpiritisme + ", lgProp=" + lgProp + ", startTime=" + startTime + '}';
    }
     
}
