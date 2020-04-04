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
                PreparedStatement requete = ConnexionBDD.getInstance().prepareStatement("UPDATE goat, piege "
                        + "SET goat.x = goat.x + ?, goat.x = goat.x - ?, goat.y = goat.y - ?, goat.y = goat.y + ? "
                        + "WHERE pseudo = ?");
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
                requete.setString(5, Main.scene.getPseudo());

                requete.executeUpdate();

                requete.close();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        
        if (e.getKeyCode() == KeyEvent.VK_R) {

            try {

                PreparedStatement requete = ConnexionBDD.getInstance().prepareStatement("DELETE FROM piege");
                
                requete.executeUpdate();
                
                requete.close();
                
                
                PreparedStatement requete1 = ConnexionBDD.getInstance().prepareStatement("UPDATE goat SET nbVie = 5");
                
                requete1.executeUpdate();
                
                requete1.close();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
