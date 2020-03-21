package dessin;

import FenetreGraphique.FenetreGraphique;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Timer;

/**
 *
 * @author maxime.bulabois
 */

public class FenetreDeJeu {
   
    private static int TILE_WIDTH = 32;
    private static int TILE_HEIGHT = 32;

    private static int TILES_PER_ROW = 20;
    private static int TILES_PER_COL = 20;
    
    private static int longueurChamp = 30;
    private static int largeurChamp = 15;
    
    
    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) throws IOException {

        BufferedImage tileSet = ImageIO.read(new File("tileSet.png"));

        int [][] mat = new int [largeurChamp][longueurChamp];
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
        //mat[5][10]=35;
      
        int xFenetreG = 0;
        int yFenetreG = 0;
        
        FenetreGraphique fenetre = new FenetreGraphique("TheGoat", (longueurChamp + 5) * TILE_WIDTH, (largeurChamp + 5) * TILE_HEIGHT);
        Graphics2D contexte = fenetre.getGraphics2D();
        
        for(int i = 0; i<largeurChamp; i++){
            for(int j = 0; j<longueurChamp ; j++){
                xFenetreG = (j)*TILE_WIDTH;
                yFenetreG = (i)*TILE_HEIGHT;
                int tileNumber = mat [i][j];
                int tileX = (tileNumber % TILES_PER_ROW) * TILE_WIDTH + tileNumber % TILES_PER_ROW + 1;
                int tileY = (tileNumber / TILES_PER_ROW) * TILE_HEIGHT + tileNumber / TILES_PER_ROW + 1;
                BufferedImage tile = tileSet.getSubimage(tileX, tileY, TILE_WIDTH, TILE_HEIGHT);

                contexte.drawImage(tile, xFenetreG, yFenetreG, null);
                contexte.drawOval( 32*10 , 32*5 , 32 , 32);
                }
        
            fenetre.actualiser();
             
            }
        }


}
