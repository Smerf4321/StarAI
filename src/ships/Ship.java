package ships;

import static GUIHelpers.DrawHelper.DrawQuadTexture;
import static GUIHelpers.DrawHelper.QuickTextureLoad;
import GUIHelpers.Drawable;
import board.Board;
import board.Spot;

/**
 * This is an abstract class that defines common features of each ship
 * @author Patryk
 */
public abstract class Ship implements Drawable{
    private boolean killed = false;
    private boolean computer = false;
    private Spot spot;
    private final int maxHealth;
    private int currentHealth;
    private int movementRange;
    private int weaponsRange;
    private final int weaponsDamage;
    private final String shipTexture;
    
    //boolean that define whether the ship have acted this turn
    private boolean initiative = false;
    
    private final boolean canAttack;
    private final boolean canRepair;
    
    /**
     * Abstract constructor for ship
     * @param computer is ship controlled by computer
     * @param maxHealth max health of the ship
     * @param weaponsDamage damage dealt by the ship
     * @param canAttack defines whether the ship can take attack action
     * @param canRepair defines whether the ship can take repair action
     * @param shipTexture texture of the ship
     * @param spot pointer to the spot the ship is on
     */
    public Ship (boolean computer, int maxHealth, int weaponsDamage, boolean canAttack, boolean canRepair, String shipTexture, Spot spot){
        this.computer = computer;
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.weaponsDamage = weaponsDamage;
        this.canAttack = canAttack;
        this.canRepair = canRepair;
        this.shipTexture = shipTexture;
        this.spot = spot;
    }
    
    @Override
    public void Draw(){
        DrawQuadTexture(QuickTextureLoad(shipTexture), spot.getX(), spot.getY(), 128, 128);
    }
    
    public void setComputer (boolean computer){
        this.computer = computer;
    }
    
    public boolean isComputer (){
        return computer;
    }
    
    public int getHealth(){
        return currentHealth;
    }
    
    public int getMovementRange(){
        return movementRange;
    }
    
    public int getWeaponsRange(){
        return weaponsRange;
    }
    
    public int getWeaponsDamage(){
        return weaponsDamage;
    }
    
    public boolean getInitiative(){
        return initiative;
    }
    
    public void spendInitiative(){
        initiative = true;
    }
    
    public void regainInitiative(){
        initiative = false;
    }
    
    public boolean getCanAttack(){
        return canAttack;
    }
    
    public boolean getCanRepair(){
        return canRepair;
    }
    
    public boolean isKilled(){
        return killed;
    }
    
    /**
     * Decreases the health of the ship by specified amount
     * @param damage int damage dealt to this ship
     */
    public void damage (int damage){
        currentHealth -= damage;
        if (currentHealth <= 0){
            killed = true;
        }
    }
    
    /**
     * Increases the health of the ship by specified amount
     * @param repair int repair done on this ship
     */
    public void repair (int repair){
        currentHealth += repair;
        if (currentHealth > maxHealth){
            currentHealth = maxHealth;
        }
    }
    
    /**
     * Function that checks if the starting spot is in range of a specified spot
     * @param board Board class, e.g. current game board
     * @param start starting Spot for the calculation
     * @param end ending Spot for the calculation
     * @param range int limit (movement range etc) 
     * @return whether Spot End is in specified range from Spot Start
     */
    public static boolean isSpotinRange (Board board, Spot start, Spot end, int range){
        float x = Math.abs(start.getX() - end.getX()); 
        float y = Math.abs(start.getY() - end.getY());
        return x+y <= range;
    }
    
    /**
     * Returns the texture name of the ship
     * @return string shipTexture
     */
    public String getShipTexture(){
        return shipTexture;
    }
}
