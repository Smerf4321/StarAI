package board;

import java.util.List;
import ships.*;

/**
 *
 * @author Patryk
 */
public final class Board {
    private int height;
    private int width;
    private Spot[][] map;
    private List<Ship> ships;
    
    public Board(int width, int height){
        this.height = height/64;
        this.width = width/64;
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
        map = new Spot[20][15];
        for (int x = 0; x < width; x++){
            for (int y = 0; y < height; y++){
                map[x][y] = new Spot(x*64, y*64, null);
            }
        }
        
        /*
        spawnP1Ship(0,2,new Cruiser(false));
        spawnP1Ship(0,3,new Carrier(false));
        spawnP1Ship(0,4,new Cruiser(false));
        spawnP1Ship(1,2,new Fighter(false));
        spawnP1Ship(1,3,new Fighter(false));
        spawnP1Ship(1,4,new Fighter(false));

        spawnP1Ship(0,2,new Cruiser(true));
        spawnP1Ship(0,3,new Carrier(true));
        spawnP1Ship(0,4,new Cruiser(true));
        spawnP1Ship(1,2,new Fighter(true));
        spawnP1Ship(1,3,new Fighter(true));
        spawnP1Ship(1,4,new Fighter(true));
        */
    }
    
    public void Draw(){
        for (int x = 0; x < width; x++){
            for (int y = 0; y < height; y++){
                Spot spot = map[x][y];
                spot.Draw();
            }
        }
    }
    
    /**
     * Adds a ship to a specified Spot and list of P1 player ships
     * @param x x-coord of Spot
     * @param y y-coord of Spot
     * @param ship Ship spawned at the spot
     */
    private void spawnP1Ship(int x, int y, Ship ship){
        map[x][y].setShip(ship);
        ships.add(ship);
    }
    
    public List getShipList(){
        return ships;
    }
}
