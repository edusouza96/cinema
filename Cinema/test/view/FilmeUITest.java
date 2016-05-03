package view;

import model.Filme;
import org.junit.Test;
import static org.junit.Assert.*;
import repositorio.RepositorioFilmes;

/**
 *
 * @see Testa a Classe FilmeUI
 */
public class FilmeUITest {
    
    private final RepositorioFilmes lista = new RepositorioFilmes();
    private Filme filme = new Filme("The Avengers","Ficção/Ação","Heróis da marvel unidos para lutar contra loki");
    
    public FilmeUITest() {
    }   
    
    /**
     * testa o cadastro de filmes
     */
    @Test
    public void testCadastrarFilme() {
        lista.adicionar(filme);
        assertTrue(lista.getListaFilmes().contains(filme));
    }
    /**
     * testa a edição de filmes
     */
    @Test
    public void testAlterarFilme(){
        lista.adicionar(filme);
        String nomeFilme2 = filme.getNomeFilme();
        String generoFilme2 = filme.getGenero();
        String sinopseFilme2 = filme.getSinopse();
        filme = lista.consultarPorNome("The Avengers");
        filme.setNomeFilme("Os vingadores");
        filme.setGenero("Ficção");
        filme.setSinopse("Filme da Marvel");
        //assertNotEquals(nomeFilme2, filme.getNomeFilme());
        //assertNotEquals(generoFilme2, filme.getGenero());
        //assertNotEquals(sinopseFilme2, filme.getSinopse());
        assertFalse(nomeFilme2.equals(filme.getNomeFilme())); 
        assertFalse(generoFilme2.equals(filme.getGenero())); 
        assertFalse(sinopseFilme2.equals(filme.getSinopse()));
        
    }
    /**
     * Testa a exclusão de filmes
     */
   @Test
    public void testDeletarFilme() {
        lista.adicionar(filme);
        filme = lista.consultarPorNome("The Avengers");
        lista.remover(filme);
        assertEquals(0, lista.getListaFilmes().size());
    }
    
    
}
