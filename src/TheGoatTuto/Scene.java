/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TheGoatTuto;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
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

    private int xGoat;
    private int dx;
    
    private int yGoat;
    private int dy;

    //Constructeur
    public Scene() {
        super();

        this.xGoat = 0;
        this.yGoat = 0;
        this.dx = 0;
        this.dy = 0;
        icoFond = new ImageIcon(getClass().getResource("/images/grass.jpg"));
        this.imgFond1 = this.icoFond.getImage();
        icoGoat = new ImageIcon(getClass().getResource("/images/goat.png"));
        this.imgGoat = this.icoGoat.getImage().getScaledInstance(94, 102, Image.SCALE_SMOOTH);
        
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(new Clavier());

        Thread chronoEcran = new Thread(new Chrono());
        chronoEcran.start();
    }

    //Méthodes
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics g2 = (Graphics2D) g;
        
        deplacementx();
        System.out.println(xGoat + " " + yGoat);
        deplacementy();

        g2.drawImage(imgFond1, 0, 0, null);
        g2.drawImage(imgGoat, xGoat, yGoat, null);
    }
    
    public void deplacementx() {
        if(xGoat + dx < 600){
            xGoat = xGoat + dx;
        }
    }
    
    public void deplacementy() {
        if(yGoat + dy < 480){
            yGoat = yGoat + dy;
        }
    }

    //Getters
    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }
    
    //Setters
    public void setDx(int dx) {
        this.dx = dx;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }
}
