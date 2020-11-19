package ships;

/**
 * This is a concrete implementation of Cruiser which extends Ship
 * @author Patryk
 */
public class Cruiser extends Ship{
    
    static int maxHealth = 2;
    static int movementRange = 2;
    static int weaponsRange = 2;
    static int damage = 1;
    
    /**
     * Cruiser constructor
     * @param computer whether the ship is controlled by a computer
     */
    public Cruiser(boolean computer) {
        super(computer, Cruiser.maxHealth, Cruiser.damage);
    }
}
