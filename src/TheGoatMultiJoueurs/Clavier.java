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

        if (Main.scene.getPersonnage() == "goat") {

            try {
                //Connection connexion = DriverManager.getConnection("jdbc:mysql://nemrod.ens2m.fr:3306/20192020_s2_vs2_tp1_goat?serverTimezone=UTC", "goat", "9FdqUt5uXibSkOF8");
                PreparedStatement requete = ConnexionBDD.getInstance().prepareStatement("UPDATE goat, piege "
                        + "SET goat.x = goat.x + ?, goat.x = goat.x - ?, goat.y = goat.y - ?, goat.y = goat.y + ?, piege.nbVie = ? WHERE pseudo = ?");
                
                PreparedStatement requete1 = ConnexionBDD.getInstance().prepareStatement("DELETE FROM piege");
                
                if (e.getKeyCode() == KeyEvent.VK_R){
                    requete.setInt(5, 5);
                    requete1.executeUpdate();
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    requete.setInt(1, 1);
                } else {
                    requete.setInt(1, 0);
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    requete.setInt(2, 1);
                } else {
                    requete.setInt(2, 0);
                }
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    requete.setInt(3, 1);
                } else {
                    requete.setInt(3, 0);
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    requete.setInt(4, 1);
                } else {
                    requete.setInt(4, 0);
                }
                
                requete.setString(6, Main.scene.getPseudo());
                requete.executeUpdate();

                requete.close();
                requete1.close();
                //connexion.close();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
