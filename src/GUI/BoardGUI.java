package GUI;

import GUIHelpers.Clock;
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
        Laser l = new Laser(1, 1, 5, 1);
        
        while(!Display.isCloseRequested()){
            Clock.update();
            l.Update();
            
            board.Draw();
            board.getShipList().forEach((ship) -> {
                ship.Draw();
            });
            l.Draw();
            
            Display.update();
            Display.sync(60);
        }
        
        Display.destroy();
    }
}
