package view;

import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Filme;
import model.Sala;
import model.Sessao;
import model.Venda;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import repositorio.RepositorioVendas;
import util.DateUtil;

/**
 *@see Testa a Classe VendaUI 
 */
public class VendaUITest {
    private Filme filme;
    private Sala sala;
    private Date hora;
    private Date data;
    private Sessao sessao;
    private Venda venda;
    private RepositorioVendas lista;
    
    public VendaUITest() {
        try {
            filme = new Filme("The Avengers","Ficção/Ação","Heróis da marvel unidos para lutar contra loki");
            sala = new Sala(100, 300);
            this.hora = DateUtil.stringToHour("18:30");
            this.data = DateUtil.stringToDate("05/05/2015");
            sessao = new Sessao(filme, sala, hora);
            venda = new Venda(sessao, data);
            lista = new RepositorioVendas();
        } catch (ParseException ex) {
            System.err.println("Erro na data, formato invalido");
        }
    }
    
    /**
     * testa o cadastro de venda
     */
    @Test
    public void testCadastrarVenda() {
        lista.adicionar(venda);
        assertTrue(lista.getListaVendas().contains(venda));
    }
    
    
}
