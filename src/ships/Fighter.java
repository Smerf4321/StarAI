package ships;

import board.Spot;

/**
 * This is a concrete implementation of Fighter which extends Ship
 * @author Patryk
 */
public class Fighter extends Ship{

    static int maxHealth = 1;
    static int movementRange = 3;
    static int weaponsRange = 1;
    static int damage = 1;
    static boolean canAttack = true;
    static boolean canRepair = false;
    static String shipTexture;

    /**
     * Fighter constructor
     * @param computer whether the ship is controlled by a computer
     */
    public Fighter(boolean computer) {
        super(computer, Fighter.maxHealth, Fighter.damage, canAttack, canRepair, getTexture(computer));
    }
    
    private static String getTexture(boolean computer){
        if (computer){
            return "EFighters128";
        }
        else {
            return "PFighters128";
        }
    }
}
