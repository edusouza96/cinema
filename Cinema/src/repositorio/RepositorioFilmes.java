
package repositorio;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.WARNING_MESSAGE;
import model.Filme;
import IRepositorio.IFilme;

/**
 *Classe que vai ter os dados dos filmes e realizar as operações de adicionar,
 * listar, consultar por codigo e por nome, e remover o filme
 */
public class RepositorioFilmes implements IFilme {
    private ArrayList<Filme> listaFilmes;
   
    /**
     * Construtor que inicializa o arrayList dos filmes
     */
    public RepositorioFilmes(){
        listaFilmes = new ArrayList<>();
    }
    
    /**
     * Puxa toda lista de filmes
     * @return retorna uma lista de filmes
     */
    public ArrayList<Filme> getListaFilmes(){
        return listaFilmes;
    }
    
    /**
     * metodo para adicionar novos filmes no repositorio
     * @param filme recebe por parametro um objeto do tipo filme
     * @return retorna true ou false
     */
    @Override
    public boolean adicionar(Filme filme){
        Filme temFilme = consultarPorNome(filme.getNomeFilme());
        if(temFilme == null){
            listaFilmes.add(filme);
            return true;
        }else{
            return false;
        }
            
    }
    /**
     * 
     * @param filme recebe por parametro um objeto do tipo filme
     * @return retorna true ou false
     */
    public boolean hasFilme(String filme){
        Filme temFilme = consultarPorNome(filme);
        if(temFilme == null){
            return false;
        }else{
            return true;
        }
    }
    
    /**
     * metodo para consultar o filme 
     * @param codigo: Parametro codigo recebido para fazer a busca
     * @return :retorna o objeto de acordo com o código 
     */
    @Override
    public Filme consultarPorCodigo(int codigo){
        for(Filme filme: listaFilmes){
            if(filme.getCodigoFilme() == codigo)
                return filme;
        }
        return null;
    }
    /**
     * metodo para consultar o filme de acordo com o nome
     * @param nome: Parametro nome recebido para fazer a busca
     * @return : Retorna o objeto de acordo com o nome
     */
    @Override
     public Filme consultarPorNome(String nome){
         for(Filme filme: listaFilmes){
             if(filme.getNomeFilme().equals(nome))
                 return filme;
         }
         return null;
     }
     /**
      * metodo para remover um filme
      * @param filme, filme a ser removido 
      */
     @Override
     public void remover(Filme filme){
         listaFilmes.remove(filme);
     }
}

