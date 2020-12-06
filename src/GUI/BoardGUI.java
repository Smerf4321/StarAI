package GUI;

import static GUIHelpers.DrawHelper.*;
import org.lwjgl.opengl.Display;
import static org.lwjgl.opengl.GL11.*;


/**
 *
 * @author Patryk
 */
public class BoardGUI {
    public BoardGUI(int width, int height){
        
        BeginSession();
        
        while(!Display.isCloseRequested()){
            DrawQuad(0,0,100);
            DrawQuad(100,100,100);
            
            Display.update();
            Display.sync(60);
        }
        
        Display.destroy();
    }
}
