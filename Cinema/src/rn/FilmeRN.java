package rn;

import Dao.DB.FilmeDaoDB;
import Dao.FilmeDao;
import exceptions.RNException;
import java.util.List;
import model.Filme;


public class FilmeRN {
    private FilmeDao filmeDao;
    
    public FilmeRN(){
        filmeDao = new FilmeDaoDB();
    }
    
    public void adicionar(Filme f) throws RNException{
       // this.validarCamposObrigatorios(f);
        //this.validarNomeExistente(f);
        filmeDao.adicionar(f);
    }
    public List<Filme> listar(){
        return (filmeDao.listar());
    }
    
    public void deletar(Filme filme) throws RNException{
        if(filme == null || filme.getNomeFilme() == null){
            throw new RNException("Filme não Existe!");
            
        }
        filmeDao.deletar(filme);
    }
    public void atualizar (Filme filme) throws RNException{
        if(filme==null || filme.getNomeFilme() ==null){
            throw new RNException("Paciente não existe!");
            
        }
        this.validarCamposObrigatorios(filme);
        filmeDao.atualizar(filme);
    }
    
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
    
    public List<Filme> procuraPorGenero(String genero) throws RNException{
        if(genero == null || genero.isEmpty()){
            throw  new RNException("Campo Genero não informado!");
        }
        return (filmeDao.procurarPorGenero(genero));
    }
    public boolean filmeExiste(String nome){
        Filme filme = filmeDao.procurarPorNome(nome);
        return (filme != null);
    }
    
    private void validarCamposObrigatorios(Filme f) throws RNException{
        if(f.getNomeFilme() == null || f.getNomeFilme().isEmpty()){
            throw new RNException("Campo nome do filme não informado!");
        }
        if(f.getGenero() == null || f.getGenero().isEmpty()){
            throw new RNException("Campo Genero não informado!");
            
        }
    }
    
    private void validarNomeExistente(Filme f) throws RNException{
        if(filmeExiste(f.getNomeFilme())){
            throw new RNException("Filme já existente!");
        }
    }
}
