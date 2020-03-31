/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TheGoatMultiJoueurs;

import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 *
 * @author maxime.bulabois
 */
public class Souris extends MouseAdapter {
    

    @Override
    public void mouseClicked(MouseEvent e) {
        Main.scene.bombe.setX(e.getX());
        Main.scene.bombe.setY(e.getY());
        Main.scene.bombe.setActif(true);
        
            try {

            //Connection connexion = DriverManager.getConnection("jdbc:mysql://nemrod.ens2m.fr:3306/20192020_s2_vs2_tp1_goat?serverTimezone=UTC", "goat", "9FdqUt5uXibSkOF8");

                  // commande pour supprimer si besoin des lignes de la table piège 
//                PreparedStatement requete = connexion.prepareStatement("DELETE FROM piege WHERE id = 1");
//                requete.executeUpdate();
//                requete.close();                  
            // commande pour insérer l'utilisation d'un nouveau piège dans la table
            
            PreparedStatement requete = ConnexionBDD.getInstance().prepareStatement("INSERT INTO piege VALUES ?,?,?,?,?,?");
            requete.setInt(1, 9);
            requete.setString(2, "bombe");
            requete.setInt(3, 1);
            requete.setInt(4, 1);
            requete.setString(5, "Max");
            requete.setBoolean(6, true);
            requete.executeUpdate();
            //requete.close();
                                
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    }
