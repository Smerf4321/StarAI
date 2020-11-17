/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mechanics;

import board.Board;
import board.Spot;
import java.util.List;
import player.Player;
import ships.Ship;

/**
 *
 * @author Patryk
 */
public class Game {
    private Player[] players;
    private Board board;
    private Player currentTurn;
    private GameState state;
    private List<Move> movesThisTurn;
    
    private void initialize (Player p1, Player p2){
        players[0] = p1;
        players[1] = p2;
        
        board = new Board();
        currentTurn = p1;
        movesThisTurn.clear();
    }
   
    public GameState getGameState(){
        return this.state;
    }
    
    public void setGameState(GameState state){
        this.state = state;
    }
    
    public boolean playerMove(Player player, int startX, int startY, int endX, int endY, ActionType actionType){
        Spot startSpot = board.getSpot(startX, startY); 
        
        //checks if there is a ship in teh selected spot
        if (isShipNull (startSpot.getShip())){
            return false;
        }
        
        //checks if it is the players ship and if it is the players turn
        if (!isPlayerValid(startSpot.getShip(), player)){
            return false;
        }
        
        Spot endSpot = board.getSpot(endX, endY); 
        Move move = new Move(player, startSpot, endSpot); 

        switch (actionType) {
            case MOVE:
                return this.makeMove(move, player);
            case ATTACK:
                return this.makeAttack(move, player);
            case REPAIR:
                return this.makeRepair(move, player);
            default:
                return false;
        }
    }
    
    private boolean makeMove(Move move, Player player) 
    { 
        Ship sourceShip = move.getStart().getShip(); 
         
        // is the destination in range 
        if (!isInRange(move, sourceShip.getMovementRange())) { 
            return false; 
        } 
  
        // is the destination empty
        if (isSpotEmpty(move.getEnd())) { 
            return false;
        } 
  
        // store the move 
        movesThisTurn.add(move); 
  
        // move piece from the start spot to end spot 
        move.getEnd().setShip(move.getStart().getShip());
        move.getStart().setShip(null); 
  
        return true; 
    } 
    
    private boolean makeAttack (Move move, Player player){
        Ship sourceShip = move.getStart().getShip(); 
  
        // is the target in range 
        if (!isInRange(move, sourceShip.getWeaponsRange())) { 
            return false; 
        }
        
        // is there a target
        if (!isSpotEmpty(move.getEnd())) { 
            return false;
        } 
        
        //store the move
        movesThisTurn.add(move); 
        
        // assign damage to the target
        move.getEnd().getShip().damage(move.getStart().getShip().getWeaponsDamage());
        
        return true;
    }
    
    private boolean makeRepair (Move move, Player player){
        Ship sourceShip = move.getStart().getShip();  
        
        // is the target in range 
        if (!isInRange(move, sourceShip.getWeaponsRange())) { 
            return false; 
        } 
        
        // is there a target in the sot and if it belongs to the player
        if ((!isSpotEmpty(move.getEnd())) || (!move.getEnd().getShip().isComputer() == player.isComputer())) { 
            return false;
        } 
        
        //store the move
        movesThisTurn.add(move); 
        
        //assign repair to the target
        move.getEnd().getShip().repair(move.getStart().getShip().getWeaponsDamage());
        
        return true;
    }
    
    private boolean isShipNull(Ship ship){
        return ship == null;
    }
    
    private boolean isPlayerValid(Ship ship, Player player){
        return ((player == currentTurn) || (player.isComputer() == ship.isComputer()));
    }
    
    //TODO add more proper pathfinding
    private boolean isInRange (Move move, int range){
        return (Ship.isSpotinRange(board, move.getStart(),move.getEnd(), range));
    }
    
    private boolean isSpotEmpty (Spot spot){
        return spot.getShip() == null;
    }
}
