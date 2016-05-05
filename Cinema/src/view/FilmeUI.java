package view;

import exceptions.ObjectNullException;
import java.util.InputMismatchException;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.WARNING_MESSAGE;
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
        lista.adicionar(new Filme("Vingadores: Era de Ultron","ficção científica/Ação","Sequência do sucesso 'Os Vingadores', que reúne mais uma vez a equipe de super-heróis"));
        lista.adicionar(new Filme("Pixels","Fantasi/ficção científica"," seres intergalácticos interpretam um vídeo com imagens de jogos clássicos como uma declaração de guerra"));
        lista.adicionar(new Filme("Meu Passado Me Condena 2","Comédia","O filme mostra o que aconteceu com Fábio (Fábio Porchat) e Miá (Miá Mello) três anos após o casamento "));
        
        
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
                    {
                        try {
                            deletarFilme();
                        } catch (ObjectNullException ex) {
                            JOptionPane.showMessageDialog(null, "Filme Não Localizado", null, ERROR_MESSAGE);
                        }
                    }
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
        String nomeFilme = "";
        do{
            nomeFilme = Console.scanString("Nome do Filme: ");
            if(nomeFilme.equals(""))
                JOptionPane.showMessageDialog(null, "Campo Nome do Filme\nDeve ser Preenchido", "Campo Obrigatório", WARNING_MESSAGE );
        }while(nomeFilme.equals(""));
        
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
        String novoNomeFilme;
        String novoGenero;
        String novaSinopse;
        System.out.println("Dados atuais");
        System.out.println("Nome do filme: "+filme.getNomeFilme());
        System.out.println("Gênero: "+filme.getGenero());
        System.out.println("Sinopse: "+filme.getSinopse());
       
        String resposta = Console.scanString("Mudar o nome do filme? sim ou não ~> ");
        if (resposta.equalsIgnoreCase("sim")){
            do{
                novoNomeFilme = Console.scanString("Novo nome do Filme: ");
                if(novoNomeFilme.equals(""))
                    JOptionPane.showMessageDialog(null, "Campo Nome do Filme\nDeve ser Preenchido", "Campo Obrigatório", WARNING_MESSAGE );
            }while(novoNomeFilme.equals(""));
            filme.setNomeFilme(novoNomeFilme);
        }
        
        resposta = Console.scanString("Mudar o genero do filme? sim ou não ~> ");
        if (resposta.equalsIgnoreCase("sim")){
            do{
                novoGenero = Console.scanString("Novo Genero do Filme: ");
                if(novoGenero.equals(""))
                    JOptionPane.showMessageDialog(null, "Campo Genero do Filme\nDeve ser Preenchido", "Campo Obrigatório", WARNING_MESSAGE );
            }while(novoGenero.equals(""));
            filme.setGenero(novoGenero);
        }
            
        
        resposta = Console.scanString("Mudar a sinopse do filme? sim ou não ~> ");
        if (resposta.equalsIgnoreCase("sim")){
            do{
                novaSinopse = Console.scanString("Nova Sinopse do Filme: ");
                if(novaSinopse.equals(""))
                    JOptionPane.showMessageDialog(null, "Campo Sinopse do Filme\nDeve ser Preenchido", "Campo Obrigatório", WARNING_MESSAGE );
                    
            }while(novaSinopse.equals(""));
            filme.setSinopse(novaSinopse);
        }
            
        JOptionPane.showMessageDialog(null, "Mudanças Concluida"); 
    }
    /**
     * Metodo que deleta um filme da lista
     */    
    private void deletarFilme() throws ObjectNullException {
        String filmeDeletar = Console.scanString("Nome do filme a excluir: ");
        Filme filme = lista.consultarPorNome(filmeDeletar);
        if(filme == null)
            throw new ObjectNullException();
        lista.remover(filme);
        JOptionPane.showMessageDialog(null, "Exclusão Concluida");
        
    }

    /**
     * Getter do RepositorioFilmes
     * @return Retorna a lista de Filmes
     */
    public RepositorioFilmes getLista() {
        return lista;
    }
    
    
    
    
}
