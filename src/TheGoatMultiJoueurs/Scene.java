/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TheGoatMultiJoueurs;

import Pieges.Bombe;
import Pieges.Piege;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
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

    private String personnage;

    private Bombe bombe;

    //Constructeur
    public Scene(String pseudo, String personnage) {
        super();
        this.pseudo = pseudo;
        this.personnage = personnage;
        
        this.bombe = new Bombe(0,0,"",false);

        this.tileMap = new TilesTuto(16, 50);

        this.iconGoat = new ImageIcon(getClass().getResource("/images/goat.png"));
        this.imageGoat = this.iconGoat.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);

        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(new Clavier());
        this.addMouseListener(new Souris());

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
//        System.out.println(dataGoat);
//        System.out.println(dataPiege);

        for (int i = 0; i < dataGoat.size(); i = i + 3) {
            g.drawString((String) dataGoat.get(i), (int) dataGoat.get(i + 1), (int) dataGoat.get(i + 2));
            g.drawImage(imageGoat, (int) dataGoat.get(i + 1), (int) dataGoat.get(i + 2), null);
        }

        for (int i = 0; i < dataPiege.size(); i = i + 5) {
            System.out.println(dataPiege.get(i));
            if(dataPiege.get(i).equals("bombe")) {
//                    (String) dataPiege.get(i), //Type
//                    (int) dataPiege.get(i + 1), //x
//                    (int) dataPiege.get(i + 2), //y
//                    (String) dataPiege.get(i + 3), //Propiertaire
//                    (boolean) dataPiege.get(i + 4)); // Actif
            g.drawString((String) dataPiege.get(i + 3), (int) dataPiege.get(i + 1), (int) dataPiege.get(i + 2));
            g.drawImage(bombe.getImage(), (int) dataPiege.get(i + 1), (int) dataPiege.get(i + 2), null);
            }
        }
    }

    public void runMethodes() {
        win();
        collision();
    }

    //Getters
    public String getPseudo() {
        return pseudo;
    }

    public String getPersonnage() {
        return personnage;
    }

    /////////////////SQL//////////////////
    public ArrayList dataGoat() {
        ArrayList sqlResult = new ArrayList();

        try {
            PreparedStatement requete = ConnexionBDD.getInstance().prepareStatement("SELECT x, y, pseudo FROM goat");
            ResultSet resultat = requete.executeQuery();
            while (resultat.next()) {
                sqlResult.add(resultat.getString("pseudo"));
                sqlResult.add(resultat.getInt("x"));
                sqlResult.add(resultat.getInt("y"));
            }
            requete.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return sqlResult;
    }

    public ArrayList dataPiege() {
        ArrayList sqlResult = new ArrayList();

        try {
            PreparedStatement requete = ConnexionBDD.getInstance().prepareStatement("SELECT x, y, proprietaire, type, actif FROM piege");
            ResultSet resultat = requete.executeQuery();
            while (resultat.next()) {
                sqlResult.add(resultat.getString("type"));
                sqlResult.add(resultat.getInt("x"));
                sqlResult.add(resultat.getInt("y"));
                sqlResult.add(resultat.getString("proprietaire"));
                sqlResult.add(resultat.getBoolean("actif"));

            }
            requete.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return sqlResult;
    }

    public void win() {

        try {
            PreparedStatement requete = ConnexionBDD.getInstance().prepareStatement("SELECT pseudo FROM goat WHERE pseudo = ? AND x = 500");
            requete.setString(1, pseudo);
            ResultSet resultat = requete.executeQuery();
            while (resultat.next()) {
                System.out.println("You Won The Game!!");
            }

            requete.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void collision() {
        try {

            //*****Zone de detection carrée autour de la bombe*****//
//            PreparedStatement requete = ConnexionBDD.getInstance().prepareStatement("SELECT pseudo, proprietaire FROM goat, piege "
//                    + "WHERE goat.x + 40 >= piege.x - 35 AND goat.x + 40 <= piege.x + 35 AND goat.y + 50 >= piege.y - 20 AND goat.y + 50 <= piege.y + 50");
            //*****Zone de detection ronde autour de la bombe*****//
            PreparedStatement requete = ConnexionBDD.getInstance().prepareStatement("SELECT pseudo, proprietaire FROM goat, piege "
                    + "WHERE SQRT((goat.x + 40 - piege.x - 5)*(goat.x + 40 - piege.x - 5)+(goat.y + 50 - piege.y - 20)*(goat.y + 50 - piege.y - 20)) < 40");

            //*****Fonctionne pas*****//
//            PreparedStatement requete = ConnexionBDD.getInstance().prepareStatement("UPDATE goat SET nbVie = nbVie - 1 FROM goat INNER JOIN piege "
//                    + "WHERE SQRT((goat.x + 40 - piege.x - 5)*(goat.x + 40 - piege.x - 5)+(goat.y + 50 - piege.y - 20)*(goat.y + 50 - piege.y - 20)) < 40))");
            ResultSet resultat = requete.executeQuery();

            while (resultat.next()) {
                String pseudo = resultat.getString("pseudo");
                String proprietaire = resultat.getString("proprietaire");

                PreparedStatement requete1 = ConnexionBDD.getInstance().prepareStatement("UPDATE goat SET nbVie = nbVie - 1, x = x - 30 WHERE pseudo = ? AND nbVie > 0");
                requete1.setString(1, pseudo);
                requete1.executeUpdate();

                requete1.close();

                System.out.println(pseudo + " killed by " + proprietaire);
            }

            requete.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
