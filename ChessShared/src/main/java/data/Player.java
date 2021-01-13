package data;

import Enums.PlayerColor;

import javax.websocket.Session;

public class Player {
    private transient Session session;
    private String name;
    private PlayerColor color;
    private int playerNr;
    private boolean playerTurn;

    public Player(){
    }

    public int getPlayerNr() {
        return playerNr;
    }

    public void setPlayerNr(int playerNr) {
        this.playerNr = playerNr;
    }

    public Session getSession() {
        return session;
    }

    public String getName() {
        return name;
    }

    public PlayerColor getColor() {
        return color;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public void setColor(PlayerColor color) {
        this.color = color;
    }

    public void setPlayerTurn(boolean playerTurn) {
        this.playerTurn = playerTurn;
    }
    public boolean getPlayerTurn(){ return playerTurn;}

    public void switchTurn(){
        playerTurn = !playerTurn;
    }
}
