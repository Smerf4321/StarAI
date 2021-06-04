package ships;

import board.Spot;

/**
 * This is a concrete implementation of Fighter which extends Ship
 * @author Patryk
 */
public class Fighter extends Ship{

    static int maxHealth = 3;
    static int movementRange = 3;
    static int weaponsRange = 1;
    static int repairRange = 0;
    static int damage = 3;
    static int repair = 0;
    static boolean canAttack = true;
    static boolean canRepair = false;
    static int value = 2;

    /**
     * Fighter constructor
     * @param computer whether the ship is controlled by a computer
     * @param spot spot where the ship is
     */
    public Fighter(boolean computer, Spot spot) {
        super(computer, Fighter.maxHealth, Fighter.damage, Fighter.repair, canAttack, canRepair, Fighter.movementRange, Fighter.weaponsRange, Fighter.repairRange, getTexture(computer), spot, value);
    }
    
    /**
     * Returns the texture based on the player controlling the ship
     * @param computer whether the ship belongs to the computer
     * @return texture name as string
     */
    private static String getTexture(boolean computer){
        if (computer){
            return "EFighters128";
        }
        else {
            return "PFighters128";
        }
    }
}
