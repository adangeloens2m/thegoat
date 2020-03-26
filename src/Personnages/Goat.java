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
public class Goat extends Personnage{
    
    private Image imageGoat;
    private ImageIcon iconGoat;
    
    private String pseudo;
    
    private int nbVie;
    
    private int skin;
    
    //CONSTRUCTEUR
    public Goat(int x, int y, String pseudo){
        super(x, y, 50, 50);
        this.pseudo = pseudo;
        this.skin = skin;
        this.iconGoat = new ImageIcon(getClass().getResource("/images/goat.png"));
        this.imageGoat = this.iconGoat.getImage();
    }
    
    //GETTERS
    public Image getImageGoat(){
        return imageGoat;
    }
    
}
