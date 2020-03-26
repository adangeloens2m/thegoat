/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personnages;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author maxime.bulabois
 */
public class GOAT extends Personnage{
    
    private Image imageGoat;
    private ImageIcon iconGoat;
    
    
    //CONSTRUCTEUR
    public GOAT(int x, int y){
        super(x, y, 50, 50);
        this.iconGoat = new ImageIcon("src/images/goat.png");
        this.imageGoat = this.iconGoat.getImage();
    }
    
    //GETTERS
    public Image getImageGoat(){
        return imageGoat;
    }
    
}
