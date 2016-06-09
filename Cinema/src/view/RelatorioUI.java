package view;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import model.Filme;
import model.Sala;
import model.Sessao;
import model.Venda;
import rn.FilmeRN;
import rn.SalaRN;
import rn.SessaoRN;
import rn.VendaRN;
import util.Console;
import util.DateUtil;

/**
 *Classe respponsavel por exibir os relatórios
 */
public class RelatorioUI {
    private FilmeRN filmeRN;
    private SalaRN salaRN;
    private SessaoRN sessaoRN;
    private VendaRN vendaRN;
    
    /**
     * Construtor
     */
    public RelatorioUI() {
        filmeRN = new FilmeRN();
        salaRN = new SalaRN();
        sessaoRN = new SessaoRN();
        vendaRN = new VendaRN();
        
    }
    /**
     * metodo de escolha de cada ação
     */
    public void executar() {
        int opcao;
        do{
            System.out.println(MenuUI.menuRelatorio());
            opcao = Console.scanInt("Digite sua opção desejada:");
            switch (opcao) {
                case MenuUI.VENDA_FILME:
                    vendaPorFilme();
                    break;
                case MenuUI.VENDA_SALA:
                    vendaPorSala();
                    break;
                case MenuUI.VENDA_SESSAO:
                    vendaPorSessao();
                    break;
                case MenuUI.VENDA_DATA:
                    vendaPorData();
                    break;
                case MenuUI.VENDA_HORARIO:
                    vendaPorHorario();
                    break;
                case MenuUI.FILME_SESSAO:
                    filmePorSessao();
                    break;
                case MenuUI.SALAS_SESSAO:
                    salasPorSessao();
                    break;
                case MenuUI.SAIR:
                    JOptionPane.showMessageDialog(null, "Retornando ao Menu Principal!");
                    break;
                
                default:
                    JOptionPane.showMessageDialog(null, "Opção Invalida!", null, ERROR_MESSAGE);
            }
        }while(opcao != 0);
    }
    
    /**
     * exibe o número de vendas por filme
     */
    private void vendaPorFilme(){
        List<Filme> listaFilmes = filmeRN.listar();
        List<Venda> listaVendas = vendaRN.listar();
        System.out.println("___________________________________________\n");
        System.out.println("_________VENDA_POR FILME___________________\n");
        System.out.println("___________________________________________\n");
        System.out.println(String.format("%-20s", "Nº de Vendas") + "\t"
                + String.format("%-80s", "|Nome do Filme"));
        for(Filme filme : listaFilmes){
            int cont =0;
            for(Venda venda : listaVendas){
                if(venda.getFilmeSalaSessao().getFilme().getCodigoFilme() == filme.getCodigoFilme()){
                    cont++;
                }
            }
            System.out.println(String.format("%-20s", cont) + "\t"
                    + String.format("%-80s", "|" + filme.getNomeFilme()));
        }
    }
    
    /**
     * exibe o número de vendas por uma determinada data
     */
    private void vendaPorData(){
        List<Venda> listaVendas = vendaRN.listar();
        System.out.println("___________________________________________\n");
        System.out.println("_________VENDA_POR_DATA____________________\n");
        System.out.println("___________________________________________\n");
        
        String dataBusca = Console.scanString("Digite uma data a consultar_ ");
        
        System.out.println(String.format("%-20s", "Nº de Vendas") + "\t"
                + String.format("%-80s", "|Data"));
        
        try {
            Date dataConvertida = DateUtil.stringToDate(dataBusca);
            int cont =0;
            for(Venda venda : listaVendas){
                String dtConvertida = DateUtil.dateToString(venda.getData());
                
                if(dtConvertida.equals(dataBusca)){
                    cont++;
                }
            }
            System.out.println(String.format("%-20s", cont) + "\t"
                + String.format("%-80s", "|" + dataBusca));
        
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Data Inválida\n Favor digitar no Padrão dd/mm/aaaa", "Operação cancelada", ERROR_MESSAGE);
        }
        
    }
    /**
     * exibe o número de vendas por sala
     */
    private void vendaPorSala(){
        List<Sala> listaSalas = salaRN.listar();
        List<Venda> listaVendas = vendaRN.listar();
        System.out.println("___________________________________________\n");
        System.out.println("__________VENDA_POR_SALA___________________\n");
        System.out.println("___________________________________________\n");
        System.out.println(String.format("%-20s", "Nº de Vendas") + "\t"
                + String.format("%-80s", "|Nº da Sala"));
        for(Sala sala : listaSalas){
            int cont =0;
            for(Venda venda : listaVendas){
                if(venda.getFilmeSalaSessao().getSala().getNumeroSala() == sala.getNumeroSala()){
                    cont++;
                }
            }
            System.out.println(String.format("%-20s", cont) + "\t"
                    + String.format("%-80s", "|" + sala.getNumeroSala()));
        }
    }
    /**
     * Exibe o número de vendas por sessão
     */
    private void vendaPorSessao(){
        List<Venda> listaVendas = vendaRN.listar();
        List<Sessao> listaSessoes = sessaoRN.listar();
        System.out.println("___________________________________________\n");
        System.out.println("__________VENDA_POR_SESSÃO_________________\n");
        System.out.println("___________________________________________\n");
        System.out.println(String.format("%-20s", "Nº de Vendas") + "\t"
                + String.format("%-80s", "|Sessão"));
        for(Sessao sessao : listaSessoes){
            int cont =0;
            for(Venda venda : listaVendas){
                if(venda.getFilmeSalaSessao().getCodigoSessao() == sessao.getCodigoSessao()){
                    cont++;
                }
            }
            System.out.println(String.format("%-20s", cont) + "\t"
                    + String.format("%-80s", "|" + sessao.toString()));
        }
    }
    
    /**
     * Exibe o número de vendas por horario
     */
    private void vendaPorHorario(){
        List<Venda> listaVendas = vendaRN.listar();
        List<Sessao> listaSessoes = sessaoRN.listar();
        System.out.println("___________________________________________\n");
        System.out.println("__________VENDA_POR_HORÁRIO_DA_SESSÃO______\n");
        System.out.println("___________________________________________\n");
        System.out.println(String.format("%-20s", "Nº de Vendas") + "\t"
                + String.format("%-80s", "|Horario"));
        for(Sessao sessao : listaSessoes){
            int cont =0;
            for(Venda venda : listaVendas){
                if(venda.getFilmeSalaSessao().getHorario().equals(sessao.getHorario())){
                    cont++;
                }
            }
            System.out.println(String.format("%-20s", cont) + "\t"
                    + String.format("%-80s", "|" + sessao.getHorario()));
        }
    }
    
    /**
     * Exibe os filmes que estiveram em mais sessões
     */
    private void filmePorSessao(){
        List<Filme> listaFilmes = filmeRN.listar();
        List<Sessao> listaSessoes = sessaoRN.listar();
        System.out.println("___________________________________________\n");
        System.out.println("__________FILME_POR_SESSÃO_________________\n");
        System.out.println("___________________________________________\n");
        System.out.println(String.format("%-30s", "N° de Sessões") + "\t"
                + String.format("%-80s", "|Filme"));
        for(Filme filme : listaFilmes){
            int cont =0;
            for(Sessao sessao : listaSessoes){
                if(sessao.getFilme().getCodigoFilme() == filme.getCodigoFilme()){
                    cont++;
                }
            }
            System.out.println(String.format("%-30s", cont) + "\t"
                    + String.format("%-80s", "|" + filme.getNomeFilme()));
        }
    }
    
    /**
     * Exibe as salas que foram mais utilizadas em sessões
     */
    private void salasPorSessao(){
        List<Sala> listaSalas = salaRN.listar();
        List<Sessao> listaSessoes = sessaoRN.listar();
        System.out.println("___________________________________________\n");
        System.out.println("_______SALAS_POR_SESSÃO____________________\n");
        System.out.println("___________________________________________\n");
        System.out.println(String.format("%20s", "Sessões") + "\t"
                + String.format("%-80s", "|N° da Sala"));
        for(Sala sala : listaSalas){
            int cont =0;
            for(Sessao sessao : listaSessoes){
                if(sessao.getSala().getNumeroSala() == sala.getNumeroSala()){
                    cont++;
                }
            }
            System.out.println(String.format("%-20s", cont) + "\t"
                    + String.format("%-80s", "|" + sala.getNumeroSala()));
        }
    }
}
