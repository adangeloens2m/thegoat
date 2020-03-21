/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exempledejeu1;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * Exemple de classe monstre heritee d'un autre monstre
 * 
 * @author guillaume.laurent
 */
public class PogoZombie extends Zombie {

    public PogoZombie(int ligne) {
        super(ligne);
        try {
            this.sprite = ImageIO.read(getClass().getClassLoader().getResource("resources/pogoZombie.png"));
        } catch (IOException ex) {
            Logger.getLogger(Jeu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void miseAJour() {
        super.miseAJour();
        y = 80 + ligne * 80 - 60*Math.abs(Math.cos(x/30));
    }   
    
}
