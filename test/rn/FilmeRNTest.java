package rn;

import java.util.List;
import model.Filme;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

/**
 *
 * Classe que teste as regras de negocio da classe FilmeRN
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FilmeRNTest {
    private FilmeRN lista;
    public FilmeRNTest() {
        lista = new FilmeRN();
        
    }
    
    /**
     * Teste do método adicionar, da classe FilmeRN.
     * @throws java.lang.Exception
     */
    @Test
    public void testAAdicionar() throws Exception {
        System.out.println("adicionar");
        Filme f = new Filme("filmeTeste", "generoTeste", "sinopseTeste");
        lista.adicionar(f);
        assertNotNull(lista.procuraPorNome(f.getNomeFilme()));
    }

    /**
     * Teste do método listar, da classe FilmeRN.
     */
    @Test
    public void testBListar() {
        System.out.println("listar");
        int expResult = 0;
        int result = lista.listar().size();
        assertNotEquals(expResult, result);
        
    }

     /**
     * Teste do método procuraPorNome, da classe FilmeRN.
     * @throws java.lang.Exception
     */
    @Test
    public void testCProcuraPorNome() throws Exception {
        System.out.println("procuraPorNome");
        String nome = "filmeTeste";
        Filme expResult = null;
        Filme result = lista.procuraPorNome(nome);
        assertNotEquals(expResult, result);
        
    }

    /**
     * Teste do metodo procurarPorId, da classe FilmeRN.
     * @throws java.lang.Exception
     */
    @Test
    public void testDProcurarPorId() throws Exception {
        System.out.println("procurarPorId");
        int tamanhoLista = lista.listar().size();
        int ultimoRegistro = lista.listar().get(tamanhoLista-1).getCodigoFilme();
        
        Filme result = lista.procurarPorId(ultimoRegistro);
        assertNotNull(result);
       
    }

    /**
     * Teste do metodo procuraPorGenero , da classe FilmeRN.
     * @throws java.lang.Exception
     */
    @Test
    public void testEProcuraPorGenero() throws Exception {
        System.out.println("procuraPorGenero");
        String genero = "teste";
        List<Filme> expResult = null;
        List<Filme> result = lista.procuraPorGenero(genero);
        assertNotEquals(expResult, result);
        
    }

    /**
     * Teste do metodo filmeExiste, da classe FilmeRN.
     */
    @Test
    public void testFFilmeExiste() {
        System.out.println("filmeExiste");
        String nome = "filme";
        boolean expResult = false;
        boolean result = lista.filmeExiste(nome);
        assertEquals(expResult, result);
    }
    
    /**
     * Teste do metodo atualizar, da classe FilmeRN.
     * @throws java.lang.Exception
     */
    @Test
    public void testGAtualizar() throws Exception {
        System.out.println("atualizar");
        int tamanhoLista = lista.listar().size();
        int ultimoRegistro = lista.listar().get(tamanhoLista-1).getCodigoFilme();
        Filme filme = new Filme(ultimoRegistro,"filmeTeste", "generoTeste", "sinopseTeste");
        lista.atualizar(filme);
        assertTrue(!lista.listar().contains(filme));
        
    }
    /**
     * Teste do metodo deletar, da classe FilmeRN.
     * @throws java.lang.Exception
     */
    @Test
    public void testHDeletar() throws Exception {
        System.out.println("deletar");
        int tamanhoLista = lista.listar().size();
        int ultimoRegistro = lista.listar().get(tamanhoLista-1).getCodigoFilme();
        Filme filme = new Filme(ultimoRegistro,"filmeTeste", "generoTeste", "sinopseTeste");
        lista.deletar(filme);
        int _tamanhoLista = lista.listar().size();
        
        assertTrue(_tamanhoLista != tamanhoLista);
    }

    

   
    
}
