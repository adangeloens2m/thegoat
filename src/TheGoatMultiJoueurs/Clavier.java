/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TheGoatMultiJoueurs;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Arnaud
 */
public class Clavier extends KeyAdapter {

    @Override
    public void keyPressed(KeyEvent e) {

        //Deplacement goat
        if (Main.scene.getPersonnage() == "goat") {

            try {
                PreparedStatement requete = ConnexionBDD.getInstance().prepareStatement(
                        "UPDATE goat SET x = x + ?, x = x - ?, y = y - ?, y = y + ? WHERE pseudo = ?");
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    requete.setInt(1, 10);
                } else {
                    requete.setInt(1, 0);
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    requete.setInt(2, 10);
                } else {
                    requete.setInt(2, 0);
                }
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    requete.setInt(3, 10);
                } else {
                    requete.setInt(3, 0);
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    requete.setInt(4, 10);
                } else {
                    requete.setInt(4, 0);
                }
                requete.setString(5, Main.scene.getPseudo());

                requete.executeUpdate();

                requete.close();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        //Remise à zero des tables
        if (/*Main.scene.getPersonnage() == "admin" &&*/ e.getKeyCode() == KeyEvent.VK_R) {

            try {

                PreparedStatement requete = ConnexionBDD.getInstance().prepareStatement("DELETE FROM piege");

                requete.executeUpdate();

                requete.close();

                PreparedStatement requete1 = ConnexionBDD.getInstance().prepareStatement("DELETE FROM goat");

                requete1.executeUpdate();

                requete1.close();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        //Faire apparaître sa goat
        if (Main.scene.getPersonnage() == "goat" && e.getKeyCode() == KeyEvent.VK_P) {

            try {

                PreparedStatement requete = ConnexionBDD.getInstance().prepareStatement("INSERT INTO goat VALUES (?,?,?,?,?,?)");
                requete.setInt(1, 1);
                requete.setString(2, Main.scene.getPseudo());
                requete.setInt(3, 0);
                requete.setInt(4, 200);
                requete.setInt(5, 5);
                requete.setString(6, "Bleu");
                requete.executeUpdate();

                requete.close();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
