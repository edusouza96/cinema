package dao;

import model.Venda;

/**
 *contem adicionar e consultar por registro
 */
public interface VendaDao {
    public void adicionar(Venda venda);
    public Venda consultarPorRegistro(int registro);
    
    
    
}
