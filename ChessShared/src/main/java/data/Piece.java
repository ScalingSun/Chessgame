package data;

import Enums.PieceType;
import Enums.PlayerColor;

import java.util.List;

public abstract class Piece {
    transient protected Board board;
    transient public Coordinate ownLocation;
    protected int moveCount = 0;
    PieceType type;
    protected PlayerColor color;

    public Piece(PieceType p,int Xaxis, int Yaxis,PlayerColor c, Board b){
        board = b;
        type = p;
        board.setPiece(this,Xaxis,Yaxis);
        color = c;
        ownLocation = board.getCoordinate(Xaxis,Yaxis);

    }
    public void setBoard(Board board) {
        this.board = board;
    }

    public PlayerColor getColor() {
        return color;
    }
    public PieceType getPieceType(){
        return type;
    }
//    private int getDirection(int i){
//        int result = 0;
//
//        return result;
//    }
    public Coordinate getOwnLocation(){
        return ownLocation;
    }
    public abstract List<Coordinate> getAvailableMoves();

    public  void setNewLocation(Coordinate coord){
        ownLocation = coord;
    }

    public int getMoveCount() {
        return moveCount;
    }
    public void addMoveCount(){
        moveCount++;
    }
}
