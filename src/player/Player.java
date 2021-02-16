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

    public abstract void Update();
}
