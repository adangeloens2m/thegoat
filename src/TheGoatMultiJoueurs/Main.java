/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TheGoatMultiJoueurs;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Arnaud
 */
public class Main {

    public static Scene scene;

    public static void main(String[] args) {

        //Fenetre du jeu
        JFrame fenetre = new JFrame("TheGoatMultiJoueurs");
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setSize(1100, 560);
        fenetre.setLocationRelativeTo(null);
        fenetre.setResizable(false);
        fenetre.setAlwaysOnTop(true);

        //Boite de dialogue
        String pseudo = JOptionPane.showInputDialog(fenetre, "Entrez votre pseudo", "The Goat", JOptionPane.QUESTION_MESSAGE);
        String personnage = (String) JOptionPane.showInputDialog(fenetre, "Choisissez un rôle", "The Goat", JOptionPane.QUESTION_MESSAGE, null, new String[]{"goat", "loup"}, "goat");
        String skin = "";
        if(personnage == "goat"){
            skin = (String) JOptionPane.showInputDialog(fenetre, "Choisissez un skin", "The Goat", JOptionPane.QUESTION_MESSAGE, null, new String[]{"goat", "bleu", "banc", "rouge"}, "goat");
        }
         if (pseudo.isBlank()) {
            System.exit(0);
        }
        //Instanciation Scene
        scene = new Scene(pseudo, personnage, skin);

        fenetre.setContentPane(scene);
        fenetre.setVisible(true);
    }

}
