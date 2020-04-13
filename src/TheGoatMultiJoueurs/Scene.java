/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TheGoatMultiJoueurs;

import Pieges.Bombe;
import Pieges.Ravin;
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
    private String skin;

    private Bombe bombe;
    private Ravin ravin;
    
    private int indice;

    //Constructeur
    public Scene(String pseudo, String personnage, String skin) {
        super();
        this.pseudo = pseudo;
        this.personnage = personnage;
        this.skin = skin;

        //Entrer le joueur dans la BDD
        if (personnage == "goat") {
            try {
                PreparedStatement requete = ConnexionBDD.getInstance().prepareStatement("INSERT INTO goat VALUES (?,?,?,?,?,?)");
                requete.setInt(1, 1);
                requete.setString(2, pseudo);
                requete.setInt(3, 0);
                requete.setInt(4, 200);
                requete.setInt(5, 5);
                requete.setString(6, skin);
                requete.executeUpdate();

                requete.close();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else {
            try {
                PreparedStatement requete = ConnexionBDD.getInstance().prepareStatement("INSERT INTO loup VALUES (?,?,?,?,?)");
                requete.setInt(1, 1);
                requete.setString(2, pseudo);
                requete.setInt(3, 0);
                requete.setInt(4, 0);
                requete.setInt(5, 0);
                requete.executeUpdate();

                requete.close();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        this.bombe = new Bombe(0, 0, "", false); //Création de l'objet bombe
        this.ravin = new Ravin(0, 0 , ""); //Création de l'objet ravin

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


        //Affichage de la barre des pièges
        if(personnage == "loup"){
            ImageIcon iconBarrePiege = new ImageIcon(getClass().getResource("/images/barrePiege.png"));
            Image imageBarrePiege = iconBarrePiege.getImage();
            g.drawImage(imageBarrePiege, 400, 440, null);
            
            ImageIcon iconSelectionPiege = new ImageIcon(getClass().getResource("/images/SelectionPiege.png"));
            Image imageSelectionPiege = iconSelectionPiege.getImage();
            g.drawImage(imageSelectionPiege, (401 + 46*this.indice), 440, this);
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
            } else if (dataPiege.get(i).equals("ravin")){
                ravin.setX((int) dataPiege.get(i + 1));
                ravin.setY((int) dataPiege.get(i + 2));
                ravin.setProprietaire((String) dataPiege.get(i + 3));
                ravin.setActif((boolean) dataPiege.get(i + 4));
                ravin.collision();
                g.drawString(ravin.getProprietaire(), ravin.getX(), ravin.getY());
                g.drawImage(ravin.getImage(), ravin.getX(), ravin.getY(), null);
            }
        }

        //Affichage des Goats
        for (int i = 0; i < dataGoat.size(); i = i + 4) {
            if ((int) dataGoat.get(i + 3) > 0) {
                g.drawString((String) dataGoat.get(i), (int) dataGoat.get(i + 1), (int) dataGoat.get(i + 2));
                g.drawString((int) dataGoat.get(i + 3) + " VIES", (int) dataGoat.get(i + 1), (int) dataGoat.get(i + 2) + 80);
                g.drawImage(imageGoat, (int) dataGoat.get(i + 1), (int) dataGoat.get(i + 2), null);
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

    public int getIndice() {
        return indice;
    }

    //Setters
    public void setIndice(int indice) {
        this.indice = indice;
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
                sqlResult.add(resultat.getInt("nbVie"));
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
            requete.setInt(2, tileMap.getWidth() - 40);
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
