/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.sql.*;
import java.text.SimpleDateFormat;

/**
 *
 * @author personr
 */
public class Message {
    
    private final int id;
    private final int gameId;
    private final java.sql.Date date;
    private final String text;
    private final int isLg;
    private final String username;
    private final int dayNb;

    public Message(int id, int gameId, Date date, String username, String text, int isLg, int dayNb) {
        this.id = id;
        this.gameId = gameId;
        this.date = date;
        this.text = text;
        this.isLg = isLg;
        this.username = username;
        this.dayNb = dayNb;
    }

    public int getDayNb() {
        return dayNb;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public int getGameId() {
        return gameId;
    }

    public Date getDate() {
        return date;
    }
    
    // retourne l'heure pour afficher dans le chat
    public String getPrettyDate(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    public String getText() {
        return text;
    }

    public int getIsLg() {
        return isLg;
    }

    @Override
    public String toString() {
        return "Message{" + "id=" + id + ", gameId=" + gameId + ", date=" + date + ", text=" + text + ", isLg=" + isLg + ", username=" + username + '}';
    }
    
}
