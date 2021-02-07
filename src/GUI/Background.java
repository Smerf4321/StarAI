package GUI;

import static GUIHelpers.DrawHelper.DrawQuadTexture;
import static GUIHelpers.DrawHelper.QuickTextureLoad;

/**
 *
 * @author Patryk
 */
public class Background {
    private static String texture = "background";
    private int width;
    private int height;
    
    public Background(int width, int height){
        this.width = 2048;
        this.height = 1280;
    }
    
    public void Draw(){
        DrawQuadTexture(QuickTextureLoad(texture), 0, 0, 16*128, 8*128);
    }
}
