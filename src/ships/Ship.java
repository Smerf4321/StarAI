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
    private boolean killed = false;
    private boolean computer = false;
    private Spot spot;
    private final int maxHealth;
    private int currentHealth;
    private final int movementRange;
    private final int weaponsRange;
    private final int weaponsDamage;
    private String shipTextureName;
    private Texture shipTexture;
    
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
     */
    public Ship (boolean computer, int maxHealth, int weaponsDamage, boolean canAttack, boolean canRepair, int movementRange, int weaponsRange, String shipTextureName){
        this.computer = computer;
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.movementRange = movementRange;
        this.weaponsRange = weaponsRange;
        this.weaponsDamage = weaponsDamage;
        this.canAttack = canAttack;
        this.canRepair = canRepair;
        this.shipTextureName = shipTextureName;
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
    
    public void loadTexture(){
        this.shipTexture = QuickTextureLoad(shipTextureName);
    }
    
    public void Draw(float x, float y){
        DrawQuadTexture(shipTexture, x, y, 128, 128);
    }
}
