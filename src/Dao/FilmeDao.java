package Dao;

import java.util.List;
import model.Filme;
/**
 * Classe que contem as 'Assinaturas' dos metodos a ser implementados
 * @see procurarPorNome(String nome): procura uma linha de dados através de um nome valido inserido
 * @see procurarPorGenero(String genero): retorna uma lista de dados através de um genero valido inserido
 */
public interface FilmeDao extends Dao<Filme>{
    public Filme procurarPorNome(String nome);
    public List<Filme> procurarPorGenero(String genero);
}
