package ships;

import board.Spot;

/**
 * This is a concrete implementation of Carrier which extends Ship
 * @author Patryk
 */
public class Carrier extends Ship{
    static int maxHealth = 9;
    static int movementRange = 1;
    static int weaponsRange = 1;
    static int repairRange = 2;
    static int damage = 3;
    static int repair = 1;
    static boolean canAttack = true;
    static boolean canRepair = true;
    static String shipTexture;
    static int value = 10;
    static HealthBar hpBar = new HealthBar();
    
    /**
     * Carrier constructor
     * @param computer whether the ship is controlled by a computer
     * @param spot spot where the ship is
     */
    public Carrier(boolean computer, Spot spot) {
        super(computer,
                Carrier.maxHealth,
                Carrier.damage,
                Carrier.repair,
                canAttack,
                canRepair,
                Carrier.movementRange,
                Carrier.weaponsRange,
                Carrier.repairRange,
                getTexture(computer),
                spot, 
                value, 
                hpBar);
    }
    
    
    /**
     * Returns the texture based on the player controlling the ship
     * @param computer whether the ship belongs to the computer
     * @return texture name as string
     */
    private static String getTexture(boolean computer){
        if (computer){
            return "ECarrier128";
        }
        else {
            return "PCarrier128";
        }
    }
}
