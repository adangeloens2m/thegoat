/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pieges;

import Personnages.Goat;
import TheGoatMultiJoueurs.ConnexionBDD;
import java.awt.Image;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.ImageIcon;

/**
 *
 * @author achen
 */
public class Bombe extends Piege{
    private Image imgBombe;
    private ImageIcon icoBombe;

    public Bombe(int x, int y, String proprietaire) {
        super(x, y, 30, 30, proprietaire, false);
        this.icoBombe = new ImageIcon(getClass().getResource("/images/bombe.png"));
        this.imgBombe = this.icoBombe.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
    }

    public Image getImgBombe() {
        return imgBombe;
    }
    
    public void xSuivi(){
        this.setX(this.getX() - 1);
    }
    
    public void explosion(Goat goat){
        if (this.getX() == goat.getX() && this.getY() == goat.getY()){
            try {
                PreparedStatement requete = ConnexionBDD.getInstance().prepareStatement("UPDATE piege SET actif = ? WHERE type = ? AND x = ? AND y = ?");
                requete.setBoolean(1, false);
                requete.setString(2,"bombe");
                requete.setInt(3,this.getX());
                requete.setInt(4,this.getY());
                requete.executeUpdate();
                requete.close();
            }catch (SQLException ex) {
                ex.printStackTrace();
        }
    }
    }
}
