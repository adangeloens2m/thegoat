/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pieges;

import TheGoatMultiJoueurs.ConnexionBDD;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author achen
 */
public class Piege {

    private int x;
    private int y;

    private int largeur;
    private int hauteur;

    String proprietaire;

    private boolean actif;

    public Piege(int x, int y, int largeur, int hauteur, String proprietaire, boolean actif) {
        this.x = x;
        this.y = y;
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.proprietaire = proprietaire;
        this.actif = actif;
    }

    public int getLargeur() {
        return largeur;
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }

    public int getHauteur() {
        return hauteur;
    }

    public void setHauteur(int hauteur) {
        this.hauteur = hauteur;
    }

    public String getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(String proprietaire) {
        this.proprietaire = proprietaire;
    }

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public void payDay(String proprietaire){
        try {
            PreparedStatement requete = ConnexionBDD.getInstance().prepareStatement(
                    "UPDATE loup SET coin = coin + 1 WHERE pseudo = '" + proprietaire + "'");
            requete.executeUpdate();
            
            requete.close();
            
            System.out.println("ok");
        } catch (SQLException ex) {
            Logger.getLogger(Piege.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
