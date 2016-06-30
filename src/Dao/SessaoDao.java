package Dao;

import java.util.Date;
import java.util.List;
import model.Filme;
import model.Sala;
import model.Sessao;

/**
 * Classe que contem as 'Assinaturas' dos metodos a ser implementados
 
 * @see procurarPorHorario(Date horario)
 * :procura horario de acordo com parametro, retornando uma lista dos horarios desejados
 */
public interface SessaoDao extends Dao<Sessao>{
    public List<Sessao> procurarPorHorario(Date horario);
    
}
