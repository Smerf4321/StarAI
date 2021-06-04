package board;

import GUI.Background;
import GUIHelpers.Drawable;
import java.util.ArrayList;
import java.util.List;
import ships.*;

/**
 * Defines the mechanical elements of the board
 * @author Patryk
 */
public final class Board implements Drawable{
    private int height;
    private int width;
    private Spot[][] map;
    private ArrayList<Ship> ships;
    private Background background;
    
    /**
     * Initialises the board
     * @param width of the board
     * @param height of the board
     */
    public Board(int width, int height){
        this.height = height;
        this.width = width;
        background = new Background();
        setMap();
    }
    
    public Board (Board b){
        this.height = b.height;
        this.width = b.width;
        this.ships = b.ships;
        this.map = b.map;
    }
    
    /**
     * Resets the map to its starting state
     */
    public void setMap (){
        map = new Spot[width][height];
        for (int x = 0; x < width; x++){
            for (int y = 0; y < height; y++){
                map[x][y] = new Spot(x, y);
            }
        }
        
        ships = new ArrayList<>();
        
        setShip(new Cruiser(false, map[0][(height-3)/2]));
        setShip(new Carrier(false, map[0][(height-3)/2+1]));
        setShip(new Cruiser(false, map[0][(height-3)/2+2]));
        setShip(new Fighter(false, map[1][(height-3)/2]));
        setShip(new Fighter(false, map[1][(height-3)/2+1]));
        setShip(new Fighter(false, map[1][(height-3)/2+2]));

        setShip(new Cruiser(true, map[width-1][(height-3)/2]));
        setShip(new Carrier(true, map[width-1][(height-3)/2+1]));
        setShip(new Cruiser(true, map[width-1][(height-3)/2+2]));
        setShip(new Fighter(true, map[width-2][(height-3)/2]));
        setShip(new Fighter(true, map[width-2][(height-3)/2+1]));
        setShip(new Fighter(true, map[width-2][(height-3)/2+2]));
    }
    
    /**
     * Loades all of the textures used for this board
     */
    public void loadTexture(){
        background.loadTexture();
        for (int x = 0; x < width; x++){
            for (int y = 0; y < height; y++){
                Spot spot = map[x][y];
                spot.loadTexture();
            }
        } 
        ships.forEach((ship) -> {
            ship.loadTexture();
        });
    }
    
    /**
     * Draws the board using quads
     */
    @Override
    public void Draw(){
        //background.Draw();
        for (int x = 0; x < width; x++){
            for (int y = 0; y < height; y++){
                Spot spot = map[x][y];
                spot.Draw();
            }
        }    
        
        ships.forEach((ship) -> {
            if (!ship.isKilled()){
                ship.DrawHP();
            }
        });
        
        ships.forEach((ship) -> {
            if (!ship.isKilled()){
                ship.Draw();
            }
        });
    }

    /**
     * Returns the list of ships
     * @return List<Ship>
     */
    public List<Ship> getShipList(){
        return ships;
    }
    
    public Spot getSpot(int x, int y){
        return map[x][y];
    }
    
    /**
     * returns a ship at specified coordinates
     * @param x coordinate
     * @param y coordinate
     * @return ship class or null if no ship was found
     */
    public Ship getShipAt (int x, int y){
        Spot spot = map[x][y];
        Ship ship = null;
        for (Ship s: ships){
            if (s.spot == spot) {
                ship = s;
            }
        }
        return ship;
    }
    
    public void removeShip(Ship s){
        ships.remove(s);
    }
    
    public void setShip(Ship ship){
        ships.add(ship);
    }
    
    public int getHeight(){
        return this.height;
    }
    
    public int getWidth(){
        return this.width;
    }
    
    public Spot[][] getMap(){
        return map;
    }
}
