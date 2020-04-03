/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TheGoatMultiJoueurs;

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
    
    private Image imageBombe;
    private ImageIcon iconBombe;
    
    private String pseudo;
    
    private String personnage;

    //Constructeur
    public Scene(String pseudo, String personnage) {
        super();
        this.pseudo = pseudo;
        this.personnage = personnage;

        this.tileMap = new TilesTuto(16, 50);

        this.iconGoat = new ImageIcon(getClass().getResource("/images/goat.png"));
        this.imageGoat = this.iconGoat.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);

        this.iconBombe = new ImageIcon(getClass().getResource("/images/bombe.png"));
        this.imageBombe = this.iconBombe.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        
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

        for (int i = 0; i < dataGoat.size(); i = i + 3) {
            g.drawString((String) dataGoat.get(i), (int) dataGoat.get(i + 1), (int) dataGoat.get(i + 2));
            g.drawImage(imageGoat, (int) dataGoat.get(i + 1), (int) dataGoat.get(i + 2), null);
        }

        for (int i = 0; i < dataPiege.size(); i = i + 3) {
            g.drawString((String) dataPiege.get(i), (int) dataPiege.get(i + 1), (int) dataPiege.get(i + 2));
            g.drawImage(imageBombe, (int) dataPiege.get(i+1), (int) dataPiege.get(i+2), null);
        }
        
        System.out.println(dataGoat);
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
    
    public void collision(){
        try {

            //*****Zone de detection carrée autour de la bombe*****//
//            PreparedStatement requete = ConnexionBDD.getInstance().prepareStatement("SELECT pseudo, proprietaire FROM goat, piege "
//                    + "WHERE goat.x + 40 >= piege.x - 35 AND goat.x + 40 <= piege.x + 35 AND goat.y + 50 >= piege.y - 20 AND goat.y + 50 <= piege.y + 50");
            
            //*****Zone de detection ronde autour de la bombe*****//
            PreparedStatement requete = ConnexionBDD.getInstance().prepareStatement("SELECT pseudo, proprietaire FROM goat, piege "
                    + "WHERE SQRT((goat.x + 40 - piege.x - 5)*(goat.x + 40 - piege.x - 5)+(goat.y + 50 - piege.y - 20)*(goat.y + 50 - piege.y - 20)) < 40");
                        
//            PreparedStatement requete = ConnexionBDD.getInstance().prepareStatement("UPDATE goat SET nbVie = nbVie - 1 WHERE (SELECT pseudo, proprietaire FROM goat, piege "
//                    + "WHERE goat.x + 40 >= piege.x - 35 AND goat.x + 40 <= piege.x + 35 AND goat.y + 50 >= piege.y - 20 AND goat.y + 50 <= piege.y + 50)");
            
            ResultSet resultat = requete.executeQuery();
            
            while (resultat.next()) {
                String pseudo = resultat.getString("pseudo");
                String proprietaire = resultat.getString("proprietaire");
                
//                requete = ConnexionBDD.getInstance().prepareStatement("UPDATE goat SET nbVie = nbVie - 1 WHERE pseudo = ?");
//                requete.setString(1, pseudo);
                
                System.out.println(pseudo + " killed by " + proprietaire);  
            }

            requete.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
