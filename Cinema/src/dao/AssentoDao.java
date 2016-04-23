package dao;

import model.Assento;

/**
 *contem adicionar, consultar por codigo, consultar por data
 */
public interface AssentoDao {
    public void adicionar(Assento assento);
    public Assento consultarPorCodigo(int codigo);
    public Assento consultarPorData(String data);
}
