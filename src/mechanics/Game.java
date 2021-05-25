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
 * @author Patryk Neter
 */
public class Game {
    private final Player[] players;
    private final Board board;
    private int currentTurn;
    private GameState state;
    private int boardWidth = 8;
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
        currentTurn = 1;
        new BoardGUI(boardWidth, boardHeight, board, p1, p2);
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
        switch (evaluateGameState()){
            case HUMAN_WIN:
                System.exit(0);
                break;
            case COMPUTER_WIN:
                System.exit(0);
                break;
            case ACTIVE:
                break;   
        }
        
        ArrayList<Ship> l = new ArrayList();
        
        for (Ship s : board.getShipList()){
            if (s.isKilled()){
                l.add(s);
            }
        }
        
        for (Ship s : l){
            board.removeShip(s);
        }
        
        if (currentTurn == 1){
            currentTurn = 2;
        }
        else {
            currentTurn = 1;
        }
    }
    
    /**
     * Returns true if current turn belongs to the player
     * @param p number of the player (1 - human, 2 - computer)
     * @return 
     */
    public boolean isTurnMine(int p){
        return currentTurn == p;
    }
    
    /**
     * this function evaluates the state of the game, e.g. checks win conditions
     * @return the current state of the game
     */
    public GameState evaluateGameState(){
        List<Ship> ships = board.getShipList();
        for (Ship s : ships){
            //checks if either of the carriers are destroyed and changes gamestate accordingly
            if ((s instanceof Carrier) && s.isKilled()){
                if (s.isComputer()){
                    System.out.println("HUMAN WIN");
                    return GameState.HUMAN_WIN;
                }
                else {
                    System.out.println("COMPUTER WIN");
                    return GameState.COMPUTER_WIN;
                }
            }
        }
        return GameState.ACTIVE;
    }
}
