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
    
    private int indice = 0;
    
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
        } else if (Main.scene.getPersonnage() == "loup"){
            if (e.getKeyCode() == KeyEvent.VK_RIGHT){ //&& indice <= nombre de piege possible){
                System.out.println(Main.scene.getIndice());
                Main.scene.setIndice(1);
                System.out.println(Main.scene.getIndice());
            } else if (e.getKeyCode() == KeyEvent.VK_LEFT && indice >= 0){
                System.out.println(Main.scene.getIndice());
                Main.scene.setIndice(-1);
                System.out.println(Main.scene.getIndice());
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
                
                PreparedStatement requete2 = ConnexionBDD.getInstance().prepareStatement("DELETE FROM loup");

                requete2.executeUpdate();

                requete2.close();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}