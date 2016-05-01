package view;

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

/**
 *
 */
public class RelatorioUI {
    private RepositorioVendas listaVendas;
    private RepositorioAssentos listaAssentos;
    private RepositorioSessao listaSessao;
    private RepositorioSalas listaSalas;
    private RepositorioFilmes listaFilmes;
    
    public RelatorioUI(RepositorioFilmes listaFilmes, RepositorioSalas listaSalas, RepositorioSessao listaSessao, RepositorioAssentos listaAssentos, RepositorioVendas listaVendas) {
        this.listaVendas = listaVendas;
        this.listaAssentos = listaAssentos;
        this.listaFilmes = listaFilmes;
        this.listaSalas = listaSalas;
        this.listaSessao = listaSessao;
    }
    
    public void executar() {
        int opcao;
        do{
            System.out.println(MenuUI.menuRelatorio());
            opcao = Console.scanInt("Digite sua opção desejada:");
            switch (opcao) {
                case MenuUI.CADASTRAR:
                    vendaPorFilme();
                    break;
                case MenuUI.LISTAR:
                    vendaPorSala();
                    break;
                case MenuUI.SAIR:
                    JOptionPane.showMessageDialog(null, "Retornando ao Menu Principal!");
                    break;
                
                default:
                    JOptionPane.showMessageDialog(null, "Opção Invalida!", null, ERROR_MESSAGE);
            }
        }while(opcao != 0);
    }
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
}
