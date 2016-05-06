package repositorio;

import dao.SessaoDao;
import java.util.ArrayList;
import java.util.Date;
import model.Sessao;
import util.DateUtil;
/**
Classe que vai ter os dados das sessões e realizar as operações de adicionar,remover,
 * listar, consultar por nome do filme, por nunmero da sala e por horario da sessao
 */
public class RepositorioSessao implements SessaoDao {
    private ArrayList<Sessao> listaSessao;

    /**
     * construtor que inicializa a lista
     */
    public RepositorioSessao() {
        listaSessao = new ArrayList<>();
    }

    /**
     * getter da lista Sessão
     * @return retorna a listaSessao
     */
    public ArrayList<Sessao> getListaSessao() {
        return listaSessao;
    }

    /***
     * metodo que adiciona um objeto na lista
     * @param sessao Recebe por parametro um objeto do tipo sessao para adicionar na lista
     */
    @Override
    public void adicionar(Sessao sessao) {
        try{
            
        
        Sessao verificaSessao = consultarPorSala(sessao.getSala().getNumeroSala());
       int diferencaHoras;
        diferencaHoras = sessao.getHorario().compareTo(verificaSessao.getHorario());
        System.out.println(diferencaHoras);
        listaSessao.add(sessao);
        
        }catch(Exception ex){
            
        }
    }

    /**
     * metodo consultar pelo numero do codigo
     * @param codigo recebe por parametro o codigo de uma sessão
     * @return Retorna um objeto do tipo sessão
     */
    @Override    public Sessao consultarPorCodigo(int codigo) {
        for(Sessao sessao : listaSessao){
            if(sessao.getCodigoSessao() == codigo)
                return sessao;
        }
        return null;
    }
    
    /**
     * Metodo consultar pelo numero da sala
     * @param sala recebe por parametro o numero de uma sala
     * @return Retorna um objeto do tipo sessao de acordo com a requisição
     */
    @Override
    public Sessao consultarPorSala(int sala) {
        for(Sessao sessao: listaSessao){
            if(sessao.getSala().getNumeroSala() == sala)
                return sessao;
        }
        return null;
    }

    /**
     * Metodo consultar pelo nome do filme
     * @param filme recebe por parametro o nome de um filme
     * @return Retorna um objeto do tipo sessao de acordo com a requisição
     */
    @Override
    public Sessao consultarPorFilme(String filme) {
        for(Sessao sessao:listaSessao){
            if(sessao.getFilme().getNomeFilme().equalsIgnoreCase(filme))
                return sessao;
        }
        return null;
    }

    /**
     * Metodo consultar pelo horario da sessao
     * @param horario recebe por parametro o horario de alguma sessão
     * @return Retorna um objeto do tipo sessao de acordo com a requisição
     */
    @Override
    public Sessao consultarPorHorario(String horario) {
        String horarioString;
        for(Sessao sessao: listaSessao){
            horarioString = DateUtil.hourToString(sessao.getHorario());
            if(horarioString.equals(horario))
                return sessao;
        }
        return null;
    }

    /**
     * método remover uma sessão da lista
     * @param sessao recebe por parametro um objeto do tipo sessão para que este seja removido da lista
     */
    @Override
    public void remover(Sessao sessao) {
        listaSessao.remove(sessao);
    }

    
    
    
    
    
}
