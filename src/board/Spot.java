package board;

import static GUIHelpers.DrawHelper.DrawQuadTexture;
import static GUIHelpers.DrawHelper.QuickTextureLoad;
import ships.Ship;

/**
 * This class defines a spot on the board
 * @author Patryk
 */
public class Spot {
    private Ship ship;
    private float x;
    private float y;
    private static float width = 64;
    private static float height = 64;
    private String spotTexture = "EmptySpot";
   
    /**
     * This is a constructor for a spot with a ship
     * @param ship Ship on the Spot, can be null
     * @param x how far is the spot along the x-axis
     * @param y how far is the spot along y-axis
     */
    public Spot (float x, float y, Ship ship){
        this.ship = ship;
        this.x = x;
        this.y = y;
    }
    
    /**
     * This is a constructor for an empty spot
     * @param x how far is the spot along the x-axis
     * @param y how far is the spot along y-axis
     */
    public Spot (float x, float y){
        this.x = x;
        this.y = y;
    }
    
    /**
     * Draws the spot
     */
    public void Draw(){
        DrawQuadTexture(QuickTextureLoad(spotTexture), x, y, width, height);
    }
    
    /**
     * Returns the ship object
     * @return Ship object
     */
    public Ship getShip(){
        return ship;
    }
    
    /**
     * Sets the passed ship 
     * @param ship Ship object
     */
    public void setShip (Ship ship){
        this.ship = ship;
    }
    
    /**
     * Returns where the Spot is on the x-axis
     * @return float
     */
    public float getX(){
        return x;
    }
    
    /**
     * Return where the Spot is on the y-axis
     * @return float
     */
    public float getY(){
        return y;
    }
    
    /**
     * Returns the width of the spot
     * @return float
     */
    public float getWidth(){
        return width;
    }
    
    /**
     * Returns the length of the spot
     * @return float
     */
    public float getHeight(){
        return height;
    }
}
