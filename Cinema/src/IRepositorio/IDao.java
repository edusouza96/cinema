/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IRepositorio;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author 631420057
 * @param <T>
 */
public interface IDao<T> extends Collection<T> {
    public void inserir(T obj);
    public ArrayList<T> exibir();
    public void atualizar(int id);
    public void deletar(int id);
    public T consultarPorID(int id);
    
    
}
