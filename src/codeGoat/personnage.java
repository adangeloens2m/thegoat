/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codeGoat;

/**
 *
 * @author paulb
 */
public class personnage {
    private String pseudo;
    private int nombreDeVie;
    private int coorX;
    private int coorY;
    private double hauteur;
    private double largueur;

    public personnage(String pseudo, int nombreDeVie, int coorX, int coorY, double hauteur, double largueur) {
        this.pseudo = pseudo;
        this.nombreDeVie = nombreDeVie;
        this.coorX = coorX;
        this.coorY = coorY;
        this.hauteur = hauteur;
        this.largueur = largueur;
    }

    public String getPseudo() {
        return pseudo;
    }

    public int getNombreDeVie() {
        return nombreDeVie;
    }

    public int getCoorX() {
        return coorX;
    }

    public int getCoorY() {
        return coorY;
    }

    public double getHauteur() {
        return hauteur;
    }

    public double getLargueur() {
        return largueur;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public void setNombreDeVie(int nombreDeVie) {
        this.nombreDeVie = nombreDeVie;
    }

    public void setCoorX(int coorX) {
        this.coorX = coorX;
    }

    public void setCoorY(int coorY) {
        this.coorY = coorY;
    }
         
}
