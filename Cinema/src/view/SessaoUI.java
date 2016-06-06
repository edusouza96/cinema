package view;

import exceptions.HourNotAvailable;
import exceptions.RNException;
import java.text.ParseException;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
 import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.WARNING_MESSAGE;
import model.Filme;
import model.Sala;
import model.Sessao;
import rn.FilmeRN;
import rn.SalaRN;
import rn.SessaoRN;
import util.Console;
import util.DateUtil;

/**
 *Interface da sessão para o usuario
 * contem cadastrar,listar,alterar, deletar filme
 */
public class SessaoUI {
    private SessaoRN sessaoRN;

    /**
     * Construtor 
     */
    public SessaoUI() {
        sessaoRN = new SessaoRN();
    }
    
    /**
     * metodo que contem as opções para execução dos procedimentos
     */
    public void executar() {
        int opcao = 0;
        do{
            System.out.println(MenuUI.menuSessao());
            try{
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
                    case MenuUI.BUSCA_ESPECIFICA:
                        procurarPorHorario();
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
        }while(opcao != 0);
    }

    /**
     * metodo que coleta as informações da sessão e depois
     * envia para RN tratar da requisição
     */
    private void cadastrarSessao(){
        FilmeRN filmeRN = new FilmeRN(); 
        SalaRN salaRN = new SalaRN();
      
        try{
            exibirFilmes();
            System.out.println("---------------------------------\n");
            int codigoFilme = Console.scanInt("Código do Filme: ");

            exibirSalas();
            System.out.println("---------------------------------\n");
            int numeroSala = Console.scanInt("Numero da Sala: ");
            String horario = Console.scanString("Horario da Sessão: ");
            Date hora;
            
            Filme filme = filmeRN.procurarPorId(codigoFilme);
            Sala sala = salaRN.procurarPorNumero(numeroSala);
            hora = DateUtil.stringToHour(horario);
            sessaoRN.adicionar(new Sessao(filme,sala,hora));
            JOptionPane.showMessageDialog(null, "Sessão cadastrada com suceso");
        
        }catch(InputMismatchException ex){
            JOptionPane.showMessageDialog(null, "Somente valor numérico", "Erro", ERROR_MESSAGE);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Hora Inválida\n Favor digitar no Padrão HH:MM", "Operação cancelada", ERROR_MESSAGE);
        } catch (RNException ex) {
            System.err.println(ex.getMessage());
        } catch (HourNotAvailable ex) {
            
        }
        
             
    }
    /**
     * metodo que lista os nomes dos filmes
     */
    private void exibirFilmes(){
        FilmeRN filmeRN = new FilmeRN();
        List<Filme> listaFilmes = filmeRN.listar();
        if(listaFilmes.isEmpty()){
            JOptionPane.showMessageDialog(null, "Filmes não encontrados", "Aviso", WARNING_MESSAGE);
        }else{
            System.out.println("___________________________________________\n");
            System.out.println(String.format("%-15s", "Código do Filme") + "\t"
                    + String.format("%-50s", "|Nome do Filme") + "\t"
                    + String.format("%-20s", "|Gênero") + "\t"
                    + String.format("%-40s", "|Sinopse"));
            for (Filme filme : listaFilmes) {
                System.out.println(String.format("%-15s", filme.getCodigoFilme()) + "\t"
                        + String.format("%-50s", "|" + filme.getNomeFilme()) + "\t"
                        + String.format("%-20s", "|" + filme.getGenero()) + "\t"
                        + String.format("%-40s", "|" + filme.getSinopse()));
            }
        }
    }
    /**
     * metodoque lista o numero das salas
     */
    private void exibirSalas(){
        SalaRN salaRN = new SalaRN();
        List<Sala> listaSalas = salaRN.listar();
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
     * metodo que faz uma requisição para RN e depois exibe todas as
     * sessoes cadastradas no banco
     */
    private void mostrarSessoes() {
        List<Sessao> listaSessoes = sessaoRN.listar();
        this.mostrarSessoes(listaSessoes);
    }
    
    /**
     * Método que verifica se a lista está vazia e se não estiver trata visualmente para ser exibido na tela
     * @param listaSessoes recebe uma lista de sessoes para fazer as verificações e tratar a saida
     */
    private void mostrarSessoes(List<Sessao> listaSessoes) {
        if(listaSessoes.isEmpty()){
            JOptionPane.showMessageDialog(null, "Sessões não encontradas", "Aviso", WARNING_MESSAGE);
        }else{
            System.out.println(String.format("%-20s", "Código Sessão")+"\t"
                +String.format("%-50s", "|Nome do Filme")+"\t"
                +String.format("%-30s", "|Numero da Sala")+"\t"
                + String.format("%-30s", "|Horario da sessão"));
            for(Sessao sessao : listaSessoes){
                
                System.out.println(String.format("%-20s", sessao.getCodigoSessao())+"\t"
                        +String.format("%-50s", "|"+sessao.getFilme().getNomeFilme())+"\t"
                        +String.format("%-30s", "|"+sessao.getSala().getNumeroSala())+"\t"
                        +String.format("%-30s", "|"+ sessao.getHorario()));

            }
        }
    }
    
     /**
     * Recebe um objeto sessao e trata visualmente os dados para ser exibido na tela
     * @param s : Objero sessao recebido por parametro
     */
    private void mostrarSessao(Sessao s){
        System.out.println("----------------------------------");
        System.out.println("Dados Atuais");
        System.out.println("Código da Sessão: "+s.getCodigoSessao());
        System.out.println("Filme: "+s.getFilme().getNomeFilme());
        System.out.println("Sala: "+s.getSala().getNumeroSala());
        System.out.println("Horário: "+s.getHorario());
    }

    /**
     * metodo responsavel para alterar dados da sessão
     */
    private void alterarSessao(){
        FilmeRN filmeRN = new FilmeRN(); 
        SalaRN salaRN = new SalaRN();
        
        try{
            int sessaoAlterar = Console.scanInt("Código da sessão a alterar: ");
            Sessao sessao = sessaoRN.procurarPorId(sessaoAlterar);
            this.mostrarSessao(sessao);
           
           
            System.out.println("---------------------------------\n");
            exibirFilmes();
            int  codigoFilme = Console.scanInt("~>Digite o código do filme para esta sessao,[Digite '0' caso não queira alterar]: ");
            if(codigoFilme > 0){
                Filme filme = filmeRN.procurarPorId(codigoFilme);
                sessao.setFilme(filme);
            }
            
            System.out.println("---------------------------------\n");
            exibirSalas();
            int  numeroSala = Console.scanInt("~>Digite o numero da Sala para esta sessao,[Digite '0' caso não queira alterar]: ");
            if(numeroSala > 0){
                Sala sala = salaRN.procurarPorNumero(numeroSala);
                sessao.setSala(sala);
            }
            System.out.println("---------------------------------\n");
            String hora = Console.scanString("Novo Horario para esta sessao: ");
            if(!hora.isEmpty()){
                sessao.setHorario(DateUtil.stringToHour(hora));
            }
            
            sessaoRN.atualizar(sessao);
            JOptionPane.showMessageDialog(null, "Mudanças Concluida");
            
        }catch(InputMismatchException ex){
            JOptionPane.showMessageDialog(null, "Somente valor numérico", "Erro", ERROR_MESSAGE);
        }catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Hora Inválida\n Favor digitar no Padrão HH:MM", "Operação cancelada", ERROR_MESSAGE);
        } catch (RNException ex) {
           System.err.println(ex.getMessage());
        } catch (HourNotAvailable ex) {
            
        }   
    }

    /**
     * metodo que deleta uma sessão 
     */
    private void deletarSessoes(){
        try{
            int sessaoDeletar = Console.scanInt("Codigo da sessão a excluir: ");
            Sessao sessao = sessaoRN.procurarPorId(sessaoDeletar);
            this.mostrarSessao(sessao);
            char resposta = Console.scanChar("Realmente deseja remover essa Sessão?(s/n)");
            if(resposta == 'S' || resposta == 's' ){
                sessaoRN.deletar(sessao);
                JOptionPane.showMessageDialog(null, "Exclusão Concluida");
            }else{
                JOptionPane.showMessageDialog(null, "Operação cancelada", "Aviso", WARNING_MESSAGE);
            }
        }catch(InputMismatchException ex){
            JOptionPane.showMessageDialog(null, "Somente valor numérico", "Erro", ERROR_MESSAGE);
        } catch (RNException ex) {
            System.err.println(ex.getMessage());
        }    
    }

   
    /**
     * Busca entre as sessões cadastradas horarios superiores ao inserido
     * Retornando uma lista
     */
    private void procurarPorHorario() {
        String horario = Console.scanString("Horario: ");
        Date hora; 
        try{
            hora = DateUtil.stringToHour(horario);
            List<Sessao> listaSessao = sessaoRN.procuraPorHorario(hora);
            this.mostrarSessoes();
        }catch (RNException ex) {
            System.err.println(ex.getMessage());
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Hora Inválida\n Favor digitar no Padrão HH:MM", "Operação cancelada", ERROR_MESSAGE);
        }

    }
}
