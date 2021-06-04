package ships;

import static GUIHelpers.DrawHelper.*;

/**
 *
 * @author Patryk
 */
public class HealthBar {

    private int x;
    private int y;
    private int maxHealth;
    private int currentHealth;
    
    public HealthBar(){
    }

    public void Draw(int x, int y){
        DrawColorQuad((x*128) + 16, (y*128) + 112, 96, 8);
    }
}
