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

        //Initiatlisation de l'image du piège
        this.icon = new ImageIcon(getClass().getResource("/images/trou.png"));
        this.image = this.icon.getImage().getScaledInstance(this.getLargeur(), this.getHauteur(), Image.SCALE_SMOOTH);
    }

    public void collision() {
        try {

            //*****Zone de detection carré*****//
            PreparedStatement requete = ConnexionBDD.getInstance().prepareStatement("SELECT pseudo, proprietaire FROM goat, piege "
                    + "WHERE pseudo = '" + Main.scene.getPseudo() + "' AND type = 'ravin' AND goat.x + 40 >= piege.x AND goat.x + 40 <= piege.x + 100 AND goat.y + 40 >= piege.y AND goat.y + 40 <= piege.y + 100");

            ResultSet resultat = requete.executeQuery();

            while (resultat.next()) {
                String pseudo = resultat.getString("pseudo");
                String proprietaire = resultat.getString("proprietaire");

                //Mort de la goat + réaparition au début de la fenetre
                PreparedStatement requete1 = ConnexionBDD.getInstance().prepareStatement("UPDATE goat SET nbVie = nbVie - 1, x = 0 WHERE pseudo = ? AND nbVie > 0");
                requete1.setString(1, pseudo);
                requete1.executeUpdate();

                requete1.close();

                System.out.println(pseudo + " killed by " + proprietaire);
                this.payDay(proprietaire);
                
                //DECLENCHEMENT D'UN BRUITAGE
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
