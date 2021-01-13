package data;

public class Coordinate {
    int Xaxis;
    int Yaxis;
    Piece piece;

    public Coordinate(int X,int Y){
        Xaxis = X;
        Yaxis = Y;
    }

    public int getXaxis() {
        return Xaxis;
    }

    public int getYaxis() {
        return Yaxis;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;
    }
    public Boolean hasPiece(){
        if(piece != null){
            return true;
        }
        return false;
    }
    public void removePiece(){
        piece = null;
    }
}
