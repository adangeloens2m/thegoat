/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TheGoatMultiJoueurs;

import java.io.IOException;
import javax.swing.JFrame;

/**
 *
 * @author Arnaud
 */
public class Main {

    public static Scene scene;
    
    public static void main(String[] args){
        
        //Fenetre du jeu
        JFrame fenetre = new JFrame("TheGoatMultiJoueur");
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setSize(1100, 560);
        fenetre.setLocationRelativeTo(null);
        fenetre.setResizable(false);
        fenetre.setAlwaysOnTop(true);
        
        //Instanciation Scene
        scene = new Scene ("Arnaud");
        
        fenetre.setContentPane(scene);
        fenetre.setVisible(true);
    }
    
}