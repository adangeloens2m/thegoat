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
    
    private int xFond1;
    
    //Constructeur
    public Scene(){
        super();
        
        this.xFond1 = -50;
        icoFond = new ImageIcon(getClass().getResource("/images/grass.jpg"));
        this.imgFond1 = this.icoFond.getImage();
        icoGoat = new ImageIcon(getClass().getResource("/images/goat.png"));
        this.imgGoat = this.icoGoat.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        }
    
    //Méthodes
    
    public void paintComponent(Graphics g){
        
        super.paintComponent(g);
        Graphics g2 =  (Graphics2D) g;
        
        g2.drawImage(imgFond1, xFond1, 0, null);
        g2.drawImage(imgGoat, 0, 0, null);
        
        
    }
}
