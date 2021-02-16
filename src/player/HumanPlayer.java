package player;

import board.Board;
import board.Spot;
import org.lwjgl.input.Mouse;

/**
 * This is a concrete implementation of a human player
 * @author Patryk
 */
public class HumanPlayer extends Player{
    
    private Board board;
    private Spot currentSpot;
    private boolean mouseButton0Pressed = false;
    private boolean mouseButton1Pressed = false;
    
    public HumanPlayer(Board board, boolean isTurn){
        this.computer = false;
        this.isTurn = isTurn;
        this.board = board;
    }
    
    @Override
    public void Update(){
        //Left Mouse button
        //Checks if the clicked spot contains a valid ship and saves that spot
        if (Mouse.isButtonDown(0) && !mouseButton0Pressed){
            Spot clickedSpot = board.getSpot((int) Math.floor(Mouse.getX() / 128), (int) Math.floor((board.getHeight()*128 - Mouse.getY() - 1) / 128));
            
            if (clickedSpot.getShip() != null && !clickedSpot.getShip().isComputer() && isTurn){
                currentSpot = clickedSpot;       
                System.out.println(clickedSpot.getShip().getShipTexture());
            }
        }
        
        //Right mouse button
        else if (Mouse.isButtonDown(1) && !mouseButton1Pressed && currentSpot != null){
            Spot targetSpot = board.getSpot((int) Math.floor(Mouse.getX() / 128), (int) Math.floor((board.getHeight()*128 - Mouse.getY() - 1) / 128));
            
            //Checks if the selected spot is in movement range
            if (targetSpot.getShip() == null){
                if (isInRange
                    (Math.round(targetSpot.getX()/128), 
                    Math.round(targetSpot.getY()/128), 
                    Math.round(currentSpot.getX()/128), 
                    Math.round(currentSpot.getY()/128),  
                    currentSpot.getShip().getMovementRange())){
                    
                targetSpot.setShip(currentSpot.getShip());
                currentSpot.removeShip();
                }
            }
            
            //Checks if the ship in the targeted spot is controlled by enemy and is in range of weapons
            //and if the currently selected ship can attack
            else if (targetSpot.getShip().isComputer() 
                    && currentSpot.getShip().getCanAttack()
                    && isInRange
                    (Math.round(targetSpot.getX()/128), 
                    Math.round(targetSpot.getY()/128), 
                    Math.round(currentSpot.getX()/128), 
                    Math.round(currentSpot.getY()/128),  
                    currentSpot.getShip().getWeaponsRange())){
                
                //Damages the enemy ship and checks if that ship is killed then removes it
                targetSpot.getShip().damage(currentSpot.getShip().getWeaponsDamage());
                if (targetSpot.getShip().isKilled()){
                    targetSpot.removeShip();
                }
            }
            
            //Checks if the ship in the selected spot is controlled by friendly and is in range of repair
            //and if the currently selected ship can repair
            else if (!targetSpot.getShip().isComputer() 
                    && targetSpot.getShip().getCanRepair()
                    && isInRange
                    (Math.round(targetSpot.getX()/128), 
                    Math.round(targetSpot.getY()/128), 
                    Math.round(currentSpot.getX()/128), 
                    Math.round(currentSpot.getY()/128),  
                    currentSpot.getShip().getWeaponsRange())){
                
                //Repairs the targeted ship
                targetSpot.getShip().repair(currentSpot.getShip().getWeaponsDamage());
            }
            currentSpot = null;
        }
        
        mouseButton0Pressed = Mouse.isButtonDown(0);
        mouseButton1Pressed = Mouse.isButtonDown(1);
    }
    
    private boolean isInRange(int targetX, int targetY, int currentX, int currentY, int range){
        return (Math.abs(targetX - currentX) + Math.abs(targetY - currentY) <= range);
    }
}
