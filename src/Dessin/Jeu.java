package Dessin;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author maxime.bulabois
 */
public class Jeu {
    private BufferedImage jardin;
    private GOAT uneGoat;

    public Jeu() {
        try {
           this.jardin = ImageIO.read(getClass().getClassLoader().getResource("resources/jardin.jpg"));
        } catch (IOException ex) {
            Logger.getLogger(Jeu.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.uneGoat = new GOAT(5,5);
    }

    public void miseAJour() {
        this.uneGoat.miseAJour();
    }

    public void rendu(Graphics2D contexte) {
        contexte.drawImage(this.jardin, 0, 0, null);
        this.uneGoat.rendu(contexte); 
    }

}
