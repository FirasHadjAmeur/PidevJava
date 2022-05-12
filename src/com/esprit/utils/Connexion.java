/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author LENOVO
 */
public class Connexion {
       public final String url  ="jdbc:mysql://localhost:3306/pidev3";
    public final String user ="root";
    public final String pwd = "";
    private Connection cnx;
    public static Connexion ct;
    
    
    private Connexion() {
        try {
            cnx=DriverManager.getConnection(url, user, pwd);
            System.out.println("Connexion etablie !!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    public static Connexion getInstance(){
        if(ct==null)
            ct=new Connexion();
        return ct;
    }

    public Connection getCnx() {
        return cnx;
    }
    
}
