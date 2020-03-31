/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TheGoatMultiJoueurs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author maxbu
 */
public class ConnexionBDD {
    
    private String url = "jdbc:mysql://nemrod.ens2m.fr:3306/20192020_s2_vs2_tp1_goat?serverTimezone=UTC";
    
    private String user = "goat";
    
    private String password = "9FdqUt5uXibSkOF8";
    
    private static Connection connexion;
    
  private ConnexionBDD(){
    try {
      connexion = DriverManager.getConnection(url, user, password);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
   
  //Méthode qui va nous retourner notre instance et la créer si elle n'existe pas
   public static Connection getInstance(){
    if(connexion == null){
      new ConnexionBDD();
    }
    return connexion;   
  }   
}

