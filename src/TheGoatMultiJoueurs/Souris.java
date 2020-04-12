/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TheGoatMultiJoueurs;

import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author maxime.bulabois
 */
public class Souris extends MouseAdapter {

    @Override
    public void mouseClicked(MouseEvent e) {

        // commande pour insérer l'utilisation d'un nouveau piège dans la table
        if (Main.scene.getPersonnage() == "loup") {
            
            if (Main.scene.getIndice() == 0){

                if (e.getButton() == MouseEvent.BUTTON1) {

                    try {

                        PreparedStatement requete = ConnexionBDD.getInstance().prepareStatement("INSERT INTO piege VALUES (?,?,?,?,?,?)");
                        requete.setInt(1, 3);
                        requete.setString(2, "bombe");
                        requete.setInt(3, e.getX());
                        requete.setInt(4, e.getY());
                        requete.setString(5, Main.scene.getPseudo());
                        requete.setBoolean(6, true);
                        requete.executeUpdate();

                        requete.close();

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            } else if (Main.scene.getIndice() == 1){

                if (e.getButton() == MouseEvent.BUTTON1) {
                    try {

                        PreparedStatement requete = ConnexionBDD.getInstance().prepareStatement("INSERT INTO piege VALUES (?,?,?,?,?,?)");
                        requete.setInt(1, 3);
                        requete.setString(2, "ravin");
                        requete.setInt(3, e.getX());
                        requete.setInt(4, e.getY());
                        requete.setString(5, Main.scene.getPseudo());
                        requete.setBoolean(6, true);
                        requete.executeUpdate();

                        requete.close();

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }
}
