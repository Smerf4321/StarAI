package ships;

import static GUIHelpers.DrawHelper.DrawQuadTexture;
import static GUIHelpers.DrawHelper.QuickTextureLoad;
import GUIHelpers.Drawable;
import board.Spot;
import org.newdawn.slick.opengl.Texture;

/**
 * This is an abstract class that defines common features of each ship
 * @author Patryk
 */
public abstract class Ship implements Drawable{
    public boolean killed = false;
    private boolean computer = false;
    public Spot spot;
    private final int maxHealth;
    private int currentHealth;
    private final int movementRange;
    private final int weaponsRange;
    private final int weaponsDamage;
    private String shipTextureName;
    private Texture shipTexture;
    public int value;
    
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
     * @param movementRange maximum range for movement in spots
     * @param weaponsRange maximum range for attacks in spots 
     * @param shipTextureName name of the ships texture
     * @param spot spot where the ship is
     */
    public Ship (boolean computer, int maxHealth, int weaponsDamage, boolean canAttack, boolean canRepair, int movementRange, int weaponsRange, String shipTextureName, Spot spot, int value){
        this.computer = computer;
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.movementRange = movementRange;
        this.weaponsRange = weaponsRange;
        this.weaponsDamage = weaponsDamage;
        this.canAttack = canAttack;
        this.canRepair = canRepair;
        this.shipTextureName = shipTextureName;
        this.spot = spot;
        this.value = value;
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
    
    public int getMaxHealth(){
        return maxHealth;
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
        if (damage > 0){
            currentHealth -= damage;
            if (currentHealth <= 0){
                killed = true;
            }
        }
    }
    
    /**
     * Increases the health of the ship by specified amount
     * @param repair int repair done on this ship
     */
    public void repair (int repair){
        if (repair > 0){
            currentHealth += repair;
            if (currentHealth > maxHealth){
                currentHealth = maxHealth;
            }
        }
    }
    
    /**
     * Loads in the texture of the ship into memory
     */
    public void loadTexture(){
        this.shipTexture = QuickTextureLoad(shipTextureName);
    }
    
    /**
     * Draws the ship at the coordinates of it's spot
     */
    public void Draw(){
        DrawQuadTexture(shipTexture, spot.getX(), spot.getY(), 128, 128);
    }
    
    /**
     * Returns the name of the ship as string
     * @return name of the ship
     */
    @Override
    public String toString(){
        return shipTextureName;
    }
}
