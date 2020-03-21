package exempledejeu1;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * Exemple de classe jeu
 *
 * @author guillaume.laurent
 */
public class Jeu {

    private BufferedImage jardin;
    private Zombie unZombie;

    public Jeu() {
        try {
           this.jardin = ImageIO.read(getClass().getClassLoader().getResource("resources/jardin.jpg"));
        } catch (IOException ex) {
            Logger.getLogger(Jeu.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.unZombie = new Zombie(2);
    }

    public void miseAJour() {
        this.unZombie.miseAJour();
    }

    public void rendu(Graphics2D contexte) {
        contexte.drawImage(this.jardin, 0, 0, null);
        this.unZombie.rendu(contexte); 
    }

}
