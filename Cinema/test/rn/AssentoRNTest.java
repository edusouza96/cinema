package rn;

import java.text.ParseException;
import model.Assento;
import model.Filme;
import model.Sala;
import model.Sessao;
import org.junit.Test;
import static org.junit.Assert.*;
import util.DateUtil;

/**
 *
 * Classe que teste as regras de negocio da classe AssentoRN
 */
public class AssentoRNTest {
    private AssentoRN lista;
    private Filme filme;
    private Sala sala;
    private Sessao sessao;
        
    /**
     * construtor que inicializa os objetos
     */
    public AssentoRNTest() {
        lista = new AssentoRN();
        filme = new Filme(100, "filmeTeste", "Genero Teste", "sinopse Teste");
        sala = new Sala (666, 100);
        try {
            sessao =  new Sessao(30, sala, filme, DateUtil.stringToHour("03:00"));
        } catch (ParseException ex) {
            System.out.println("");
        }
    }
    

    /**
     * Testa do método adicionar, da classe AssentoRN.
     * @throws java.lang.Exception
     */
    @Test
    public void testAdicionar() throws Exception {
        System.out.println("adicionar");
        Assento a = new Assento(sessao, 10, DateUtil.stringToDate("05/03/2016"));
        lista.adicionar(a);
        assertTrue(!lista.listar().contains(a));
    }

    /**
     * Teste do método atualizar, da classe AssentoRN.
     * @throws java.lang.Exception
     */
    @Test
    public void testAtualizar() throws Exception {
        System.out.println("atualizar");
        int tamanhoLista = lista.listar().size();
        int ultimoRegistro = lista.listar().get(tamanhoLista-1).getCodigoAssento();
        Assento assento = new Assento(ultimoRegistro,sessao, 9, DateUtil.stringToDate("05/03/2016"));
        lista.atualizar(assento);
        assertTrue(!lista.listar().contains(assento));

    }

    /**
     * Teste do metodo procurarPorDataCodigo ,da classe AssentoRN.
     * @throws java.lang.Exception
     */
    @Test
    public void testProcurarPorDataCodigo() throws Exception {
        System.out.println("procurarPorDataCodigo");
        Assento result = lista.procurarPorDataCodigo(DateUtil.stringToDate("05/03/1996"), 4);
        assertNotNull(result);
     
    }
    
}
