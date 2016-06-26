package rn;

import Dao.DB.SessaoDaoDB;
import Dao.SessaoDao;
import exceptions.HourNotAvailable;
import exceptions.RNException;
import java.util.Date;
import java.util.List;
import model.Sessao;

/**
 *Classe que aplica as regras de negocio da entidade Sessão
 * e também faz requisiçoes para Dao
 */
public class SessaoRN {
    private SessaoDao sessaoDao;

    /**
     * Construtor
     */
    public SessaoRN() {
        sessaoDao = new SessaoDaoDB();
    }
    /**
     * verifica se foi preenchido todos os campos de criterio obrigatório
     *  -Verifica o filme
     *  -Verifica a sala
     *  -Verifica o horario
     * @param s : Objeto sessao que contem os dados
     * @throws RNException : lança exceção em caso alguns dos campos não seja informado
     */
    private void validarCamposObrigatorios(Sessao s) throws RNException{
        if(s.getFilme() == null ){
            throw new RNException("Campo filme não informado!");
        }
        if(s.getSala() == null ){
            throw new RNException("Campo Sala não informado!");
        }
        if(s.getHorario() == null){
            throw new RNException("Campo horário não informado!");
        }
    } 
    
    /**
     * Método que traz a diferença de tempo entre sessoes
     * @param sessaoParametro recebe por parametro um objeto do tipo sessão
     */
    public void verificaTempo(Sessao sessaoParametro) throws HourNotAvailable{
        List<Sessao> listaSessao ;
        listaSessao = this.listar();
        for(Sessao sessao: listaSessao){
            if(sessao.getSala().getNumeroSala() == sessaoParametro.getSala().getNumeroSala()){
                long diferencaHorasMile;
                diferencaHorasMile = sessao.getHorario().getTime() - sessaoParametro.getHorario().getTime();
                long diferencaHorasMinutos = (diferencaHorasMile/1000)/60;
                
                if(diferencaHorasMinutos > -180 && diferencaHorasMinutos < 180){
                        throw new HourNotAvailable();
                }
            }
        }
    }
    /**
     * Manda os dados para verificar se foram preenchidos os campos obrigatorios;
     * requisita a Dao para adicionar uma sessão no Banco de dados;
     * @param s : Objeto Sessao recebido da view
     * @throws RNException : Lança uma exceção em caso de alguma falha
     */
    public void adicionar(Sessao s) throws RNException, HourNotAvailable{
        this.validarCamposObrigatorios(s);
        this.verificaTempo(s);
        sessaoDao.adicionar(s);
    }
    
    /**
     * envia para a View uma lista de sessoes
     * @return : retorna uma lista
     */
    public List<Sessao> listar(){
        return (sessaoDao.listar());
    }
    
    /**
     * Verifica se o objeto sessao recebido não esta vazio ou null;
     * Requisita a DAO para excluir a sessao em questão
     * @param sessao : Objeto sessao recebido da View
     * @throws RNException : lança exceção caso a sessao não exista
     */
    public void deletar(Sessao sessao) throws RNException{
        if(sessao == null || sessao.getHorario() == null){
            throw new RNException("Sessão não Existe!");
            
        }
        sessaoDao.deletar(sessao);
    }
    
    /**
     * Verifica se o objeto sessao recebido não esta vazio ou null;
     * Manda os dados para verificar se foram preenchidos os campos obrigatorios;
     * Requisita a DAO para atualizar a sessao em questão
     * @param sessao :Objeto sessao recebido da View
     * @throws RNException : lança exceção caso ocorra uma falha
     */
    public void atualizar (Sessao sessao) throws RNException, HourNotAvailable{
        if(sessao==null || sessao.getHorario() ==null){
            throw new RNException("Sessão não existe!");
            
        }
        this.verificaTempo(sessao);
        this.validarCamposObrigatorios(sessao);
        sessaoDao.atualizar(sessao);
    }
     
   /**
     * Verifica se o parametro passado não está vazio
     * Faz uma pesquisa para verificar a existencia da sessao através do código informado
     * @param codigoSessao : còdigo recebido da view
     * @return : Retorna um objeto Sessao
     * @throws RNException : Lança uma exceção caso não seja informado o código ou se não for encontrada a sessao
     */
    public Sessao procurarPorId(int codigoSessao) throws RNException{
        if(codigoSessao <= 0){
            throw new RNException("Campo Código não informado!");
        }
        Sessao sessao = sessaoDao.procurarPorId(codigoSessao);
        if(sessao == null){
            throw new RNException("Sessão não Encontrada!");
        }
        return sessao;
    }   
    /**
     * Verifica se o horario recebido não esta vazio;
     * Faz uma pesquisa pra ver se existe algum objeto com o horario inserido
     * @param horario : Horario recebido por parametro
     * @return : Retorna uma lista 
     * @throws RNException : lança uma exceção caso Horario não seja informado
     */
    public List<Sessao> procuraPorHorario(Date horario) throws RNException{
        if(horario == null  ){
            throw  new RNException("Campo Horario não informada!");
        }
        return (sessaoDao.procurarPorHorario(horario));
    }
}


// 
//    /**
//     * Verifica se o filme recebido não esta vazio;
//     * Faz uma pesquisa pra ver se existe algum objeto com o filme inserido
//     * @param filme : Filme recebido por parametro
//     * @return : Retorna uma lista 
//     * @throws RNException : lança uma exceção caso Filme não seja informado
//     */
//    public List<Sessao> procuraPorFilme(Filme filme) throws RNException{
//        if(filme == null ){
//            throw  new RNException("Campo Filme não informado!");
//        }
//        return (sessaoDao.procurarPorFilme(filme));
//    }
//    
//    
//    /**
//     * Verifica se a sala recebida não esta vazia;
//     * Faz uma pesquisa pra ver se existe algum objeto com a sala inserida
//     * @param sala : Sala recebido por parametro
//     * @return : Retorna uma lista 
//     * @throws RNException : lança uma exceção caso Sala não seja informado
//     */
//    public List<Sessao> procuraPorSala(Sala sala) throws RNException{
//        if(sala == null || sala.getNumeroSala() <= 0 ){
//            throw  new RNException("Campo Sala não informada!");
//        }
//        return (sessaoDao.procurarPorSala(sala));
//    }
  