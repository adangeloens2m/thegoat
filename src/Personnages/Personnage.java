/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personnages;

import TheGoatTuto.Main;

/**
 *
 * @author maxime.bulabois
 */
public class Personnage {

    private int largeur, hauteur; //dimensions du perso
    private int x, y; //position du perso
    private String pseudo;

    public Personnage(int x, int y, int largeur, int hauteur, String pseudo) {

        this.x = x;
        this.y = y;
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.pseudo = pseudo;
    }

    //Methodes


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

    public String getPseudo() {
        return pseudo;
    }

    //SETTERS
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }  
}
