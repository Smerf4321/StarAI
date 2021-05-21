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
 * Defines all the static functions to do with drawing objects on the graphical interface
 * @author Patryk
 */
public class DrawHelper {
    
    /**
     * Creates the initial window/display
     * @param width of the display
     * @param height of the display
     */
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
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
    }
    
    /**
     * Draws a quad, structure with 4 coordinates
     * @param startX top left x-coord
     * @param startY top left y-coord
     * @param width of the quad
     * @param height of the quad
     */
    public static void DrawQuad(float startX, float startY, float width, float height){
        glBegin(GL_QUADS);
        glVertex2f(startX, startY); //Top left
        glVertex2f(startX + width, startY); //Top right
        glVertex2f(startX + width, startY + height); //Bottom Right
        glVertex2f(startX, startY + height); //Bottom Left
        glEnd();
    }
    
    /**
     * Draws a quad with a texture binded
     * @param texture of the graphical object
     * @param startX top left x-coord
     * @param startY top left y-coord
     * @param width of the quad
     * @param height of the quad
     */
    public static void DrawQuadTexture(Texture texture, float startX, float startY, float width, float height){
        texture.bind();
        glTranslatef(startX, startY, 0);
        glBegin(GL_QUADS);
        
        glTexCoord2f(0, 0);
        glVertex2f(0, 0);
        
        glTexCoord2f(1, 0);
        glVertex2f(width, 0);
        
        glTexCoord2f(1, 1);
        glVertex2f(width, height);
        
        glTexCoord2f(0, 1);
        glVertex2f(0, height);
        glEnd();
        
        glLoadIdentity();
    }
    
    /**
     * Loads a texture into the memory
     * @param path to the texture image
     * @param filetype of the texture image
     * @return reference to the loaded texture
     */
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
    
    /**
     * Wrapper method for loading textures with name only 
     * @param name of the texture
     * @return reference to the loaded texture 
     */
    public static Texture QuickTextureLoad(String name){
        Texture texture;
        texture = LoadTexture("resources/" + name + ".png", "PNG");
        return texture;
    }
}
