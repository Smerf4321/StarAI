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
        if (game.isTurnMine(2) && false){
            Random r = new Random();
            ArrayList<Move> moves = getAllMoves();
            Move move = moves.get(r.nextInt(moves.size()));
            
            System.out.println(move.toString());
            
            Ship target;
            switch(move.type){
                case MOVE:
                    move.ship.spot = move.end;
                    break;
                case ATTACK:
                    target = board.getShipAt((int)move.end.getX(), (int)move.end.getY());
                    target.damage(move.ship.getWeaponsDamage());
                    if (target.isKilled()){
                        board.removeShip(target);
                    }
                    break;
                case REPAIR:
                    target = board.getShipAt((int)move.end.getX(), (int)move.end.getY());
                    target.repair(target.getWeaponsDamage());
            }
            
            game.endTurn();
        }
    }
    
    public ArrayList getAllMoves (){
        ArrayList<Move> allMoves = new ArrayList<>();
        
        for (Ship ship: board.getShipList()){
            if (ship.isComputer()){
                for (int x = 0; x < board.getWidth(); x++){
                    for (int y = 0; y < board.getHeight(); y++){
                        Ship targetShip = board.getShipAt(x, y);
                        Spot targetSpot = board.getMap()[x][y];

                        if (targetShip == null && isInRange(x, y, Math.round(ship.spot.getX()/128), Math.round(ship.spot.getY()/128), ship.getMovementRange())){
                            allMoves.add(new Move(this, ship.spot, targetSpot, ship, MoveType.MOVE));
                        }

                        if (targetShip != null && isInRange(x, y, Math.round(ship.spot.getX()/128), Math.round(ship.spot.getY()/128), ship.getWeaponsRange())){
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
                            allMoves.add(new Move(this, ship.spot, targetSpot, ship, type));
                        }
                    }
                }   
            }
        }
        
        return allMoves;
    }
    
    @Override
    public String toString(){
        return "ComputerPlayer";
    }
}
