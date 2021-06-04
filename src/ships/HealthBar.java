package ships;

import static GUIHelpers.DrawHelper.*;

/**
 *
 * @author Patryk
 */
public class HealthBar {
    
    public HealthBar(){}

    
    /**
     * Draws the healthbar
     * @param x coordinate of the spot
     * @param y coordinate of the spot
     * @param maxHealth of the ship
     * @param currentHealth of the ship
     */
    public void Draw(int x, int y, int maxHealth, int currentHealth){
        float multiplier = (float)currentHealth / (float)maxHealth;
        DrawColorQuad(1f, 0, 0, (x*128) + 16, (y*128) + 112, 96, 8);
        DrawColorQuad(0, 1f, 0, (x*128) + 16, (y*128) + 112, multiplier*96, 8);
    }
}
