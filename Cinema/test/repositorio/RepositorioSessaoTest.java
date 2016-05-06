package repositorio;

import java.util.ArrayList;
import model.Sessao;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @see Testa a Classe RepositorioSessao
 */
public class RepositorioSessaoTest {
    private RepositorioSessao lista;
    public RepositorioSessaoTest() {
        lista = new RepositorioSessao();
    }

   
    /**
     * Teste do método getListaSessao, da classe RepositorioSessao.
     */
    @Test
    public void testGetListaSessao() {
        ArrayList<Sessao> expResult = null;
        ArrayList<Sessao> result = lista.getListaSessao();
        assertFalse(expResult == result);
        
    }

    
    /**
     * Teste do método adicionar, da class RepositorioSessao.
     */
    @Test
    public void testAdicionar() {
        Sessao sessao = null;
        lista.adicionar(sessao);
        assertTrue(lista.getListaSessao().contains(sessao));
    }

    /**
     * Teste do método consultarPorCodigo, da classe RepositorioSessao.
     */
    @Test
    public void testConsultarPorCodigo() {
        int codigo = 0;
        Sessao expResult = null;
        Sessao result = lista.consultarPorCodigo(codigo);
        assertEquals(expResult, result);
        
    }

    /**
     * Teste do método consultarPorSala, da classe RepositorioSessao.
     */
    @Test
    public void testConsultarPorSala() {
        int sala = 0;
        Sessao expResult = null;
        Sessao result = lista.consultarPorSala(sala);
        assertEquals(expResult, result);
        
    }

    /**
     * Teste do método consultarPorFilme, da classe RepositorioSessao.
     */
    @Test
    public void testConsultarPorFilme() {
        String filme = "";
        Sessao expResult = null;
        Sessao result = lista.consultarPorFilme(filme);
        assertEquals(expResult, result);
        
    }

    /**
     * Teste do método consultarPorHorario, da classe RepositorioSessao.
     */
    @Test
    public void testConsultarPorHorario() {
        String horario = "";
        Sessao expResult = null;
        Sessao result = lista.consultarPorHorario(horario);
        assertEquals(expResult, result);
        
    }

    /**
     * Teste do método remover, da classe RepositorioSessao.
     */
    @Test
    public void testRemover() {
        Sessao sessao = null;
        lista.remover(sessao);
        assertEquals(0, lista.getListaSessao().size());
        
    }

   
    
}
