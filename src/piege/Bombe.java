/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piege;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author achen
 */
public class Bombe extends Piege{
    private Image imgBombe;
    private ImageIcon icoBombe;

    public Bombe(Image imgBombe, ImageIcon icoBombe, String type, int x, int y, String proprietaire, boolean actif) {
        super(type, x, y, proprietaire, actif);
        this.imgBombe = this.icoBombe.getImage();
        this.icoBombe = new ImageIcon("/images/bombe.png");
    }

    public Image getImgBombe() {
        return imgBombe;
    }
    

   
    
    
}
