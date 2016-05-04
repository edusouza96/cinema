package repositorio;

import java.util.ArrayList;
import model.Assento;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @see Testa a Classe RepositorioAssentos
 */
public class RepositorioAssentosTest {
    private RepositorioAssentos lista;
    public RepositorioAssentosTest() {
        lista = new RepositorioAssentos();
    }
   
    /**
     * Teste do metodo getListaAssentos,da classe RepositorioAssentos.
     */
    @Test
    public void testGetListaAssentos() {
        ArrayList<Assento> expResult = null;
        ArrayList<Assento> result = lista.getListaAssentos();
        assertTrue(expResult != result);
        
    }

    /**
     * Teste do metodo adicionar, da classe RepositorioAssentos.
     */
    @Test
    public void testAdicionar() {
        Assento assento = null;
        lista.adicionar(assento);
        assertTrue(lista.getListaAssentos().contains(assento));
    }

    /**
     * Teste do método consultarPorCodigo , da classe RepositorioAssentos.
     */
    @Test
    public void testConsultarPorCodigo() {
        int codigo = 0;
        Assento expResult = null;
        Assento result = lista.consultarPorCodigo(codigo);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of consultarPorDataCodigo method, of class RepositorioAssentos.
     */
    @Test
    public void testConsultarPorDataCodigo() {
        String data = "";
        int codigo = 0;
        Assento expResult = null;
        Assento result = lista.consultarPorDataCodigo(data, codigo);
        assertEquals(expResult, result);
       
    }
    
}
