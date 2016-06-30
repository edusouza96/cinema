package Dao;

import java.util.List;
/**
 * Classe que contem as 'Assinaturas' dos metodos a ser implementados
 * @see void adicionar(T model)
 * adiciona uma linha de dados
 * @see listar()
 * lista um conjunto de dados
 * @see procurarPorId(int id)
 * : procura uma linha de dados através de um id valido inserido
 * @see void atualizar(T model)
 * : atualiza  uma linha de dados
 * @see void deletar(T model)
 * : deleta uma linha de dados
 * @param <T> Parametro do generic, oou seja, uma forma genérica de representar todos os tipos de objetos
 */
public interface Dao<T> {
    public void adicionar(T model);
    public List<T> listar();
    public T procurarPorId(int id);
    public void atualizar(T model);
    public void deletar(T model);
}
