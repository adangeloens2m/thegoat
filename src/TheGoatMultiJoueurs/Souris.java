/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TheGoatMultiJoueurs;

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

        // commande pour insérer un nouveau piège dans la table
        if (Main.scene.getPersonnage() == "loup") {

            //Récupération de coin du joueur
            int coin = 0;
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

            //Posage de la bombe
            if (Main.scene.getIndice() == 0) {

                if (e.getButton() == MouseEvent.BUTTON1 && coin > Main.scene.getBombe().getCost() - 1) {

                    try {

                        PreparedStatement requete = ConnexionBDD.getInstance().prepareStatement("INSERT INTO piege VALUES (?,?,?,?,?)");
                        requete.setString(1, "bombe");                  //Type de piège
                        requete.setInt(2, e.getX() - 30);               //Position x
                        requete.setInt(3, e.getY() - 30);               //Position y
                        requete.setString(4, Main.scene.getPseudo());   //Pseudo proprietaire
                        requete.setBoolean(5, true);                    //Statut actif
                        requete.executeUpdate();

                        requete.close();

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                    //Decompte des coins
                    try {
                        PreparedStatement requete = ConnexionBDD.getInstance().prepareStatement(
                                "UPDATE loup SET coin = coin - '" + Main.scene.getBombe().getCost() + "' WHERE pseudo = '" + Main.scene.getPseudo() + "'");

                        requete.executeUpdate();
                        requete.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(Souris.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    //DECLENCHEMENT D'UN BRUITAGE
                }
            }

            //Posage ravin
            if (Main.scene.getIndice() == 1) {

                if (e.getButton() == MouseEvent.BUTTON1 && coin > Main.scene.getRavin().getCost() - 1) {
                    try {

                        PreparedStatement requete = ConnexionBDD.getInstance().prepareStatement("INSERT INTO piege VALUES (?,?,?,?,?)");
                        requete.setString(1, "ravin");
                        requete.setInt(2, e.getX() - 50);
                        requete.setInt(3, e.getY() - 50);
                        requete.setString(4, Main.scene.getPseudo());
                        requete.setBoolean(5, true);
                        requete.executeUpdate();

                        requete.close();

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                    try {
                        PreparedStatement requete = ConnexionBDD.getInstance().prepareStatement(
                                "UPDATE loup SET coin = coin - '" + Main.scene.getRavin().getCost() + "' WHERE pseudo = '" + Main.scene.getPseudo() + "'");

                        requete.executeUpdate();
                        requete.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(Souris.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                //DECLENCHEMENT D'UN BRUITAGE
            }

            //Posage mine
            if (Main.scene.getIndice() == 2) {

                if (e.getButton() == MouseEvent.BUTTON1 && coin > Main.scene.getMine().getCost() - 1) {

                    try {

                        PreparedStatement requete = ConnexionBDD.getInstance().prepareStatement("INSERT INTO piege VALUES (?,?,?,?,?)");
                        requete.setString(1, "mine");
                        requete.setInt(2, e.getX() - 25);
                        requete.setInt(3, e.getY() - 25);
                        requete.setString(4, Main.scene.getPseudo());
                        requete.setBoolean(5, true);
                        requete.executeUpdate();

                        requete.close();

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                    try {
                        PreparedStatement requete = ConnexionBDD.getInstance().prepareStatement(
                                "UPDATE loup SET coin = coin - '" + Main.scene.getMine().getCost() + "' WHERE pseudo = '" + Main.scene.getPseudo() + "'");

                        requete.executeUpdate();
                        requete.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(Souris.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                //DECLENCHEMENT D'UN BRUITAGE
            }

            //Posage explosifTC
            if (Main.scene.getIndice() == 3) {

                if (e.getButton() == MouseEvent.BUTTON1 && coin > Main.scene.getExplosifTC().getCost() - 1) {

                    try {

                        PreparedStatement requete = ConnexionBDD.getInstance().prepareStatement("INSERT INTO piege VALUES (?,?,?,?,?)");
                        requete.setString(1, "explosifTC");
                        requete.setInt(2, e.getX() - 30);
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
                                "UPDATE loup SET coin = coin - '" + Main.scene.getExplosifTC().getCost() + "' WHERE pseudo = '" + Main.scene.getPseudo() + "'");

                        requete.executeUpdate();
                        requete.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(Souris.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                //DECLENCHEMENT D'UN BRUITAGE
            }

            //Déclenchement explosifTC
            if (e.getButton() == MouseEvent.BUTTON3) {
                try {
                    //Piste pour déclencher l'explosif au bout du pointeur de la souris :   
                    //if ((e.getX() - coorx) < 60 && (e.getX() - coorx) > 0 && (e.getX() - coory) < 60 && (e.getX() - coory) > 0) {

                    PreparedStatement requete = ConnexionBDD.getInstance().prepareStatement("UPDATE piege SET actif = false WHERE type = 'explosifTC' and proprietaire = '" + Main.scene.getPseudo() + "'");
                    Main.scene.getExplosifTC().collision();
                    requete.executeUpdate();
                    requete.close();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                //DECLENCHEMENT D'UN BRUITAGE
            }
        }
    }
}
