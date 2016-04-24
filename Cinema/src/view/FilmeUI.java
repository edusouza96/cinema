package view;

import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import model.Filme;
import repositorio.RepositorioFilmes;
import util.Console;

/**
 *Interface do Filme para o usuario
 * contem cadastrar,listar,alterar, deletar filme
 */
public class FilmeUI {
    private RepositorioFilmes lista;

    /**
     * Construtor que recebe o objeto repositorio
     * @param lista Parametro do tipo RepositorioFilmes
     */
    public FilmeUI(RepositorioFilmes lista) {
        this.lista = lista;
    }
    /**
     * metodo que contem as opções para execução dos procedimentos
     */
    public void executar() {
        int opcao;
        do {
            System.out.println(MenuUI.menuFilme());
            opcao = Console.scanInt("Digite sua opção desejada:");
            switch (opcao) {
                case MenuUI.CADASTRAR:
                    cadastrarFilme();
                    break;
                case MenuUI.LISTAR:
                    mostrarFilmes();
                    break;
                case MenuUI.EDITAR:
                    alterarFilme();
                    break;
                case MenuUI.DELETAR:
                    deletarFilme();
                    break;
                case MenuUI.SAIR:
                    JOptionPane.showMessageDialog(null, "Retornando ao Menu Principal!");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção Invalida!", null, ERROR_MESSAGE);

            }
        } while (opcao != MenuUI.SAIR);
    }
    
    /**
     * metodo que coleta as informações do filme e depois 
     * coloca na lista do repositorio
     */
    private void cadastrarFilme() {
        String nomeFilme = Console.scanString("Nome do Filme: ");
        String genero = Console.scanString("Gênero: ");
        String sinopse = Console.scanString("Sinopse: ");
        lista.adicionar(new Filme(nomeFilme,genero,sinopse));
        JOptionPane.showMessageDialog(null, "Filme cadastrado com suceso");     
    }
    /**
     * metodo que exibe todos os filmes cadastrados,  que se encontram no repositorio
     */
    private void mostrarFilmes() {
        System.out.println("___________________________________________\n");
        System.out.println(String.format("%-50s", "Nome do Filme") + "\t"
                + String.format("%-20s", "|Gênero") + "\t"
                + String.format("%-40s", "|Sinopse"));
        for (Filme filme : lista.getListaFilmes()) {
            System.out.println(String.format("%-50s", filme.getNomeFilme()) + "\t"
                    + String.format("%-20s", "|" + filme.getGenero()) + "\t"
                    + String.format("%-40s", "|" + filme.getSinopse()));
        }
    }
    /**
     * metodo responsavel para alterar algum dado do filme
     */
    private void alterarFilme() {
        String filmeAlterar = Console.scanString("Nome do filme á alterar: ");
        Filme filme = lista.consultarPorNome(filmeAlterar);
        System.out.println("Dados atuais");
        System.out.println("Nome do filme: "+filme.getNomeFilme());
        System.out.println("Gênero: "+filme.getGenero());
        System.out.println("Sinopse: "+filme.getSinopse());
        String resposta = Console.scanString("Mudar o nome do filme? sim ou não ~> ");
        if (resposta.equalsIgnoreCase("sim"))
            filme.setNomeFilme(Console.scanString("Novo nome do Filme: "));
        resposta = Console.scanString("Mudar o genero do filme? sim ou não ~> ");
        if (resposta.equalsIgnoreCase("sim"))
            filme.setGenero(Console.scanString("Novo Genero do Filme: "));
        resposta = Console.scanString("Mudar a sinopse do filme? sim ou não ~> ");
        if (resposta.equalsIgnoreCase("sim"))
            filme.setSinopse(Console.scanString("Nova Sinopse do Filme: "));
        JOptionPane.showMessageDialog(null, "Mudanças Concluida"); 
    }
    /**
     * Metodo que deleta um filme da lista
     */    
    private void deletarFilme() {
        String filmeDeletar = Console.scanString("Nome do filme a excluir: ");
        Filme filme = lista.consultarPorNome(filmeDeletar);
        lista.remover(filme);
        JOptionPane.showMessageDialog(null, "Exclusão Concluida");
    }
    
    
    
}
