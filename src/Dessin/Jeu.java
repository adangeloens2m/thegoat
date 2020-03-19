package Dessin;

import FenetreGraphique.FenetreGraphique;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author maxime.bulabois
 */

public class Jeu {
    
    private static int TILE_WIDTH = 32;
    private static int TILE_HEIGHT = 32;

    private static int TILES_PER_ROW = 20;
    private static int TILES_PER_COL = 20;

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) throws IOException {

        BufferedImage tileSet = ImageIO.read(new File("tileSet.png"));

        int [][] mat = new int [19][90];
        for (int i = 0 ; i<mat.length ; i++){
            for (int j = 0 ; j<mat[i].length ; j++) {
                if(i==1){
                    mat[i][j] = 184;
                }
                else{
                    mat[i][j] = 21;  
                }
            }
        }
      
        int xFenetreG = 0;
        int yFenetreG = 0;
        
        FenetreGraphique fenetre = new FenetreGraphique("TheGoat", 100 * TILE_WIDTH, 20 * TILE_HEIGHT);
        Graphics2D contexte = fenetre.getGraphics2D();
        
        for(int i = 0; i<19; i++){
            for(int j = 0; j<90 ; j++){
                xFenetreG = (j)*TILE_WIDTH;
                yFenetreG = (i)*TILE_HEIGHT;
                int tileNumber = mat [i][j];
                int tileX = (tileNumber % TILES_PER_ROW) * TILE_WIDTH + tileNumber % TILES_PER_ROW + 1;
                int tileY = (tileNumber / TILES_PER_ROW) * TILE_HEIGHT + tileNumber / TILES_PER_ROW + 1;
                BufferedImage tile = tileSet.getSubimage(tileX, tileY, TILE_WIDTH, TILE_HEIGHT);

                contexte.drawImage(tile, xFenetreG, yFenetreG, null);
   
                }
        
            fenetre.actualiser();
            
            }
        }

    }