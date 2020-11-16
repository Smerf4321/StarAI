/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ships;

import board.Board;
import board.Spot;

/**
 * This is an abstract class that defines common features of each ship
 * @author Patryk
 */
public abstract class Ship {
    private boolean killed = false;
    private boolean computer = false;
    private final int maxHealth;
    private int currentHealth;
    private int movementRange;
    private int weaponsRange;
    
    //boolean that define whether the ship have acted this turn
    private boolean haveActed = false;
    
    /**
     * Abstract constructor for ship
     * @param computer is ship controlled by computer
     * @param maxHealth max health of the ship
     */
    public Ship (boolean computer, int maxHealth){
        this.computer = computer;
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
    }
    
    public void setComputer (boolean computer){
        this.computer = computer;
    }
    
    public int getHealth(){
        return currentHealth;
    }
    
    public boolean damage (int damage){
        currentHealth -= damage;
        return currentHealth <= 0;
    }
    
    public void repair (int repair){
        currentHealth += repair;
        if (currentHealth > maxHealth){
            currentHealth = maxHealth;
        }
    }
    
    private boolean isSpotinRange (Board board, Spot start, Spot end, int range){
        int x = Math.abs(start.getX() - end.getX()); 
        int y = Math.abs(start.getY() - end.getY());
        return x+y <= range;
    }
}
