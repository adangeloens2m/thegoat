/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TheGoatTuto;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author Arnaud
 */
public class Souris extends MouseAdapter{

    @Override
    public void mouseClicked(MouseEvent e) {
        Main.scene.bombe.setX(e.getX());
        Main.scene.bombe.setY(e.getY());
        Main.scene.bombe.setActif(true);
    }
}
