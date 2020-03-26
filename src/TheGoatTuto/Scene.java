/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TheGoatTuto;

import Personnages.Goat;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import Pieges.Bombe;

/**
 *
 * @author Arnaud
 */
public class Scene extends JPanel {

    private TilesTuto tileMap;

    private int xGoat;
    private int dx;

    private int yGoat;
    private int dy;

    private int xSuiviBombe;

    private int timer;
    
    public Goat goat;
    
    public Bombe bombe;
    
    //Constructeur
    public Scene() {
        super();

        this.tileMap = new TilesTuto(16, 50);

        this.goat = new Goat(0 , 0, "Arnaud", "goat");
        
        this.bombe = new Bombe(-100, -100, "Arnaud");
        
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

        g2.drawImage(goat.getImageGoat(), goat.getX(), goat.getY(), null);
        g2.drawImage(bombe.getImgBombe(), bombe.getX() - xSuiviBombe, bombe.getY(), null);
    }

    public void runMethodes() {
        goat.deplacement();

        if (timer == 25) {
            //Méthodes ralenties
            tileMap.move();
            win();
            if (bombe.isActif()) {
                xSuiviBombe++;
            }
            //
            timer = 0;
        }
        timer++;
    }

    public void win() {
        if (xGoat + dx + tileMap.getxDynamique() == tileMap.getWidth()) {
            System.out.println("You Won !!");
        }
    }

    //Getters

    //Setters
}
