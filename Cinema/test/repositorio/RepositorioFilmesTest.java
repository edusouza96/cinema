package repositorio;

import java.util.ArrayList;
import model.Filme;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 *@see Testa a Classe RepositorioFilmes 
 */
public class RepositorioFilmesTest {
    private RepositorioFilmes lista;
    private Filme filme;
    
    public RepositorioFilmesTest() {
        lista = new RepositorioFilmes();
        filme = new Filme("nome filme", "genero do filme", "sinopse do filme");
        lista.adicionar(filme);
    }
    
    /**
     * Teste do método getListaFilmes, da classe RepositorioFilmes.
     */
    @Test
    public void testGetListaFilmes() {
        ArrayList<Filme> expResult = null;
        ArrayList<Filme> result = lista.getListaFilmes();
        assertFalse(expResult == result);
        
    }

    /**
     * Teste do método adicionar, da classe RepositorioFilmes.
     */
    @Test
    public void testAdicionar() {
        Filme filme = null;
        lista.adicionar(filme);
        assertTrue(lista.getListaFilmes().contains(filme));
    }

    /**
     * Teste do método consultarPorCodigo, da classe RepositorioFilmes.
     */
    @Test
    public void testConsultarPorCodigo() {
        int codigo = 0;
        Filme expResult = null;
        Filme result = lista.consultarPorCodigo(codigo);
        assertEquals(expResult, result);
        
    }

    /**
     * Teste do método consultarPorNome, da classe RepositorioFilmes.
     */
    @Test
    public void testConsultarPorNome() {
        String nome = "";
        Filme expResult = null;
        Filme result = lista.consultarPorNome(nome);
        assertEquals(expResult, result);
        
    }

    /**
     * Teste do método remover, da classe RepositorioFilmes.
     */
    @Test
    public void testRemover() {
        
        lista.remover(filme);
        assertEquals(0, lista.getListaFilmes().size());
    }
    
}
