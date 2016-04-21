package view;

import javax.swing.JOptionPane;
import model.Sala;
import repositorio.RepositorioSalas;
import util.Console;

/**
 *Interface da Sala para o Usuario
 * contem cadastrar, listar, alterar, deletar sala
 */
public class SalaUI {
    public RepositorioSalas lista;

    /***
     * Construtor que recebe o objeto repositorio 
     * @param lista Parametro do tipo RepositorioSalas
     */
    public SalaUI(RepositorioSalas lista) {
        this.lista = lista;
    }
    /**
     * metodo que contem um switch para escolher os procedimentos a ser realix=zado
     */
    public void executar(){
        int opcao = 0;
        do{
            opcao = Console.scanInt("Digite a opção desejada:");
            switch(opcao){
                case 1:
                    cadastrarSala();
                    break;
                case 2:
                    mostrarSalas();
                    break;
                case 3:
                    alterarFilme();
                    break;
                case 4:
                    deletarSala();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.err.println("Opção Inválida...");
                        
            }
            
        }while(opcao !=0);
    }
    /**
     * Metodo que coleta as informações da sala e depois inseri na lista do repositorio
     */
    private void cadastrarSala() {
        int numeroSala  = Console.scanInt("Número da Sala: ");
        int quantidadeSala = Console.scanInt("Capacidade Maxima da sala: ");
        lista.adicionar(new Sala(numeroSala,quantidadeSala));
        JOptionPane.showMessageDialog(null, "Sala Cadastrada com Sucesso");
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
    private void alterarFilme() {
        int salaAlterar = Console.scanInt("Número da sala á alterar: ");
        Sala sala = lista.consultarPorSala(salaAlterar);
        System.out.println("Dados Atuais");
        System.out.println("Número da sala: "+sala.getNumeroSala());
        System.out.println("Capacidade da Sala: "+sala.getQuantidadeSala());
        String resposta = Console.scanString("Mudar a capacidade da Sala: sim ou não ~> ");
        if(resposta.equalsIgnoreCase("sim"))
            sala.setQuantidadeSala(Console.scanInt("Nova Capacidade da sala: "));
        JOptionPane.showMessageDialog(null, "Mudanças Concluida");
    }
    /**
     * metodo que exclui uma sala da lista
     */
    private void deletarSala() {
        int filmeDeletar = Console.scanInt("Numero da sala a excluir: ");
        Sala sala = lista.consultarPorSala(filmeDeletar);
        lista.remover(sala);
        JOptionPane.showMessageDialog(null, "Exclusão Concluida");
    }
            
}
