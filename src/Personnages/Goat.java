/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personnages;

import TheGoatTuto.Main;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author maxime.bulabois
 */
public class Goat extends Personnage {

    private Image imageGoat;
    private ImageIcon iconGoat;

    private String pseudo;

    private int nbVie;

    private int skin;

    private int dx;
    private int dy;

    //CONSTRUCTEUR
    public Goat(int x, int y, String pseudo, String skin) {
        super(x, y, 80, 80);
        this.pseudo = pseudo;
        this.iconGoat = new ImageIcon(getClass().getResource("/images/" + skin + ".png"));
        this.imageGoat = this.iconGoat.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
    }

    //GETTERS
    public Image getImageGoat() {
        return imageGoat;
    }
    
    //Setters

    public void setDx(int dx) {
        this.dx = dx;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }
    
    
    //Methodes
        public void deplacement() {
        if (dx == 1) {
            x = Math.min(x + dx, Main.scene.getWidth() - imageGoat.getWidth(null));
        }
        if (dx == -1) {
            x = Math.max(x + dx, 0);
        }
        if (dy == 1) {
            y = Math.min(y + dy, Main.scene.getHeight() - imageGoat.getHeight(null));
        }
        if (dy == -1) {
            y = Math.max(y + dy, 0);
        }
    }
}
