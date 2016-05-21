package IRepositorio;

import model.Filme;

/**
 *contem adicionar, consultar por codigo, consultar por nome, remover
 */
public interface IFilme {
    public boolean adicionar(Filme filme);
    public Filme consultarPorCodigo(int codigo);
    public Filme consultarPorNome(String nome);
    public void remover(Filme filme);
}
