package player;

/**
 * This is an abstract class defining common features of all players
 * @author Patryk
 */
public abstract class Player {
    protected boolean computer;
    protected boolean isTurn;
    
    public boolean isComputer(){
        return this.computer;
    }
    
    public void passTurn(){
        isTurn = !isTurn;
    }

    public abstract void Update();
}
