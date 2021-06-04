package player;

import board.Board;
import board.Spot;
import mechanics.Move;
import mechanics.Game;
import mechanics.MoveType;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import ships.Carrier;
import ships.Ship;

/**
 * This is a concrete implementation of a human player
 * @author Patryk
 */
public class HumanPlayer extends Player{
    
    private Board board;
    private Ship currentShip;
    private boolean mouseButton0Pressed = false;
    private boolean mouseButton1Pressed = false;
    private boolean keyboardButtonPressed = false;
    
    public HumanPlayer(Board board, Game game){
        this.computer = false;
        this.board = board;
        this.game = game;
    }
    
    /**
     * Used to catch mouse presses
     */
    @Override
    public void Update(){
        //Button to pass the turn. Currently can be used to pass computers turn
        if (Keyboard.isKeyDown(Keyboard.KEY_SPACE) && !keyboardButtonPressed){
            game.endTurn();
        }
        keyboardButtonPressed = Keyboard.isKeyDown(Keyboard.KEY_SPACE);
        
        if (game.isTurnMine(1)){
            //Left Mouse button
            //Checks if theere is a ship at the clicked coordinates and saves it
            if (Mouse.isButtonDown(0) && !mouseButton0Pressed){
                Ship clickedShip = board.getShipAt((int) Math.floor(Mouse.getX() / 128), (int) Math.floor((board.getHeight()*128 - Mouse.getY() - 1) / 128));

                if (clickedShip != null && !clickedShip.isComputer()){
                    currentShip = clickedShip;
                }
            }

            //Right mouse button
            else if (Mouse.isButtonDown(1) && !mouseButton1Pressed && currentShip != null){
                int x = (int) Math.floor(Mouse.getX() / 128);
                int y = (int) Math.floor((board.getHeight()*128 - Mouse.getY() - 1) / 128);
                
                Spot targetSpot = board.getSpot(x, y);
                Ship clickedShip = board.getShipAt(x, y);

                //Checks if the selected spot is in movement range
                if ((clickedShip == null || clickedShip.isKilled())){
                    if (isInRange
                        (targetSpot.getX(), 
                        targetSpot.getY(), 
                        currentShip.spot.getX(), 
                        currentShip.spot.getY(),  
                        currentShip.getMovementRange())){
                        
                        currentShip.spot = targetSpot;
                        
                        endTurn();
                    }
                }

                //Checks if the ship in the targeted spot is controlled by enemy and is in range of weapons
                //and if the currently selected ship can attack
                else if (clickedShip.isComputer() 
                        && !clickedShip.isKilled()
                        && currentShip.getCanAttack()
                        && isInRange
                        (targetSpot.getX(), 
                        targetSpot.getY(), 
                        currentShip.spot.getX(), 
                        currentShip.spot.getY(),  
                        currentShip.getWeaponsRange())){
                    
                    //Damages the enemy ship and checks if that ship is killed then removes it
                    clickedShip.damage(currentShip.getWeaponsDamage());
                    
                    endTurn();
                }

                //Checks if the ship in the selected spot is controlled by friendly and is in range of repair
                //and if the currently selected ship can repair
                else if (!clickedShip.isComputer() 
                        && !clickedShip.isKilled()
                        && currentShip.getCanRepair()
                        && !(clickedShip instanceof Carrier)
                        && clickedShip.getHealth() < clickedShip.getMaxHealth()
                        && isInRange
                        (targetSpot.getX(), 
                        targetSpot.getY(), 
                        currentShip.spot.getX(), 
                        currentShip.spot.getY(),  
                        currentShip.getRepairRange())){
                    
                    //Repairs the targeted ship
                    clickedShip.repair(currentShip.getRepair());
                    
                    endTurn();
                }
                
            }
            mouseButton0Pressed = Mouse.isButtonDown(0);
            mouseButton1Pressed = Mouse.isButtonDown(1);
        }
    }
    
    public void endTurn(){
        currentShip = null;
        game.endTurn();
    }
    
    /**
     * Returns the player as a string description
     * @return name of player
     */
    @Override
    public String toString(){
        return "HumanPlayer";
    }
}
