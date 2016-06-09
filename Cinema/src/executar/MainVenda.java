package executar;
import java.util.InputMismatchException;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
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
    
    public static void main(String[] args){
        
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
                        new SalaUI().executar();
                        break;
                    case MenuUI.SESSAO:
                        new SessaoUI().executar();
                        break;
                    case MenuUI.VENDA:
                        new VendaUI().executar();
                        break;
                    case MenuUI.RELATORIO:
                        new RelatorioUI().executar();
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
