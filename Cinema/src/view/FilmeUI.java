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
                    case MenuUI.CONSULTAR_GENERO:
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
     * coloca na lista do repositorio
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
     * metodo que exibe todos os filmes cadastrados,  que se encontram no repositorio
     */
    private void mostrarFilmes() {
        List<Filme> listaFilmes = filmeRN.listar();
        this.mostrarFilmes(listaFilmes);
    }
    
    private void mostrarFilmes(List<Filme> listaFilmes) {
        if (listaFilmes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Filmes não encontrados", null, WARNING_MESSAGE);
        } else {
            System.out.println("___________________________________________\n");
            System.out.println(String.format("%-50s", "Nome do Filme") + "\t"
                    + String.format("%-20s", "|Gênero") + "\t"
                    + String.format("%-40s", "|Sinopse"));
            for (Filme filme : listaFilmes) {
                System.out.println(String.format("%-50s", filme.getNomeFilme()) + "\t"
                        + String.format("%-20s", "|" + filme.getGenero()) + "\t"
                        + String.format("%-40s", "|" + filme.getSinopse()));
            }
        }
    }
    
    private void mostrarFilme(Filme f) {
        System.out.println("-----------------------------");
        System.out.println("Dados atuais");
        System.out.println("Nome do filme: "+f.getNomeFilme());
        System.out.println("Gênero: "+f.getGenero());
        System.out.println("Sinopse: "+f.getSinopse());
        System.out.println("-----------------------------");
    }
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
        String filmeAlterar = Console.scanString("Nome do filme á alterar: ");
        try {
            Filme filme = filmeRN.procuraPorNome(filmeAlterar);
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
        }  
    }
    /**
     * Metodo que deleta um filme 
     */    
    private void deletarFilme(){
        String filmeDeletar = Console.scanString("Nome do filme a excluir: ");
        try {
            Filme filme = filmeRN.procuraPorNome(filmeDeletar);
            this.mostrarFilme(filme);
            char resposta = Console.scanChar("Realmente deseja remover esse Filme?(s/n)");
            if (resposta == 's') {
                filmeRN.deletar(filme);
                JOptionPane.showMessageDialog(null, "Filme deletado com sucesso");
            } else {
                JOptionPane.showMessageDialog(null, "Operação cancelada", "Aviso", WARNING_MESSAGE);
            }
        } catch (RNException ex) {
            System.err.println(ex.getMessage());
        }
    }

    
    
    
    
    
}
