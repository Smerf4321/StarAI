package ships;

import board.Spot;

/**
 * This is a concrete implementation of Cruiser which extends Ship
 * @author Patryk
 */
public class Cruiser extends Ship{
    
    static int maxHealth = 2;
    static int movementRange = 2;
    static int weaponsRange = 2;
    static int damage = 1;
    static boolean canAttack = false;
    static boolean canRepair = true;
    static String shipTexture;
    
    /**
     * Cruiser constructor
     * @param computer whether the ship is controlled by a computer
     * @param spot spot where the ship is
     */
    public Cruiser(boolean computer, Spot spot) {
        super(computer, Cruiser.maxHealth, Cruiser.damage, canAttack, canRepair, Cruiser.movementRange, Cruiser.weaponsRange, getTexture(computer), spot);
        
    }
    
    private static String getTexture(boolean computer){
        if (computer){
            return "ECruiser128";
        }
        else {
            return "PCruiser128";
        }
    }

}
