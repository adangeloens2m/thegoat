/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TheGoatTuto;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Arnaud
 */
public class Clavier implements KeyListener{

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

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
