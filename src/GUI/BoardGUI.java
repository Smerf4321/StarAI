package GUI;

import GUIHelpers.Clock;
import static GUIHelpers.DrawHelper.*;
import board.Board;
import org.lwjgl.opengl.Display;
import player.Player;


/**
 * Draws and updates the board 
 * @author Patryk
 */
public class BoardGUI {
    public BoardGUI(int width, int height, Board board, Player human, Player computer){
        
        BeginSession(width, height);
        
        //Loads all the textures
        board.loadTexture();
        
        //Loop that constantly updates the graphical interface
        while(!Display.isCloseRequested()){
            Clock.update();
            board.Draw();
            human.Update();
            computer.Update();
            
            Display.update();
            Display.sync(60);
        }
        
        Display.destroy();
    }
}
