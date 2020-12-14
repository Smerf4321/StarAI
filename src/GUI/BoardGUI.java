package GUI;

import GUIHelpers.Clock;
import static GUIHelpers.DrawHelper.*;
import board.Board;
import org.lwjgl.opengl.Display;


/**
 *
 * @author Patryk
 */
public class BoardGUI {
    public BoardGUI(int width, int height){
        
        BeginSession(width, height);
        
        Board board = new Board(width, height);
        
        while(!Display.isCloseRequested()){
            Clock.update();
            
            board.Draw();

            Display.update();
            Display.sync(60);
        }
        
        Display.destroy();
    }
}
