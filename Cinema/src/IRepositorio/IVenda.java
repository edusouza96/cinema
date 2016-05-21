package IRepositorio;

import model.Venda;

/**
 *contem adicionar e consultar por registro
 */
public interface IVenda {
    public void adicionar(Venda venda);
    public Venda consultarPorRegistro(int registro);
    
    
    
}
