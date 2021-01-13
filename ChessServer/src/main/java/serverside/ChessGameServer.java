package serverside;

import Communication.Message;
import Communication.Move;
import Enums.PlayerColor;
import Enums.messageType;
import com.google.gson.Gson;
import data.Board;
import data.Player;

import javax.websocket.Session;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ChessGameServer {
    private static List<Player>  players = new ArrayList<>();
    Gson g = new Gson();
    Board board;

    //CONSTRUCTOR
    public ChessGameServer(){

    }
    public List<Player> getPlayers() {
        return players;
    }
    //GETTERS



    public void gameOver(Message msg) {
    }

    // SETTERS


    // PUBLIC METHODS
    public void message(Message msg) {
    }

    public void register(Message msg, Session s){
        if(players.size() >= 2){
            return;//perhaps send message back to user that max has been reached?
        }

        Player newplayer = new Player();
        newplayer.setSession(s);
        newplayer.setName(msg.getMessage());
        if(!isSomeoneConnectedAlready()){
            newplayer.setPlayerNr(0);
            newplayer.setColor(PlayerColor.WHITE);
        }
        else{
            newplayer.setPlayerNr(1);
            newplayer.setColor(PlayerColor.BLACK);
        }
        players.add(newplayer);


        if(bothPlayersReady()){
            for(Player p : players){
                sendRegisterPlayerData(p);
            }
            startGame();
        }
    }

    private boolean bothPlayersReady() {
        if(players.size() == 2){
            return true;
        }
        return false;
    }

    public void move(Message msg) {
        Move m  = g.fromJson(msg.getMessage(), Move.class);
        board.move(m.getCurrentX(),m.getCurrentY(),m.getNewX(),m.getNewY());
        Move returnMove = new Move(m.getCurrentX(),m.getCurrentY(),m.getNewX(),m.getNewY(),board.getCoordinate(m.getNewX(),m.getNewY()).getPiece().getPieceType(),board.getCoordinate(m.getNewX(),m.getNewY()).getPiece().getColor());
        String semiResult = g.toJson(returnMove);
        Message message = new Message(messageType.MOVE,semiResult,5);
        String result = g.toJson(message);
        for(Player p : players){
            p.switchTurn();
            p.getSession().getAsyncRemote().sendText(result);
        }
    }


    //PRIVATE METHODS
    private void sendRegisterPlayerData(Player p){
        String playerToText = g.toJson(p);
        String newmsg = g.toJson(new Message(messageType.REGISTER, playerToText,p.getPlayerNr()));
        p.getSession().getAsyncRemote().sendText(newmsg);
    }



    private boolean isSomeoneConnectedAlready(){
        for(Player p : players){
            if(p.getName() != null){
                return true;
            }
        }
        return false;
    }
    private void startGame(){
        board = new Board(players);
        for(Player p : players){
            Message m;
            if(p.getColor() == PlayerColor.WHITE){
                p.setPlayerTurn(true);
                String s = g.toJson(p);
                m = new Message(messageType.READY,s,p.getPlayerNr());
                String result = g.toJson(m);
                p.getSession().getAsyncRemote().sendText(result);
            }
            if(p.getColor() == PlayerColor.BLACK){
                p.setPlayerTurn(false);
                String s = g.toJson(p);
                m = new Message(messageType.READY,s,p.getPlayerNr());
                String result = g.toJson(m);
                p.getSession().getAsyncRemote().sendText(result);
            }
        }
    }


}
