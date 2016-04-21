package executar;
import java.text.ParseException;
import repositorio.RepositorioFilmes;
import repositorio.RepositorioSalas;
import repositorio.RepositorioSessao;
import view.FilmeUI;
import view.SalaUI;
import view.SessaoUI;
/**
 * Da inicio as operações da sala
 */
public class MainSessao {
    private static RepositorioSessao listaSessao;
    private static RepositorioFilmes listaFilme;
    private static RepositorioSalas listaSala;
    
    public static void main(String[] args) throws ParseException {
        listaFilme = new RepositorioFilmes();
        new FilmeUI(listaFilme).executar();
        listaSala = new RepositorioSalas();
        new SalaUI(listaSala).executar();
        listaSessao = new RepositorioSessao();
        new SessaoUI(listaSessao, listaFilme, listaSala).executar();
    }
    
}
