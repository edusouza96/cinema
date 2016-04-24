package repositorio;

import dao.AssentoDao;
import java.util.ArrayList;
import model.Assento;
import util.DateUtil;

/**
 *Classe que vai ter os dados do Assento e realizar as operações de adicionar,
 * listar, consultar por codigo e por data
 */
public class RepositorioAssentos implements AssentoDao{
    private ArrayList<Assento> listaAssentos;

    /**
     * Construtor que inicializar a lista
     */
    public RepositorioAssentos() {
        this.listaAssentos =  new ArrayList<>();
    }

    /**
     * getter de listaAssentos
     * @return retorna uma lista de objetos Assentos
     */
    public ArrayList<Assento> getListaAssentos() {
        return listaAssentos;
    }

    /**
     * metodo que adiciona o objeto a llista de assento
     * @param assento recebe por parametro um objeto assento
     */
    @Override
    public void adicionar(Assento assento) {
        listaAssentos.add(assento);
    }

    /**
     * metodo que consulta um assento por codigo
     * @param codigo recebe por parametro um codigo
     * @return retorna o objeto assento
     */
    @Override
    public Assento consultarPorCodigo(int codigo) {
        for(Assento assento: listaAssentos){
            if(assento.getCodigoAssento() == codigo){
                return assento;
            }
        }
        return null;
    }

    /**
     * metodo que consulta um assento por data
     * @param data recebe por parametro uma data
     * @param codigo recebe por parametro um codigo de sessao
     * @return retorna o objeto assento
     */
    @Override
    public Assento consultarPorDataCodigo(String data,int codigo) {
        String dataString;
        for(Assento assento: listaAssentos){
            dataString = DateUtil.dateToString(assento.getData());
            if(dataString.equalsIgnoreCase(data) && assento.getSessao().getCodigoSessao() == codigo){
                return assento;
            }
        }
        return null;
    }   
}