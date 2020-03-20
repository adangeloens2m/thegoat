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
public class GOAT {
    private int ligne, colonne;
    private BufferedImage apparence;
    
    public GOAT(int ligne, int colonne){
        try {
            this.apparence = ImageIO.read(getClass().getClassLoader().getResource("Apparence.png"));
        } catch (IOException ex) {
            Logger.getLogger(FenetreDeJeu.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.ligne = 20*ligne;
        this.colonne = 20*colonne;
    }
    
    public void miseAJour() {
        
    }

    
    public void rendu(Graphics2D contexte) {
        contexte.drawImage(this.apparence, (int) ligne, (int) colonne, null);
    }
}
