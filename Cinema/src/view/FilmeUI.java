package view;

import exceptions.RNException;
import java.util.InputMismatchException;
import java.util.List;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.WARNING_MESSAGE;
import model.Filme;
import rn.FilmeRN;
import util.Console;

/**
 *Interface do Filme para o usuario
 * contem cadastrar,listar,alterar, deletar filme
 */
public class FilmeUI {
    private FilmeRN filmeRN;

    /**
     * Construtor 
     */
    public FilmeUI() {
        filmeRN = new FilmeRN();
    }
    /**
     * metodo que contem as opções para execução dos procedimentos
     */
    public void executar(){
        int opcao = 0;
        do {
            System.out.println(MenuUI.menuFilme());
            try{
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
                    case MenuUI.BUSCA_ESPECIFICA:
                        consultarFilmePorGenero();
                        break;
                    case MenuUI.SAIR:
                        JOptionPane.showMessageDialog(null, "Retornando ao Menu Principal!");
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opção Invalida!", null, ERROR_MESSAGE);

                }
            }catch(InputMismatchException ex){
                JOptionPane.showMessageDialog(null, "Somente valor numérico", "Erro", ERROR_MESSAGE);
            }
        } while (opcao != MenuUI.SAIR);
    }
    
    /**
     * metodo que coleta as informações do filme e depois 
     * envia para RN tratar da requisição
     */
    private void cadastrarFilme() {
        String nomeFilme = Console.scanString("Nome do Filme: ");
        String genero = Console.scanString("Gênero: ");
        String sinopse = Console.scanString("Sinopse: ");
        try {
            filmeRN.adicionar(new Filme(nomeFilme, genero, sinopse));
            JOptionPane.showMessageDialog(null, "Filme cadastrado com sucesso");
        } catch (RNException ex) {
            System.err.println(ex.getMessage());
        } 
        
    }
    
    /**
     * metodo que faz uma requisição para RN e depois exibe todos os filmes cadastrados no banco
     */
    private void mostrarFilmes() {
        List<Filme> listaFilmes = filmeRN.listar();
        this.mostrarFilmes(listaFilmes);
    }
    
    /**
     * Método que verifica se a lista está vazia e se não estiver trata visualmente para ser exibido na tela
     * @param listaFilmes recebe uma lista de filmes para fazer as verificações e tratas a saida
     */
    private void mostrarFilmes(List<Filme> listaFilmes) {
        if (listaFilmes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Filmes não encontrados", null, WARNING_MESSAGE);
        } else {
            System.out.println("___________________________________________\n");
            System.out.println(String.format("%-10s", "Código") + "\t"
                    + String.format("%-50s", "|Nome do Filme") + "\t"
                    + String.format("%-20s", "|Gênero") + "\t"
                    + String.format("%-40s", "|Sinopse"));
            for (Filme filme : listaFilmes) {
                System.out.println(String.format("%-10s", filme.getCodigoFilme()) + "\t"
                        + String.format("%-50s", "|" + filme.getNomeFilme()) + "\t"
                        + String.format("%-20s", "|" + filme.getGenero()) + "\t"
                        + String.format("%-40s", "|" + filme.getSinopse()));
            }
        }
    }
    /**
     * Recebe um objeto filme e trata visualmente os dados deles para ser exibido na tela
     * @param f : Objeto Filme recebido por parametro
     */
    private void mostrarFilme(Filme f) {
        System.out.println("-----------------------------");
        System.out.println("Dados atuais");
        System.out.println("Nome do filme: "+f.getNomeFilme());
        System.out.println("Gênero: "+f.getGenero());
        System.out.println("Sinopse: "+f.getSinopse());
        System.out.println("-----------------------------");
    }
    
    /**
     * Através de um genero inserido ele retorna uma lista dos filmes de tal genero 
     */
    private void consultarFilmePorGenero() {
        String genero = Console.scanString("Genero: ");
        try {
            List<Filme> listaFilme = filmeRN.procuraPorGenero(genero);
            this.mostrarFilmes(listaFilme);
        } catch (RNException ex) {
            System.err.println(ex.getMessage());
        }

    }
    /**
     * metodo responsavel para alterar algum dado do filme
     */
    private void alterarFilme() {
        try {
            int filmeAlterar = Console.scanInt("Código do filme á alterar: ");
            Filme filme = filmeRN.procurarPorId(filmeAlterar);
            this.mostrarFilme(filme);

            System.out.println("Digite os dados do filme que quer alterar [Vazio caso nao queira]");
            String novoNomeFilme = Console.scanString("Novo nome do Filme: ");
            String novoGenero = Console.scanString("Novo Genero do Filme: ");
            String novaSinopse = Console.scanString("Nova Sinopse do Filme: ");
            if (!novoNomeFilme.isEmpty()) {
                filme.setNomeFilme(novoNomeFilme);
            }
            if (!novoGenero.isEmpty()) {
                filme.setGenero(novoGenero);
            }
            if (!novaSinopse.isEmpty()) {
                filme.setSinopse(novaSinopse);
            }

            filmeRN.atualizar(filme);
            JOptionPane.showMessageDialog(null, "Filme atualizado com sucesso");
        } catch (RNException ex) {
            System.err.println(ex.getMessage());
        }  catch(InputMismatchException ex){
            JOptionPane.showMessageDialog(null, "Somente valor numérico", "Erro", ERROR_MESSAGE);
        }
    }
    /**
     * Metodo que deleta um filme 
     */    
    private void deletarFilme(){
        
        try {
            int filmeDeletar = Console.scanInt("Código do filme a excluir: ");
            Filme filme = filmeRN.procurarPorId(filmeDeletar);
            this.mostrarFilme(filme);
            char resposta = Console.scanChar("Realmente deseja remover esse Filme?(s/n)");
            if (resposta == 's' || resposta == 'S' ) {
                filmeRN.deletar(filme);
                JOptionPane.showMessageDialog(null, "Filme deletado com sucesso");
            } else {
                JOptionPane.showMessageDialog(null, "Operação cancelada", "Aviso", WARNING_MESSAGE);
            }
        } catch (RNException ex) {
            System.err.println(ex.getMessage());
        }catch(InputMismatchException ex){
            JOptionPane.showMessageDialog(null, "Somente valor numérico", "Erro", ERROR_MESSAGE);
        }
    }    
}
