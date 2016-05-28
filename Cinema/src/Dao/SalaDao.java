package Dao;

import model.Sala;

public interface SalaDao extends Dao<Sala> {
    public Sala procurarPorNumero(int numero);
}
