/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codeGoat;

/**
 *
 * @author paulb
 */
public class testGoat {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //création d'une Goat test "max"
        theGoat max;
        max = new theGoat("max","red",5,1,1);
        
        //vérification des paramètres de "max"
        System.out.println(max);
        
        //test de déplacement gauche
        System.out.println(max.gauche(5));
        System.out.println(max);
        
        //test de déplacement droite 
        System.out.println(max.droite(15));
        System.out.println(max);
        
        //test de déplacement avant
        System.out.println(max.avant(1));
        System.out.println(max);
        
        //test de déplacement arriere 
        System.out.println(max.arriere(13));
        System.out.println(max);
    }
    
}
