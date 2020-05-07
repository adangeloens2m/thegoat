/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TheGoatMultiJoueurs;

import Pieges.Bombe;
import Pieges.ExplosifTC;
import Pieges.Mine;
import Pieges.Ravin;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Arnaud
 */
public class Scene extends JPanel {

    private TilesTuto tileMap;

    private ImageIcon iconRealGoat;
    private Image imageRealGoat;
    private ImageIcon iconRougeGoat;
    private Image imageRougeGoat;
    private ImageIcon iconBleueGoat;
    private Image imageBleueGoat;
    private ImageIcon iconVerteGoat;
    private Image imageVerteGoat;
    private ImageIcon iconJauneGoat;
    private Image imageJauneGoat;
    private ImageIcon iconBlancheGoat;
    private Image imageBlancheGoat;
    private Map<String, Image> imageMap;

    private String pseudo;
    private String personnage;
    private String skin;

    private Bombe bombe;
    private Ravin ravin;
    private Mine mine;
    private ExplosifTC explosifTC;

    private int indice;

    private int xDynamique;

    private boolean defilHost;

    //Constructeur
    public Scene(String pseudo, String personnage, String skin) {
        super();
        this.pseudo = pseudo;
        this.personnage = personnage;
        this.skin = skin;
        this.defilHost = false;

        //Entrer le joueur dans la BDD
        if (personnage == "goat") {
            try {
                PreparedStatement requete = ConnexionBDD.getInstance().prepareStatement("INSERT INTO goat VALUES (?,?,?,?,?)");
                requete.setString(1, pseudo);
                requete.setInt(2, 0);
                requete.setInt(3, 200);
                requete.setInt(4, 5);
                requete.setString(5, skin);
                requete.executeUpdate();

                requete.close();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else {
            try {
                PreparedStatement requete = ConnexionBDD.getInstance().prepareStatement("INSERT INTO loup VALUES (?,?)");
                requete.setString(1, pseudo);
                requete.setInt(2, 5);
                requete.executeUpdate();

                requete.close();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        this.bombe = new Bombe(0, 0, ""); //Création de l'objet bombe
        this.ravin = new Ravin(0, 0, ""); //Création de l'objet ravin
        this.mine = new Mine(0, 0, ""); //Création de l'objet mine
        this.explosifTC = new ExplosifTC(0, 0, ""); //Création de l'objet explosif télécommandé

        this.tileMap = new TilesTuto(35, 16); //Création de la map

        this.iconRealGoat = new ImageIcon(getClass().getResource("/images/RealGoat.png"));
        this.imageRealGoat = this.iconRealGoat.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH); //Image goat
        this.iconRougeGoat = new ImageIcon(getClass().getResource("/images/GoatRouge.png"));
        this.imageRougeGoat = this.iconRougeGoat.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH); //Image goat
        this.iconBleueGoat = new ImageIcon(getClass().getResource("/images/GoatBleue.png"));
        this.imageBleueGoat = this.iconBleueGoat.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH); //Image goat
        this.iconVerteGoat = new ImageIcon(getClass().getResource("/images/GoatVerte.png"));
        this.imageVerteGoat = this.iconVerteGoat.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH); //Image goat
        this.iconJauneGoat = new ImageIcon(getClass().getResource("/images/GoatJaune.png"));
        this.imageJauneGoat = this.iconJauneGoat.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH); //Image goat
        this.iconBlancheGoat = new ImageIcon(getClass().getResource("/images/GoatBlanche.png"));
        this.imageBlancheGoat = this.iconBlancheGoat.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH); //Image goat

        this.imageMap = new HashMap<String, Image>();
        imageMap.put("RealGoat", imageRealGoat);
        imageMap.put("GoatRouge", imageRougeGoat);
        imageMap.put("GoatBleue", imageBleueGoat);
        imageMap.put("GoatVerte", imageVerteGoat);
        imageMap.put("GoatJaune", imageJauneGoat);
        imageMap.put("GoatBlanche", imageBlancheGoat);

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
        ArrayList dataLoup = dataLoup();

        //Affichage de la barre des pièges
        if (personnage == "loup") {
            ImageIcon iconBarrePiege = new ImageIcon(getClass().getResource("/images/barrePiege1.1.png"));
            Image imageBarrePiege = iconBarrePiege.getImage();
            g.drawImage(imageBarrePiege, 400, 440, null);

            ImageIcon iconSelectionPiege = new ImageIcon(getClass().getResource("/images/SelectionPiege.png"));
            Image imageSelectionPiege = iconSelectionPiege.getImage();
            g.drawImage(imageSelectionPiege, (401 + 46 * this.indice), 440, this);

            for (int i = 0; i < dataLoup.size(); i += 2) {
                if (dataLoup.get(i).equals(pseudo)) {
                    int coin = (int) dataLoup.get(i + 1);
                    g.drawString("Coin : " + coin, 400, 435);
                    g.drawString(Integer.toString(coin / 5), 440, 486);
                    g.drawString(Integer.toString(coin / 10), 486, 486);
                    g.drawString(Integer.toString(coin / 20), 530, 486);
                    g.drawString(Integer.toString(coin / 15), 576, 486);
                }
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
                //Affichage des ravins
            } else if (dataPiege.get(i).equals("ravin")) {
                ravin.setX((int) dataPiege.get(i + 1));
                ravin.setY((int) dataPiege.get(i + 2));
                ravin.setProprietaire((String) dataPiege.get(i + 3));
                ravin.setActif((boolean) dataPiege.get(i + 4));
                ravin.collision();
                g.drawString(ravin.getProprietaire(), ravin.getX(), ravin.getY());
                g.drawImage(ravin.getImage(), ravin.getX(), ravin.getY(), null);
                //Affichage des mines
            } else if (dataPiege.get(i).equals("mine")) {
                mine.setX((int) dataPiege.get(i + 1));
                mine.setY((int) dataPiege.get(i + 2));
                mine.setProprietaire((String) dataPiege.get(i + 3));
                mine.setActif((boolean) dataPiege.get(i + 4));
                mine.collision();
                if (personnage == "loup") {
                    g.drawString(mine.getProprietaire(), mine.getX(), mine.getY());
                    g.drawImage(mine.getImage(), mine.getX(), mine.getY(), null);
                } else if (personnage == "goat" && mine.isActif() == false) {
                    g.drawString(mine.getProprietaire(), mine.getX(), mine.getY());
                    g.drawImage(mine.getImage(), mine.getX(), mine.getY(), null);
                }
                //Affichage des explosifs télécommandés
            } else if (dataPiege.get(i).equals("explosifTC")) {
                explosifTC.setX((int) dataPiege.get(i + 1));
                explosifTC.setY((int) dataPiege.get(i + 2));
                explosifTC.setProprietaire((String) dataPiege.get(i + 3));
                explosifTC.setActif((boolean) dataPiege.get(i + 4));
                g.drawImage(explosifTC.getImage(), explosifTC.getX(), explosifTC.getY(), null);
            }
        }

        //Affichage des Goats
        for (int i = 0; i < dataGoat.size(); i = i + 5) {
            if ((int) dataGoat.get(i + 3) > 0) {
                g.drawString((String) dataGoat.get(i), (int) dataGoat.get(i + 1), (int) dataGoat.get(i + 2));
                g.drawString((int) dataGoat.get(i + 3) + " VIES", (int) dataGoat.get(i + 1), (int) dataGoat.get(i + 2) + 80);
                g.drawImage(imageMap.get((String) dataGoat.get(i + 4)), (int) dataGoat.get(i + 1), (int) dataGoat.get(i + 2), null);
            }
        }
    }

    //Méthodes annexes appelées toutes les x ms
    public void slowRefreshMethodes() {
        win();
        defilement();
        freeCoin();
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

    public int getxDynamique() {
        return xDynamique;
    }

    public ExplosifTC getExplosifTC() {
        return explosifTC;
    }

    //Setters
    public void setIndice(int indice) {
        this.indice = indice;
    }

    public void setDefilHost(boolean defilHost) {
        this.defilHost = defilHost;
    }

    /////////////////SQL//////////////////
    //Méthode de récupération des données des goats dans une ArrayList
    public ArrayList dataGoat() {
        ArrayList sqlResult = new ArrayList();

        try {
            PreparedStatement requete = ConnexionBDD.getInstance().prepareStatement("SELECT * FROM goat");
            ResultSet resultat = requete.executeQuery();
            while (resultat.next()) {
                sqlResult.add(resultat.getString("pseudo"));
                sqlResult.add(resultat.getInt("x"));
                sqlResult.add(resultat.getInt("y"));
                sqlResult.add(resultat.getInt("nbVie"));
                sqlResult.add(resultat.getString("skin"));
            }
            requete.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return sqlResult;
    }

    //Méthode de récupération des données des loups dans une ArrayList
    public ArrayList dataLoup() {
        ArrayList sqlResult = new ArrayList();

        try {
            PreparedStatement requete = ConnexionBDD.getInstance().prepareStatement("SELECT * FROM loup");
            ResultSet resultat = requete.executeQuery();
            while (resultat.next()) {
                sqlResult.add(resultat.getString("pseudo"));
                sqlResult.add(resultat.getInt("coin"));
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
            PreparedStatement requete = ConnexionBDD.getInstance().prepareStatement("SELECT * FROM piege");
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

    public void defilement() {

        try {
            if (defilHost) {
                PreparedStatement requete = ConnexionBDD.getInstance().prepareStatement(
                        "UPDATE suivi SET x_dynamique = x_dynamique + 1");
                requete.executeUpdate();
                requete.close();

                PreparedStatement requete2 = ConnexionBDD.getInstance().prepareStatement("UPDATE piege SET x = x - 1");
                requete2.executeUpdate();
                requete2.close();
            }

            PreparedStatement requete1 = ConnexionBDD.getInstance().prepareStatement("SELECT * FROM suivi");
            ResultSet resultat = requete1.executeQuery();
            while (resultat.next()) {
                xDynamique = resultat.getInt("x_dynamique");
            }
            requete1.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //Méthode d'annonce de victoire
    public void win() {

        try {
            PreparedStatement requete = ConnexionBDD.getInstance().prepareStatement("SELECT pseudo FROM goat WHERE pseudo = ? AND x > ?");
            requete.setString(1, pseudo);
            requete.setInt(2, tileMap.getWidth() - xDynamique);
            ResultSet resultat = requete.executeQuery();
            while (resultat.next()) {
                System.out.println("You Won The Game!!");
            }

            requete.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void freeCoin() {

        try {
            PreparedStatement requete = ConnexionBDD.getInstance().prepareStatement(
                    "UPDATE loup SET coin = coin + 1 WHERE pseudo = '" + pseudo + "'");

            requete.executeUpdate();
            requete.close();
        } catch (SQLException ex) {
            Logger.getLogger(Scene.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
