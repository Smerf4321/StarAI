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
    static int damage = 2;
    static boolean canAttack = true;
    static boolean canRepair = false;
    static String shipTexture;
    static int value = 5;
    
    /**
     * Cruiser constructor
     * @param computer whether the ship is controlled by a computer
     * @param spot spot where the ship is
     */
    public Cruiser(boolean isPlayer2, Spot spot) {
        super(isPlayer2, Cruiser.maxHealth, Cruiser.damage, canAttack, canRepair, Cruiser.movementRange, Cruiser.weaponsRange, getTexture(isPlayer2), spot, value);
        
    }
    
    /**
     * Returns the texture based on the player controlling the ship
     * @param computer whether the ship belongs to the computer
     * @return texture name as string
     */
    private static String getTexture(boolean isPlayer2){
        if (isPlayer2){
            return "ECruiser128";
        }
        else {
            return "PCruiser128";
        }
    }

}
