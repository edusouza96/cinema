
package repositorio;

import java.util.ArrayList;
import model.Filme;

/**
 *Classe que vai ter os dados dos filmes e realizar as operações de adicionar,
 * listar, consultar 
 */
public class RepositorioFilmes {
    private ArrayList<Filme> listaFilmes;
   
    /**
     * Construtor que inicializa o arrayList dos filmes
     */
    public RepositorioFilmes(){
        listaFilmes = new ArrayList<>();
    }
    /**
     * metodo para adicionar novos filmes no repositorio
     * @param filme 
     */
    public void adicionar(Filme filme){
        listaFilmes.add(filme);
    }
    /**
     * Puxa toda lista de pacientes
     * @return 
     */
    public ArrayList<Filme> getListaFilmes(){
        return listaFilmes;
    }
    /**
     * metodo para consultar o filme 
     * @param codigo: Parametro codigo recebido para fazer a busca
     * @return :retorna o objeto de acordo com o código 
     */
    public Filme ConsultaPorCodigo(int codigo){
        for(Filme filme: listaFilmes){
            if(filme.getCodigo() == codigo)
                return filme;
        }
        return null;
    }
    /**
     * metodo para consultar o filme de acordo com o nome
     * @param nome: Parametro nome recebido para fazer a busca
     * @return : Retorna o objeto de acordo com o nome
     */
     public Filme ConsultaPorNome(String nome){
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
     public void remover(Filme filme){
         listaFilmes.remove(filme);
     }
}

