package board;

import GUI.Background;
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
    private Background background;
    
    public Board(int width, int height){
        this.height = height;
        this.width = width;
        background = new Background(1,1);
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
        
        setShip(new Cruiser(false), map[0][2]);
        setShip(new Carrier(false), map[0][3]);
        setShip(new Cruiser(false), map[0][4]);
        setShip(new Fighter(false), map[1][2]);
        setShip(new Fighter(false), map[1][3]);
        setShip(new Fighter(false), map[1][4]);

        setShip(new Cruiser(true), map[9][2]);
        setShip(new Carrier(true), map[9][3]);
        setShip(new Cruiser(true), map[9][4]);
        setShip(new Fighter(true), map[8][2]);
        setShip(new Fighter(true), map[8][3]);
        setShip(new Fighter(true), map[8][4]);
    }
    
    @Override
    public void Draw(){
        background.Draw();
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
    
    public void setShip(Ship ship, Spot spot){
        ships.add(ship);
        spot.setShip(ship);
    }
    
    public int getHeight(){
        return this.height;
    }
    
    public int getWidth(){
        return this.width;
    }
}
