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
        try {

            Connection connexion = DriverManager.getConnection("jdbc:mysql://nemrod.ens2m.fr:3306/20192020_s2_vs2_tp1_goat", "etudiant", "YTDTvj9TR3CDYCmP");
            PreparedStatement requete = connexion.prepareStatement("UPDATE goat SET x = ?, y = ?, actif = ? WHERE pseudo = ?");
            
            
            //*****Poser une bombe*****//
            
            
            
            requete.executeUpdate();

            requete.close();
            connexion.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
   
    
}
