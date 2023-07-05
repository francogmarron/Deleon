/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unsl.SparkProductoSQL2o.producto;

/**
 *
 * @author franco
 */
import java.util.List;
import org.sql2o.*;

public class UserDAO {
    
    private List<User> usuarios;
    
    /*
    public List<User> verificarPersona( String email, String pass) {
        
        //colocar los datos de su  servidor de Mysql (root) y contrasea (adminadmin)
        Sql2o sql2o = new Sql2o("jdbc:mysql://localhost:3306/loginspark?serverTimezone=UTC", "root", "adminadmin");
         
        try (Connection con = sql2o.open()) {
            
            String sql = "SELECT * FROM usuarios WHERE email = :email and  pass = :pass";

            usuarios = con
                .createQuery(sql)
                .addParameter("email", email)
                .addParameter("pass", pass)
                .executeAndFetch(User.class);
        }
        catch(Exception e) {
            System.out.println(e);}
        return usuarios;
    }
*/
}
