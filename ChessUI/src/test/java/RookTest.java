import Enums.PieceType;
import Enums.PlayerColor;
import Pieces.Pawn;
import Pieces.Rook;
import data.Board;
import data.Coordinate;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;

public class RookTest {
    public Board b = new Board(new ArrayList<>());
    public Rook r = new Rook(PieceType.ROOK,0,0, PlayerColor.WHITE,b);
    @Test
    public void rookCanMoveOnXaxis(){
           List<Coordinate> result =  r.getAvailableMoves();
           for(Coordinate c : result){
               if(c.getYaxis() == r.getOwnLocation().getYaxis() && c.getXaxis() == r.getOwnLocation().getXaxis() + 1){
                   assert(true);
               }
           }
        }
        @Test
    public void RookCanMoveOnYaxis(){
            List<Coordinate> result =  r.getAvailableMoves();
            for(Coordinate c : result){
                if(c.getXaxis() == r.getOwnLocation().getXaxis() && c.getYaxis() == r.getOwnLocation().getYaxis() + 1){
                    assert(true);
                }
            }
        }
        @Test
    public void rookCannotMoveDiagonally(){
            List<Coordinate> result =  r.getAvailableMoves();
            for(Coordinate c : result){
                if(c.getXaxis() == 1 && c.getYaxis() == 1){ //start at 0.0, 1,1 would be diagonally.
                    assert(false);
                }
            }
            assert(true);
    }
    @Test
    public void RookCannotPassOverUnits(){
        Pawn p = new Pawn(PieceType.PAWN,0,1,PlayerColor.WHITE,b); //obstructing piece
        List<Coordinate> result =  r.getAvailableMoves();
        for(Coordinate c : result){
            if(c.getXaxis() ==  r.getOwnLocation().getXaxis() && c.getYaxis() == 2){ //the pawn is standing at 0.1, so that means that 0.2 would not be available.
                assert(false);
            }
        }
        assert(true);
    }
}
