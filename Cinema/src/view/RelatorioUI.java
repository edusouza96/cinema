package view;

import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import model.Filme;
import model.Sala;
import model.Sessao;
import model.Venda;
import repositorio.RepositorioAssentos;
import repositorio.RepositorioFilmes;
import repositorio.RepositorioSalas;
import repositorio.RepositorioSessao;
import repositorio.RepositorioVendas;
import util.Console;
import util.DateUtil;

/**
 *Classe respponsavel por exibir os relatórios
 */
public class RelatorioUI {
    private RepositorioVendas listaVendas;
    private RepositorioAssentos listaAssentos;
    private RepositorioSessao listaSessao;
    private RepositorioSalas listaSalas;
    private RepositorioFilmes listaFilmes;
    
    /**
     * Construtor que inicializa as listas
     * @param listaFilmes
     * @param listaSalas
     * @param listaSessao
     * @param listaAssentos
     * @param listaVendas 
     */
    public RelatorioUI(RepositorioFilmes listaFilmes, RepositorioSalas listaSalas, RepositorioSessao listaSessao, RepositorioAssentos listaAssentos, RepositorioVendas listaVendas) {
        this.listaVendas = listaVendas;
        this.listaAssentos = listaAssentos;
        this.listaFilmes = listaFilmes;
        this.listaSalas = listaSalas;
        this.listaSessao = listaSessao;
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
        System.out.println("___________________________________________\n");
        System.out.println("_________VENDA_POR FILME___________________\n");
        System.out.println("___________________________________________\n");
        System.out.println(String.format("%-20s", "Nº de Vendas") + "\t"
                + String.format("%-80s", "|Nome do Filme"));
        for(Filme filme : listaFilmes.getListaFilmes()){
            int cont =0;
            for(Venda venda : listaVendas.getListaVendas()){
                if(venda.getFilmeSalaSessao().getFilme().equals(filme)){
                    cont++;
                }
            }
            System.out.println(String.format("%-20s", cont) + "\t"
                    + String.format("%-80s", "|" + filme.getNomeFilme()));
        }
    }
    private void vendaPorData(){
        System.out.println("___________________________________________\n");
        System.out.println("_________VENDA_POR_DATA____________________\n");
        System.out.println("___________________________________________\n");
        System.out.println(String.format("%-20s", "Nº de Vendas") + "\t"
                + String.format("%-80s", "|Data"));
        String dataBusca = Console.scanString("Digite uma data a consultar");
        try {
            Date dataConvertida = DateUtil.stringToDate(dataBusca);
            int cont =0;
            for(Venda venda : listaVendas.getListaVendas()){
                if(venda.getData() == dataConvertida){
                    cont++;
                }
            }
            System.out.println(String.format("%-20s", cont) + "\t"
                + String.format("%-80s", "|" + dataBusca));
        
        } catch (ParseException ex) {
            Logger.getLogger(RelatorioUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    /**
     * exibe o número de vendas por sala
     */
    private void vendaPorSala(){
        System.out.println("___________________________________________\n");
        System.out.println("__________VENDA_POR_SALA___________________\n");
        System.out.println("___________________________________________\n");
        System.out.println(String.format("%-20s", "Nº de Vendas") + "\t"
                + String.format("%-80s", "|Nº da Sala"));
        for(Sala sala : listaSalas.getListaSalas()){
            int cont =0;
            for(Venda venda : listaVendas.getListaVendas()){
                if(venda.getFilmeSalaSessao().getSala().equals(sala)){
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
        System.out.println("___________________________________________\n");
        System.out.println("__________VENDA_POR_SESSÃO_________________\n");
        System.out.println("___________________________________________\n");
        System.out.println(String.format("%-20s", "Nº de Vendas") + "\t"
                + String.format("%-80s", "|Sessão"));
        for(Sessao sessao : listaSessao.getListaSessao()){
            int cont =0;
            for(Venda venda : listaVendas.getListaVendas()){
                if(venda.getFilmeSalaSessao().equals(sessao)){
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
        System.out.println("___________________________________________\n");
        System.out.println("__________VENDA_POR_HORÁRIO_DA_SESSÃO______\n");
        System.out.println("___________________________________________\n");
        System.out.println(String.format("%-20s", "Nº de Vendas") + "\t"
                + String.format("%-80s", "|Horario"));
        for(Sessao sessao : listaSessao.getListaSessao()){
            int cont =0;
            for(Venda venda : listaVendas.getListaVendas()){
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
        System.out.println("___________________________________________\n");
        System.out.println("__________FILME_POR_SESSÃO_________________\n");
        System.out.println("___________________________________________\n");
        System.out.println(String.format("%-30s", "N° de Sessões") + "\t"
                + String.format("%-80s", "|Filme"));
        for(Filme filme : listaFilmes.getListaFilmes()){
            int cont =0;
            for(Sessao sessao : listaSessao.getListaSessao()){
                if(sessao.getFilme().equals(filme)){
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
        System.out.println("___________________________________________\n");
        System.out.println("_______SALAS_POR_SESSÃO____________________\n");
        System.out.println("___________________________________________\n");
        System.out.println(String.format("%20s", "Sessões") + "\t"
                + String.format("%-80s", "|N° da Sala"));
        for(Sala sala : listaSalas.getListaSalas()){
            int cont =0;
            for(Sessao sessao : listaSessao.getListaSessao()){
                if(sessao.getFilme().equals(sala)){
                    cont++;
                }
            }
            System.out.println(String.format("%-20s", cont) + "\t"
                    + String.format("%-80s", "|" + sala.getNumeroSala()));
        }
    }
}
