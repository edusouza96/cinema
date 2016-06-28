package Dao;

import java.util.List;
import model.Venda;

/**
 *Classe que contem as 'Assinaturas' dos metodos a ser implementados
 * *No momento não possui nenhum comportamento a  mais em relação as outras entidade*
 */
public interface VendaDao extends Dao<Venda>{

    public List<Venda> listarPorData();
    
    
}
