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

        this.bombe = new Bombe(0, 0, "", false);

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

        //Goats
        for (int i = 0; i < dataGoat.size(); i = i + 3) {
            g.drawString((String) dataGoat.get(i), (int) dataGoat.get(i + 1), (int) dataGoat.get(i + 2));
            g.drawImage(imageGoat, (int) dataGoat.get(i + 1), (int) dataGoat.get(i + 2), null);
        }

        for (int i = 0; i < dataPiege.size(); i = i + 5) {
            //Bombe
            if (dataPiege.get(i).equals("bombe")) {
                bombe.setX((int) dataPiege.get(i + 1));
                bombe.setY((int) dataPiege.get(i + 2));
                bombe.setProprietaire((String) dataPiege.get(i + 3));
                bombe.setActif((boolean) dataPiege.get(i + 4));
                bombe.collision();
                g.drawString(bombe.getProprietaire(), bombe.getX(), bombe.getY());
                g.drawImage(bombe.getImage(), bombe.getX(), bombe.getY(), null);
            }
        }
    }

    public void runMethodes() {
        win();
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
}
