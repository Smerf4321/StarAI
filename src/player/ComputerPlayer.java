package player;

import board.Board;
import board.Spot;
import java.util.ArrayList;
import mechanics.Game;
import mechanics.Move;
import mechanics.MoveType;
import ships.Ship;
import java.util.Random;
import ships.Carrier;

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
            ArrayList<Move> moves = getMoves(true);
            Move move = findBestMinimax(moves);

            applyMove(move);
            
            game.endTurn();
        }
    }
    
    private void applyMove(Move move){
        switch(move.type){
                case MOVE:
                    move.ship.spot = move.end;
                    break;
                case ATTACK:
                    move.target.damage(move.ship.getWeaponsDamage());
                    break;
                case REPAIR:
                    move.target.repair(move.ship.getWeaponsDamage());
            }
    }
    
    private void reverseMove(Move move){
        switch(move.type){
                case MOVE:
                    move.ship.spot = move.start;
                    break;
                case ATTACK:
                    move.target.killed = false;
                    move.target.repair(move.ship.getWeaponsDamage());
                    break;
                case REPAIR:
                    move.target.damage(move.ship.getWeaponsDamage());
            }
    }
    
    public ArrayList<Move> getMoves(boolean isComputer){
        ArrayList<Move> allMoves = new ArrayList<>();
        
        for (Ship ship: board.getShipList()){
            if ((ship.isComputer() == isComputer) && !ship.isKilled()){
                for (int x = 0; x < board.getWidth(); x++){
                    for (int y = 0; y < board.getHeight(); y++){
                        Ship targetShip = board.getShipAt(x, y);
                        Spot targetSpot = board.getMap()[x][y];

                        if ((targetShip == null || targetShip.isKilled()) && isInRange(x, y, Math.round(ship.spot.getX()/128), Math.round(ship.spot.getY()/128), ship.getMovementRange())){
                            allMoves.add(new Move(this, ship.spot, targetSpot, ship, targetShip, MoveType.MOVE));
                        }

                        if (targetShip != null 
                                && !targetShip.isKilled() 
                                && isInRange(x, y, Math.round(ship.spot.getX()/128), Math.round(ship.spot.getY()/128), ship.getWeaponsRange())){
                            MoveType type = null;
                            if (ship.getCanRepair() && targetShip.isComputer() && !(targetShip instanceof Carrier) && targetShip.getHealth() < targetShip.getMaxHealth()){
                                type = MoveType.REPAIR;
                            }
                            else if (ship.getCanAttack() && !targetShip.isComputer()){
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
    
    private int evaluateBoardState(){
        int totalValue = 0;
        
        for (Ship s : board.getShipList()){
            if (!s.isKilled()){
                if (s.isComputer()){
                    totalValue += s.value * (s.getHealth()/s.getMaxHealth());
                }
                else {
                    totalValue -= s.value * (s.getHealth()/s.getMaxHealth());
                }
            }
        }

        return totalValue;
    }
    
    private int minimax(int targetDepth, int depth, boolean isComputer, int alpha, int beta){
        int value = evaluateBoardState();
        
        if (depth >= targetDepth){
            return value;
        }
        
        if (isComputer){
            int best = -9999;
            
            for (Move m : getMoves(isComputer)){
                applyMove(m);
                int current = minimax(targetDepth, depth+1, !isComputer, alpha, beta);
                best = Math.max(best, current);
                alpha = Math.max(alpha, best);
                reverseMove(m);
                
                if (beta <= alpha){
                    break;
                }
            }
            
            return best;
        }
        else {
            int best = 9999;
            
            for (Move m : getMoves(!isComputer)){
                //move to next node
                applyMove(m);
                //get the value of that node
                int current = minimax(targetDepth, depth+1, !isComputer, alpha, beta);
                //compare the current node value with the best
                best = Math.min(best, current);
                
                beta = Math.min(beta, best);
                reverseMove(m);
                
                if (beta <= alpha){
                    break;
                }
            }
            
            return best;
        }
    }
    
    private Move findBestMinimax(ArrayList<Move> moves){
        int highestValue = -9999;
        Move highestMove = new Move();
        
        for (Move m : moves){
            applyMove(m);
            int moveValue = minimax(4, 0, false, -9999, 9999);
            reverseMove(m);
            
            if (moveValue > highestValue){
                highestValue = moveValue;
                highestMove = m;
            }
        }
        
        return highestMove;
    }
    
    @Override
    public String toString(){
        return "ComputerPlayer";
    }
}
