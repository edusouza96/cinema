package view;

import exceptions.RNException;
import java.awt.HeadlessException;
import java.text.ParseException;
import java.util.InputMismatchException;
import java.util.List;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.WARNING_MESSAGE;
import model.Assento;
import model.Sessao;
import model.Venda;
import rn.AssentoRN;
import rn.SessaoRN;
import rn.VendaRN;
import util.Console;
import util.DateUtil;

/**
 *Interface da venda para o usuario
 * contem realizar venda,listar vendas 
 */
public class VendaUI {
    private VendaRN vendaRN;

    /**
     * Construtor 
     */
    public VendaUI() {
        vendaRN = new VendaRN();
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
                    case MenuUI.BUSCA_VENDA:
                         consultarPorRegistroVenda();
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
                    JOptionPane.showMessageDialog(null, "Erro não identificado, tente novamente"+ex, null, ERROR_MESSAGE);
                }
        }while(opcao != 0);
    }

    /**
     * Realizar as vendas de ingresso do cinema
     */
    private void realizarVenda() {
        AssentoRN assentoRN = new AssentoRN();
        SessaoRN sessaoRN = new SessaoRN();
        String dataString = Console.scanString("Dia da Sessão: ");
        mostrarSessoes();
        try {
            int codigoSessao = Console.scanInt("Digite o código da sessão desejada: ");
            Assento assento = assentoRN.procurarPorDataCodigo(DateUtil.stringToDate(dataString), codigoSessao);
           
            if(assento == null){
                
                Sessao sessao = sessaoRN.procurarPorId(codigoSessao);
                vendaRN.adicionar(new Venda(sessao,DateUtil.stringToDate(dataString)));
                JOptionPane.showMessageDialog(null, "Compra de ingresso realizada");
                int lugares = sessao.getSala().getQuantidadeSala() -1;
                assentoRN.adicionar(new Assento(sessao,lugares,DateUtil.stringToDate(dataString)));
            }else{
                if(assento.getAssentoLivres() > 0){
                    vendaRN.adicionar(new Venda(assento.getSessao(),DateUtil.stringToDate(dataString)));
                    JOptionPane.showMessageDialog(null, "Compra de ingresso realizada!");
                    assento.ocuparLugar();
                    assentoRN.atualizar(assento);
                }else{
                  JOptionPane.showMessageDialog(null, "Não tem mais esta sessão para este dia", "", JOptionPane.WARNING_MESSAGE);
                }
            }
            
        } catch (RNException ex) {
            System.err.println(ex.getMessage());
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Data Inválida\n Favor digitar no Padrão DD/MM/AAAA", "Operação cancelada", ERROR_MESSAGE);
        }catch(InputMismatchException ex){
            JOptionPane.showMessageDialog(null, "Somente valor numérico", "Erro", ERROR_MESSAGE);
        }catch(HeadlessException ex){
          JOptionPane.showMessageDialog(null, "Erro não identificado, tente novamente", null, ERROR_MESSAGE);
            
        } 
             
    }
    
    /**
     * exibi todas as sessoes
     */
    private void mostrarSessoes(){
        SessaoRN sessaoRN = new SessaoRN();
        
        List<Sessao> listaSessoes = sessaoRN.listar();
        if(listaSessoes.isEmpty()){
            JOptionPane.showMessageDialog(null, "Sessões não encontradas", "Aviso", WARNING_MESSAGE);
        }else{
            System.out.println("___________________________________________\n");
            System.out.println(String.format("%-20s", "Código da Sessão") + "\t"
                    + String.format("%-50s", "|Filme") + "\t"
                    + String.format("%-20s", "|Sala") + "\t"
                    + String.format("%-40s", "|Horario"));
            for (Sessao sessao : listaSessoes) {
                System.out.println(String.format("%-20s", sessao.getCodigoSessao()) + "\t"
                        + String.format("%-50s", "|" + sessao.getFilme().getNomeFilme()) + "\t"
                        + String.format("%-20s", "|" + sessao.getSala().getNumeroSala()) + "\t"
                        + String.format("%-40s", "|" + sessao.getHorario()));
            }
        }
    }
   
     /**
     * metodo que faz uma requisição para RN e depois exibe todos as vendas 
     */
    private void mostrarVendas() {
        List<Venda> listaVendas = vendaRN.listar();
        this.mostrarVendas(listaVendas);
    }
    
    /**
     * Método que verifica se a lista está vazia e se não estiver trata visualmente para ser exibido na tela
     * @param listaVendas recebe uma lista de vendas para fazer as verificações e tratas a saida
     */
    private void mostrarVendas(List<Venda> listaVendas) {
        String dataString;
        System.out.println("___________________________________________\n");
        System.out.println(String.format("%-50s", "Registro Venda") + "\t"
                + String.format("%-20s", "|Data") + "\t"
                + String.format("%-80s", "|Filme/sala/horario"));
        for (Venda venda : listaVendas) {
            dataString = DateUtil.dateToString(venda.getData());
            System.out.println(String.format("%-50s",venda.getRegistroVenda()) + "\t"
                    + String.format("%-20s", "|" + dataString) + "\t"
                    + String.format("%-80s", "|" + venda.getFilmeSalaSessao()));
        }
    }

    private void consultarPorRegistroVenda() {
        try{
            int registroVenda = Console.scanInt("Registro Venda: ");
            Venda venda = vendaRN.procurarPorId(registroVenda);
            this.mostrarVenda(venda);
        }catch (RNException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private void mostrarVenda(Venda v) {
        System.out.println("-----------------------------");
        System.out.println("Dados Atuais");
        System.out.println("Registro Venda: "+v.getRegistroVenda());
        System.out.println("Sessão: "+v.getFilmeSalaSessao());
        System.out.println("Data: "+v.getData());
        System.out.println("-----------------------------");
    }
    
}

  