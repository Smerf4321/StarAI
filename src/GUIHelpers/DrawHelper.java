package GUIHelpers;

import GUI.BoardGUI;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glVertex2f;

/**
 *
 * @author Patryk
 */
public class DrawHelper {
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 960;
    
    public static void BeginSession(){
        Display.setTitle("StarAI");
        try {
            Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
            Display.create();
        } catch (LWJGLException ex) {
            Logger.getLogger(BoardGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0.0, (double)WIDTH, (double)HEIGHT, 0.0, 1.0, -1.0);
        glMatrixMode(GL_MODELVIEW);
        glEnable(GL_TEXTURE_2D);
    }
    
    public static void DrawQuad(float startX, float startY, float side){
        glBegin(GL_QUADS);
        glVertex2f(startX, startY); //Top left
        glVertex2f(startX + side, startY); //Top right
        glVertex2f(startX + side, startY + side); //Bottom Right
        glVertex2f(startX, startY + side); //Bottom Left
        glEnd();
    }
}
