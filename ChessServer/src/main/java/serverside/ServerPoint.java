package serverside;

import Communication.Message;
import Enums.PlayerColor;
import Enums.messageType;
import com.google.gson.Gson;
import data.Player;
import logging.Logging;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.ArrayList;
import java.util.List;


@ServerEndpoint(value="/communicator/")
public class ServerPoint {
    private Gson g = new Gson();
    private String websocketSession = "[WebSocket Session ID] : ";
    private List<Session> uselessSessions = new ArrayList<>();
    private static ChessGameServer server = new ChessGameServer();
    Logging logger = new Logging();

    @OnOpen
    public void onConnect(Session session) {
        uselessSessions.add(session);
        if(server.getPlayers().size() < 2){
            System.out.println("[WebSocket Connected] SessionID: " + session.getId());
            logger.writeToLog("new client with Session ID " + session.getId() + " connected.", "onConnect()");
            System.out.println("[#sessions]: " + server.getPlayers().size());
        }
        else{
            System.out.println("Max players reached.");
        }
    }

    @OnMessage
    public void onText(String message, Session session) {
        System.out.println(websocketSession + session.getId() + " [Received] : " + message);
        logger.writeToLog(message, "onText()");
        handleMessageFromClient(message, session);

    }

    @OnClose
    public void onClose(CloseReason reason, Session session) {
        System.out.println(websocketSession + session.getId() + " [Socket Closed]: " + reason);
        logger.writeToLog("client with Session ID " + session.getId() + " disconnected.", "onClose()");
        for(Player p : server.getPlayers()){
            if(p.getSession() == session){
                server.getPlayers().remove(p);
                break;
            }
        }
    }

    @OnError
    public void onError(Throwable cause, Session session) {
        System.out.println(websocketSession + session.getId() + "[ERROR]: ");
        logger.writeToLog("client with Session ID " + session.getId() + " got the error: " + cause, "onError()");
        cause.printStackTrace(System.err);
    }

    // Handle incoming message from client
    private void handleMessageFromClient(String jsonMessage, Session session) {
        Message msg = g.fromJson(jsonMessage, Message.class);
        switch(msg.getType()){
            case REGISTER:
                server.register(msg, session);
                break;
            case MOVE:
                server.move(msg);
                break;
            case MESSAGE:
                server.message(msg);
                break;
            case GAMEOVER:
                server.gameOver(msg);
                break;
        }
    }


}