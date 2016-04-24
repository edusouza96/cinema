package view;

import java.text.ParseException;
import java.util.Date;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import model.Filme;
import model.Sala;
import model.Sessao;
import repositorio.RepositorioFilmes;
import repositorio.RepositorioSalas;
import repositorio.RepositorioSessao;
import util.Console;
import util.DateUtil;

/**
 *Interface da sessão para o usuario
 * contem cadastrar,listar,alterar, deletar filme
 */
public class SessaoUI {
    private RepositorioSessao lista;
    private RepositorioFilmes listaFilmes;
    private RepositorioSalas listaSalas;

    /**
     * Construtor que recebe o objeto repositorio
     * @param lista Parametro do tipo RepositorioSessão
     * @param listaFilme Parametro do tipo RepositorioFilme
     * @param listaSala Parametro do tipo RepositorioSala
     */
    public SessaoUI(RepositorioSessao lista,RepositorioFilmes listaFilme,RepositorioSalas listaSala) {
        this.lista = lista;
        this.listaFilmes = listaFilme;
        this.listaSalas = listaSala;
    }
    
    /**
     * metodo que contem as opções para execução dos procedimentos
     * @throws java.text.ParseException
     */
    public void executar() throws ParseException{
        int opcao;
        do{
            System.out.println(MenuUI.menuSessao());
            opcao = Console.scanInt("Digite a opção  desejada: ");
            switch(opcao){
                case MenuUI.CADASTRAR:
                    cadastrarSessao();
                    break;
                case MenuUI.LISTAR:
                    mostrarSessoes();
                    break;
                case MenuUI.EDITAR:
                    alterarSessao();
                    break;
                case MenuUI.DELETAR:
                   deletarSessoes();
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
     * metodo que coleta as informações da sessão e depois
     * coloca na lista do repositorio
     */
    private void cadastrarSessao() throws ParseException {
        exibirFilmes();
        System.out.println("---------------------------------\n");
        String nomeFilme = Console.scanString("Nome do Filme: ");
        Filme filme = listaFilmes.consultarPorNome(nomeFilme);
        exibirSalas();
        System.out.println("---------------------------------\n");
        int numeroSala = Console.scanInt("Numero da Sala: ");
        Sala sala = listaSalas.consultarPorSala(numeroSala);
        String horario = Console.scanString("Horario da Sessão: ");
        Date hora = DateUtil.stringToHour(horario);
        lista.adicionar(new Sessao(filme,sala,hora));
        JOptionPane.showMessageDialog(null, "Sessão cadastrada com suceso");     
    }
    /**
     * metodo que lista os nomes dos filmes
     */
    private void exibirFilmes(){
        System.out.println("---------Nome do Filme--------");
        for(Filme filme: listaFilmes.getListaFilmes())
            System.out.println("--"+filme.getNomeFilme());
    }
    /**
     * metodoque lista o numero das salas
     */
    private void exibirSalas(){
        System.out.println("-----------Numero da Sala--------");
        for(Sala sala : listaSalas.getListaSalas())
            System.out.println("--"+sala.getNumeroSala());
    }
    
    /**
     * metodo que exibe todas as sessões cadastradas na lista
     */
    private void mostrarSessoes() {
        System.out.println(String.format("%-20s", "Código Sessão")+"\t"
                +String.format("%-50s", "|Nome do Filme")+"\t"
                +String.format("%-30s", "|Numero da Sala")+"\t"
                + String.format("%-30s", "|Horario da sessão"));
        for(Sessao sessao : lista.getListaSessao()){
            String hora = DateUtil.hourToString(sessao.getHorario());
            System.out.println(String.format("%-20s", sessao.getCodigoSessao())+"\t"
                    +String.format("%-50s", "|"+sessao.getFilme().getNomeFilme())+"\t"
                    +String.format("%-30s", "|"+sessao.getSala().getNumeroSala())+"\t"
                    +String.format("%-30s", "|"+ hora));
        
        }
    }

    /**
     * metodo responsavel para alterar dados da sessão
     */
    private void alterarSessao() throws ParseException {
        int sessaoAlterar = Console.scanInt("Código da sessão a alterar: ");
        Sessao sessao = lista.consultarPorCodigo(sessaoAlterar);
        System.out.println("Dados Atuais");
        System.out.println(sessao);
        String resposta = Console.scanString("Mudar o Filme: sim ou não ~> ");
        if(resposta.equalsIgnoreCase("sim")){
            exibirFilmes();
            System.out.println("---------------------------------\n");
            String nomeFilme = Console.scanString("Novo Filme para esta sessao: ");
            sessao.setFilme(listaFilmes.consultarPorNome(nomeFilme));
        }
        resposta = Console.scanString("Mudar a Sala: sim ou não ~> ");
        if(resposta.equalsIgnoreCase("sim")){
            exibirSalas();
            System.out.println("---------------------------------\n");
            int numeroSala = Console.scanInt("Nova sala para esta sessao: ");
            sessao.setSala(listaSalas.consultarPorSala(numeroSala));
        }
        resposta = Console.scanString("Mudar o Horario: sim ou não ~> ");
        if(resposta.equalsIgnoreCase("sim")){
            String hora = Console.scanString("Novo Horario para esta sessao: ");
            sessao.setHorario(DateUtil.stringToHour(hora));
        }
           
        JOptionPane.showMessageDialog(null, "Mudanças Concluida");        
    }

    /**
     * metodo que deleta uma sessão da lista
     */
    private void deletarSessoes() {
        int sessaoDeletar = Console.scanInt("Codigo da sessão a excluir: ");
        Sessao sessao = lista.consultarPorCodigo(sessaoDeletar);
        lista.remover(sessao);
        JOptionPane.showMessageDialog(null, "Exclusão Concluida");
                
    }
    
    
}
