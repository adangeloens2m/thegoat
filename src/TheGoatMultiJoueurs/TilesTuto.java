/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TheGoatMultiJoueurs;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Arnaud and Maxime
 */
public class TilesTuto {

    private int TILE_WIDTH = 33;
    private int TILE_HEIGHT = 33;

    private int TILES_PER_ROW = 20;
    private int TILES_PER_COL = 20;
    
    private int longueurChamp;
    private int largeurChamp;

    private int[][] mat;
    private BufferedImage tileSet;

    public TilesTuto(int longueurChamp, int largeurChamp) {
        this.largeurChamp = largeurChamp;
        this.longueurChamp = longueurChamp;
        
        //Création de la matrice
        mat = new int[largeurChamp][longueurChamp];
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                if (j == 0) {
                    mat[i][j] = 191;
                } else if (j == longueurChamp - 1) {
                    mat[i][j] = 69;
                } else if (i == 0 || i == largeurChamp - 1) {
                    mat[i][j] = 184;
                } else if (i == 10 && j == 28) {
                    mat[i][j] = 15;
                } else if (i == 8 && j == 15) {
                    mat[i][j] = 16;
                } else if (i == 3 && j == 26) {
                    mat[i][j] = 35;
                } else if (i ==3 && j == 27){
                    mat[i][j] = 36;
                } else if (mat[i-1][j] == 35){
                    mat[i][j] = 55;
                }else if (mat[i-1][j] == 36){
                    mat[i][j] = 56;
                }else {
                    mat[i][j] = 21;
                }
            }
        }

        try {
            tileSet = ImageIO.read(getClass().getResource("/images/tileSet2.png"));
        } catch (IOException ex) {
            Logger.getLogger(TilesTuto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Disposition des tiles
    public void DrawLayer(Graphics g) {
        for (int j = 0; j < mat.length; j++) {
            for (int i = 0; i < mat[j].length; i++) {
                int colonneTileSet = mat[j][i] % TILES_PER_ROW;
                int ligneTileSet = mat[j][i] / TILES_PER_ROW;

                g.drawImage(tileSet,
                        i * TILE_WIDTH - Main.scene.getxDynamique(),
                        j * TILE_HEIGHT,
                        i * TILE_WIDTH + TILE_WIDTH - Main.scene.getxDynamique(),
                        j * TILE_HEIGHT + TILE_HEIGHT,
                        colonneTileSet * TILE_WIDTH + 1,
                        ligneTileSet * TILE_HEIGHT + 1,
                        colonneTileSet * TILE_WIDTH + TILE_WIDTH,
                        ligneTileSet * TILE_HEIGHT + TILE_HEIGHT,
                        null);
            }
        }
    }
    
    public int getWidth(){
        return (longueurChamp - 1)* TILE_WIDTH;
    }
}
