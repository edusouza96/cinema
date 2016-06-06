package rn;

import Dao.AssentoDao;
import Dao.DB.AssentoDaoDB;
import exceptions.RNException;
import java.util.List;
import model.Assento;

/**
 * Classe que aplica as regras de negocio da entidade Assento
 * e também faz requisiçoes para Dao
 */
public class AssentoRN {
    private AssentoDao assentoDao;
    
    /**
     * Construtor
     */
    public AssentoRN() {
        assentoDao = new AssentoDaoDB();
    }
    
    /**
     * requisita a Dao para adicionar um assento no Banco de dados;
     * @param a : Objeto Assento recebido da view
     * @throws RNException : Lança uma exceção em caso de alguma falha
     */
    public void adicionar(Assento a) throws RNException{
        assentoDao.adicionar(a);
    }
    
    /***
     * envia para a view uma lista de assentos
     * @return : retorna uma lista
     */
    public List<Assento> listar(){
        return (assentoDao.listar());
    }
    
      /**
     * Verifica se o objeto sala recebido não esta vazio;
     * Requisita a DAO para atualizar q sala em questão
     * @param assento :Objeto assento recebido da View
     * @throws RNException : lança exceção caso o assento não exista
     */
    public void atualizar (Assento assento) throws RNException{
        if(assento == null || assento.getAssentoLivres() <= 0){
            throw new RNException("Assento não existe!");
        }
        assentoDao.atualizar(assento);
    }
    
    /**
     * Verifica se o parametro passado não está vazio
     * @param data : Data recebida da vies
     * @param codigo : Numero recebido da view
     * @return : Retorna um objeto Assento
     * @throws RNException : Lança uma exceção caso não seja informado o codigo ou a data
     */
    public Assento procurarPorDataCodigo(String data,int codigo) throws RNException{
        if(codigo <= 0){
            throw new RNException("Campo Código  não informado!");
        }
        if(data.isEmpty()){
            throw  new RNException("Data não informada!");
        }
        
        Assento assento = assentoDao.consultarPorDataCodigo(data, codigo);
        if(assento == null){
            //throw new RNException("Assento não Encontrada!");
        }
        return assento;
    }
}
