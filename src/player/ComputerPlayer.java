package player;

import board.Board;
import board.Spot;
import java.util.ArrayList;
import mechanics.Game;
import ships.Ship;

/**
 * This is a concrete implementation of a computer controller player
 * @author Patryk
 */
public class ComputerPlayer extends Player{
    
    private Board board;
    private Spot currentSpot;
    
    public ComputerPlayer(Board board, Game game){
        this.computer = true;
        this.board = board;
        this.game = game;
    }

    @Override
    public void Update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public ArrayList getAllMoves (){
        ArrayList allMoves;
        
        for (Ship Ship: board.getShipList()){
            
        }
        
        return allMoves;
    }
    
    
}
