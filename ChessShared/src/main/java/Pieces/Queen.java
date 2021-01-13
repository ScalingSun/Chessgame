package Pieces;

import Enums.PieceType;
import Enums.PlayerColor;
import data.Board;
import data.Coordinate;
import data.Piece;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {
    public Queen(PieceType p, int X, int Y, PlayerColor c, Board b) {
        super(p,X,Y,c,b);
    }


    @Override
    public List<Coordinate> getAvailableMoves() {
        List<Coordinate> result = new ArrayList();
        for(int i = 0; i < 8; i++){ //this for loop is for direction. 0 being northeast, 1 being southeast, 2 being southwest, 3 being northwest.

            if (i == 0) {
                for(int Q = 0; Q < 8; Q++){
                    int incrementnumber = ownLocation.getYaxis() + Q;
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
            }


            if (i == 1) {
                for(int Q = 0; Q < 8; Q++){
                    int newYaxis = ownLocation.getYaxis() + Q;
                    int newXaxis = ownLocation.getXaxis() + Q;
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
            }

            if (i == 2) {
                for(int Q = 0; Q < 8; Q++){
                    int incrementnumber = ownLocation.getXaxis() + Q;
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
            }
            if (i == 3) {
                for(int Q = 0; Q < 8; Q++){
                    int newYaxis = ownLocation.getYaxis() - Q;
                    int newXaxis = ownLocation.getXaxis() + Q;
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
            }
            if (i == 4) {
                for(int Q = 0; Q < 8; Q++){
                    int decrementnumber = ownLocation.getYaxis() - Q;
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
            }
            if (i == 5) {
                for(int Q = 0; Q < 8; Q++){
                    int newYaxis = ownLocation.getYaxis() - Q;
                    int newXaxis = ownLocation.getXaxis() - Q;
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
            }

            if (i == 6) {
                for(int Q = 0; Q < 8; Q++){
                    int decrementnumber = ownLocation.getXaxis() - Q;
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
            }

            if (i == 7) {
                for(int Q = 0; Q < 8; Q++){
                    int newYaxis = ownLocation.getYaxis() + Q;
                    int newXaxis = ownLocation.getXaxis() - Q;
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
        }
        return result;
    }
}
