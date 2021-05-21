package GUI;

import static GUIHelpers.DrawHelper.DrawQuadTexture;
import static GUIHelpers.DrawHelper.QuickTextureLoad;
import org.newdawn.slick.opengl.Texture;

/**
 * This class defines the background image of the board
 * @author Patryk
 */
public class Background {
    private Texture texture;
    private int width;
    private int height;
    
    public Background(){
        this.width = 2048;
        this.height = 1280;
    }
    
    /**
     * Loads the texture of the background
     */
    public void loadTexture(){
        this.texture = QuickTextureLoad("background");
    }
    
    /**
     * Draws the background
     */
    public void Draw(){
        DrawQuadTexture(texture, 0, 0, 16*128, 8*128);
    }
}
