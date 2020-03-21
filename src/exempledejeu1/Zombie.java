/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exempledejeu1;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * Exemple de classe monstre
 * 
 * @author guillaume.laurent
 */
public class Zombie {

    protected BufferedImage sprite;
    protected double x, y;
    protected int ligne;

    public Zombie(int ligne) {
        try {
            this.sprite = ImageIO.read(getClass().getClassLoader().getResource("resources/zombie.png"));
        } catch (IOException ex) {
            Logger.getLogger(Jeu.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.ligne = ligne;
        this.x = 700;
        this.y = 80 + ligne * 80;
    }

    public void miseAJour() {
        if (x > 150) {
            x = x - 1;
        }
    }

    public void rendu(Graphics2D contexte) {
        contexte.drawImage(this.sprite, (int) x, (int) y, null);
    }

}
