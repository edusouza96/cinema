package IRepositorio;

import model.Sala;

/**
 *contem adicionar, consultar por sala, remover
 */
public interface ISala {
    public void adicionar(Sala sala);
    public Sala consultarPorSala(int NumeroSala);
    public void remover(Sala sala);
}
