/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TheGoatTuto;

import FenetreGraphique.FenetreGraphique;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author maxime.bulabois
 */

public class Tiles {
    
    private int TILE_WIDTH = 32;
    private int TILE_HEIGHT = 32;

    private int TILES_PER_ROW = 20;
    private int TILES_PER_COL = 20;
    
    private int longueurChamp = 30;
    private int largeurChamp = 15;
    
//    public Graphics2D fondDeJeu() throws IOException {
//
//        BufferedImage tileSet = ImageIO.read(new File("tileSet.png"));
//        int[][] mat = new int[largeurChamp][longueurChamp];
//
//        for (int i = 0; i < mat.length; i++) {
//            for (int j = 0; j < mat[i].length; j++) {
//                if (i == 1) {
//                    mat[i][j] = 184;
//                } else {
//                    mat[i][j] = 21;
//                }
//            }
//        }
//
//        int xFenetreG = 0;
//        int yFenetreG = 0;
//
//        FenetreGraphique fenetre = new FenetreGraphique("TheGoat", (longueurChamp + 5) * TILE_WIDTH, (largeurChamp + 5) * TILE_HEIGHT);
//        Graphics2D contexte = fenetre.getGraphics2D();
//
//        for (int i = 0; i < largeurChamp; i++) {
//            for (int j = 0; j < longueurChamp; j++) {
//                xFenetreG = (j) * TILE_WIDTH;
//                yFenetreG = (i) * TILE_HEIGHT;
//                int tileNumber = mat[i][j];
//                int tileX = (tileNumber % TILES_PER_ROW) * TILE_WIDTH + tileNumber % TILES_PER_ROW + 1;
//                int tileY = (tileNumber / TILES_PER_ROW) * TILE_HEIGHT + tileNumber / TILES_PER_ROW + 1;
//                BufferedImage tile = tileSet.getSubimage(tileX, tileY, TILE_WIDTH, TILE_HEIGHT);
//
//                contexte.drawImage(tile, xFenetreG, yFenetreG, null);
//            }
//        }
//        
//        return contexte;
//    }
    
    private int[][] map;
    private BufferedImage tileSheet;
    
    public Tiles(int[][] existingMap){
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
