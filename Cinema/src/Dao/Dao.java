package Dao;

import java.util.List;

public interface Dao<T> {
    public void adicionar(T model);
    public List<T> listar();
    public T procurarPorId(int id);
    public void atualizar(T model);
    public void deletar(T model);
}
