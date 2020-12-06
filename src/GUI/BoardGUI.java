package GUI;

import static GUIHelpers.DrawHelper.*;
import board.Spot;
import org.lwjgl.opengl.Display;
import ships.Fighter;


/**
 *
 * @author Patryk
 */
public class BoardGUI {
    public BoardGUI(int width, int height){
        
        BeginSession(width, height);
        
        Spot spot = new Spot(0, 0, new Fighter(true));
        Spot spot2 = new Spot(0, 64, new Fighter(true));
        
        while(!Display.isCloseRequested()){
            spot.Draw();
            spot2.Draw();
            
            Display.update();
            Display.sync(60);
        }
        
        Display.destroy();
    }
}
