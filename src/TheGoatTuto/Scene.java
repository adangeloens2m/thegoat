/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TheGoatTuto;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Arnaud
 */
public class Scene extends JPanel {

    private ImageIcon icoFond;
    private Image imgFond1;

    private ImageIcon icoGoat;
    private Image imgGoat;

    private ImageIcon icoBombe;
    private Image imgBombe;

    private int xGoat;
    private int dx;

    private int yGoat;
    private int dy;

    private int xBombe;

    private int yBombe;
    
    private Tiles layer;

    //Constructeur
    public Scene(){
        super();

//        this.xGoat = 0;
//        this.yGoat = 0;
        this.xBombe = -100;
        this.yBombe = -100;
        
        layer = new Tiles(new int[][]{
            {184,184,0,184,0,0,184,0,0}
        });
        
        icoFond = new ImageIcon(getClass().getResource("/images/grass.jpg"));
        this.imgFond1 = this.icoFond.getImage();
        icoGoat = new ImageIcon(getClass().getResource("/images/goat.png"));
        this.imgGoat = this.icoGoat.getImage().getScaledInstance(90, 100, Image.SCALE_SMOOTH);
        icoBombe = new ImageIcon(getClass().getResource("/images/bombe.png"));
        this.imgBombe = this.icoBombe.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);

        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(new Clavier());
        this.addMouseListener(new Souris());

        Thread chronoEcran = new Thread(new Chrono());
        chronoEcran.start();
    }

    //Méthodes
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics g2 = (Graphics2D) g;

        deplacementx();
        deplacementy();
        
        layer.DrawLayer(g2);
        g2.drawImage(imgGoat, xGoat, yGoat, null);
        g2.drawImage(imgBombe, xBombe, yBombe, null);
    }
    
    public void deplacementx() {
        if (dx == 1) {
            xGoat = Math.min(xGoat + dx, Main.scene.getWidth() - imgGoat.getWidth(null));
        } else if (dx == -1) {
            xGoat = Math.max(xGoat + dx, 0);
        }
    }

    public void deplacementy() {
        if (dy == 1) {
            yGoat = Math.min(yGoat + dy, Main.scene.getHeight() - imgGoat.getHeight(null));
        } else if (dy == -1) {
            yGoat = Math.max(yGoat + dy, 0);
        }
    }

    //Getters
    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    public int getxBombe() {
        return xBombe;
    }

    public int getyBombe() {
        return yBombe;
    }

    //Setters
    public void setDx(int dx) {
        this.dx = dx;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public void setxBombe(int xPiege) {
        this.xBombe = xPiege;
    }

    public void setyBombe(int yPiege) {
        this.yBombe = yPiege;
    }
}
