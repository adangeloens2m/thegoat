/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pieges;

import TheGoatMultiJoueurs.ConnexionBDD;
import TheGoatMultiJoueurs.Main;
import java.awt.Image;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;

/**
 *
 * @author maxime.bulabois
 */
public class ExplosifTC extends Piege {
    
    private ImageIcon icon;
    private Image image;
    private ImageIcon icon2;
    private Image image2;
    
    
    public ExplosifTC(int x, int y, String proprietaire) {
        super(x, y, 60, 60, proprietaire, true);
        
        this.icon = new ImageIcon(getClass().getResource("/images/ExplosifTC.png"));
        this.image = this.icon.getImage().getScaledInstance(this.getLargeur(), this.getHauteur(), Image.SCALE_SMOOTH);
        this.icon2 = new ImageIcon(getClass().getResource("/images/bombe2.png"));
        this.image2 = this.icon2.getImage().getScaledInstance(this.getLargeur(), this.getHauteur(), Image.SCALE_SMOOTH);
    } 
    
    
    public void collision() {
        try {

            //*****Zone de detection carrée*****//
//            PreparedStatement requete = ConnexionBDD.getInstance().prepareStatement("SELECT pseudo, proprietaire FROM goat, piege "
//                    + "WHERE goat.x + 40 >= piege.x AND goat.x + 40 <= piege.x + 90 AND goat.y + 40 >= piege.y AND goat.y + 40 <= piege.y + 100");

            //*****Zone de detection ronde*****//
            PreparedStatement requete = ConnexionBDD.getInstance().prepareStatement("SELECT pseudo, proprietaire, piege.x, piege.y FROM goat, piege "
                    + "WHERE pseudo = '" + Main.scene.getPseudo() + "' AND type = 'explosifTC' AND piege.actif "
                    + "AND SQRT((goat.x + 40 - piege.x - 30)*(goat.x + 40 - piege.x - 30)+(goat.y + 40 - piege.y - 40)*(goat.y + 40 - piege.y - 40)) < 40");
            ResultSet resultat = requete.executeQuery();

            while (resultat.next()) {
                String pseudo = resultat.getString("pseudo");
                String proprietaire = resultat.getString("proprietaire");
                int coorx = resultat.getInt("x");
                int coory = resultat.getInt("y");
                
                PreparedStatement requete1 = ConnexionBDD.getInstance().prepareStatement("UPDATE goat SET nbVie = nbVie - 1, x = 0 WHERE pseudo = ? AND nbVie > 0");
                requete1.setString(1, pseudo);
                requete1.executeUpdate();

                requete1.close();
                
                PreparedStatement requete2 = ConnexionBDD.getInstance().prepareStatement("UPDATE piege SET actif = false WHERE type = 'explosifTC' AND proprietaire = ? AND x = ? AND y = ?");
                requete2.setString(1, proprietaire);
                requete2.setInt(2, coorx);
                requete2.setInt(3, coory);
                requete2.executeUpdate();

                requete2.close();

                System.out.println(pseudo + " killed by " + proprietaire);
            }

            requete.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
   
    public Image getImage() {
        if(this.isActif()){
            return image;
        } else {
            return image2;
        }
    }
}
