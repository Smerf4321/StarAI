package board;

import static GUIHelpers.DrawHelper.DrawQuadTexture;
import static GUIHelpers.DrawHelper.QuickTextureLoad;
import GUIHelpers.Drawable;
import org.newdawn.slick.opengl.Texture;

/**
 * This class defines a spot on the board
 * @author Patryk
 */
public class Spot implements Drawable{
    private final int x;
    private final int y;
    private  Texture spotTexture;
    
    /**
     * This is a constructor for an empty spot
     * @param x how far is the spot along the x-axis
     * @param y how far is the spot along y-axis
     */
    public Spot (int x, int y){
        this.x = x;
        this.y = y;
    }
    
    /**
     * Loads the texture of the spot
     */
    public void loadTexture(){
        this.spotTexture = QuickTextureLoad("emptySpot128");
    }
    
    /**
     * Draws the spot and the ship on it
     */
    @Override
    public void Draw(){
        DrawQuadTexture(spotTexture, 128*x, 128*y, 128, 128);
    }
    
    /**
     * Returns where the Spot is on the x-axis
     * @return float
     */
    public int getX(){
        return x;
    }
    
    /**
     * Return where the Spot is on the y-axis
     * @return float
     */
    public int getY(){
        return y;
    }
    
    /**
     * Returns the spot as a string description
     * @return spot coordinates
     */
    @Override
    public String toString(){
        String s = "x:" + x + ", y:" + y;
        return s;
    }
}
