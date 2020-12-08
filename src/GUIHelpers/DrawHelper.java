package GUIHelpers;

import GUI.BoardGUI;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

/**
 *
 * @author Patryk
 */
public class DrawHelper {
    
    public static void BeginSession(int width, int height){
        
        width = 128*width;
        height = 128*height;
        
        Display.setTitle("StarAI");
        try {
            Display.setDisplayMode(new DisplayMode(width, height));
            Display.create();
        } catch (LWJGLException ex) {
            Logger.getLogger(BoardGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0.0, width, height, 0.0, 1.0, -1.0);
        glMatrixMode(GL_MODELVIEW);
        glEnable(GL_TEXTURE_2D);
    }
    
    public static void DrawQuad(float startX, float startY, float width, float length){
        glBegin(GL_QUADS);
        glVertex2f(startX, startY); //Top left
        glVertex2f(startX + width, startY); //Top right
        glVertex2f(startX + width, startY + length); //Bottom Right
        glVertex2f(startX, startY + length); //Bottom Left
        glEnd();
    }
    
    public static void DrawQuadTexture(Texture texture, float startX, float startY, float width, float length){
        texture.bind();
        glTranslatef(startX, startY, 0);
        glBegin(GL_QUADS);
        
        glTexCoord2f(0, 0);
        glVertex2f(0, 0);
        
        glTexCoord2f(1, 0);
        glVertex2f(width, 0);
        
        glTexCoord2f(1, 1);
        glVertex2f(width, length);
        
        glTexCoord2f(0, 1);
        glVertex2f(0, length);
        glEnd();
        
        glLoadIdentity();
    }
    
    public static Texture LoadTexture(String path, String filetype){
        Texture texture = null;
        InputStream in = ResourceLoader.getResourceAsStream(path);
        try {
            texture = TextureLoader.getTexture(filetype, in);
        } catch (IOException ex) {
            Logger.getLogger(DrawHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return texture;
    }
    
    public static Texture QuickTextureLoad(String name){
        Texture texture;
        texture = LoadTexture("resources/" + name + ".png", "PNG");
        return texture;
    }
}
