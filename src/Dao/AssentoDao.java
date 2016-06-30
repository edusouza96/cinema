package Dao;

import java.util.Date;
import model.Assento;

/**
  Classe que contem as 'Assinaturas' dos metodos a ser implementados
 * @see consultarPorDataCodigo(Date data, int codigo)
 * procura uma linha de dados que tenha uma sessao de um determinado dia
 */
public interface AssentoDao extends Dao<Assento> {
     public Assento consultarPorDataCodigo(Date data, int codigo);
}
