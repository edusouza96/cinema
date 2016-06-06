package rn;

import Dao.DB.VendaDaoDB;
import Dao.VendaDao;
import exceptions.RNException;
import java.util.List;
import model.Venda;

/**
 * Classe que aplica as regras de negocio da entidade Venda
 * e também faz requisiçoes para Dao
 */
public class VendaRN {
    private VendaDao vendaDao;

    /**
     * Construtor
     */
    public VendaRN() {
        vendaDao = new VendaDaoDB();
    }

    /**
     * Verifica se objeto tem todas as informações preenchidas
     *  -Verifica se a sessao foi inserido 
     *  -Verifica se a data foi inserisa 
     * @param v : Objeto venda que contem os dados
     * @throws RNException : lança uma exceção caso um dos campos estejam vazio
     */
    private void validarCamposObrigatorios(Venda v) throws RNException {
        if(v.getFilmeSalaSessao() == null){
            throw new RNException("Campo Sessão não informado!");
        }
        if(v.getData() == null){
            throw new RNException("Campo Data não informado!");
        }
    }
    /**
     * requisita a Dao para adicionar uma Venda no Banco de dados;
     * @param v : Objeto Venda recebido da view
     * @throws RNException : Lança uma exceção em caso de alguma falha
     */
    public void adicionar(Venda v) throws RNException{
        this.validarCamposObrigatorios(v);
        vendaDao.adicionar(v);
    }
    /***
     * envia para a view uma lista de vendas
     * @return : retorna uma lista
     */
    public List<Venda> listar(){
        return (vendaDao.listar());
    }
    
    /**
     * Verifica se o parametro passado não está vazio
     * @param registroVenda : Numero recebido da view
     * @return : Retorna um objeto Venda
     * @throws RNException : Lança uma exceção caso não seja informado o numero ou se não for encontrada a venda
     */
    public Venda procurarPorId(int registroVenda) throws RNException{
        if(registroVenda <= 0){
            throw new RNException("Campo Registro Venda não informado!");
        }
        Venda venda = vendaDao.procurarPorId(registroVenda);
        if(venda == null){
            throw new RNException("Venda não Encontrada!");
        }
        return venda;
    }
    
}
