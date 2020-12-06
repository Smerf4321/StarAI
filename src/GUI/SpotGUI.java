package GUI;

import org.newdawn.slick.opengl.Texture;

/**
 *
 * @author Patryk
 */
public class SpotGUI {
    private float x;
    private float y;
    private float side;
    private Texture texture;
    
    public SpotGUI(float x, float y, float side, Texture texture){
        this.x = x;
        this.y = y;
        this.side = side;
        this.texture = texture;
    }
}
