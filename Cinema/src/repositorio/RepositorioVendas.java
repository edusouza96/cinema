package repositorio;

import dao.VendaDao;
import java.util.ArrayList;
import model.Venda;

/**
 *Classe que vai ter os dados da venda e realizar as operações de adicionar,
 * listar, consultar por registro
 */
public class RepositorioVendas implements VendaDao{
    private ArrayList<Venda> listaVendas;

    /**
     * construtor que inicializa a lista
     */
    public RepositorioVendas() {
        this.listaVendas =  new  ArrayList<>();
    }

    /**
     * getter de listaVendas
     * @return retorna uma lista 
     */
    public ArrayList<Venda> getListaVendas() {
        return listaVendas;
    }

    /**
     * metodo para adicionar uma venda na lista
     * @param venda 
     */
    @Override
    public void adicionar(Venda venda) {
        listaVendas.add(venda);
    }

    /**
     * metodo de consultar pelo registro
     * @param registro recebe um registro de venda por parametro
     * @return retorna um objeto venda
     */
    @Override
    public Venda consultarPorRegistro(int registro) {
        for(Venda venda:listaVendas){
            if(venda.getRegistroVenda() == registro)
                return venda;
        }
        return null;
    }
    
    
    
    
}
