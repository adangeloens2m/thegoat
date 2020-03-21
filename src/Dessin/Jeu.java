/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dessin;

/**
 *
 * @author maxime.bulabois
 */
public class Jeu {
    
//    private int TILE_WIDTH = 32;
//    private int TILE_HEIGHT = 32;
//
//    private int TILES_PER_ROW = 20;
//    private int TILES_PER_COL = 20;
//    
    private int longueurChamp;
    private int largeurChamp;
    private int mat[][];
    private int coorX;
    private int coorY;
    private int valeurTile;
   
    
    public int[][] creationMat(int longueurChamp, int largeurChamp){
        int mat[][] = new int[longueurChamp][largeurChamp];
        return mat;
    }
    
    
    public void setMat(int coorX, int coorY, int valeurTile) {
        mat[coorX][coorY] = valeurTile;
    }
    
    
}
