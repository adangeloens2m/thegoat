/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TheGoatTuto;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Arnaud
 */
public class Chrono implements Runnable {

    //Temps de pause (ms) entre 2 boucles
    private final int PAUSE = 2;

    @Override
    public void run() {
        while (true) {
            Main.scene.repaint();
            try {
                Thread.sleep(PAUSE);
            } catch (InterruptedException ex) {
                Logger.getLogger(Chrono.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
