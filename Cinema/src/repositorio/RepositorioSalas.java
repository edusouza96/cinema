package repositorio;

import dao.SalaDao;
import java.util.ArrayList;
import model.Sala;

/**
 *Classe que vai ter os dados da sala e realizar as operações de adicionar,
 * listar, consultar 
 */
public class RepositorioSalas implements SalaDao{
    private ArrayList<Sala> listaSalas;
    
    /**
     * contrutor para inicializar a lista de salas
     */
    public RepositorioSalas() {
        listaSalas = new ArrayList<>();
    }

    /**
     * getter da lista de salas
     * @return retorna uma lista de salas
     */
    public ArrayList<Sala> getListaSalas() {
        return listaSalas;
    }

    /*
    *metodo para adicionar um objeto sala na listaSala
    */
    @Override
    public void adicionar(Sala sala) {
        listaSalas.add(sala);
    }
    /***
     * metodo consultar por numero da sala
     * @param sala Recebe por parametro o numero da sala
     * @return Retorna os dados da sala requisitada
     */
    @Override
    public Sala consultarPorSala(int sala) {
        for(Sala salas: listaSalas){
            if(salas.getNumeroSala() == sala)
                return salas;
        }
        return null;
    }
    /**
     * metodo remover sala
     * @param sala recebe por parametro um objeto sala para remover o objeto da lista
     */
    @Override
    public void remover(Sala sala) {
        listaSalas.remove(sala);
    }
    
    
    
}
