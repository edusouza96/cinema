package view;

import exceptions.ObjectNullException;
import java.awt.HeadlessException;
import java.text.ParseException;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import model.Assento;
import model.Sessao;
import model.Venda;
import repositorio.RepositorioAssentos;
import repositorio.RepositorioFilmes;
import repositorio.RepositorioSalas;
import repositorio.RepositorioSessao;
import repositorio.RepositorioVendas;
import util.Console;
import util.DateUtil;

/**
 *Interface da venda para o usuario
 * contem realizar venda,listar vendas 
 */
public class VendaUI {
    private RepositorioVendas listaVendas;
    private RepositorioAssentos listaAssentos;
    private RepositorioSessao listaSessao;
    private RepositorioSalas listaSalas;
    private RepositorioFilmes listaFilmes;

    /**
     * Construtor que recebe o objeto repositorio
     * @param listaFilmes recebe objeto repositorio filme
     * @param listaSalas recebe objeto repositorio salas
     * @param listaSessao recebe objeto repositorio sessao
     * @param listaVendas recebe objeto reporitorio vendas
     * @param listaAssentos recebe objeto reporitorio assentos
     */
    public VendaUI(RepositorioFilmes listaFilmes, RepositorioSalas listaSalas, RepositorioSessao listaSessao, RepositorioAssentos listaAssentos, RepositorioVendas listaVendas) {
        this.listaVendas = listaVendas;
        this.listaAssentos = listaAssentos;
        this.listaFilmes = listaFilmes;
        this.listaSalas = listaSalas;
        this.listaSessao = listaSessao;
              
        try {
            SessaoUI sessaoUI = new SessaoUI(listaSessao,listaFilmes,listaSalas);
            Sessao sessao = sessaoUI.getLista().getListaSessao().get(1);
            listaVendas.adicionar(new Venda(sessao, DateUtil.stringToDate("05/05/2016")));
        } catch (ParseException ex) {
            System.out.println("Não tem erro aqui, pois sou que manipulo");
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Erro não identificado!\nRetorne ao menu principal e tente novamente", null, ERROR_MESSAGE);
        }
    }
    
    /**
    * metodo que contem as opções para execução dos procedimentos
    */
    public void executar(){
        int opcao = 0;
        do{
            System.out.println(MenuUI.menuVenda());
            try{
                opcao = Console.scanInt("Digite sua opção desejada:");
                switch (opcao) {
                    case MenuUI.CADASTRAR:
                        realizarVenda();
                        break;
                    case MenuUI.LISTAR:
                        mostrarVendas();
                        break;
                    case MenuUI.SAIR:
                        JOptionPane.showMessageDialog(null, "Retornando ao Menu Principal!");
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opção Invalida!", null, ERROR_MESSAGE);
                }
                }catch(InputMismatchException ex){
                    JOptionPane.showMessageDialog(null, "Somente valor numérico", "Erro", ERROR_MESSAGE);
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(null, "Erro não identificado, tente novamente", null, ERROR_MESSAGE);
                }
        }while(opcao != 0);
    }

    /**
     * Realizar as vendas de ingresso do cinema
     */
    private void realizarVenda() {
        Date dataSessao;
        String dataString = Console.scanString("Dia da Sessão: ");
        mostrarSessoes();
        try {
            int codigoSessao = Console.scanInt("Digite o código da sessão desejada: ");
            Assento assento = listaAssentos.consultarPorDataCodigo(dataString,codigoSessao);
            dataSessao = DateUtil.stringToDate(dataString);
            if(assento == null){
                Sessao sessao = listaSessao.consultarPorCodigo(codigoSessao);
                if(sessao == null)
                    throw new ObjectNullException();
                listaVendas.adicionar(new Venda(sessao,dataSessao));
                JOptionPane.showMessageDialog(null, "Compra de ingresso realizada");
                int lugares = sessao.getSala().getQuantidadeSala() -1;
                listaAssentos.adicionar(new Assento(sessao,lugares , dataSessao));
            }else{
                if(assento.getAssentoLivres() > 0){
                    listaVendas.adicionar(new Venda(assento.getSessao(), dataSessao));
                    JOptionPane.showMessageDialog(null, "Compra de ingresso realizada");
                    assento.ocuparLugar();
                }else{
                  JOptionPane.showMessageDialog(null, "Não tem mais esta sessão para este dia", "", JOptionPane.WARNING_MESSAGE);
                }
            }
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Data Inválida\n Favor digitar no Padrão DD/MM/AAAA", "Operação cancelada", ERROR_MESSAGE);
        }catch(InputMismatchException ex){
            JOptionPane.showMessageDialog(null, "Somente valor numérico", "Erro", ERROR_MESSAGE);
        } catch (ObjectNullException ex) {
            JOptionPane.showMessageDialog(null, "Sessão Não Localizado", null, ERROR_MESSAGE);
        }catch(HeadlessException ex){
            JOptionPane.showMessageDialog(null, "Erro não identificado, tente novamente", null, ERROR_MESSAGE);
        }
             
    }
    
    /**
     * exibi todas as sessoes
     */
    private void mostrarSessoes(){
        System.out.println("___________________________________________\n");
        System.out.println(String.format("%-50s", "Código Sessão") + "\t"
                + String.format("%-80s", "|Filme/sala/horario"));
        for (Sessao sessao : listaSessao.getListaSessao()) {
            System.out.println(String.format("%-50s",sessao.getCodigoSessao()) + "\t"
                    + String.format("%-80s", "|" + sessao.toString()));
        }
    }
    /**
     * exibi todas as vendas realizadas
     */
    private void mostrarVendas() {
        String dataString;
        System.out.println("___________________________________________\n");
        System.out.println(String.format("%-50s", "Registro Venda") + "\t"
                + String.format("%-20s", "|Data") + "\t"
                + String.format("%-80s", "|Filme/sala/horario"));
        for (Venda venda : listaVendas.getListaVendas()) {
            dataString = DateUtil.dateToString(venda.getData());
            System.out.println(String.format("%-50s",venda.getRegistroVenda()) + "\t"
                    + String.format("%-20s", "|" + dataString) + "\t"
                    + String.format("%-80s", "|" + venda.getFilmeSalaSessao()));
        }
    }
    
}

  