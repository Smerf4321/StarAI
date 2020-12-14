package ships;

import static GUIHelpers.Clock.Delta;
import static GUIHelpers.DrawHelper.DrawQuadTexture;
import static GUIHelpers.DrawHelper.QuickTextureLoad;

/**
 *
 * @author Patryk
 */
public class Laser {
    private final String texture = "Laser32";
    private int x;
    private int y;
    private final int endX;
    private final int endY;
    private final int speed = 30;
    
    private boolean first = true;
    
    public Laser(int x, int y, int endX, int endY){
        this.x = x*128 + 48;
        this.y= y*128 + 48;
        this.endX = endX*128 + 48;
        this.endY = endY*128 + 48;
    }
    
    public void Draw(){
        DrawQuadTexture(QuickTextureLoad(texture), x, y, 32, 32);
    }
    
    public void Update(){
        if (first){
            first = false;
        }
        else {
            x += Delta() * speed;
        }
    }
}
