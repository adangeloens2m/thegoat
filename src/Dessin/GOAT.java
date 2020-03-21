package Dessin;


import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author maxime.bulabois
 */
public class GOAT  extends JFrame {
    private int ligne, colonne;
    private BufferedImage apparence;
    
    public GOAT(int ligne, int colonne){
        try {
            this.apparence = ImageIO.read(getClass().getClassLoader().getResource("Apparence.png"));
        } catch (IOException ex) {
            Logger.getLogger(FenetreDeJeu.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.ligne = 32*ligne;
        this.colonne = 32*colonne;
    }
    
    public void miseAJour() {
        
    }

    
    public void rendu(Graphics2D contexte) {
        contexte.drawOval((int) ligne, (int) colonne, 20 , 20);
    }
}
