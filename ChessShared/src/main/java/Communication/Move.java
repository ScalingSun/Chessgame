package Communication;

import Enums.PieceType;
import Enums.PlayerColor;
import data.Piece;

public class Move {
    private int currentX;
    private int currentY;
    private int newX;
    private int newY;
    private PieceType pieceType;
    private PlayerColor color;
    public Move(int oldX,int oldY,int nX,int nY){
        currentX = oldX;
        currentY = oldY;
        newX = nX;
        newY = nY;
    }
    public Move(int oldX, int oldY, int nX, int nY, PieceType type, PlayerColor c){
        currentX = oldX;
        currentY = oldY;
        newX = nX;
        newY = nY;
        pieceType = type;
        color = c;
    }

    public int getCurrentX() {
        return currentX;
    }

    public int getCurrentY() {
        return currentY;
    }

    public int getNewX() {
        return newX;
    }

    public int getNewY() {
        return newY;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public PlayerColor getColor() {
        return color;
    }
}
