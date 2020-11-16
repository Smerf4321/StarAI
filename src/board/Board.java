/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package board;

/**
 *
 * @author Patryk
 */
public final class Board {
    private int width;
    private int height;
    private Spot[][] map;
    
    public Board(){
        width = 10;
        height = 7;
        resetMap();
    }
    
    public Spot getSpot (int x, int y){
        return map[x][y];
    }
    
    public void resetMap (){
        for (int x = 0; x < 10; x++){
            for (int y = 0; y < 7; y++){
                map[x][y] = new Spot(x, y, null);
            }
        }
    }
}
