package player;

import mechanics.Game;

/**
 * This is an abstract class defining common features of all players
 * @author Patryk
 */
public abstract class Player {
    protected boolean computer;
    protected boolean isTurn;
    protected Game game;
    
    public boolean isComputer(){
        return this.computer;
    }
    
    protected void passTurn(){
        game.endTurn();
        isTurn = false;
    }
    
    public void gainTurn(){
        isTurn = true;
    }

    public abstract void Update();
}
