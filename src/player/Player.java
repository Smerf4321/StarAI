package player;

import mechanics.Game;

/**
 * This is an abstract class defining common features of all players
 * @author Patryk
 */
public abstract class Player {
    protected boolean computer;
    protected Game game;
    
    public boolean isComputer(){
        return this.computer;
    }
    
    /**
     * Checks if two spots are in the specified range
     * @param targetX x coord of the target
     * @param targetY y coord of the target
     * @param currentX x coord of the starting point
     * @param currentY y coord of the starting point
     * @param range specified range
     * @return boolean if the two points are in range then true
     */
    protected boolean isInRange(int targetX, int targetY, int currentX, int currentY, int range){
        return (Math.abs(targetX - currentX) + Math.abs(targetY - currentY) <= range);
    }

    public abstract void Update();
}
