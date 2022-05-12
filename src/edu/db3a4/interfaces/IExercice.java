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
public interface IExercice<T> {
    public void ajouterExercice(T t);
     public void supprimerExercice(T t);
     public void updateExercice(T t);
     public List<T> displayExercice();
}
