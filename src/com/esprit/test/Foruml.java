/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.test;

import com.esprit.modeles.Commentaire;
import com.esprit.modeles.Like;
import com.esprit.modeles.Publication;
import com.esprit.services.CommentaireService;
import com.esprit.services.LikeService;
import com.esprit.services.PublicationService;
import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author HP
 */
public class Foruml {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //declaration + instanciation
        

        Like l2 = new Like(1,"3");
        LikeService ls = new LikeService();
        //ls.create(l2);
        //ls.delete();
        //ls.countLike("3");
        //ls.countDislike("3");
        
        //Commentaire c = new Commentaire();
        //Commentaire c1 = new Commentaire("kkkkk", new Timestamp(new Date().getTime()));

        CommentaireService cs = new CommentaireService();
        //cs.create(c1);
        //c1.setId(5);
        //cs.update(c1);
        //cs.delete(5);
        
        
        
        
       

        
        Publication p2 = new Publication("riadh", "hhh", new Timestamp(new Date().getTime()), "", 1);
        System.out.println(p2);
        
        PublicationService ps = new PublicationService();
        //ps.create(p2);
       //ps.update(p2);
       //ps.delete("4");
        ps.displayAllByUser(1);
    }
    
}
