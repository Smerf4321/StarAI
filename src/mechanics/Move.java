package mechanics;

import board.Spot;
import ships.Ship;

/**
 * This class defines a Move in game. E.g. one ship taking a singular action in a game
 * @author Patryk
 */
public class Move {
    private boolean isComputer;
    public Spot start;
    public Spot end;
    public Ship ship;
    public Ship target;
    public MoveType type;
    
    /**
     * Dummy object, should never be initialised
     */
    public Move(){
        
    }
    
    /**
     * Constructor for an Action
     * @param player Player taking an action
     * @param start Spot at which the action starts 
     * @param end Spot at which the action ends
     */
    public Move(boolean isComputer, Spot start, Spot end, Ship ship, Ship target, MoveType type){
        this.isComputer = isComputer; 
        this.start = start; 
        this.end = end; 
        this.ship = ship;
        this.target = target;
        this.type = type;
    }
    
    /**
     * Returns the move as a string description
     * @return move as a string
     */
    @Override
    public String toString(){
        String s = isComputer + ", " + start.toString() + ", " + end.toString() + ", " + ship.toString() + ", " + type.name();
        return s;
    }
}
