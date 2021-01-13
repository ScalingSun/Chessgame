package communication;
import Communication.Message;
import Communication.Move;
import Enums.messageType;
import Game.ChessGame;
import Game.IChessGame;
import UI.IChessGUI;
import com.google.gson.Gson;
import data.Board;
import data.Player;
import javafx.application.Platform;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@ClientEndpoint
public class ClientPoint {

    // Singleton
    private static ClientPoint instance = null;

    /**
     * The local websocket uri to connect to.
     */
    private final String uri = "ws://localhost:8095/communicator/";

    private Session session;

    private String message;
    // Status of the webSocket client
    boolean isRunning = false;
    private IChessGame game;
    private Player player;
    boolean isPlayerset = false;
    public Gson g;
    private IChessGUI gui;
    // Private constructor (singleton pattern)
    private ClientPoint() {

    }
    public void setGUI(IChessGUI gui){
        this.gui = gui;
    }


    /**
     * Get singleton instance of this class.
     * Ensure that only one instance of this class is created.
     * @return instance of client web socket
     */
    public static ClientPoint getInstance() {
        if (instance == null) {
            System.out.println("[WebSocket Client create singleton instance]");
            instance = new ClientPoint();

        }
        return instance;
    }


    /**
     *  Start the connection.
     */
    public void start() {
        System.out.println("[WebSocket Client start connection]");
        if (!isRunning) {
            startClient();
            isRunning = true;
        }
    }

    public void stop() {
        System.out.println("[WebSocket Client stop]");
        if (isRunning) {
            stopClient();
            isRunning = false;
        }
    }

    @OnOpen
    public void onWebSocketConnect(Session session){
        System.out.println("[WebSocket Client open session] " + session.getRequestURI());
        this.session = session;
    }

    @OnMessage
    public void onWebSocketText(String message, Session session){
        Gson g = new Gson();
        Message message1 = g.fromJson(message , Message.class);
        switch(message1.getType()){

            case REGISTER:
                receivedRegister(message1,session);
                break;
            case READY:
                receivedReady(message1,session);
                break;
            case MESSAGE:
                receivedMessage(message1,session);
                break;
            case MOVE:
                receivedMove(message1,session);
                break;
            case GAMEOVER:
                receivedGameOver(message1,session);
                break;
        }
    }

    private void receivedReady(Message message1, Session session) {
        Player p = g.fromJson(message1.getMessage(),Player.class);
        game.setTurn(p.getPlayerTurn());
        game.start();
    }

    private void receivedGameOver(Message message1, Session session) {

    }

    private void receivedMove(Message message1, Session session) {
        Move m = g.fromJson(message1.getMessage(),Move.class);
        Platform.runLater(new Runnable(){
            @Override
            public void run() {
                game.updateBoard(m);
            }
        });



    }

    private void receivedMessage(Message message1, Session session) {
    }

    private void receivedRegister(Message message, Session session) {
        g = new Gson();
        Player yourself = g.fromJson(message.getMessage(),Player.class);
        player = yourself;
        game.setPlayerdata(yourself);
        System.out.println("receivedRegister");
    }

    @OnError
    public void onWebSocketError(Session session, Throwable cause){

        System.out.println("[WebSocket Client connection error] " + cause.toString());
        cause.printStackTrace();
    }

    @OnClose
    public void onWebSocketClose(CloseReason reason){
        System.out.print("[WebSocket Client close session] " + session.getRequestURI());
        System.out.println(" for reason " + reason);
        session = null;
    }

    private void sendMessageToServer(Message message) {
        g = new Gson();
        String result = g.toJson(message,Message.class);
        session.getAsyncRemote().sendText(result);
    }

    /**
     * Get the latest message received from the websocket communication.
     * @return The message from the websocket communication
     */
    public String getMessage() {
        return message;
    }

    /**
     * Set the message, but no action is taken when the message is changed.
     * @param message the new message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Start a WebSocket client.
     */
    private void startClient() {
        System.out.println("[WebSocket Client start]");
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            container.connectToServer(this, new URI(uri));

        } catch (IOException | URISyntaxException | DeploymentException ex) {
            // do something useful eventually
            ex.printStackTrace();
        }
    }

    /**
     * Stop the client when it is running.
     */
    private void stopClient(){
        System.out.println("[WebSocket Client stop]");
        try {
            session.close();

        } catch (IOException ex){
            // do something useful eventually
            ex.printStackTrace();
        }
    }

    // Process incoming json message
    private void processMessage(String jsonMessage) {

    }


    public void sendRegister(String s){
        Message message1 = new Message(messageType.REGISTER,s,5);
        sendMessageToServer(message1);
    }
    public void sendMove(int currentX, int CurrentY, int newX, int newY){
        Move move = new Move(currentX, CurrentY, newX, newY);
        Message m = new Message(messageType.MOVE, g.toJson(move) ,player.getPlayerNr());
        session.getAsyncRemote().sendText(g.toJson(m));
    }


    public void assignGame(ChessGame game) {
        this.game = game;
    }

    public void setGame(ChessGame chessGame) {
        this.game = chessGame;
    }
}
