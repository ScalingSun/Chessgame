package Pieces;

import Enums.PieceType;
import Enums.PlayerColor;
import data.Board;
import data.Coordinate;
import data.Piece;

import javax.xml.transform.Result;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {
//todo: fix black/white met for(int i = -1; i < 3; i = i+2);
    public Pawn(PieceType p, int X, int Y, PlayerColor c, Board b) {
        super(p,X,Y,c,b);
    }

    @Override
    public List<Coordinate> getAvailableMoves() { //todo: all 'getavailablemoves" are a bit hacky made, and should be revisited.
        List<Coordinate> result = new ArrayList<>();
        if(getColor() == PlayerColor.WHITE){//can only go upwards
            if(moveCount == 0){//on pawn first move has the option to move 2 squares.
                for(int i = 1; i < 3; i++){
                        Coordinate coord = board.getCoordinate(ownLocation.getXaxis(), ownLocation.getYaxis() + i);
                        if (!coord.hasPiece()) {
                            result.add(coord);
                        }
                }
            }
            else{
                result.add(board.getCoordinate(ownLocation.getXaxis(),ownLocation.getYaxis() + 1));
            }

        }
        if(getColor() == PlayerColor.BLACK){//can only go downwards
            if(moveCount == 0){//on pawn first move has the option to move 2 squares.
                for(int i = 1; i < 3; i++){
                    Coordinate coord = board.getCoordinate(ownLocation.getXaxis(), ownLocation.getYaxis() - i);
                    if (!coord.hasPiece()) {
                        result.add(coord);
                    }
                }
            }
            else{
                result.add(board.getCoordinate(ownLocation.getXaxis(),ownLocation.getYaxis() -1));
            }

        }

        result.addAll(getDiagonals());
        return result;
    }

    private List<Coordinate> getDiagonals() {
        List<Coordinate> result = new ArrayList<>();
        if(getColor() == PlayerColor.WHITE){
            if(ownLocation.getXaxis() - 1 > -1){
                Coordinate diagonalLeft = board.getCoordinate(ownLocation.getXaxis() -1, ownLocation.getYaxis() + 1);
                if(diagonalLeft.hasPiece() && getColor() != diagonalLeft.getPiece().getColor()){
                    result.add(diagonalLeft);
                }
            }
            if(ownLocation.getXaxis() + 1 < 8){
                Coordinate diagonalRight = board.getCoordinate(ownLocation.getXaxis() + 1, ownLocation.getYaxis() + 1);
                if(diagonalRight.hasPiece() && getColor() != diagonalRight.getPiece().getColor()){
                    result.add(diagonalRight);
                }
            }
        }

        if(getColor() == PlayerColor.BLACK){
            if(ownLocation.getXaxis() - 1 > -1){
                Coordinate diagonalLeft = board.getCoordinate(ownLocation.getXaxis() -1, ownLocation.getYaxis() - 1);
                if(diagonalLeft.hasPiece() && getColor() != diagonalLeft.getPiece().getColor()){
                    result.add(diagonalLeft);
                }
            }
            if(ownLocation.getXaxis() + 1 < 8){
                Coordinate diagonalRight = board.getCoordinate(ownLocation.getXaxis() + 1, ownLocation.getYaxis() -1);
                if(diagonalRight.hasPiece() && getColor() != diagonalRight.getPiece().getColor()){
                    result.add(diagonalRight);
                }
            }
        }
        return result;
    }


}
