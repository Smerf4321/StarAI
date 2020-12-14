package board;

import GUIHelpers.Drawable;
import java.util.ArrayList;
import java.util.List;
import ships.*;

/**
 *
 * @author Patryk
 */
public final class Board implements Drawable{
    private int height;
    private int width;
    private Spot[][] map;
    private ArrayList<Ship> ships;
    
    public Board(int width, int height){
        this.height = height;
        this.width = width;
        setMap();
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
    public void setMap (){
        map = new Spot[width][height];
        for (int x = 0; x < width; x++){
            for (int y = 0; y < height; y++){
                map[x][y] = new Spot(x*128, y*128);
            }
        }
        
        ships = new ArrayList<>();
        ships.add(new Cruiser(false, map[0][2]));
        ships.add(new Carrier(false, map[0][3]));
        ships.add(new Cruiser(false, map[0][4]));
        ships.add(new Fighter(false, map[1][2]));
        ships.add(new Fighter(false, map[1][3]));
        ships.add(new Fighter(false, map[1][4]));

        ships.add(new Cruiser(true, map[9][2]));
        ships.add(new Carrier(true, map[9][3]));
        ships.add(new Cruiser(true, map[9][4]));
        ships.add(new Fighter(true, map[8][2]));
        ships.add(new Fighter(true, map[8][3]));
        ships.add(new Fighter(true, map[8][4]));
    }
    
    @Override
    public void Draw(){
        for (int x = 0; x < width; x++){
            for (int y = 0; y < height; y++){
                Spot spot = map[x][y];
                spot.Draw();
            }
        }
    }

    /**
     * Returns the list of ships
     * @return List<Ship>
     */
    public List<Ship> getShipList(){
        return ships;
    }
}
