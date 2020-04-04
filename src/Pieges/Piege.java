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
    
    private int largeur;
    private int hauteur;
    
    private String proprietaire;
    
    private String type;
    
    private boolean actif;

    public Piege(String type, int largeur, int hauteur, String proprietaire, boolean actif) {
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }
}
