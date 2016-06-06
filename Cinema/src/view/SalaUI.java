package view;

import exceptions.RNException;
import java.util.InputMismatchException;
import java.util.List;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.WARNING_MESSAGE;
import model.Sala;
import rn.SalaRN;
import util.Console;

/**
 *Interface da Sala para o Usuario
 * contem cadastrar, listar, alterar, deletar sala
 */
public class SalaUI {
    private SalaRN salaRN;

    /***
     * Construtor 
     */
    public SalaUI() {
        salaRN = new SalaRN();
    }
    /**
     * metodo que contem um switch para escolher os procedimentos a ser realizado
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
                    case MenuUI.BUSCA_ESPECIFICA:
                        consultarSalaPorNumero();
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
        }while(opcao !=0);
    }
    
    /**
     * Metodo que coleta as informações da sala e depois 
     * envia para RN tratar da requisição
     */
    private void cadastrarSala() {
        try{
            int numeroSala  = Console.scanInt("Número da Sala: ");
            int quantidadeSala = Console.scanInt("Capacidade Maxima da sala: ");
        
            salaRN.adicionar(new Sala(numeroSala,quantidadeSala));
            JOptionPane.showMessageDialog(null, "Sala Cadastrada com Sucesso");
        }catch(InputMismatchException ex){
            JOptionPane.showMessageDialog(null, "Somente valor numérico", "Erro", ERROR_MESSAGE);
        }catch(RNException ex){
            System.err.println(ex.getMessage());
        }
        
    }
    
    /**
     * metodo que faz uma requisição para RN e depois exibe todas as salas cadastradas no banco
     */
    private void mostrarSalas(){
        List<Sala> listaSalas = salaRN.listar();
        this.mostrarSalas(listaSalas);
    }
    
    /**
     * Método que verifica se a lista está vazia e se não estiver trata visualmente para ser exibido na tela
     * @param listaSalas recebe uma lista de salas para fazer as verificações e tratar a saida
     */
    private void mostrarSalas(List<Sala> listaSalas) {
        if(listaSalas.isEmpty()){
            JOptionPane.showMessageDialog(null, "Salas não encontradas", "Aviso", WARNING_MESSAGE);
            
        }else{
            System.out.println("___________________________________________\n");
            System.out.println(String.format("%-30s", "Numero da Sala")+ "\t"
                    + String.format("%-30s", "|Capacidade da Sala"));
            for(Sala sala : listaSalas){
                System.out.println(String.format("%-30s", sala.getNumeroSala())+"\t"
                        + String.format("%-30s", "|" + sala.getQuantidadeSala()));
            }
        }
       
    }
    
    /**
     * Recebe um objeto sala e trata visualmente os dados para ser exibido na tela
     * @param s : Objero sala recebido por parametro
     */
    private void mostrarSala(Sala s){
        System.out.println("-----------------------------");
        System.out.println("Dados Atuais");
        System.out.println("Número da sala: "+s.getNumeroSala());
        System.out.println("Capacidade da Sala: "+s.getQuantidadeSala());
        System.out.println("-----------------------------");
    }
    /**
     * metodo que faz alteração na capacidade da sala
     */
    private void alterarFilme(){
        
        try{
            int salaAlterar = Console.scanInt("Número da sala á alterar: ");
            Sala sala = salaRN.procurarPorNumero(salaAlterar);
            this.mostrarSala(sala);
            
            int capacidadeSala = Console.scanInt("Nova Capacidade da sala [Digite '0' caso não queira alterar]: ");
            if(capacidadeSala != 0){
                sala.setQuantidadeSala(capacidadeSala);
            }
            salaRN.atualizar(sala);
            JOptionPane.showMessageDialog(null, "Mudanças Concluida");
        } catch (RNException ex) {
            System.err.println(ex.getMessage());
        }catch(InputMismatchException ex){
            JOptionPane.showMessageDialog(null, "Somente valor numérico", "Erro", ERROR_MESSAGE);
        }
    }
    /**
     * metodo que exclui uma sala
     */
    private void deletarSala(){
        
        try{
            int filmeDeletar = Console.scanInt("Numero da sala a excluir: ");
            Sala sala = salaRN.procurarPorNumero(filmeDeletar);
            this.mostrarSala(sala);
            char resposta = Console.scanChar("Realmente deseja remover essa Sala?(s/n)");
            if(resposta == 'S' || resposta == 's' ){
                salaRN.deletar(sala);
                JOptionPane.showMessageDialog(null, "Exclusão Concluida");
            }else{
                JOptionPane.showMessageDialog(null, "Operação cancelada", "Aviso", WARNING_MESSAGE);
            }
        } catch (RNException ex) {
            System.err.println(ex.getMessage());
        }catch(InputMismatchException ex){
            JOptionPane.showMessageDialog(null, "Somente valor numérico", "Erro", ERROR_MESSAGE);
        }        
    }

    /***
     * consulta dados de uma sala a partir de um numero da sala digitado
     */
    private void consultarSalaPorNumero() {
        
        try{
            int numeroSala = Console.scanInt("Numero da Sala: ");
            Sala sala = salaRN.procurarPorNumero(numeroSala);
            this.mostrarSala(sala);
        }catch (RNException ex) {
            System.err.println(ex.getMessage());
        }
    }
            
    
}
