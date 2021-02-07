package GUI;

import GUIHelpers.Clock;
import static GUIHelpers.DrawHelper.*;
import board.Board;
import org.lwjgl.opengl.Display;
import player.Player;


/**
 *
 * @author Patryk
 */
public class BoardGUI {
    public BoardGUI(int width, int height, Board board, Player human, Player computer){
        
        BeginSession(width, height);
        
        while(!Display.isCloseRequested()){
            Clock.update();
            board.Draw();
            human.Update();

            Display.update();
            Display.sync(60);
        }
        
        Display.destroy();
    }
}
