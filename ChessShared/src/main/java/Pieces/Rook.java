package Pieces;

import Enums.PieceType;
import Enums.PlayerColor;
import data.Board;
import data.Coordinate;
import data.Piece;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {

    public Rook(PieceType p, int X, int Y, PlayerColor c, Board b) {
        super(p,X,Y,c,b);
    }

    @Override
    public List<Coordinate> getAvailableMoves(){//todo THIS CODE NEEDS WORK. OTHER PRIORITIES FIRST
        List<Coordinate> result = new ArrayList<>();
        for(int i = 0; i < 4; i++){ //this for loop is for direction. 0 being north, 1 being east, 2 being south, 3 being west.
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

            if (i == 2) {
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


            if (i == 3) {
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
        }
        return result;
















        /*
        List<Coordinate> result = new ArrayList<>();
        boolean XaxisPossible = true;
        boolean YaxisPossible = true;
        for(int x = 0; x < 8; x++){
            for(int y = 0; y < 8; y++){
                    if(ownLocation.getXaxis() == x && XaxisPossible){ // Xaxis line
                        Coordinate tempcoord = board.getCoordinate(x,y);
                        if(tempcoord.hasPiece()){// piece on Xaxis
                            if(ownLocation.getYaxis() != y){ // not itself
                                XaxisPossible = false;
                                result.add(tempcoord);
                            }
                        }
                        else{
                            result.add(tempcoord);
                        }
                    }

                    if(ownLocation.getYaxis() == y && YaxisPossible){ // Yaxis line
                        Coordinate tempcoord = board.getCoordinate(x,y);
                        if(tempcoord.hasPiece()){// piece on Xaxis
                            if(ownLocation.getXaxis() != x){
                                YaxisPossible = false;
                                result.add(tempcoord);
                            }
                        }
                        else{
                            result.add(tempcoord);
                        }
                    }
                }
            }
        return result;*/
    }

}
