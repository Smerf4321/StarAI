package mechanics;

import board.Spot;
import player.Player;
import ships.Ship;

/**
 * This class defines a Move in game. E.g. one ship taking a singular action in a game
 * @author Patryk
 */
public class Move {
    private Player player;
    private Spot start;
    private Spot end;
    private Ship ship;
    private MoveType type;
    
    /**
     * Constructor for an Action
     * @param player Player taking an action
     * @param start Spot at which the action starts 
     * @param end Spot at which the action ends
     */
    public Move(Player player, Spot start, Spot end, Ship ship, MoveType type){
        this.player = player; 
        this.start = start; 
        this.end = end; 
        this.ship = ship;
        this.type = type;
    }
    
    @Override
    public String toString(){
        String s = player.toString() + start.toString() + end.toString() + ship.toString() + type.name();
        return s;
    }
}
