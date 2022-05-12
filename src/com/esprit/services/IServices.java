/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;

import java.util.List;

/**
 *
 * @author HP
 */
public interface IServices<T> {
   public boolean add(T t);

    public boolean update(T t);

    public boolean delete(T t);

    public List<T> getAll();

    public T getOne(T t);
    
    public T getById(int id);
    
   
    
    
    public void ajouter(T t);
    public void supprimer(T t);
    public void modifier(T t);
    public List<T> afficher();
    
}