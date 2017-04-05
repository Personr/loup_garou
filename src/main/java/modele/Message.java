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
public class Message {
    
    private final int id;
    private final int gameId;
    private final java.sql.Date date;
    private final String text;
    private final int isLg;
    private final String username;

    public Message(int id, int gameId, Date date, String text, int isLg, String username) {
        this.id = id;
        this.gameId = gameId;
        this.date = date;
        this.text = text;
        this.isLg = isLg;
        this.username = username;
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
