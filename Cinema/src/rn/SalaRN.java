package rn;

import Dao.DB.SalaDaoDB;
import Dao.SalaDao;
import exceptions.RNException;
import java.util.List;
import model.Sala;

/**
 * Classe que aplica as regras de negocio da entidade Sala
 * e também faz requisiçoes para Dao
 */
public class SalaRN {
    private SalaDao salaDao;

    /**
     * Construtor
     */
    public SalaRN() {
        salaDao = new SalaDaoDB();
    }
    
    /**
     * Manda os dados para verificar se foram preenchidos os campos obrigatorios;
     * Manda valida se já foi cadastrada a sala em questão;
     * requisita a Dao para adicionar uma sala no Banco de dados;
     * @param s : Objeto Sala recebido da view
     * @throws RNException : Lança uma exceção em caso de alguma falha
     */
    public void adicionar(Sala s) throws RNException{
        this.validarCamposObrigatorios(s);
        this.validarSalaExistente(s);
        salaDao.adicionar(s);
    }

    /***
     * envia para a view uma lista de salass
     * @return : retorna uma lista
     */
    public List<Sala> listar(){
        return (salaDao.listar());
    }
    
    /**
     * Verifica se o objeto sala recebido não esta vazio ;
     * Requisita a DAO para excluir a sala em questão
     * @param sala: Objeto sala recebido da View
     * @throws RNException : lança exceção caso sala não exista
     */
    public void deletar(Sala sala) throws RNException{
        if(sala == null || sala.getNumeroSala() == 0){
            throw new RNException("Sala não existe!");
        }
        salaDao.deletar(sala);
    }
    
    /**
     * Verifica se o objeto sala recebido não esta vazio;
     * Manda os dados para verificar se foram preenchidos os campos obrigatorios;
     * Requisita a DAO para atualizar q sala em questão
     * @param sala :Objeto sala recebido da View
     * @throws RNException : lança exceção caso a sala não exista
     */
    public void atualizar (Sala sala) throws RNException{
        if(sala == null || sala.getNumeroSala() == 0){
            throw new RNException("Sala não existe!");
        }
        this.validarCamposObrigatorios(sala);
        salaDao.atualizar(sala);
    }
    
    /**
     * Verifica se o parametro passado não está vazio
     * Faz uma pesquisa para verificar a existencia da sala através do numero informado
     * @param numero : Numero recebido da view
     * @return : Retorna um objeto Sala
     * @throws RNException : Lança uma exceção caso não seja informado o numero ou se não for encontrada a sala
     */
    public Sala procurarPorNumero(int numero) throws RNException{
        if(numero <= 0){
            throw new RNException("Campo Numero não informado!");
        }
        Sala sala = salaDao.procurarPorNumero(numero);
        if(sala == null){
            throw new RNException("Sala não Encontrada!");
        }
        return sala;
    }
    
    /**
     * Verifica se objeto tem todas as informações preenchidas
     *  -Verifica se o numero da sala foi inserido e se é maior que zero
     *  -Verifica se a capacidade da sala foi inserisa e se é maior que zero
     * @param s : Objeto sala que contem os dados
     * @throws RNException : lança uma exceção caso um dos campos esteja menor ou igual a zero
     */
    private void validarCamposObrigatorios(Sala s) throws RNException {
        if(s.getNumeroSala() <= 0){
            throw new RNException("Campo Numero da sala não informado!");
        }
        if(s.getQuantidadeSala() <= 0){
            throw new RNException("Campo Capacidade da sala não informado!");
        }
    }

    /**
     * Verifica se já não existe allguma sala com o  memmo numero
     * @param s : Objeto sala a ser verificado
     * @throws RNException : lança uma exceção caso exista a sala   
     */
    private void validarSalaExistente(Sala s) throws RNException {
        if(salaExiste(s.getNumeroSala())){
            throw new RNException("Sala já existe!");
        }
    }

    /**
     * Verifica se a partir do numero da sala, exista alguma sala com o mesmo numero
     * @param numeroSala : número da sala a ser testado
     * @return : retorna true ou false a respeito da existencia da sala
     */
    private boolean salaExiste(int numeroSala) {
        Sala sala = salaDao.procurarPorNumero(numeroSala);
        return sala != null;
    }
    
}
