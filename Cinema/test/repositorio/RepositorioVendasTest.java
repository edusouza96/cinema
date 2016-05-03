package repositorio;

import java.util.ArrayList;
import model.Venda;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *@see Testa a Classe RepositorioVendas
 */
public class RepositorioVendasTest {
    private RepositorioVendas lista;
    
    public RepositorioVendasTest() {
        lista = new RepositorioVendas();
    }
    
    /**
     * Teste do metodo getListaVendas, da classe RepositorioVendas.
     */
    @Test
    public void testGetListaVendas() {
        ArrayList<Venda> expResult = null;
        ArrayList<Venda> result = lista.getListaVendas();
        assertTrue(expResult != result);
       
    }

    /**
     * Teste do metodo adicionar , da classe RepositorioVendas.
     */
    @Test
    public void testAdicionar() {
        Venda venda = null;
        lista.adicionar(venda);
        assertTrue(lista.getListaVendas().contains(venda));
        
    }

    /**
     * Teste do metodo consultarPorRegistro , da classe RepositorioVendas.
     */
    @Test
    public void testConsultarPorRegistro() {
        int registro = 0;
         Venda expResult = null;
        Venda result = lista.consultarPorRegistro(registro);
        assertEquals(expResult, result);
        
    }
    
}
