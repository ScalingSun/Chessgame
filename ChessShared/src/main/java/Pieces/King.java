package Pieces;

import Enums.PieceType;
import Enums.PlayerColor;
import data.Board;
import data.Coordinate;
import data.Piece;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {

    public King(PieceType p, int X, int Y, PlayerColor c, Board b) {
        super(PieceType.KING,X,Y,c,b); // FIX THIS YOU MORON
    }



    @Override
    public List<Coordinate> getAvailableMoves() {
        List<Coordinate> result = new ArrayList();
        for(int i = 0; i < 8; i++){ //this for loop is for direction. 0 being northeast, 1 being southeast, 2 being southwest, 3 being northwest.
            if (i == 0) {
                    int incrementnumber = ownLocation.getYaxis() + 1;
                    if(incrementnumber < 8){
                        Coordinate tempcoord = board.getCoordinate(ownLocation.getXaxis(),incrementnumber);
                        if(tempcoord.getPiece() == this){
                            continue;
                        }
                        if(tempcoord.hasPiece()){
                            Piece p = tempcoord.getPiece();
                            if(p.getColor() == getColor()){
                                break;
                            }
                            result.add(tempcoord);
                            break;
                        }
                        result.add(tempcoord);
                    }
            }


            if (i == 1) {
                    int newYaxis = ownLocation.getYaxis() + 1;
                    int newXaxis = ownLocation.getXaxis() + 1;
                    if(newXaxis < 8 && newYaxis < 8){
                        Coordinate tempcoord = board.getCoordinate(newXaxis,newYaxis);
                        if(tempcoord.getPiece() == this){
                            continue;
                        }
                        if(tempcoord.hasPiece()){
                            Piece p = tempcoord.getPiece();
                            if(p.getColor() == getColor()){
                                break;
                            }
                            result.add(tempcoord);
                            break;
                        }
                        result.add(tempcoord);
                    }
            }

            if (i == 2) {
                    int incrementnumber = ownLocation.getXaxis() + 1;
                    if(incrementnumber < 8){
                        Coordinate tempcoord = board.getCoordinate(incrementnumber, ownLocation.getYaxis());
                        if(tempcoord.getPiece() == this){
                            continue;
                        }
                        if(tempcoord.hasPiece()){
                            Piece p = tempcoord.getPiece();
                            if(p.getColor() == getColor()){
                                break;
                            }
                            result.add(tempcoord);
                            break;
                        }
                        result.add(tempcoord);
                    }
            }
            if (i == 3) {
                    int newYaxis = ownLocation.getYaxis() - 1;
                    int newXaxis = ownLocation.getXaxis() + 1;
                    if(newXaxis < 8 && newYaxis  > -1){
                        Coordinate tempcoord = board.getCoordinate(newXaxis,newYaxis);
                        if(tempcoord.getPiece() == this){
                            continue;
                        }
                        if(tempcoord.hasPiece()){
                            Piece p = tempcoord.getPiece();
                            if(p.getColor() == getColor()){
                                break;
                            }
                            result.add(tempcoord);
                            break;
                        }
                        result.add(tempcoord);
                    }
            }
            if (i == 4) {
                    int decrementnumber = ownLocation.getYaxis() - 1;
                    if(decrementnumber > -1){
                        Coordinate tempcoord = board.getCoordinate(ownLocation.getXaxis(), decrementnumber);
                        if(tempcoord.getPiece() == this){
                            continue;
                        }
                        if(tempcoord.hasPiece()){
                            Piece p = tempcoord.getPiece();
                            if(p.getColor() == getColor()){
                                break;
                            }
                            result.add(tempcoord);
                            break;
                        }
                        result.add(tempcoord);
                    }
            }
            if (i == 5) {
                    int newYaxis = ownLocation.getYaxis() - 1;
                    int newXaxis = ownLocation.getXaxis() - 1;
                    if(newXaxis > -1 && newYaxis > -1){
                        Coordinate tempcoord = board.getCoordinate(newXaxis,newYaxis);
                        if(tempcoord.getPiece() == this){
                            continue;
                        }
                        if(tempcoord.hasPiece()){
                            Piece p = tempcoord.getPiece();
                            if(p.getColor() == getColor()){
                                break;
                            }
                            result.add(tempcoord);
                            break;
                        }
                        result.add(tempcoord);
                    }
            }

            if (i == 6) {
                    int decrementnumber = ownLocation.getXaxis() - 1;
                    if(decrementnumber > -1){
                        Coordinate tempcoord = board.getCoordinate(decrementnumber, ownLocation.getYaxis());
                        if(tempcoord.getPiece() == this){
                            continue;
                        }
                        if(tempcoord.hasPiece()){
                            Piece p = tempcoord.getPiece();
                            if(p.getColor() == getColor()){
                                break;
                            }
                            result.add(tempcoord);
                            break;
                        }
                        result.add(tempcoord);
                    }
            }

            if (i == 7) {
                    int newYaxis = ownLocation.getYaxis() + 1;
                    int newXaxis = ownLocation.getXaxis() - 1;
                    if(newXaxis > -1 && newYaxis < 8){
                        Coordinate tempcoord = board.getCoordinate(newXaxis,newYaxis);
                        if(tempcoord.getPiece() == this){
                            continue;
                        }
                        if(tempcoord.hasPiece()){
                            Piece p = tempcoord.getPiece();
                            if(p.getColor() == getColor()){
                                break;
                            }
                            result.add(tempcoord);
                            break;
                        }
                        result.add(tempcoord);
                    }
                }
        }
        return result;
    }
}
