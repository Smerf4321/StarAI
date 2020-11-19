package mechanics;

import board.Spot;
import player.Player;
import ships.Ship;

/**
 * This class defines a Action in game. E.g. one ship taking a singular action in a game
 * @author Patryk
 */
public class Action {
    private Player player;
    private Spot start;
    private Spot end;
    private Ship shipMoved;
    private Ship shipKilled;
    
    /**
     * Constructor for an Action
     * @param player Player taking an action
     * @param start Spot at which the action starts 
     * @param end Spot at which the action ends
     */
    public Action(Player player, Spot start, Spot end){
        this.player = player; 
        this.start = start; 
        this.end = end; 
        this.shipMoved = start.getShip(); 
    }
    
    public Spot getStart(){
        return this.start;
    }
    
    public Spot getEnd(){
        return this.end;
    }
}
