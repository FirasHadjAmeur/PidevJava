/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.db3a4.interfaces;

import java.util.List;

/**
 *
 * @author safwen
 */
public interface ICategoryexercice<T> {
    public void ajouterCategoryexercice(T t);
     public void supprimerCategoryexercice(T t);
     public void updateCategoryexercice(T t);
     public List<T> displayCategoryexercice();
    
}
