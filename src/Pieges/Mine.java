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
public class Mine extends Piege {

    private ImageIcon icon;
    private Image image;
    private ImageIcon icon2;
    private Image image2;

    public Mine(int x, int y, String proprietaire) {
        super(x, y, 50, 50, proprietaire, true, 200);

        //Initiatlisation de l'image du piège
        this.icon = new ImageIcon(getClass().getResource("/images/Mine.png"));
        this.image = this.icon.getImage().getScaledInstance(this.getLargeur(), this.getHauteur(), Image.SCALE_SMOOTH);
        this.icon2 = new ImageIcon(getClass().getResource("/images/Mine2.png"));
        this.image2 = this.icon2.getImage().getScaledInstance(this.getLargeur(), this.getHauteur(), Image.SCALE_SMOOTH);
    }

    public void collision() {
        try {

            //*****Zone de detection ronde*****//
            PreparedStatement requete = ConnexionBDD.getInstance().prepareStatement("SELECT pseudo, proprietaire, piege.x, piege.y FROM goat, piege "
                    + "WHERE pseudo = '" + Main.scene.getPseudo() + "' AND type = 'mine' AND piege.actif "
                    + "AND SQRT((goat.x + 40 - piege.x - 30)*(goat.x + 40 - piege.x - 30)+(goat.y + 40 - piege.y - 30)*(goat.y + 40 - piege.y - 30)) < 60");
            ResultSet resultat = requete.executeQuery();

            while (resultat.next()) {
                String pseudo = resultat.getString("pseudo");
                String proprietaire = resultat.getString("proprietaire");
                int coorx = resultat.getInt("x");
                int coory = resultat.getInt("y");

                //Mort de la goat + réaparition au début de la fenetre
                PreparedStatement requete1 = ConnexionBDD.getInstance().prepareStatement("UPDATE goat SET nbVie = nbVie - 1, x = 0 WHERE pseudo = ? AND nbVie > 0");
                requete1.setString(1, pseudo);
                requete1.executeUpdate();

                requete1.close();

                //Desactivation du piège
                PreparedStatement requete2 = ConnexionBDD.getInstance().prepareStatement("UPDATE piege SET actif = false WHERE type = 'mine' AND proprietaire = ? AND x = ? AND y = ?");
                requete2.setString(1, proprietaire);
                requete2.setInt(2, coorx);
                requete2.setInt(3, coory);
                requete2.executeUpdate();

                requete2.close();

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
        if (this.isActif()) {
            return image;
        } else {
            return image2;
        }
    }
}
