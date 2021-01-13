package Game;

import Communication.Move;
import DTO.DTOUser;
import Enums.PieceType;
import Enums.PlayerColor;
import Pieces.*;
import UI.IChessGUI;
import com.google.gson.Gson;
import communication.ClientPoint;
import data.Board;
import data.Coordinate;
import data.Piece;
import data.Player;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import static Enums.PlayerColor.BLACK;
import static Enums.PlayerColor.WHITE;

public class ChessGame implements IChessGame {
    boolean yourTurn;
    Piece selectedPiece;
    ClientPoint client;
    Player thisplayer;
    Board board = new Board(new ArrayList<>());
    private IChessGUI gui;
    public ChessGame(IChessGUI gui){
        this.gui = gui;
        setup();
        client = ClientPoint.getInstance();
        client.start();
        client.setGUI(gui);
        client.setGame(this);
    }



    @Override
    public void setPlayerdata(Player p) {
        thisplayer = p;
        gui.setPlayerData(p.getPlayerNr(),p.getName());
        gui.sendNotification("You are the " + p.getColor().toString() + " pieces.");

    }

    @Override
    public void setOpponentName() {

    }

    @Override
    public void movePiece(Piece pieceToMove, Coordinate destination) {
        client.sendMove(pieceToMove.getOwnLocation().getXaxis(),pieceToMove.getOwnLocation().getYaxis(), destination.getXaxis(),destination.getYaxis());
        //board.move(pieceToMove.getOwnLocation().getXaxis(),pieceToMove.getOwnLocation().getYaxis(), destination.getXaxis(),destination.getYaxis());
        //gui.movePiece(pieceToMove.getOwnLocation().getXaxis(),pieceToMove.getOwnLocation().getYaxis(),pieceToMove.getColor(),pieceToMove.getPieceType(),destination.getXaxis(),destination.getYaxis());
    }
    @Override
    public void boardPressed(int xaxis, int yaxis) {
        //check on server side if there is a piece on this location
        if(yourTurn) {
            Coordinate clickedCoord = board.getCoordinate(xaxis, yaxis);
            if (selectedPiece == null) {// for first click on the actual piece;
                if (clickedCoord.hasPiece() && clickedCoord.getPiece().getColor() == thisplayer.getColor()) {
                    highlight(xaxis, yaxis);
                    selectedPiece = clickedCoord.getPiece();
                }
            } else { // to move the actual piece
                for (Coordinate c : selectedPiece.getAvailableMoves()) {
                    if (c == clickedCoord) {
                        movePiece(selectedPiece, clickedCoord);
                        break;
                    }
                }
                //remove highlighting here
                gui.removeAllHighlight();
                selectedPiece = null;
            }
        }
        else{
            gui.sendNotification("Wait till your opponent has moved.");
        }
    }

    @Override
    public void start() {
        gui.disableInput();
        if(thisplayer.getColor() == WHITE){
            yourTurn = true;
        }
        else{
            yourTurn = false;
        }

    }

    @Override
    public void register(String u, String p) throws IOException, InterruptedException {

        Gson g = new Gson();
        String result = g.toJson(new DTOUser(u,p));
        HttpClient c = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8090/api/users/login"))
                .setHeader("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(result))
                .build();

        HttpResponse<String> response = c.send(request,
                HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
        DTOUser user = g.fromJson(response.body(),DTOUser.class);
        client.sendRegister(user.getName());
    }

    @Override
    public void setTurn(boolean b) {
        yourTurn = b;
    }

    @Override
    public void switchTurn() {
        yourTurn = !yourTurn;
    }

    @Override
    public void setPlayerToPieces(PlayerColor color) {
        //implement method that you can only move 1 color pieces.
    }

    @Override
    public void updateBoard(Move m) {
        board.move(m.getCurrentX(),m.getCurrentY(),m.getNewX(),m.getNewY());
        gui.movePiece(m.getCurrentX(),m.getCurrentY(),m.getColor(),m.getPieceType(),m.getNewX(),m.getNewY());
        switchTurn();
    }

    @Override
    public void createAccount(DTOUser u) throws IOException, InterruptedException {
        Gson g = new Gson();

        HttpClient c = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8090/api/users/"))
                .setHeader("Content-Type", "application/json")
                .GET(HttpRequest.BodyPublishers.ofString(g.toJson(u)))
                .build();

        HttpResponse<String> response = c.send(request,
                HttpResponse.BodyHandlers.ofString());

        gui.sendNotification(response.body());
        System.out.println(response.body());
    }


    private void highlight(int Xaxis, int Yaxis){
        Coordinate coord =  board.getCoordinate(Xaxis,Yaxis);
        if(coord.hasPiece()){
            for(Coordinate c : coord.getPiece().getAvailableMoves()){
                gui.highlight(c.getXaxis(),c.getYaxis());
            }
        }
    }






    private void highLightAll(){
        for(Piece piece : board.getPieces()){
            if (piece == null) {
                System.out.println("test");
            }
            Coordinate c =  board.getLocationOfPiece(piece);
            gui.addPiece(piece.getColor(),piece.getPieceType(),c.getXaxis(),c.getYaxis());
            for(Coordinate coord : piece.getAvailableMoves()){
                gui.highlight(coord.getXaxis(),coord.getYaxis());
            }
        }
    }




    private void setup() {
        gui.addPiece(WHITE,PieceType.ROOK,0,0);
        gui.addPiece(WHITE,PieceType.KNIGHT,1,0);
        gui.addPiece(WHITE,PieceType.BISHOP,2,0);
        gui.addPiece(WHITE,PieceType.QUEEN,3,0);
        gui.addPiece(WHITE,PieceType.KING,4,0);
        gui.addPiece(WHITE,PieceType.BISHOP,5,0);
        gui.addPiece(WHITE,PieceType.KNIGHT,6,0);
        gui.addPiece(WHITE,PieceType.ROOK,7,0);
        for(int i = 0; i < 8; i++){
            gui.addPiece(WHITE,PieceType.PAWN,i,1);
        }



        gui.addPiece(PlayerColor.BLACK,PieceType.ROOK,0,7);
        gui.addPiece(PlayerColor.BLACK,PieceType.KNIGHT,1,7);
        gui.addPiece(PlayerColor.BLACK,PieceType.BISHOP,2,7);
        gui.addPiece(PlayerColor.BLACK,PieceType.KING,3,7);
        gui.addPiece(PlayerColor.BLACK,PieceType.QUEEN,4,7);
        gui.addPiece(PlayerColor.BLACK,PieceType.BISHOP,5,7);
        gui.addPiece(PlayerColor.BLACK,PieceType.KNIGHT,6,7);
        gui.addPiece(PlayerColor.BLACK,PieceType.ROOK,7,7);
        for(int i = 0; i < 8; i++){
            gui.addPiece(PlayerColor.BLACK,PieceType.PAWN,i,6);
        }
    }

}
