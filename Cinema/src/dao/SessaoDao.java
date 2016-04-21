package dao;

import model.Sessao;

/**
 *contem adicionar, consultar por codigo, consultar por sala, 
 * consultar por filme, consultar por horario, remover
 */
public interface SessaoDao {
    public void adicionar(Sessao sessao);
    public Sessao consultarPorCodigo(int codigo);
    public Sessao consultarPorSala(int sala);
    public Sessao consultarPorFilme(String filme);
    public Sessao consultarPorHorario(String horario);
    public void remover(Sessao sessao);
}
