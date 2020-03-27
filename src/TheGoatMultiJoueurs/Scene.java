/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TheGoatMultiJoueurs;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Arnaud
 */
public class Scene extends JPanel {

    private TilesTuto tileMap;

    private Image imageGoat;
    private ImageIcon iconGoat;

    private String pseudo;

    //Constructeur
    public Scene(String pseudo) {
        super();
        this.pseudo = pseudo;

        this.tileMap = new TilesTuto(16, 50);

        this.iconGoat = new ImageIcon(getClass().getResource("/images/goat.png"));
        this.imageGoat = this.iconGoat.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);

        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(new Clavier());

        Thread chronoEcran = new Thread(new Chrono());
        chronoEcran.start();
    }

    //Méthodes
//    @Override
//    public void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        Graphics g2 = (Graphics2D) g;
//
//        tileMap.DrawLayer(g2);
//
//        ArrayList sqlData = SQL();
//        for (int i = 0; i < sqlData.size(); i = i + 3) {
//            g2.drawString((String) sqlData.get(i), (int) sqlData.get(i + 1), (int) sqlData.get(i + 2));
//            g2.drawImage(imageGoat, (int) sqlData.get(i + 1), (int) sqlData.get(i + 2), null);
//        }
//    }

    public void runMethodes() {
        System.out.println(SQL());
    }

    //Getters
    public String getPseudo() {
        return pseudo;
    }

    /////////////////SQL//////////////////
    public ArrayList SQL() {
        ArrayList sqlResult = new ArrayList();
        try {

            Connection connexion = DriverManager.getConnection("jdbc:mysql://nemrod.ens2m.fr:3306/20192020_s2_vs2_tp1_goat", "etudiant", "YTDTvj9TR3CDYCmP");

            PreparedStatement requete = connexion.prepareStatement("SELECT x, y, pseudo FROM goat");
            ResultSet resultat = requete.executeQuery();
            while (resultat.next()) {
                sqlResult.add(resultat.getString("pseudo"));
                sqlResult.add(resultat.getInt("x"));
                sqlResult.add(resultat.getInt("y"));
            }
            requete.close();
            connexion.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return sqlResult;
    }

//    public int[] posGoat(int id) {
//        int[] sqlResult = {0, 0};
//        try {
//
//            Connection connexion = DriverManager.getConnection("jdbc:mysql://nemrod.ens2m.fr:3306/20192020_s2_vs2_tp1_goat", "etudiant", "YTDTvj9TR3CDYCmP");
//
//            PreparedStatement requete = connexion.prepareStatement("SELECT x, y, pseudo FROM goat WHERE id = ?");
//            requete.setInt(1, id);
//            ResultSet resultat = requete.executeQuery();
//            while (resultat.next()) {
//                sqlResult[0] = resultat.getInt("x");
//                sqlResult[1] = resultat.getInt("y");
//            }
//            requete.close();
//            connexion.close();
//
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        return sqlResult;
//    }
//
//    public void win() {
//        try {
//
//            Connection connexion = DriverManager.getConnection("jdbc:mysql://nemrod.ens2m.fr:3306/20192020_s2_vs2_tp1_goat", "etudiant", "YTDTvj9TR3CDYCmP");
//
//            PreparedStatement requete = connexion.prepareStatement("SELECT pseudo FROM goat WHERE pseudo = ? AND x = 500");
//            requete.setString(1, pseudo);
//            ResultSet resultat = requete.executeQuery();
//            while (resultat.next()) {
//                System.out.println("You Won The Game!!");
//            }
//
//            requete.close();
//            connexion.close();
//
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public int nbJoueurs() {
//        int nb = 0;
//        try {
//
//            Connection connexion = DriverManager.getConnection("jdbc:mysql://nemrod.ens2m.fr:3306/20192020_s2_vs2_tp1_goat", "etudiant", "YTDTvj9TR3CDYCmP");
//
//            PreparedStatement requete = connexion.prepareStatement("SELECT COUNT(DISTINCT pseudo) as nbJoueurs FROM goat;");
//            ResultSet resultat = requete.executeQuery();
//            while (resultat.next()) {
//                nb = resultat.getInt("nbJoueurs");
//            }
//
//            requete.close();
//            connexion.close();
//
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        return nb;
//    }
//
//    public String pseudoGoat(int id) {
//        String pseudo = null;
//        try {
//
//            Connection connexion = DriverManager.getConnection("jdbc:mysql://nemrod.ens2m.fr:3306/20192020_s2_vs2_tp1_goat", "etudiant", "YTDTvj9TR3CDYCmP");
//
//            PreparedStatement requete = connexion.prepareStatement("SELECT pseudo FROM goat WHERE id = ?");
//            requete.setInt(1, id);
//            ResultSet resultat = requete.executeQuery();
//            while (resultat.next()) {
//                pseudo = resultat.getString("pseudo");
//            }
//            requete.close();
//            connexion.close();
//
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        return pseudo;
//    }
//
    //    public int xGoat(int id) {
//        int x = 0;
//        try {
//
//            Connection connexion = DriverManager.getConnection("jdbc:mysql://nemrod.ens2m.fr:3306/20192020_s2_vs2_tp1_goat", "etudiant", "YTDTvj9TR3CDYCmP");
//
//            PreparedStatement requete = connexion.prepareStatement("SELECT x FROM goat WHERE id = ?");
//            requete.setInt(1, id);
//            ResultSet resultat = requete.executeQuery();
//            while (resultat.next()) {
//                x = resultat.getInt("x");
//            }
//            requete.close();
//            connexion.close();
//
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        return x;
//    }
//    public int yGoat(int id) {
//        int y = 0;
//        try {
//
//            Connection connexion = DriverManager.getConnection("jdbc:mysql://nemrod.ens2m.fr:3306/20192020_s2_vs2_tp1_goat", "etudiant", "YTDTvj9TR3CDYCmP");
//
//            PreparedStatement requete = connexion.prepareStatement("SELECT y FROM goat WHERE id = ?");
//            requete.setInt(1, id);
//            ResultSet resultat = requete.executeQuery();
//            while (resultat.next()) {
//                y = resultat.getInt("y");
//            }
//            requete.close();
//            connexion.close();
//
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        return y;
//    }
//
}
