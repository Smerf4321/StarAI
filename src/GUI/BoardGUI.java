package GUI;

import static GUIHelpers.DrawHelper.*;
import board.Board;
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
        
        Board board = new Board(width, height);
        
        while(!Display.isCloseRequested()){
            
            board.Draw();
            
            Display.update();
            Display.sync(60);
        }
        
        Display.destroy();
    }
}
