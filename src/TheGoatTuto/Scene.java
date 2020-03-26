/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TheGoatTuto;

import Personnages.GOAT;
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

    private TilesTuto tileMap;

    private ImageIcon icoGoat;
    private Image imgGoat;

    private ImageIcon icoBombe;
    private Image imgBombe;

    private int xGoat;
    private int dx;

    private int yGoat;
    private int dy;

    private int xBombe;
    private int xSuiviBombe;

    private int yBombe;

    private int timer;

    private boolean bombeClic;
    
    private GOAT Goat;
    //Constructeur
    public Scene() {
        super();

        this.xBombe = -100;
        this.yBombe = -100;

        this.tileMap = new TilesTuto(16, 50);

        Goat = new GOAT(90 , 100);
        
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
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics g2 = (Graphics2D) g;

        tileMap.DrawLayer(g2);

        g2.drawImage(this.imgGoat, xGoat, yGoat, null);
        g2.drawImage(imgBombe, xBombe - xSuiviBombe, yBombe, null);
    }

    public void runMethodes() {
        deplacementGoat();

        if (timer == 25) {
            //Méthodes ralenties
            tileMap.move();
            win();
            if (bombeClic) {
                xSuiviBombe++;
            }
            //
            timer = 0;
        }
        timer++;
    }

    public void deplacementGoat() {
        if (dx == 1) {
            xGoat = Math.min(xGoat + dx, Main.scene.getWidth() - imgGoat.getWidth(null));
        }
        if (dx == -1) {
            xGoat = Math.max(xGoat + dx, 0);
        }
        if (dy == 1) {
            yGoat = Math.min(yGoat + dy, Main.scene.getHeight() - imgGoat.getHeight(null));
        }
        if (dy == -1) {
            yGoat = Math.max(yGoat + dy, 0);
        }
    }

    public void win() {
        if (xGoat + dx + tileMap.getxDynamique() == tileMap.getWidth()) {
            System.out.println("You Won !!");
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

    public void setBombeClic(boolean bombeClic) {
        this.bombeClic = bombeClic;
    }
}
