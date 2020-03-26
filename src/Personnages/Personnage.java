/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personnages;

/**
 *
 * @author maxime.bulabois
 */


public class Personnage {
    
    private int largeur, hauteur; //dimensions du perso
    private int x, y; //position du perso
    public int compteur; //compteur des pas du perso
    
    
    public Personnage(int x, int y, int largeur, int hauteur){
        
        this.x = x;
        this.y = y;
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.compteur = 0;
    }

    //GETTERS
    public int getLargeur() {
        return largeur;
    }

    public int getHauteur() {
        return hauteur;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public int getCompteur() {
        return compteur;
    }
    
    
    //SETTERS
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }


    public void setCompteur(int compteur) {
        this.compteur = compteur;
    }
    
    
    
    
}
