package player;

import board.Board;
import org.lwjgl.input.Mouse;
import ships.Ship;

/**
 * This is a concrete implementation of a human player
 * @author Patryk
 */
public class HumanPlayer extends Player{
    
    private Board board;
    
    private Ship selectedShip;
    
    public HumanPlayer(Board board, boolean isTurn){
        this.computer = false;
        this.isTurn = isTurn;
        this.board = board;
    }
    
    public Ship selectShip(){
        return board
                .getSpot((int) Math.floor(Mouse.getX() / 128), (int) Math.floor((board.getHeight()*128 - Mouse.getY() - 1) / 128))
                .getShip();
    }
    
    public void Update(){
        if (Mouse.isButtonDown(0)){
            System.out.println("click");
            Ship clickedShip = board.getSpot((int) Math.floor(Mouse.getX() / 128), (int) Math.floor((board.getHeight()*128 - Mouse.getY() - 1) / 128)).getShip();
            if (!clickedShip.isComputer() && isTurn){
                selectedShip = selectShip();
                System.out.println(selectedShip.getShipTexture());
            }
        }
    }
}
