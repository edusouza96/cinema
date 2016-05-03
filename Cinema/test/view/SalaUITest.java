package view;

import static junit.framework.Assert.assertEquals;
import model.Sala;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import repositorio.RepositorioSalas;

/**
 *@see Testa a Classe SalaUI
 */
public class SalaUITest {
    private final RepositorioSalas lista = new RepositorioSalas();
    private Sala sala = new Sala(100, 300);
    public SalaUITest() {
    }
   
    /**
     * testa o cadastro de sala
     */
    @Test
    public void testCadastrarSala() {
        lista.adicionar(sala);
        assertTrue(lista.getListaSalas().contains(sala));
    }
    
    /**
     * Testa a edição de sala
     */
    @Test
    public void testAlterarSala(){
        lista.adicionar(sala);
        int capacidadeSala = sala.getQuantidadeSala();
        sala = lista.consultarPorSala(100);
        sala.setQuantidadeSala(700);
        //assertNotEquals(capacidadeSala, sala.getQuantidadeSala());
        assertFalse(capacidadeSala == sala.getQuantidadeSala());
    }
    
    /**
     * Testa a remoção de sala
     */
    @Test
    public void testDeletarSala(){
        lista.adicionar(sala);
        lista.remover(sala);
        assertEquals(0, lista.getListaSalas().size());
    }
    
}
