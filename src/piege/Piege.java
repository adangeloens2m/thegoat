/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piege;

/**
 *
 * @author achen
 */
public class Piege {
    private String type;
    private int x;
    private int y;
    private String proprietaire;
    private boolean actif;

    public Piege(String type, int x, int y, String proprietaire, boolean actif) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.proprietaire = proprietaire;
        this.actif = actif;
    }

    public String getType() {
        return type;
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

    public void setType(String type) {
        this.type = type;
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
