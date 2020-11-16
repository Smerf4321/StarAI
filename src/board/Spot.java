/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package board;

import ships.Ship;

/**
 * This class defines a spot on the board
 * @author Patryk
 */
public class Spot {
    private Ship ship;
    private int x;
    private int y;
   
    /**
     * This is a constructor for a Spot on a Board
     * @param ship Ship on the Spot, can be null
     * @param x how far is the spot along the x-axis
     * @param y how far is the spot along y-axis
     */
    public Spot (int x, int y, Ship ship){
        this.ship = ship;
        this.x = x;
        this.y = y;
    }
    
    public Ship getShip(){
        return ship;
    }
    
    /**
     * Returns where the Spot is on the x-axis
     * @return integer
     */
    public int getX(){
        return x;
    }
    
    /**
     * Return where the Spot is on the y-axis
     * @return integer
     */
    public int getY(){
        return y;
    }
}
