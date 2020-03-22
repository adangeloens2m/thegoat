/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TheGoatTuto;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Arnaud
 */
public class TilesTuto {
    
    private int TILE_WIDTH = 32;
    private int TILE_HEIGHT = 32;

    private int TILES_PER_ROW = 20;
    private int TILES_PER_COL = 20;
    
    private int[][] map;
    private BufferedImage tileSheet;
    
    public TilesTuto(int[][] existingMap){
        map = new int[existingMap.length][existingMap[0].length];
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                map[y][x] = existingMap.length;
            }
        }
        try {
            tileSheet = ImageIO.read(new File("tileSet.png"));
        } catch (IOException ex) {
            Logger.getLogger(Tiles.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void DrawLayer(Graphics g){
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
             int index = map[y][x];
             int yOffset = 0;
             
             if(index > (tileSheet.getWidth() / TILE_WIDTH) - 1){
                 yOffset++;
                 index = index - (tileSheet.getWidth() / TILE_WIDTH);
             }
             
             g.drawImage(tileSheet, 
                     x * TILE_WIDTH  , 
                     y * TILE_HEIGHT, 
                     (x % TILES_PER_ROW) * TILE_WIDTH + x % TILES_PER_ROW + 1, 
                     (y / TILES_PER_ROW) * TILE_HEIGHT + y / TILES_PER_ROW + 1,
                     index * TILE_WIDTH,
                     yOffset * TILE_HEIGHT,
                     (index * TILE_WIDTH) + index * TILE_WIDTH,
                     (yOffset * TILE_HEIGHT)+ yOffset * TILE_HEIGHT,
                     null);
            }
        }
    }
        
}
