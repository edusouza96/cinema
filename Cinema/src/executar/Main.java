package executar;
import repositorio.RepositorioFilmes;
import view.FilmeUI;

public class Main {
    private static RepositorioFilmes listaFilme;
    
    public static void main(String[] args) {
        listaFilme = new RepositorioFilmes();
        new FilmeUI(listaFilme).executar();
    }
    
}
