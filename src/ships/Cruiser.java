package ships;

import board.Spot;

/**
 * This is a concrete implementation of Cruiser which extends Ship
 * @author Patryk
 */
public class Cruiser extends Ship{
    
    static int maxHealth = 6;
    static int movementRange = 2;
    static int weaponsRange = 2;
    static int repairRange = 0;
    static int damage = 2;
    static int repair = 0;
    static boolean canAttack = true;
    static boolean canRepair = false;
    static String shipTexture;
    static int value = 5;
    
    /**
     * Cruiser constructor
     * @param computer whether the ship is controlled by a computer
     * @param spot spot where the ship is
     */
    public Cruiser(boolean computer, Spot spot) {
        super(computer, Cruiser.maxHealth, Cruiser.damage, Cruiser.repair, canAttack, canRepair, Cruiser.movementRange, Cruiser.weaponsRange, Cruiser.repairRange, getTexture(computer), spot, value);
        
    }
    
    /**
     * Returns the texture based on the player controlling the ship
     * @param computer whether the ship belongs to the computer
     * @return texture name as string
     */
    private static String getTexture(boolean computer){
        if (computer){
            return "ECruiser128";
        }
        else {
            return "PCruiser128";
        }
    }

}
