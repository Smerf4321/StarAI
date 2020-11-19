package board;

import ships.Carrier;
import ships.Cruiser;
import ships.Fighter;

/**
 *
 * @author Patryk
 */
public final class Board {
    private int length;
    private int width;
    private Spot[][] map;
    
    public Board(){
        length = 10;
        width = 7;
        resetMap();
    }
    
    /**
     * Returns a spot with the exact x and y coordinates
     * @param x x axis coordinate
     * @param y y axis coordinate
     * @return Spot class 
     */
    public Spot getSpot (int x, int y){
        return map[x][y];
    }
    
    /**
     * Resets the map to its starting state
     */
    public void resetMap (){
        for (int x = 0; x < length-1; x++){
            for (int y = 0; y < width-1; y++){
                map[x][y] = new Spot(x, y, null);
            }
        }
        
        map[0][2].setShip(new Cruiser(false));
        map[0][3].setShip(new Carrier(false));
        map[0][4].setShip(new Cruiser(false));
        map[1][2].setShip(new Fighter(false));
        map[1][3].setShip(new Fighter(false));
        map[1][4].setShip(new Fighter(false));
        
        map[9][2].setShip(new Cruiser(true));
        map[9][3].setShip(new Carrier(true));
        map[9][4].setShip(new Cruiser(true));
        map[8][2].setShip(new Fighter(true));
        map[8][3].setShip(new Fighter(true));
        map[8][4].setShip(new Fighter(true));
    }
}
