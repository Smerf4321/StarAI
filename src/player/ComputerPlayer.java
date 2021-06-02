package player;

import board.Board;
import board.Spot;
import java.util.ArrayList;
import java.util.Collections;
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
        this.isPlayer2 = true;
        this.board = board;
        this.game = game;
    }
    
    public ComputerPlayer(boolean isPlayer2, Board board, Game game){
        this.isPlayer2 = isPlayer2;
        this.board = board;
        this.game = game;
    }

    @Override
    public void Update() {
        if (game.isTurnMine(isPlayer2)){
            ArrayList<Move> moves = getMoves(isPlayer2);
            //Collections.shuffle(moves);
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
    
    public ArrayList<Move> getMoves(boolean isMax){
        ArrayList<Move> allMoves = new ArrayList<>();
        
        //for each ship in ship list
        for (Ship ship: board.getShipList()){
            //for each ship that belongs to either player or computer
            if ((ship.isPlayer2() == isPlayer2)
                    && !ship.isKilled()){
                
                //for a range of x values centered on the ships spot and limited by it's range
                for (int x = Math.max(ship.spot.getX()-ship.getMovementRange(), 0);
                        x <= Math.min(ship.spot.getX()+ship.getMovementRange(), board.getWidth()-1);
                        x++){
                    
                    //for a range of y values centered on the ships spot and limited by it's range
                    for (int y = Math.max(ship.spot.getY()-ship.getMovementRange(), 0);
                            y <= Math.min(ship.spot.getY()+ship.getMovementRange(), board.getHeight()-1);
                            y++){
                        
                        //get the ship and spot at the specified coordinates
                        Ship targetShip = board.getShipAt(x, y);
                        Spot targetSpot = board.getMap()[x][y];

                        //if there is no ship, or ship is killed in that spot
                        if ((targetShip == null || targetShip.isKilled()) 
                                && isInRange(x, y, ship.spot.getX(), ship.spot.getY(), ship.getMovementRange())){
                            
                            //adds a move of type Move
                            Move m = new Move(isMax, ship.spot, targetSpot, ship, targetShip, MoveType.MOVE);
                            allMoves.add(m);
                        }

                        //if there is a ship and it's not killed and is in range of weapons
                        if (targetShip != null 
                                && !targetShip.isKilled() 
                                && isInRange(x, y, ship.spot.getX(),ship.spot.getY(), ship.getWeaponsRange())){
                            MoveType type = null;
                            
                            //if current ship can repair, both ships are owned by the same player, and the target isn't instance of carrier and is missing health
                            if (ship.getCanRepair() 
                                    && (targetShip.isPlayer2() == isMax)
                                    && !(targetShip instanceof Carrier) 
                                    && targetShip.getHealth() < targetShip.getMaxHealth()){
                                //set move type to Repair
                                type = MoveType.REPAIR;
                            }
                            
                            //if current ship can attack and target ship is not owned by the same player
                            else if (ship.getCanAttack() && (targetShip.isPlayer2() != isMax)){
                                //set more type to Attack
                                type = MoveType.ATTACK;
                            }
                            
                            //if neither is true continue with the loop
                            else {
                                continue;
                            }
                            
                            //generate a Move and add it to the list
                            Move m = new Move(isMax, ship.spot, targetSpot, ship, targetShip, type);
                            allMoves.add(m);
                        }
                    }
                }   
            }
        }
        
        return allMoves;
    }
    
    //Legacy method for very simple move prioritisation
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
                if (s.isPlayer2()){
                    totalValue += s.value * ((float)s.getHealth()/(float)s.getMaxHealth());
                }
                else {
                    totalValue -= s.value * ((float)s.getHealth()/(float)s.getMaxHealth()) * 2;
                }
            }
        }
        return totalValue;
    }
    
    private int minimax(int targetDepth, int depth, boolean isPlayer2, int alpha, int beta){
        switch (game.evaluateGameState()){
            case COMPUTER_WIN:
                return Integer.MAX_VALUE;
            case HUMAN_WIN:
                return Integer.MIN_VALUE;
        }
        
        if (depth >= targetDepth){
            return evaluateBoardState();
        }
        
        if (isPlayer2){
            int best = Integer.MIN_VALUE;
            ArrayList<Move> moves = getMoves(isPlayer2);
            //Collections.shuffle(moves);
            for (Move m : moves){
                
                applyMove(m);
                
                int current = minimax(targetDepth, depth+1, !isPlayer2, alpha, beta);
                
                //System.out.println(m.toString() + Integer.toString(current));
                
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
            int best = Integer.MAX_VALUE;
            ArrayList<Move> moves = getMoves(isPlayer2);
            //Collections.shuffle(moves);
            for (Move m : moves){
                
                applyMove(m);
                
                int current = minimax(targetDepth, depth+1, !isPlayer2, alpha, beta);
                
                //System.out.println(m.toString() + Integer.toString(current));
                
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
        int highestValue = Integer.MIN_VALUE;
        Move highestMove = moves.get(0);
        
        for (Move m : moves){
            applyMove(m);
            
            int moveValue = minimax(4, 0, false, Integer.MIN_VALUE, Integer.MAX_VALUE);
            
            //System.out.println(m.toString() + Integer.toString(moveValue));
            
            reverseMove(m);
            
            if ((isPlayer2 ? moveValue < highestValue : moveValue > highestValue)){
                highestValue = moveValue;
                highestMove = m;
            }
        }
        
        //System.out.println("Highest:" + Integer.toString(highestValue));
        return highestMove;
    }
    
    @Override
    public String toString(){
        return "Player2Computer";
    }
}
