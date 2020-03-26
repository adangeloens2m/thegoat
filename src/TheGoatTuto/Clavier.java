/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TheGoatTuto;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Arnaud
 */
public class Clavier extends KeyAdapter{

//    @Override
//    public void keyPressed(KeyEvent e) {
//        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
//            Main.scene.goat.setDx(1);
//        } else if (e.getKeyCode() == KeyEvent.VK_LEFT){
//            Main.scene.goat.setDx(-1);
//        }else if (e.getKeyCode() == KeyEvent.VK_UP){
//            Main.scene.goat.setDy(-1);
//        }else if (e.getKeyCode() == KeyEvent.VK_DOWN){
//            Main.scene.goat.setDy(1);
//        }
//    }
    
        @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            try {

            Connection connexion = DriverManager.getConnection("jdbc:mysql://nemrod.ens2m.fr:3306/20192020_s2_vs2_tp1_goat", "etudiant", "YTDTvj9TR3CDYCmP");

            PreparedStatement requete = connexion.prepareStatement("UPDATE goat SET x = x + 1 WHERE pseudo = ?");
            requete.setString(1, Main.scene.getPseudo());
            System.out.println(requete);
            requete.executeUpdate();

            requete.close();
            connexion.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT){
            try {

            Connection connexion = DriverManager.getConnection("jdbc:mysql://nemrod.ens2m.fr:3306/20192020_s2_vs2_tp1_goat", "etudiant", "YTDTvj9TR3CDYCmP");

            PreparedStatement requete = connexion.prepareStatement("UPDATE goat SET x = x - 1 WHERE pseudo = ?");
            requete.setString(1, Main.scene.getPseudo());
            System.out.println(requete);
            requete.executeUpdate();

            requete.close();
            connexion.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        }else if (e.getKeyCode() == KeyEvent.VK_UP){
            try {

            Connection connexion = DriverManager.getConnection("jdbc:mysql://nemrod.ens2m.fr:3306/20192020_s2_vs2_tp1_goat", "etudiant", "YTDTvj9TR3CDYCmP");

            PreparedStatement requete = connexion.prepareStatement("UPDATE goat SET y = y + 1 WHERE pseudo = ?");
            requete.setString(1, Main.scene.getPseudo());
            System.out.println(requete);
            requete.executeUpdate();

            requete.close();
            connexion.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        }else if (e.getKeyCode() == KeyEvent.VK_DOWN){
            try {

            Connection connexion = DriverManager.getConnection("jdbc:mysql://nemrod.ens2m.fr:3306/20192020_s2_vs2_tp1_goat", "etudiant", "YTDTvj9TR3CDYCmP");

            PreparedStatement requete = connexion.prepareStatement("UPDATE goat SET y = y - 1 WHERE pseudo = ?");
            requete.setString(1, Main.scene.getPseudo());
            System.out.println(requete);
            requete.executeUpdate();

            requete.close();
            connexion.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        }
    }

//    @Override
//    public void keyReleased(KeyEvent e) {
//        Main.scene.goat.setDx(0);
//        Main.scene.goat.setDy(0);
//    }
    
        @Override
    public void keyReleased(KeyEvent e) {
        Main.scene.goat.setDx(0);
        Main.scene.goat.setDy(0);
    }
    
}
