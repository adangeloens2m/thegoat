/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TheGoatTuto;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Arnaud
 */
public class Clavier extends KeyAdapter{

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            Main.scene.setDx(1);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT){
            Main.scene.setDx(-1);
        }else if (e.getKeyCode() == KeyEvent.VK_UP){
            Main.scene.setDy(-1);
        }else if (e.getKeyCode() == KeyEvent.VK_DOWN){
            Main.scene.setDy(1);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        Main.scene.setDx(0);
        Main.scene.setDy(0);
    }
    
}
