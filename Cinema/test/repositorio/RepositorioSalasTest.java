package repositorio;

import java.util.ArrayList;
import static junit.framework.Assert.assertEquals;
import model.Sala;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @see Testa a Classe RepositorioSalas 
 */
public class RepositorioSalasTest {
    private RepositorioSalas lista;
    public RepositorioSalasTest() {
        lista = new RepositorioSalas();
    }
    
    

    /**
     * Teste do método getListaSalas, da classe RepositorioSalas.
     */
    @Test
    public void testGetListaSalas() {
        ArrayList<Sala> expResult = null;
        ArrayList<Sala> result = lista.getListaSalas();
        assertFalse(expResult == result);
        
    }

    /**
     * Teste do método adicionar , da classe RepositorioSalas.
     */
    @Test
    public void testAdicionar() {
        Sala sala = null;
        lista.adicionar(sala);
        assertTrue(lista.getListaSalas().contains(sala));
    }

    /**
     * Teste do método consultarPorSala, da classe RepositorioSalas.
     */
    @Test
    public void testConsultarPorSala() {
        int sala = 10;
        Sala expResult = null;
        Sala result = lista.consultarPorSala(sala);
        assertEquals(expResult, result);
    }

    /**
     * Teste do método remover, da classe RepositorioSalas.
     */
    @Test
    public void testRemover() {
        Sala sala = null;
        lista.remover(sala);
        assertEquals(0, lista.getListaSalas().size());
    }
    
}
