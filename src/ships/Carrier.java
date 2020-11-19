package ships;

/**
 * This is a concrete implementation of Carrier which extends Ship
 * @author Patryk
 */
public class Carrier extends Ship{
    static int maxHealth = 3;
    static int movementRange = 1;
    static int weaponsRange = 2;
    static int damage = 1;
    
    /**
     * Carrier constructor
     * @param computer whether the ship is controlled by a computer
     */
    public Carrier(boolean computer) {
        super(computer, Carrier.maxHealth, Carrier.damage);
    }
}
