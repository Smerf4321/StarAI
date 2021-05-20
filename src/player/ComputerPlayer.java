package player;

import board.Board;
import board.Spot;
import java.util.ArrayList;
import mechanics.Game;
import mechanics.Move;
import mechanics.MoveType;
import ships.Ship;
import java.util.Random;

/**
 * This is a concrete implementation of a computer controller player
 * @author Patryk
 */
public class ComputerPlayer extends Player{
    
    private Board board;
    
    public ComputerPlayer(Board board, Game game){
        this.computer = true;
        this.board = board;
        this.game = game;
    }

    @Override
    public void Update() {
        if (game.isTurnMine(2)){
            ArrayList<Move> moves = getAllMoves();
            Move move = bestMoveChooser(moves);
            
            Ship target;
            switch(move.type){
                case MOVE:
                    move.ship.spot = move.end;
                    break;
                case ATTACK:
                    target = board.getShipAt((int)move.end.getX()/128, (int)move.end.getY()/128);
                    target.damage(move.ship.getWeaponsDamage());
                    break;
                case REPAIR:
                    target = board.getShipAt((int)move.end.getX()/128, (int)move.end.getY()/128);
                    target.repair(target.getWeaponsDamage());
            }
            
            game.endTurn();
        }
    }
    
    public ArrayList getAllMoves (){
        ArrayList<Move> allMoves = new ArrayList<>();
        
        for (Ship ship: board.getShipList()){
            if (ship.isComputer() && !ship.isKilled()){
                for (int x = 0; x < board.getWidth(); x++){
                    for (int y = 0; y < board.getHeight(); y++){
                        Ship targetShip = board.getShipAt(x, y);
                        Spot targetSpot = board.getMap()[x][y];

                        if ((targetShip == null || targetShip.isKilled()) && isInRange(x, y, Math.round(ship.spot.getX()/128), Math.round(ship.spot.getY()/128), ship.getMovementRange())){
                            allMoves.add(new Move(this, ship.spot, targetSpot, ship, targetShip, MoveType.MOVE));
                        }

                        if (targetShip != null && !targetShip.isKilled() && isInRange(x, y, Math.round(ship.spot.getX()/128), Math.round(ship.spot.getY()/128), ship.getWeaponsRange())){
                            MoveType type = null;
                            if (ship.getCanRepair() && targetShip.isComputer()){
                                type = MoveType.REPAIR;
                            }
                            if (ship.getCanAttack() && !targetShip.isComputer()){
                                type = MoveType.ATTACK;
                            }
                            else {
                                continue;
                            }
                            allMoves.add(new Move(this, ship.spot, targetSpot, ship, targetShip, type));
                        }
                    }
                }   
            }
        }
        
        return allMoves;
    }
    
    private Move bestMoveChooser(ArrayList<Move> moves){
        Move highestMove = null;
        for (Move m : moves){
            if (m.type == MoveType.ATTACK){
                if (highestMove == null){
                    highestMove = m;
                }
                else {
                    if (m.target.value > highestMove.target.value){
                        highestMove = m;
                    }
                }
            }
            else if (m.type == MoveType.REPAIR && m.target.getHealth() < m.target.getMaxHealth()){
                if (highestMove == null){
                    highestMove = m;
                }
                else {
                    if (m.target.value > highestMove.target.value){
                        highestMove = m;
                    }
                }
            }
        }
        if (highestMove == null){
            Random r = new Random();
            highestMove = moves.get(r.nextInt(moves.size()));
        }
        System.out.println(highestMove.toString());
        return highestMove;
    }
    
    private int evaluateBoardState(ArrayList<Move> moves){
        int totalValue = 0;
        Move highestMove = null;
        
        for (Move m : moves){
            
        }
        
        for (Ship s : board.getShipList()){
            if (s.isComputer()){
                totalValue += s.value;
            }
            else {
                totalValue -= s.value;
            }
        }
        
        return totalValue;
    }
    
    @Override
    public String toString(){
        return "ComputerPlayer";
    }
}
