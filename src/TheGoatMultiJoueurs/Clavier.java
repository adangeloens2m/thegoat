/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TheGoatMultiJoueurs;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Arnaud
 */
public class Clavier extends KeyAdapter {

    @Override
    public void keyPressed(KeyEvent e) {
        try {

            Connection connexion = DriverManager.getConnection("jdbc:mysql://nemrod.ens2m.fr:3306/20192020_s2_vs2_tp1_goat?serverTimezone=UTC", "goat", "9FdqUt5uXibSkOF8");

            PreparedStatement requete = connexion.prepareStatement("UPDATE goat SET x = x + ?, x = x - ?, y = y - ?, y = y + ? WHERE pseudo = ?");
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                requete.setInt(1, 100);
            } else {
                requete.setInt(1, 0);
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                requete.setInt(2, 100);
            } else {
                requete.setInt(2, 0);
            }
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                requete.setInt(3, 100);
            } else {
                requete.setInt(3, 0);
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                requete.setInt(4, 100);
            } else {
                requete.setInt(4, 0);
            }
            requete.setString(5, Main.scene.getPseudo());
            
            requete.executeUpdate();

            requete.close();
            connexion.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
