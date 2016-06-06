package Dao;

import java.util.Date;
import java.util.List;
import model.Filme;
import model.Sala;
import model.Sessao;

/**
 * Classe que contem as 'Assinaturas' dos metodos a ser implementados
 * @see procurarPorFilme(Filme filme): procura filmes de acordo com parametro, retornando uma lista dos filmes desejados
 * @see procurarPorSala(Sala sala):procura salas de acordo com parametro, retornando uma lista das salas desejadas
 * @see procurarPorSala(Date horario)):procura horario de acordo com parametro, retornando uma lista dos horarios desejados
 */
public interface SessaoDao extends Dao<Sessao>{
    public List<Sessao> procurarPorHorario(Date horario);
    
}
