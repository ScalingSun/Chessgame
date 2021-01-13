package Pieces;

import Enums.PieceType;
import Enums.PlayerColor;
import data.Board;
import data.Coordinate;
import data.Piece;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {
    public Knight(PieceType p, int X, int Y, PlayerColor c, Board b) {
        super(p,X,Y,c,b);
    }


    @Override
    public List<Coordinate> getAvailableMoves() { //todo THIS CODE NEEDS WORK. OTHER PRIORITIES FIRST
        List<Coordinate> result = new ArrayList<>();
        for(int i = 0; i < 4; i++){//direction
            for(int y = -1; y < 2; y = y + 2){// sides per direction
                if (i == 0){
                    int newY = ownLocation.getYaxis() + 2;
                    int newX = ownLocation.getXaxis() + y;
                    if(newY < 8 && newX < 8 && newX > -1){
                        if(board.getCoordinate(newX,newY).hasPiece()){
                            if(board.getCoordinate(newX,newY).getPiece().getColor() != getColor()){
                                result.add(board.getCoordinate(newX,newY));
                            }
                        }else{
                            result.add(board.getCoordinate(newX,newY));
                        }
                    }
                }
                if (i == 1) {
                    int newY = ownLocation.getYaxis() + y;
                    int newX = ownLocation.getXaxis() + 2;
                    if(newY < 8  && newY > -1 && newX < 8){
                        if(board.getCoordinate(newX,newY).hasPiece()){
                            if(board.getCoordinate(newX,newY).getPiece().getColor() != getColor()){
                                result.add(board.getCoordinate(newX,newY));
                            }
                        }else{
                            result.add(board.getCoordinate(newX,newY));
                        }
                    }
                }
                if (i == 2) {
                    int newY = ownLocation.getYaxis() - 2;
                    int newX = ownLocation.getXaxis() + y;
                    if(newY > -1 && newX < 8 && newX > -1){
                        if(board.getCoordinate(newX,newY).hasPiece()){
                            if(board.getCoordinate(newX,newY).getPiece().getColor() != getColor()){
                                result.add(board.getCoordinate(newX,newY));
                            }
                        }else{
                            result.add(board.getCoordinate(newX,newY));
                        }
                    }
                }
                if (i == 3) {
                    int newY = ownLocation.getYaxis() + y;
                    int newX = ownLocation.getXaxis() - 2;
                    if(newY < 8  && newY > -1 && newX > -1){
                        if(board.getCoordinate(newX,newY).hasPiece()){
                            if(board.getCoordinate(newX,newY).getPiece().getColor() != getColor()){
                                result.add(board.getCoordinate(newX,newY));
                            }
                        }else{
                            result.add(board.getCoordinate(newX,newY));
                        }
                    }
                }
            }
        }

        return result;
    }
}
