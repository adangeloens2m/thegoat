/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piege;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author achen
 */
public class Bombe extends Piege{
    private Image imgBombe;
    private ImageIcon icoBombe;

    public Bombe(int x, int y, String proprietaire) {
        super(x, y, 30, 30, proprietaire, false);
        this.icoBombe = new ImageIcon(getClass().getResource("/images/bombe.png"));
        this.imgBombe = this.icoBombe.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
    }

    public Image getImgBombe() {
        return imgBombe;
    }
}
