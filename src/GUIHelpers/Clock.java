package GUIHelpers;

import org.lwjgl.Sys;

/**
 * Defines a clock for the game used for animations
 * @author Patryk
 */
public class Clock {
    public static long lastFrame;
    public static long totalTime;
    public static float d = 0;
    private static float multiplier = 1;
    
    public static long getTime() {
        return Sys.getTime() * 1000 / Sys.getTimerResolution();
    }
    
    public static float getDelta(){
        long currentTime = getTime();
        int delta = (int) (currentTime - lastFrame);
        lastFrame = getTime();
        return delta*0.01f;
    }
    
    public static float Delta(){
        return d*multiplier;
    }
    
    public static float getMultiplier(){
        return multiplier;
    }
    
    public static void update(){
        d = getDelta();
        totalTime += d;
    }
    
    public static void ChangeMultiplier(int change){
        if (!(multiplier + change < -1 && multiplier + change >7)){
            multiplier += change;
        }
    }
}
