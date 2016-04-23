package view;

import java.text.ParseException;
import java.util.Date;
import javax.swing.JOptionPane;
import model.Assento;
import model.Sessao;
import model.Venda;
import repositorio.RepositorioAssentos;
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

    /**
     * Construtor que recebe o objeto repositorio
     * @param listaVendas recebe objeto reporitorio vendas
     * @param listaAssentos recebe objeto reporitorio assentos
     */
    public VendaUI(RepositorioVendas listaVendas, RepositorioAssentos listaAssentos) {
        this.listaVendas = listaVendas;
        this.listaAssentos = listaAssentos;
    }
    
    /**
    * metodo que contem as opções para execução dos procedimentos
    */
    public void executar() throws ParseException{
        int opcao = 0;
        do{
             opcao = Console.scanInt("Digite sua opção desejada:");
            switch (opcao) {
                case 1:
                    realizarVenda();
                    break;
                case 2:
                    mostrarVendas();
                    break;
                case 0:
                    System.out.println("Saindo....");
                    break;
                default:
                    System.err.println("Opção inválida..");
            }
        }while(opcao != 0);
    }

    /**
     * Realizar as vendas de ingresso do cinema
     */
    private void realizarVenda() throws ParseException {
        Date dataAssento;
        String dataAssentoString;
        Boolean temLista = false;
        Boolean temAssento = true;
        Date dataSessao;
        String dataString = Console.scanString("Dia da Sessão: ");
        for(Assento assento: listaAssentos.getListaAssentos()){
            dataAssento = assento.getData();
            dataAssentoString = DateUtil.dateToString(dataAssento);
            if(dataString.equals(dataAssentoString)){
                if(assento.getAssentoLivres()>0){
                    System.out.println(assento.toString());
                    temLista = true;
                }else{
                    temAssento = false;
                }
            }
        }
        if(temLista == false && temAssento == true){
            mostrarSessoes();
            Sessao sessao = listaSessao.consultarPorCodigo(Console.scanInt("Digite o código da sessão desejada: "));
            dataSessao = DateUtil.stringToDate(dataString);
            listaVendas.adicionar(new Venda(sessao,dataSessao));
            JOptionPane.showMessageDialog(null, "Compra de ingresso realizada");
            int lugares = sessao.getSala().getQuantidadeSala() -1;
            listaAssentos.adicionar(new Assento(sessao,lugares , dataSessao));
        }else if (temLista == false && temAssento == false){
            JOptionPane.showMessageDialog(null, "Não tem mais sessão para este dia");
        }else{
            Assento assento = listaAssentos.consultarPorCodigo(Console.scanInt("Digite o código da sessão desejada: "));
            dataSessao = DateUtil.stringToDate(dataString);
            listaVendas.adicionar(new Venda(assento.getSessao(), dataSessao));
            JOptionPane.showMessageDialog(null, "Compra de ingresso realizada");
            assento.ocuparLugar();
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

  