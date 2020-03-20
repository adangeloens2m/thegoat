/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piege;

import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import outil.OutilsJDBC;

/**
 *
 * @author achen
 */
public class Piege {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {

            Connection connexion = DriverManager.getConnection("jdbc:mysql://nemrod.ens2m.fr:3306/20192020_s2_vs2_tp1_goat?serverTimezone=UTC", "goat", "9FdqUt5uXibSkOF8");

            PreparedStatement requete = connexion.prepareStatement("SELECT * FROM piege;");
            ResultSet resultat = requete.executeQuery();
            OutilsJDBC.afficherResultSet(resultat);

            requete.close();
            connexion.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void AffichePiege (){
        BufferedImage tileSet = ImageIO.read(new File("tileSet.png"));

        int [][] mat = new int [19][90];
        for (int i = 0 ; i<mat.length ; i++){
            for (int j = 0 ; j<mat[i].length ; j++) {
                if(i==1){
                    mat[i][j] = 184;
                }
                else{
                    mat[i][j] = 21;  
                }
            }
        }
        mat[18][89]=220;
    }
    
}
