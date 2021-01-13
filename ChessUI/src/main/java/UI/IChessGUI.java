package UI;

import Enums.PieceType;
import Enums.PlayerColor;

public interface IChessGUI {
    void setPlayerData(int playerNr, String name);
    void notifyStartGame(int playerNr);
     void showErrorMessage(int playerNr, String errorMessage);
    void movePiece(int startX, int startY, PlayerColor color, PieceType type, int endX, int endY);
    void addPiece(PlayerColor color, PieceType type, int Xaxis, int Yaxis);
    void setOpponentName(String name);
    void highlight(int Xaxis, int Yaxis);
    void boardPressed(int Xaxis, int Yaxis);
    void removeAllHighlight();
    void sendNotification(String Message);
    void disableInput();
    void setTurn(PlayerColor p);
    void removePiece(int X,int Y);
}
