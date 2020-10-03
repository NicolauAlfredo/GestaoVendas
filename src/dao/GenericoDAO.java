/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;

/**
 *
 * @author user
 */
public interface GenericoDAO<T> {

    void guardar(T t);

    void actualizar(T t);

    void apagar(T t);

    T buscarPorId(Integer id);

    ArrayList<T> BuscarTodos();
}
