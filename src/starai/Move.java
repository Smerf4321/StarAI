/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package starai;

import board.Spot;
import player.Player;
import ships.Ship;

/**
 *
 * @author Patryk
 */
public class Move {
    private Player player;
    private Spot start;
    private Spot end;
    private Ship shipMoved;
    private Ship shipKilled;
    
    public Move(Player player, Spot start, Spot end){
        this.player = player; 
        this.start = start; 
        this.end = end; 
        this.shipMoved = start.getShip(); 
    }
}
