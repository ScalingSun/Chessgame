package data;

import Enums.PieceType;
import Enums.PlayerColor;
import Pieces.*;

import java.util.ArrayList;
import java.util.List;

import static Enums.PlayerColor.BLACK;
import static Enums.PlayerColor.WHITE;

public class Board {
    List<Player> players;
    Coordinate[][] coordinates = new Coordinate[8][8];
    List<Piece> pieces = new ArrayList<>();

    public Board(List<Player> twoPlayers){
       players = twoPlayers;
       for(int X = 0; X < 8; X++){
           for(int Y = 0; Y < 8; Y++){
               Coordinate coordinate = new Coordinate(X,Y);
               coordinates[X][Y] = coordinate;
           }
       }
        initPieces();
    }

    private void initPieces() {
        Rook WhiteRook1 = new Rook(PieceType.ROOK,0,0, WHITE,this);
        Knight WhiteKnight1 = new Knight(PieceType.KNIGHT,1,0,WHITE,this);
        Bishop WhiteBishop1 = new Bishop(PieceType.BISHOP,2,0,WHITE,this);
        Queen WhiteQueen = new Queen(PieceType.QUEEN,3,0,WHITE,this);
        King WhiteKing = new King(PieceType.KING,4,0,WHITE,this);
        Bishop WhiteBishop2 = new Bishop(PieceType.BISHOP,5,0,WHITE,this);
        Knight WhiteKnight2 = new Knight(PieceType.KNIGHT,6,0,WHITE,this);
        Rook WhiteRook2 = new Rook(PieceType.ROOK,7,0, WHITE,this);

        for(int i = 0; i < 8; i++){
            Pawn pawn = new Pawn(PieceType.PAWN,i,1,WHITE,this);
        }

        Rook BlackRook1 = new Rook(PieceType.ROOK,0,7, BLACK,this);
        Knight BlackKnight1 = new Knight(PieceType.KNIGHT,1,7,BLACK,this);
        Bishop BlackBishop1 = new Bishop(PieceType.BISHOP,2,7,BLACK,this);
        King BlackKing = new King(PieceType.KING,3,7,BLACK,this);
        Queen BlackQueen = new Queen(PieceType.QUEEN,4,7,BLACK,this);
        Bishop BlackBishop2 = new Bishop(PieceType.BISHOP,5,7,BLACK,this);
        Knight BlackKnight2 = new Knight(PieceType.KNIGHT,6,7,BLACK,this);
        Rook BlackRook2 = new Rook(PieceType.ROOK,7,7, BLACK,this);

        for(int i = 0; i < 8; i++){
            Pawn pawn = new Pawn(PieceType.PAWN,i,6,BLACK,this);
        }
    }

    public void setPiece(Piece piece, int X, int Y){
        Coordinate coord = coordinates[X][Y];
        coord.setPiece(piece);
        pieces.add(piece);
    }

    public Coordinate[][] getCoordinates() {
        return coordinates;
    }
    public Coordinate getCoordinate(int X, int Y){
        return coordinates[X][Y];
    }

    public List<Piece> getPieces(){
        return pieces;
    }

    public Coordinate getLocationOfPiece(Piece piece){
        for(int X = 0; X < 8; X++) {
            for(int Y = 0; Y < 8; Y++) {
                if(coordinates[X][Y].getPiece() == piece){
                    return getCoordinate(X,Y);
                }
            }
        }
        return null;
    }

    private Piece getPiece(int X, int Y){
       Coordinate c =  getCoordinate(X,Y);
       return c.getPiece();
    }

    public void move(int currentX, int currentY, int newX,int newY){
        Piece pieceToMove = getPiece(currentX,currentY);
        pieceToMove.addMoveCount();

        Coordinate oldCoord = getCoordinate(currentX,currentY);
        oldCoord.removePiece();

        Coordinate newCoord = getCoordinate(newX,newY);
        pieceToMove.setNewLocation(newCoord);

        newCoord.setPiece(pieceToMove);
    }
}
