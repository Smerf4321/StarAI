package mechanics;

import GUI.BoardGUI;
import board.Board;
import java.util.ArrayList;
import java.util.List;
import player.ComputerPlayer;
import player.HumanPlayer;
import player.Player;
import ships.Carrier;
import ships.Ship;

/**
 * Game class defines a singular game
 * @author Patryk
 */
public class Game {
    private final Player[] players;
    private final Board board;
    private Player currentTurn;
    private GameState state;
    private int boardWidth = 10;
    private int boardHeight = 7;
    private Player p1;
    private Player p2;
    
    
    
    /**
     * Function that creates the environment for a new game
     */
    public Game (){
        board = new Board(boardWidth, boardHeight);
        p1 = new HumanPlayer(board, this);
        p2 = new ComputerPlayer(board, this);
        players = new Player[]{p1, p2};
        new BoardGUI(boardWidth, boardHeight, board, p1, p2);
        currentTurn = p1;
        
    }
   
    /**
     * Returns state of the game
     * @return GameState enum
     */
    public GameState getGameState(){
        return this.state;
    }
    
    /**
     * Changes the state of the game
     * @param state new game state for the game
     */
    public void setGameState(GameState state){
        this.state = state;
    }
    
    /**
     * Ends the turn of the current player 
     * and gives the turn to the other player
     */
    public void endTurn(){
        if (currentTurn == p1){
            currentTurn = p2;
            p2.gainTurn();
        }
        else {
            currentTurn = p1;
            p1.gainTurn();
        }
    }
    
    /**
     * this function evaluates the state of the game, e.g. checks win conditions
     * @return the current state of the game
     */
    private GameState evaluateGameState(){
        List<Ship> ships = board.getShipList();
        for (Ship ship : ships){
            //checks if either of the carriers are destroyed and changes gamestate accrdingly
            if ((ship instanceof Carrier) && ship.isKilled()){
                if (ship.isComputer()){
                    return GameState.HUMAN_WIN;
                }
                else {
                    return GameState.COMPUTER_WIN;
                }
            }
            
            //checks if all ships, other than Carriers have been killed and returns a Draw
            if (!ship.isKilled() && !(ship instanceof Carrier)){
                return GameState.ACTIVE;
            }
            else {
                return GameState.DRAW;
            }
        }
        return GameState.ACTIVE;
    }
}
