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
    
    private final int gameId;
    private final java.sql.Date date;
    private final String text;
    private final int isLg;

    public Message(int gameId, Date date, String text, int isLg) {
        this.gameId = gameId;
        this.date = date;
        this.text = text;
        this.isLg = isLg;
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
        return "Message{" + "gameId=" + gameId + ", date=" + date + ", text=" + text + ", isLg=" + isLg + '}';
    }
    
}
