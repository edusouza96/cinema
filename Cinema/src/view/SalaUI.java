package view;

import exceptions.ObjectNullException;
import java.util.InputMismatchException;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import model.Sala;
import repositorio.RepositorioSalas;
import util.Console;

/**
 *Interface da Sala para o Usuario
 * contem cadastrar, listar, alterar, deletar sala
 */
public class SalaUI {
    private RepositorioSalas lista;

    /***
     * Construtor que recebe o objeto repositorio 
     * @param lista Parametro do tipo RepositorioSalas
     */
    public SalaUI(RepositorioSalas lista) {
        this.lista = lista;
        lista.adicionar(new Sala(100, 200));
        lista.adicionar(new Sala(200, 400));
        lista.adicionar(new Sala(300, 600));
    }
    /**
     * metodo que contem um switch para escolher os procedimentos a ser realix=zado
     */
    public void executar(){
        int opcao=0;
        do{
            System.out.println(MenuUI.menuSala());
            try{
                opcao = Console.scanInt("Digite a opção desejada:");
                switch(opcao){
                    case MenuUI.CADASTRAR:
                        cadastrarSala();
                        break;
                    case MenuUI.LISTAR:
                        mostrarSalas();
                        break;
                    case MenuUI.EDITAR:
                        alterarFilme();
                        break;
                    case MenuUI.DELETAR:
                        deletarSala();
                        break;
                    case MenuUI.SAIR:
                        JOptionPane.showMessageDialog(null, "Retornando ao Menu Principal!");
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opção Invalida!", null, ERROR_MESSAGE);

                }
            }catch(InputMismatchException ex){
                JOptionPane.showMessageDialog(null, "Somente valor numérico", "Erro", ERROR_MESSAGE);
            } catch (ObjectNullException ex) {
                 JOptionPane.showMessageDialog(null, "Sala Não Localizada", null, ERROR_MESSAGE);
            }
            
            
        }while(opcao !=0);
    }
    /**
     * Metodo que coleta as informações da sala e depois inseri na lista do repositorio
     */
    private void cadastrarSala() {
        try{
            int numeroSala  = Console.scanInt("Número da Sala: ");
            int quantidadeSala = Console.scanInt("Capacidade Maxima da sala: ");
            lista.adicionar(new Sala(numeroSala,quantidadeSala));
            JOptionPane.showMessageDialog(null, "Sala Cadastrada com Sucesso");
        }catch(InputMismatchException ex){
            JOptionPane.showMessageDialog(null, "Somente valor numérico", "Erro", ERROR_MESSAGE);
        }
        
    }
    /**
     * metodo que mostra todos os dados referente da sala que foram inseridos na lista
     */
    private void mostrarSalas() {
        System.out.println("___________________________________________\n");
        System.out.println(String.format("%-30s", "Numero da Sala")+ "\t"
                + String.format("%-30s", "|Capacidade da Sala"));
        for(Sala sala : lista.getListaSalas()){
            System.out.println(String.format("%-30s", sala.getNumeroSala())+"\t"
                    + String.format("%-30s", "|" + sala.getQuantidadeSala()));
        }
    }
    /**
     * metodo que faz alteração na capacidade da sala
     */
    private void alterarFilme() throws ObjectNullException {
        int salaAlterar = Console.scanInt("Número da sala á alterar: ");
        Sala sala = lista.consultarPorSala(salaAlterar);
        if(sala==null)
            throw new ObjectNullException();
        System.out.println("Dados Atuais");
        System.out.println("Número da sala: "+sala.getNumeroSala());
        System.out.println("Capacidade da Sala: "+sala.getQuantidadeSala());
        String resposta = Console.scanString("Mudar a capacidade da Sala: sim ou não ~> ");
        if(resposta.equalsIgnoreCase("sim")){
            try{
                sala.setQuantidadeSala(Console.scanInt("Nova Capacidade da sala: "));
                JOptionPane.showMessageDialog(null, "Mudanças Concluida");
            }catch(InputMismatchException ex){
                JOptionPane.showMessageDialog(null, "Somente valor numérico", "Erro", ERROR_MESSAGE);
            }
        }
            
        
        
        
    }
    /**
     * metodo que exclui uma sala da lista
     */
    private void deletarSala() throws ObjectNullException {
        int filmeDeletar = Console.scanInt("Numero da sala a excluir: ");
        Sala sala = lista.consultarPorSala(filmeDeletar);
        if(sala==null)
            throw new ObjectNullException();
        lista.remover(sala);
        JOptionPane.showMessageDialog(null, "Exclusão Concluida");
    }

    /**
     * Getter do RepositorioSalas
     * @return Retorna a lista de Salas
     */
    public RepositorioSalas getLista() {
        return lista;
    }
            
    
}
