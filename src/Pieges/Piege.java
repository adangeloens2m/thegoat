/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pieges;

/**
 *
 * @author achen
 */
public class Piege {
    
    private int x;
    private int y;
    
    private int longueur;
    private int largeur;
    
    private String proprietaire;
    
    private boolean actif;

    public Piege(int x, int y, int largeur, int longueur, String proprietaire, boolean actif) {
        this.x = x;
        this.y = y;
        this.longueur = longueur;
        this.largeur = largeur;
        this.proprietaire = proprietaire;
        this.actif = actif;
    }

    public int getLongueur() {
        return longueur;
    }

    public void setLongueur(int longueur) {
        this.longueur = longueur;
    }

    public int getLargeur() {
        return largeur;
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getProprietaire() {
        return proprietaire;
    }

    public boolean isActif() {
        return actif;
    }


    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setProprietaire(String proprietaire) {
        this.proprietaire = proprietaire;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }
       


}
