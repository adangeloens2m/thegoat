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

        this.bombe = new Bombe(0, 0, "", false); //Création de l'objet bombe

        this.tileMap = new TilesTuto(33, 16); //Création de la map

        this.iconGoat = new ImageIcon(getClass().getResource("/images/goat.png"));
        this.imageGoat = this.iconGoat.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH); //Image goat

        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(new Clavier()); //Listener clavier
        this.addMouseListener(new Souris()); //Listener souris

        Thread chronoEcran = new Thread(new Chrono());
        chronoEcran.start(); //Boucle qui s'éxécute toutes les 3ms pour rafraichir le jeu
    }

    //Méthodes d'affichage des composant dans la fentre (appelée toutes les 3ms)
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        tileMap.DrawLayer(g);

        ArrayList dataGoat = dataGoat();
        ArrayList dataPiege = dataPiege();
//        System.out.println(dataGoat);
//        System.out.println(dataPiege);

        //Affichage des Goats
        for (int i = 0; i < dataGoat.size(); i = i + 4) {
            int res = Integer.parseInt((String) dataGoat.get(i + 3));
            if (res > 0){
                g.drawString((String) dataGoat.get(i), (int) dataGoat.get(i + 1), (int) dataGoat.get(i + 2));
                g.drawString((String) dataGoat.get(i + 3) + " VIES", (int) dataGoat.get(i + 1), (int) dataGoat.get(i + 2) + 80);
                g.drawImage(imageGoat, (int) dataGoat.get(i + 1), (int) dataGoat.get(i + 2), null);
            }
        }

        //Affichage des pieges
        for (int i = 0; i < dataPiege.size(); i = i + 5) {
            //Affichage des bombes
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

    //Méthodes annexes appelées toutes les 3ms
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
    //Méthode de récupération des données des goats dans une ArrayList
    public ArrayList dataGoat() {
        ArrayList sqlResult = new ArrayList();

        try {
            PreparedStatement requete = ConnexionBDD.getInstance().prepareStatement("SELECT x, y, pseudo, nbVie FROM goat");
            ResultSet resultat = requete.executeQuery();
            while (resultat.next()) {
                sqlResult.add(resultat.getString("pseudo"));
                sqlResult.add(resultat.getInt("x"));
                sqlResult.add(resultat.getInt("y"));
                sqlResult.add(resultat.getString("nbVie"));
            }
            requete.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return sqlResult;
    }

    //Méthode de récupération des données des pièges dans une ArrayList
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

    //Méthode d'annonce de victoire
    public void win() {

        try {
            PreparedStatement requete = ConnexionBDD.getInstance().prepareStatement("SELECT pseudo FROM goat WHERE pseudo = ? AND x > ?");
            requete.setString(1, pseudo);
            requete.setInt(2, tileMap.getWidth());
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
