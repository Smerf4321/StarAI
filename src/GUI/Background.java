package GUI;

import static GUIHelpers.DrawHelper.DrawQuadTexture;
import static GUIHelpers.DrawHelper.QuickTextureLoad;
import org.newdawn.slick.opengl.Texture;

/**
 *
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
    
    public void loadTexture(){
        this.texture = QuickTextureLoad("background");
    }
    
    public void Draw(){
        DrawQuadTexture(texture, 0, 0, 16*128, 8*128);
    }
}
