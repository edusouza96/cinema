package dao;

import model.Sala;

/**
 *contem adicionar, consultar por sala, remover
 */
public interface SalaDao {
    public void adicionar(Sala sala);
    public Sala consultarPorSala(int NumeroSala);
    public void remover(Sala sala);
}
