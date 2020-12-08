package board;

import static GUIHelpers.DrawHelper.DrawQuadTexture;
import static GUIHelpers.DrawHelper.QuickTextureLoad;

/**
 * This class defines a spot on the board
 * @author Patryk
 */
public class Spot {
    private final float x;
    private final float y;
    private final String spotTexture = "emptyspot128";
    
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
        DrawQuadTexture(QuickTextureLoad(spotTexture), x, y, 128, 128);
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
}
