/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pieges;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author achen
 */
public class Bombe extends Piege {

    private ImageIcon icon;
    private Image image;

    public Bombe(int x, int y, String proprietaire, boolean actif) {
        super("bombe",40, 40, proprietaire, true);
        
        this.icon = new ImageIcon(getClass().getResource("/images/bombe.png"));
        this.image = this.icon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
    }

//    public void explosion(Goat goat) {
//        if (this.getX() == goat.getX() && this.getY() == goat.getY()) {
//            try {
//                PreparedStatement requete = ConnexionBDD.getInstance().prepareStatement("UPDATE piege SET actif = ? WHERE type = ? AND x = ? AND y = ?");
//                requete.setBoolean(1, false);
//                requete.setString(2, "bombe");
//                requete.setInt(3, this.getX());
//                requete.setInt(4, this.getY());
//                requete.executeUpdate();
//                requete.close();
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//            }
//        }
//    }

    public Image getImage() {
        return image;
    }
}
