package executar;
import repositorio.RepositorioSalas;
import view.SalaUI;
/**
 * Da inicio as operações da sala
 */
public class MainSala {
    private static RepositorioSalas listaSala;
    
    public static void main(String[] args) {
        listaSala = new RepositorioSalas();
        new SalaUI(listaSala).executar();
    }
    
}
