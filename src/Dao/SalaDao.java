package Dao;

import model.Sala;

/**
 * Classe que contem as 'Assinaturas' dos metodos a ser implementados
 * @see procurarPorNumero(int numero)
 * : procura uma linha de dados através de um numero valido inserido
 */
public interface SalaDao extends Dao<Sala> {
    public Sala procurarPorNumero(int numero);
}
