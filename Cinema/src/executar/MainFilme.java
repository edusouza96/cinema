package executar;
import repositorio.RepositorioFilmes;
import view.FilmeUI;
/**
 * Da inicio as operações do filme
 */
public class MainFilme {
    private static RepositorioFilmes listaFilme;
    
    public static void main(String[] args) {
        listaFilme = new RepositorioFilmes();
        new FilmeUI(listaFilme).executar();
    }
    
}
