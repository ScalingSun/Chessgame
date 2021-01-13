package Game;

import Communication.Move;
import DTO.DTOUser;
import Enums.PlayerColor;
import UI.IChessGUI;
import data.Board;
import data.Coordinate;
import data.Piece;
import data.Player;

import java.io.IOException;

public interface IChessGame {
    void setPlayerdata(Player p);
    void setOpponentName();
    void movePiece(Piece p, Coordinate c);
    void boardPressed(int xaxis, int yaxis);
    void start();
    void register(String u, String p) throws IOException, InterruptedException;
    void setTurn(boolean value);
    void switchTurn();
    void setPlayerToPieces(PlayerColor color);
    void updateBoard(Move b);
    void createAccount(DTOUser u) throws IOException, InterruptedException;
}
