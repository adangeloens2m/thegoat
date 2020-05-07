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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author maxime.bulabois
 */
public class Souris extends MouseAdapter {

    @Override
    public void mouseClicked(MouseEvent e) {

        // commande pour insérer l'utilisation d'un nouveau piège dans la table
        if (Main.scene.getPersonnage() == "loup") {

            int coin = 5;

            try {
                PreparedStatement requete = ConnexionBDD.getInstance().prepareStatement("SELECT coin FROM loup WHERE pseudo = '" + Main.scene.getPseudo() + "'");
                ResultSet resultat = requete.executeQuery();
                while (resultat.next()) {
                    coin = resultat.getInt("coin");
                }

                requete.close();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            if (Main.scene.getIndice() == 0) {

                if (e.getButton() == MouseEvent.BUTTON1 && coin > 4) {

                    try {

                        PreparedStatement requete = ConnexionBDD.getInstance().prepareStatement("INSERT INTO piege VALUES (?,?,?,?,?)");
                        requete.setString(1, "bombe");
                        requete.setInt(2, e.getX() - 20);
                        requete.setInt(3, e.getY() - 30);
                        requete.setString(4, Main.scene.getPseudo());
                        requete.setBoolean(5, true);
                        requete.executeUpdate();

                        requete.close();

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                    try {
                        PreparedStatement requete = ConnexionBDD.getInstance().prepareStatement(
                                "UPDATE loup SET coin = coin - 5 WHERE pseudo = '" + Main.scene.getPseudo() + "'");

                        requete.executeUpdate();
                        requete.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(Souris.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            if (Main.scene.getIndice() == 1) {

                if (e.getButton() == MouseEvent.BUTTON1 && coin > 9) {
                    try {

                        PreparedStatement requete = ConnexionBDD.getInstance().prepareStatement("INSERT INTO piege VALUES (?,?,?,?,?)");
                        requete.setString(1, "ravin");
                        requete.setInt(2, e.getX() - 45);
                        requete.setInt(3, e.getY() - 45);
                        requete.setString(4, Main.scene.getPseudo());
                        requete.setBoolean(5, true);
                        requete.executeUpdate();

                        requete.close();

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                    try {
                        PreparedStatement requete = ConnexionBDD.getInstance().prepareStatement(
                                "UPDATE loup SET coin = coin - 10 WHERE pseudo = '" + Main.scene.getPseudo() + "'");

                        requete.executeUpdate();
                        requete.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(Souris.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

            if (Main.scene.getIndice() == 2) {

                if (e.getButton() == MouseEvent.BUTTON1 && coin > 19) {

                    try {

                        PreparedStatement requete = ConnexionBDD.getInstance().prepareStatement("INSERT INTO piege VALUES (?,?,?,?,?)");
                        requete.setString(1, "mine");
                        requete.setInt(2, e.getX() - 20);
                        requete.setInt(3, e.getY() - 30);
                        requete.setString(4, Main.scene.getPseudo());
                        requete.setBoolean(5, true);
                        requete.executeUpdate();

                        requete.close();

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                    try {
                        PreparedStatement requete = ConnexionBDD.getInstance().prepareStatement(
                                "UPDATE loup SET coin = coin - 20 WHERE pseudo = '" + Main.scene.getPseudo() + "'");

                        requete.executeUpdate();
                        requete.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(Souris.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

            if (Main.scene.getIndice() == 3) {

                if (e.getButton() == MouseEvent.BUTTON1 && coin > 14) {

                    try {

                        PreparedStatement requete = ConnexionBDD.getInstance().prepareStatement("INSERT INTO piege VALUES (?,?,?,?,?)");
                        requete.setString(1, "explosifTC");
                        requete.setInt(2, e.getX() - 20);
                        requete.setInt(3, e.getY() - 30);
                        requete.setString(4, Main.scene.getPseudo());
                        requete.setBoolean(5, true);
                        requete.executeUpdate();

                        requete.close();

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                    try {
                        PreparedStatement requete = ConnexionBDD.getInstance().prepareStatement(
                                "UPDATE loup SET coin = coin - 15 WHERE pseudo = '" + Main.scene.getPseudo() + "'");

                        requete.executeUpdate();
                        requete.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(Souris.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

            if (e.getButton() == MouseEvent.BUTTON3) {
                try {
//                    PreparedStatement requete = ConnexionBDD.getInstance().prepareStatement("SELECT * FROM piege WHERE piege.actif AND type = 'explosifTC'");
//                    ResultSet resultat = requete.executeQuery();
//                    
//                    while (resultat.next()) {
//                        int coorx = resultat.getInt("x");
//                        int coory = resultat.getInt("y");
//
//                        //if ((e.getX() - coorx) < 60 && (e.getX() - coorx) > 0 && (e.getX() - coory) < 60 && (e.getX() - coory) > 0) {
//                            Main.scene.getExplosifTC().collision();
//                            PreparedStatement requete2 = ConnexionBDD.getInstance().prepareStatement("UPDATE piege SET actif = false");
//                            requete2.executeUpdate();
//                            requete2.close();
//                        //}
//                    }
//                    requete.close();

                    PreparedStatement requete = ConnexionBDD.getInstance().prepareStatement("UPDATE piege SET actif = false WHERE type = 'explosifTC' and proprietaire = '" + Main.scene.getPseudo() + "'");
                    Main.scene.getExplosifTC().collision();
                    requete.executeUpdate();
                    requete.close();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }

        }
    }
}
