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
 * @author paulb
 */
public class Ravin extends Piege {
    private ImageIcon icon;
    private Image image;
    
    public Ravin(int x, int y, String proprietaire) {
        super(x, y, 100, 100, proprietaire, true, 200);
        
        this.icon = new ImageIcon(getClass().getResource("/images/trou.png"));
        this.image = this.icon.getImage().getScaledInstance(this.getLargeur(), this.getHauteur(), Image.SCALE_SMOOTH);
    } 
    
    public void collision() {
        try {

            //*****Zone de detection carré*****//
            PreparedStatement requete = ConnexionBDD.getInstance().prepareStatement("SELECT pseudo, proprietaire FROM goat, piege "
                    + "WHERE pseudo = '" + Main.scene.getPseudo() + "' AND type = 'ravin' AND goat.x + 40 >= piege.x AND goat.x + 40 <= piege.x + 90 AND goat.y + 40 >= piege.y AND goat.y + 40 <= piege.y + 100");
           
            //*****Zone de detection ronde*****//
//            PreparedStatement requete = ConnexionBDD.getInstance().prepareStatement("SELECT pseudo, proprietaire FROM goat, piege "
//                    + "WHERE type = 'ravin' AND piege.actif "
//                    + "AND SQRT((goat.x + 40 - piege.x - 5)*(goat.x + 40 - piege.x - 5)+(goat.y + 50 - piege.y - 20)*(goat.y + 50 - piege.y - 20)) < '" + this.getLargeur() + "'");
            ResultSet resultat = requete.executeQuery();

            while (resultat.next()) {
                String pseudo = resultat.getString("pseudo");
                String proprietaire = resultat.getString("proprietaire");
                
                PreparedStatement requete1 = ConnexionBDD.getInstance().prepareStatement("UPDATE goat SET nbVie = nbVie - 1, x = 0 WHERE pseudo = ? AND nbVie > 0");
                requete1.setString(1, pseudo);
                requete1.executeUpdate();

                requete1.close();
                
                this.payDay(proprietaire);

                System.out.println(pseudo + " killed by " + proprietaire);
            }

            requete.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Image getImage() {
        return image;
    }
}
