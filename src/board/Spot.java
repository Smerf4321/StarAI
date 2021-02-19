package board;

import static GUIHelpers.DrawHelper.DrawQuadTexture;
import static GUIHelpers.DrawHelper.QuickTextureLoad;
import GUIHelpers.Drawable;
import org.newdawn.slick.opengl.Texture;
import ships.Ship;

/**
 * This class defines a spot on the board
 * @author Patryk
 */
public class Spot implements Drawable{
    private final float x;
    private final float y;
    private  Texture spotTexture;
    private Ship ship = null;
    
    /**
     * This is a constructor for an empty spot
     * @param x how far is the spot along the x-axis
     * @param y how far is the spot along y-axis
     */
    public Spot (float x, float y){
        this.x = x;
        this.y = y;
    }
    
    public void loadTexture(){
        this.spotTexture = QuickTextureLoad("emptySpot128");
        
        if (ship != null){
            ship.loadTexture();
        }
    }
    
    /**
     * Draws the spot and the ship on it
     */
    @Override
    public void Draw(){
        DrawQuadTexture(spotTexture, x, y, 128, 128);
        
        if (ship != null){
            ship.Draw(x, y);
        }
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
    
    public void setShip(Ship ship){
        this.ship = ship;
    }
     
    public Ship getShip(){
        return ship;
    }
    
    public void removeShip(){
        ship = null;
    }
}
