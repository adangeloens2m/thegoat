/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TheGoatMultiJoueurs;

import Pieges.Bombe;
import java.awt.Graphics;
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
    
    private Image imageBombe;
    private ImageIcon iconBombe;
    
    private String pseudo;
    
    public Bombe bombe;

    //Constructeur
    public Scene(String pseudo) {
        super();
        this.pseudo = pseudo;

        this.tileMap = new TilesTuto(16, 50);

        this.iconGoat = new ImageIcon(getClass().getResource("/images/goat.png"));
        this.imageGoat = this.iconGoat.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);

        this.iconBombe = new ImageIcon(getClass().getResource("/images/bombe.png"));
        this.imageBombe = this.iconBombe.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(new Clavier());

        Thread chronoEcran = new Thread(new Chrono());
        chronoEcran.start();
    }

    //Méthodes
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        tileMap.DrawLayer(g);

        ArrayList dataGoat = dataGoat();
        ArrayList dataPiege = dataPiege();

        for (int i = 0; i < dataGoat.size(); i = i + 3) {
            g.drawString((String) dataGoat.get(i), (int) dataGoat.get(i + 1), (int) dataGoat.get(i + 2));
            g.drawImage(imageGoat, (int) dataGoat.get(i + 1), (int) dataGoat.get(i + 2), null);
        }

        for (int i = 0; i < dataPiege.size(); i = i + 3) {
            //g.drawString((String) dataPiege.get(i), (int) dataPiege.get(i + 1), (int) dataPiege.get(i + 2));
            g.drawImage(imageBombe, bombe.getX(), bombe.getY(), null);
        }
    }

    public void runMethodes() {
        System.out.println(dataGoat());
        //win();
    }

    //Getters
    public String getPseudo() {
        return pseudo;
    }

    /////////////////SQL//////////////////
    public ArrayList dataGoat() {
        ArrayList sqlResult = new ArrayList();
        try {

            //Connection connexion = DriverManager.getConnection("jdbc:mysql://nemrod.ens2m.fr:3306/20192020_s2_vs2_tp1_goat?serverTimezone=UTC", "goat", "9FdqUt5uXibSkOF8");

            PreparedStatement requete = ConnexionBDD.getInstance().prepareStatement("SELECT x, y, pseudo FROM goat");
            ResultSet resultat = requete.executeQuery();
            while (resultat.next()) {
                sqlResult.add(resultat.getString("pseudo"));
                sqlResult.add(resultat.getInt("x"));
                sqlResult.add(resultat.getInt("y"));
            }
            requete.close();
            //connexion.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return sqlResult;
    }
    
    
    public ArrayList dataPiege() {
        ArrayList sqlResult = new ArrayList();
        try {

            //Connection connexion = DriverManager.getConnection("jdbc:mysql://nemrod.ens2m.fr:3306/20192020_s2_vs2_tp1_goat?serverTimezone=UTC", "goat", "9FdqUt5uXibSkOF8");

            PreparedStatement requete = ConnexionBDD.getInstance().prepareStatement("SELECT x, y, proprietaire FROM piege");
            ResultSet resultat = requete.executeQuery();
            while (resultat.next()) {
                sqlResult.add(resultat.getString("proprietaire"));
                sqlResult.add(resultat.getInt("x"));
                sqlResult.add(resultat.getInt("y"));
            }
            requete.close();
            //connexion.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return sqlResult;
    }

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
}
