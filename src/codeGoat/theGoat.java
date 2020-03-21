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
public class theGoat {
    private String pseudo;
    private String skin;
    private int nombreDeVie;
    private double coorX;
    private double coorY;

    public theGoat(String pseudo, String skin, int nombreDeVie, double coorX, double coorY) {
        this.pseudo = pseudo;
        this.skin = skin;
        this.nombreDeVie = nombreDeVie;
        this.coorX = coorX;
        this.coorY = coorY;
    }

    @Override
    public String toString() {
        return "theGoat{" + "pseudo=" + pseudo + ", skin=" + skin + ", nombreDeVie=" + nombreDeVie + ", coorX=" + coorX + ", coorY=" + coorY + '}';
    }

    public String getPseudo() {
        return pseudo;
    }

    public String getSkin() {
        return skin;
    }

    public int getNombreDeVie() {
        return nombreDeVie;
    }

    public double getCoorX() {
        return coorX;
    }

    public double getCoorY() {
        return coorY;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public void setSkin(String skin) {
        this.skin = skin;
    }

    public void setNombreDeVie(int nombreDeVie) {
        this.nombreDeVie = nombreDeVie;
    }

    public void setCoorX(double coorX) {
        this.coorX = coorX;
    }

    public void setCoorY(double coorY) {
        this.coorY = coorY;
    }
    
    public String gauche(double deplacementGauche){
        String message = "";
        if (deplacementGauche < 0){
            return message = "deplacement impossible";
        }
        if ((this.getCoorY() + deplacementGauche >= 0)&&(this.getCoorY() + deplacementGauche <= 10)){
            this.coorY = this.getCoorY()+ deplacementGauche;
        }
        else {
            message = "deplacement impossible a gauche";
        }
        return message;
    }
    
    public String droite(double deplacementDroite){
        String message = "";
        if (deplacementDroite < 0){
            return message = "deplacement impossible";
        }
        if ((this.getCoorY() - deplacementDroite >= 0)&&(this.getCoorY() - deplacementDroite <= 10)){
            this.coorY = this.getCoorY() - deplacementDroite;
        }
        else {
            message = "deplacement impossible a droite";
        }
        return message;
    }
    
    public String avant(double deplacementAvant){
        String message = "";
        if (deplacementAvant < 0){
            return message = "deplacement impossible";
        }
        if ((this.getCoorX() + deplacementAvant >= 0)&&(this.getCoorY() + deplacementAvant <= 100)){
            this.coorX = this.getCoorX()+ deplacementAvant;
        }
        else {
            message = "deplacement avant impossible";
        }
        return message;
    }
    
    public String  arriere(double deplacementArriere){
        String message = "";
        if (deplacementArriere < 0){
            return message = "deplacement impossible";
        }
        else if ((this.getCoorX() - deplacementArriere >= 0)&&(this.getCoorY() - deplacementArriere <= 100)){
            this.coorX = this.getCoorX()- deplacementArriere;
        }
        else { 
            message = "deplacement arriere impossible";
        }
        return message;
    }
}
