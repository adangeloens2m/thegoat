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
    
    private BufferedImage image;
    
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
    
        public BufferedImage fondDeJeu() throws IOException {

        BufferedImage tileSet = ImageIO.read(new File("tileSet.png"));
        BufferedImage fondDeJeu = this.image;
                //new BufferedImage ((longueurChamp + 5)*TILE_WIDTH, (largeurChamp + 5)*TILE_HEIGHT, BufferedImage.TYPE_4BYTE_ABGR);
        int[][] mat = new int[largeurChamp][longueurChamp];

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                if (i == 1) {
                    mat[i][j] = 184;
                } else {
                    mat[i][j] = 21;
                }
            }
        }

        int xImage = 0;
        int yImage = 0;

        Graphics2D contexte = fondDeJeu.createGraphics();
        
        for (int i = 0; i < largeurChamp; i++) {
            for (int j = 0; j < longueurChamp; j++) {
                xImage = (j) * TILE_WIDTH;
                yImage = (i) * TILE_HEIGHT;
                int tileNumber = mat[i][j];
                int tileX = (tileNumber % TILES_PER_ROW) * TILE_WIDTH + tileNumber % TILES_PER_ROW + 1;
                int tileY = (tileNumber / TILES_PER_ROW) * TILE_HEIGHT + tileNumber / TILES_PER_ROW + 1;
                BufferedImage tile = tileSet.getSubimage(tileX, tileY, TILE_WIDTH, TILE_HEIGHT);

                contexte.drawImage(tile, xImage, yImage, null);
            }
        }
        contexte.dispose();
        
        return fondDeJeu;
    }
    
    
}
