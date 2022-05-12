/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author skon1
 * @param <T>
 */
public interface IIService<T> {

    public boolean add(T t);

    public boolean update(T t);

    public boolean delete(T t);

    public List<T> getAll()throws SQLException;

    public T getOne(T t);
    
    public T getById(int id);
    
    

}
