package mechanics;

import board.Board;
import board.Spot;
import java.util.List;
import player.Player;
import ships.Carrier;
import ships.Ship;

/**
 * Game class defines a singular game
 * @author Patryk
 */
public class Game {
    private Player[] players;
    private Board board;
    private Player currentTurn;
    private GameState state;
    private List<Action> movesThisTurn;
    
    /**
     * Function that creates the environment for a new game
     * @param p1 Player 1
     * @param p2 Player 2
     */
    private void initialize (Player p1, Player p2){
        players[0] = p1;
        players[1] = p2;
        
        board = new Board();
        currentTurn = p1;
        movesThisTurn.clear();
    }
   
    /**
     * 
     * @return state of the game
     */
    public GameState getGameState(){
        return this.state;
    }
    
    /**
     * Changes the state of the game
     * @param state new game state for the game
     */
    public void setGameState(GameState state){
        this.state = state;
    }
    
    /**
     * Calls functions responsible for specific player actions
     * @param player The player 
     * @param startX x-coord of the starting point
     * @param startY y-coord of the starting point
     * @param endX x-coord of the end point (target)
     * @param endY y-coord of the end point (target
     * @param actionType MOVE, ATTACK, REPAIR
     * @return returns whether the action was possible/successful
     */
    public boolean playerMove(Player player, int startX, int startY, int endX, int endY, ActionType actionType){
        Spot startSpot = board.getSpot(startX, startY); 
        
        //checks if there is a ship in the selected spot
        if (isShipNull (startSpot.getShip())){
            return false;
        }
        
        //checks if the ship has initiative e.g. can act this turn
        if (!startSpot.getShip().getInitiative()){
            return false;
        }
        
        //checks if it is the players ship and if it is the players turn
        if (!isPlayerValid(startSpot.getShip(), player)){
            return false;
        }
        
        Spot endSpot = board.getSpot(endX, endY); 
        Action move = new Action(player, startSpot, endSpot); 

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
    
    /**
     * Function that defines Action functionality for a ship
     * @param move Action class of a specific move
     * @param player Player doing the move
     * @return whether it was possible/successful
     */
    private boolean makeMove(Action move, Player player) 
    { 
        Ship startShip = move.getStart().getShip(); 
         
        // is the destination in range 
        if (!isInRange(move, startShip.getMovementRange())) { 
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
        
        // set the ship as already activated
        startShip.spendInitiative();
  
        return true; 
    } 
    
    //TODO add win condition 
    /**
     * Function that defines Attack functionality for a ship
     * @param move Action class of a specific move
     * @param player Player doing the move
     * @return whether it was possible/successful
     */
    private boolean makeAttack (Action move, Player player){
        Ship startShip = move.getStart().getShip(); 
        Ship endShip = move.getEnd().getShip();
  
        //can the ship attack
        if (!startShip.getCanAttack()){
            return false;
        }
        
        // is the target in range 
        if (!isInRange(move, startShip.getWeaponsRange())) { 
            return false; 
        }
        
        // is there a target
        if (!isSpotEmpty(move.getEnd())) { 
            return false;
        } 
        
        //store the move
        movesThisTurn.add(move); 
        
        // assign damage to the target
        endShip.damage(startShip.getWeaponsDamage());
        
        // set the ship as already activated
        startShip.spendInitiative();
        
        return true;
    }
    
    /**
     * Function that defines Repair functionality for a ship
     * @param move Action class of a specific move
     * @param player Player doing the move
     * @return whether it was possible/successful
     */
    private boolean makeRepair (Action move, Player player){
        Ship startShip = move.getStart().getShip();  
        Ship endShip = move.getEnd().getShip();
        
        //can the ship repair
        if (!startShip.getCanRepair()){
            return false;
        }
        
        // is the target in range 
        if (!isInRange(move, startShip.getWeaponsRange())) { 
            return false; 
        } 
        
        // is there a target in the spot and if it belongs to the player
        if ((!isSpotEmpty(move.getEnd())) || (!move.getEnd().getShip().isComputer() == player.isComputer())) { 
            return false;
        } 
        
        //store the move
        movesThisTurn.add(move); 
        
        //assign repair to the target
        endShip.repair(startShip.getWeaponsDamage());
        
        // set the ship as already activated
        startShip.spendInitiative();
        
        return true;
    }
    
    //tells you whether ship actually exists
    private boolean isShipNull(Ship ship){
        return ship == null;
    }
    
    //checks if it is the players turn and if the ship doing an action belongs to the player
    private boolean isPlayerValid(Ship ship, Player player){
        return ((player == currentTurn) || (player.isComputer() == ship.isComputer()));
    }
    
    //TODO add more proper pathfinding, so that ships block movement
    private boolean isInRange (Action move, int range){
        return (Ship.isSpotinRange(board, move.getStart(),move.getEnd(), range));
    }
    
    //checks if the Spot has no ship on it
    private boolean isSpotEmpty (Spot spot){
        return spot.getShip() == null;
    }
    
    /**
     * this function evaluates the state of the game, e.g. checks win conditions
     * @return the current state of the game
     */
    private GameState evaluateGameState(){
        List<Ship> ships = board.getShipList();
        for (Ship ship : ships){
            //checks if either of the carriers are destroyed and changes gamestate accrdingly
            if ((ship instanceof Carrier) && ship.isKilled()){
                if (ship.isComputer()){
                    return GameState.HUMAN_WIN;
                }
                else {
                    return GameState.COMPUTER_WIN;
                }
            }
            
            //checks if all ships, other than Carriers have been killed and returns a Draw
            if (!ship.isKilled() && !(ship instanceof Carrier)){
                return GameState.ACTIVE;
            }
            else {
                return GameState.DRAW;
            }
        }
        return GameState.ACTIVE;
    }
}
