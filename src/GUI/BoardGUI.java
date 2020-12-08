package GUI;

import static GUIHelpers.DrawHelper.*;
import board.Board;
import org.lwjgl.opengl.Display;
import ships.*;


/**
 *
 * @author Patryk
 */
public class BoardGUI {
    public BoardGUI(int width, int height){
        
        BeginSession(width, height);
        
        Board board = new Board(width, height);
        Fighter f = new Fighter(true, board.getSpot(1, 1));
        Cruiser c = new Cruiser(true, board.getSpot(2, 1));
        
        while(!Display.isCloseRequested()){
            
            board.Draw();
            f.Draw();
            c.Draw();
            
            Display.update();
            Display.sync(60);
        }
        
        Display.destroy();
    }
}
