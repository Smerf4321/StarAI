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
        
        //for each ship in ship list
        for (Ship ship: board.getShipList()){
            //for each ship that belongs to eithe rplayer or computer
            if ((ship.isComputer() == isComputer) 
                    && !ship.isKilled()){
                
                //for a range of x values centered on the ships spot and limited by it's range
                for (int x = Math.max(ship.spot.getX()-ship.getMovementRange(), 0);
                        x < Math.min(ship.spot.getX()+ship.getMovementRange(), board.getWidth());
                        x++){
                    
                    //for a range of y values centered on the ships spot and limited by it's range
                    for (int y = Math.max(ship.spot.getY()-ship.getMovementRange(), 0);
                            y < Math.min(ship.spot.getY()+ship.getMovementRange(), board.getHeight());
                            y++){
                        
                        //get the ship and spot at the specified coordinates
                        Ship targetShip = board.getShipAt(x, y);
                        Spot targetSpot = board.getMap()[x][y];

                        //if there is no ship, or ship is killed in that spot
                        if ((targetShip == null || targetShip.isKilled()) 
                                && isInRange(x, y, ship.spot.getX(), ship.spot.getY(), ship.getMovementRange())){
                            
                            //adds a move of type Move
                            allMoves.add(new Move(this, ship.spot, targetSpot, ship, targetShip, MoveType.MOVE));
                        }

                        //if there is a ship and it's not killed and is in range of weapons
                        if (targetShip != null 
                                && !targetShip.isKilled() 
                                && isInRange(x, y, ship.spot.getX(),ship.spot.getY(), ship.getWeaponsRange())){
                            MoveType type = null;
                            
                            //if current ship can repair, both ships are owned by the same player, and the target isn't instance of carrier and is missing health
                            if (ship.getCanRepair() 
                                    && (targetShip.isComputer() == isComputer)
                                    && !(targetShip instanceof Carrier) 
                                    && targetShip.getHealth() < targetShip.getMaxHealth()){
                                //set move type to Repair
                                type = MoveType.REPAIR;
                            }
                            
                            //if current ship can attack and target ship is not owned by the same player
                            else if (ship.getCanAttack() && (targetShip.isComputer() != isComputer)){
                                //set more type to Attack
                                type = MoveType.ATTACK;
                            }
                            
                            //if neither is true continue with the loop
                            else {
                                continue;
                            }
                            
                            Move m = new Move(this, ship.spot, targetSpot, ship, targetShip, type);
                            System.out.println(m.toString());
                            allMoves.add(m);
                        }
                    }
                }   
            }
        }
        
        return allMoves;
    }
    
//    public ArrayList<Move> getMoves(boolean isComputer){
//        ArrayList<Move> allMoves = new ArrayList<>();
//        
//        for (Ship ship: board.getShipList()){
//            if ((ship.isComputer() == isComputer) && !ship.isKilled()){
//                for (int x = 0; x < board.getWidth(); x++){
//                    for (int y = 0; y < board.getHeight(); y++){
//                        Ship targetShip = board.getShipAt(x, y);
//                        Spot targetSpot = board.getMap()[x][y];
//
//                        if ((targetShip == null || targetShip.isKilled()) && isInRange(x, y, Math.round(ship.spot.getX()/128), Math.round(ship.spot.getY()/128), ship.getMovementRange())){
//                            allMoves.add(new Move(this, ship.spot, targetSpot, ship, targetShip, MoveType.MOVE));
//                        }
//
//                        if (targetShip != null 
//                                && !targetShip.isKilled() 
//                                && isInRange(x, y, Math.round(ship.spot.getX()/128), Math.round(ship.spot.getY()/128), ship.getWeaponsRange())){
//                            MoveType type = null;
//                            if (ship.getCanRepair() && targetShip.isComputer() == isComputer && !(targetShip instanceof Carrier) && targetShip.getHealth() < targetShip.getMaxHealth()){
//                                type = MoveType.REPAIR;
//                            }
//                            else if (ship.getCanAttack() && targetShip.isComputer() != isComputer){
//                                type = MoveType.ATTACK;
//                            }
//                            else {
//                                continue;
//                            }
//                            allMoves.add(new Move(this, ship.spot, targetSpot, ship, targetShip, type));
//                        }
//                    }
//                }   
//            }
//        }
//        
//        return allMoves;
//    }
    
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
                    totalValue += s.value * ((float)s.getHealth()/(float)s.getMaxHealth())-1;
                }
                else {
                    totalValue -= s.value * ((float)s.getHealth()/(float)s.getMaxHealth());
                }
            }
        }
        return totalValue;
    }
    
    private int minimax(int targetDepth, int depth, boolean isComputer, int alpha, int beta){
        switch (game.evaluateGameState()){
            case COMPUTER_WIN:
                return Integer.MAX_VALUE;
            case HUMAN_WIN:
                return Integer.MIN_VALUE;
        }
        
        if (depth >= targetDepth){
            return evaluateBoardState();
        }
        
        if (isComputer){
            int best = Integer.MIN_VALUE;
            ArrayList<Move> moves = getMoves(isComputer);
            for (Move m : moves){
                
                applyMove(m);
                int current = minimax(targetDepth, depth+1, !isComputer, alpha, beta);
                
                best = Math.max(best, current)-depth;
                alpha = Math.max(alpha, best);
                reverseMove(m);
                
//                if (beta <= alpha){
//                    break;
//                }
            }
            
            return best;
        }
        else {
            int best = Integer.MAX_VALUE;
            ArrayList<Move> moves = getMoves(!isComputer);
            for (Move m : moves){
                
                applyMove(m);
                int current = minimax(targetDepth, depth+1, !isComputer, alpha, beta);
                
                best = Math.min(best, current)-depth;
                beta = Math.min(beta, best);
                reverseMove(m);
                
//                if (beta <= alpha){
//                    break;
//                }
            }
            
            return best;
        }
    }
    
    private Move findBestMinimax(ArrayList<Move> moves){
        int highestValue = Integer.MIN_VALUE;
        Move highestMove = moves.get(0);
        
        for (Move m : moves){
            applyMove(m);
            int moveValue = minimax(2, 0, false, Integer.MIN_VALUE, Integer.MAX_VALUE);
            reverseMove(m);
            
            if (moveValue > highestValue){
                highestValue = moveValue;
                highestMove = m;
            }
        }
        
        System.out.println("Highest:" + Integer.toString(highestValue));
        return highestMove;
    }
    
    @Override
    public String toString(){
        return "ComputerPlayer";
    }
}
