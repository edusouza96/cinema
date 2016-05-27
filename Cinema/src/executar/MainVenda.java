package executar;
import java.util.InputMismatchException;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import repositorio.RepositorioAssentos;
import repositorio.RepositorioFilmes;
import repositorio.RepositorioSalas;
import repositorio.RepositorioSessao;
import repositorio.RepositorioVendas;
import util.Console;
import view.FilmeUI;
import view.MenuUI;
import view.RelatorioUI;
import view.SalaUI;
import view.SessaoUI;
import view.VendaUI;
/**
 * Da inicio as operações da sala
 */
public class MainVenda {
    private static RepositorioSessao listaSessao;
    private static RepositorioFilmes listaFilme;
    private static RepositorioSalas listaSala;
    private static RepositorioAssentos listaAssento;
    private static RepositorioVendas listaVenda;
    
    
    public static void main(String[] args){
        listaFilme = new RepositorioFilmes();
        listaSala = new RepositorioSalas();
        listaSessao = new RepositorioSessao();        
        listaAssento = new RepositorioAssentos();
        listaVenda = new RepositorioVendas();
        
        int opcao = 0;
        
        do{
            System.out.println(MenuUI.menuPrincipal());
            try{
                opcao = Console.scanInt("Digite a opção desejada ~> ");
                switch(opcao){
                    case MenuUI.FILME:
                        new FilmeUI().executar();
                        break;
                    case MenuUI.SALA:
                        new SalaUI(listaSala).executar();
                        break;
                    case MenuUI.SESSAO:
                        new SessaoUI(listaSessao, listaFilme, listaSala).executar();
                        break;
                    case MenuUI.VENDA:
                        new VendaUI(listaFilme,listaSala,listaSessao,listaAssento,listaVenda).executar();
                        break;
                    case MenuUI.RELATORIO:
                        new RelatorioUI(listaFilme,listaSala,listaSessao,listaAssento,listaVenda).executar();
                        break;
                    case MenuUI.SAIR:
                        JOptionPane.showMessageDialog(null, "Sistema Finalizado!");
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opção Invalida!", null, ERROR_MESSAGE);
                }
            }catch(InputMismatchException ex){
                    JOptionPane.showMessageDialog(null, "Somente valor numérico", "Erro", ERROR_MESSAGE);
                }
                
        }while(opcao != MenuUI.SAIR);
        
    }
    
}
