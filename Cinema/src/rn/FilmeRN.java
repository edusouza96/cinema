package rn;

import Dao.DB.FilmeDaoDB;
import Dao.FilmeDao;
import exceptions.RNException;
import java.util.List;
import model.Filme;

/**
 * Classe que aplica as regras de negocio da entidade Filme
 * e também faz requisiçoes para Dao
 */
public class FilmeRN {
    private FilmeDao filmeDao;
    
    /**
     * Construtor
     */
    public FilmeRN(){
        filmeDao = new FilmeDaoDB();
    }
    
    /**
     * Manda os dados para verificar se foram preenchidos os campos obrigatorios;
     * Manda valida se já foi cadastrado o filme em questão;
     * requisita a Dao para adicionar um filme no Banco de dados;
     * @param f : Objeto Filme recebido da view
     * @throws RNException : Lança uma exceção em caso de alguma falha
     */
    public void adicionar(Filme f) throws RNException{
        this.validarCamposObrigatorios(f);
        this.validarNomeExistente(f);
        filmeDao.adicionar(f);
    }
    /**
     * envia para a View uma lista de filmes
     * @return : retorna uma lista
     */
    public List<Filme> listar(){
        return (filmeDao.listar());
    }
    
    /**
     * Verifica se o objeto filme recebido não esta vazio ou null;
     * Requisita a DAO para excluir o filme em questão
     * @param filme : Objeto filme recebido da View
     * @throws RNException : lança exceção caso ocorra uma falha
     */
    public void deletar(Filme filme) throws RNException{
        if(filme == null || filme.getNomeFilme() == null){
            throw new RNException("Filme não Existe!");
            
        }
        filmeDao.deletar(filme);
    }
    
    /**
     * Verifica se o objeto filme recebido não esta vazio ou null;
     * Manda os dados para verificar se foram preenchidos os campos obrigatorios;
     * Requisita a DAO para atualizar o filme em questão
     * @param filme :Objeto filme recebido da View
     * @throws RNException : lança exceção caso ocorra uma falha
     */
    public void atualizar (Filme filme) throws RNException{
        if(filme==null || filme.getNomeFilme() ==null){
            throw new RNException("Filme não existe!");
            
        }
        this.validarCamposObrigatorios(filme);
        filmeDao.atualizar(filme);
    }
    
    /**
     * Verifica se o nome do filme recebido não esta vazio;
     * Faz uma pesquisa pra ver se existe algum objeto com o nome do filme inserido
     * @param nome : Nome recebido por parametro
     * @return : Retorna um objeto Filme
     * @throws RNException : lança uma exceção caso ocorra uma falha
     */
    public Filme procuraPorNome(String nome) throws RNException{
        if(nome == null || nome.isEmpty()){
            throw new RNException("Campo Nome não informado!");
        }
        Filme filme = filmeDao.procurarPorNome(nome);
        if(filme == null){
            throw  new RNException("Filme não encontrado!");
        }
        return (filme);
    }
    
    /**
     * Verifica se o parametro passado não está vazio
     * Faz uma pesquisa para verificar a existencia do filme através do código informado
     * @param codigoFilme : còdigo recebido da view
     * @return : Retorna um objeto Filme
     * @throws RNException : Lança uma exceção caso não seja informado o código ou se não for encontrado o filme
     */
    public Filme procurarPorId(int codigoFilme) throws RNException{
        if(codigoFilme <= 0){
            throw new RNException("Campo Código não informado!");
        }
        Filme filme = filmeDao.procurarPorId(codigoFilme);
        if(filme == null){
            throw new RNException("Filme não Encontrado!");
        }
        return filme;
    }
    
    
    /**
     * Verifica se o genero do filme recebido não esta vazio;
     * Faz uma pesquisa pra ver se existe algum objeto com o genero do filme inserido
     * @param genero : Genero recebido por parametro
     * @return : Retorna uma lista de filmes do genero fornecido
     * @throws RNException : lança uma exceção caso ocorra um erro
     */
    public List<Filme> procuraPorGenero(String genero) throws RNException{
        if(genero == null || genero.isEmpty()){
            throw  new RNException("Campo Genero não informado!");
        }
        return (filmeDao.procurarPorGenero(genero));
    }
    
    /**
     * Verifica se a partir de um nome de filme, existe um objeto que contenha o filme procurado
     * @param nome: nome do filme recebido por parametro
     * @return :true ou false a respeito se existe o filme
     */
    public boolean filmeExiste(String nome){
        Filme filme = filmeDao.procurarPorNome(nome);
        return (filme != null);
    }
    
    /**
     * verifica se foi preenchido todos os campos de criterio obrigatório
     *  -Verifiva o nome
     *  -Verifca o genero
     * @param f : Objeto filme que contem os dados
     * @throws RNException : lança exceção em caso de falha
     */
    private void validarCamposObrigatorios(Filme f) throws RNException{
        if(f.getNomeFilme() == null || f.getNomeFilme().isEmpty()){
            throw new RNException("Campo nome do filme não informado!");
        }
        if(f.getGenero() == null || f.getGenero().isEmpty()){
            throw new RNException("Campo Genero não informado!");
            
        }
    }
    
    /**
     * Verifica se ja foi cadastrado algum filme contendo o mesmo nome
     * @param f : Objeto filme a ser verificado
     * @throws RNException : lança excção caso exista o filme
     */
    private void validarNomeExistente(Filme f) throws RNException{
        if(filmeExiste(f.getNomeFilme())){
            throw new RNException("Filme já existente!");
        }
    }
}
