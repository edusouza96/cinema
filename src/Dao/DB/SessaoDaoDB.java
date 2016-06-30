package Dao.DB;

import Dao.SessaoDao;
import exceptions.DBException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Filme;
import model.Sala;
import model.Sessao;
import util.DateUtil;

/**
 * Classe que implementa os métodos para manipular os dados na base de dados
 */
public class SessaoDaoDB extends DaoDB<Sessao> implements SessaoDao{

    /**
     * Método que envia os dados para o banco de dados
     * @param sessao: um objeto sessao que contem as informações a ser enviadas ao banco
     */
    @Override
    public void adicionar(Sessao sessao) {
        int id=0;
        try{
            String sql = "INSERT INTO sessao (sala_numeroSala,filme_codigoFilme,horario)"
                    + "VALUES (?,?,?)";
            conectarObtendoId(sql);
            String hora = DateUtil.hourToString(sessao.getHorario());
            comando.setInt(1, sessao.getSala().getNumeroSala());
            comando.setInt(2, sessao.getFilme().getCodigoFilme());
            comando.setString(3, hora);
            comando.executeUpdate();
            ResultSet resultado = comando.getGeneratedKeys();
            if(resultado.next()){
                id = resultado.getInt(1);
                sessao.setCodigoSessao(id);
            }else{
                System.err.println("Erro de Sistema - Nao gerou o id conforme esperado!");
                throw new DBException("Nao gerou o id conforme esperado!");
            }
        }catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao salvar Sessão no Banco de Dados!");
            throw new RuntimeException(ex);
        } finally {
            fecharConexao();
        }
    }

     /**
     * Metodo que lista as sessoes do banco
     * @return : Traz uma lista de sessões
     */
    @Override
    public List<Sessao> listar() {
        List<Sessao> listaSessoes = new ArrayList<>();
        String sql = "SELECT codigoSessao,horario,numeroSala,quantidadeSala,codigoFilme,nomeFilme,genero,sinopse FROM sessao,sala,filme WHERE sessao.filme_codigoFilme = filme.codigoFilme AND sessao.sala_numeroSala = sala.numeroSala";
        try{
            conectar(sql);
            ResultSet resultado = comando.executeQuery();
            while(resultado.next()){
                int codigoSessao = resultado.getInt("codigoSessao");
                Date horario = resultado.getTime("horario");
                int numeroSala = resultado.getInt("numeroSala");
                int quantidadeSala = resultado.getInt("quantidadeSala");
                int codigoFilme = resultado.getInt("codigoFilme");
                String nomeFilme = resultado.getString("nomeFilme");
                String genero = resultado.getString("genero");
                String sinopse = resultado.getString("sinopse");
                
                Sala sala = new Sala(numeroSala, quantidadeSala);

                Filme filme = new Filme(codigoFilme,nomeFilme, genero, sinopse);
                
                Sessao sessao = new Sessao(codigoSessao,sala,filme,horario);
                listaSessoes.add(sessao);
            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar as sessões no Banco de Dados!");
            throw new RuntimeException(ex);
        } finally {
            fecharConexao();
        }
        return (listaSessoes);
    }

     /**
     * Metodo que devolve um objeto de sessoes de acordo com o ID passado
     * @param id: ID para buscar uma linha de dados no banco
     * @return : Retorna um objeto do tipo sessao de acordo com o ID fornecido
     */
    @Override
    public Sessao procurarPorId(int id) {
        String sql = "SELECT codigoSessao,horario,numeroSala,quantidadeSala,codigoFilme,nomeFilme,genero,sinopse FROM sessao,sala,filme WHERE codigoSessao = ?  and filme_codigoFilme = codigoFilme and sala_numeroSala = numeroSala";
        try{
            conectar(sql);
            comando.setInt(1, id);
            ResultSet resultado = comando.executeQuery();
            if(resultado.next()){
                int codigoSessao = resultado.getInt("codigoSessao");
                Date horario = resultado.getTime("horario");
                int numeroSala = resultado.getInt("numeroSala");
                int quantidadeSala = resultado.getInt("quantidadeSala");
                int codigoFilme = resultado.getInt("codigoFilme");
                String nomeFilme = resultado.getString("nomeFilme");
                String genero = resultado.getString("genero");
                String sinopse = resultado.getString("sinopse");
                
                Sala sala = new Sala(numeroSala, quantidadeSala);

                Filme filme = new Filme(codigoFilme,nomeFilme, genero, sinopse);
                
                Sessao sessao = new Sessao(codigoSessao,sala,filme,horario);
                return sessao;
            }
        }catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar a sessao pelo Codigo no Banco de Dados!");
            throw new RuntimeException(ex);
        } finally {
            fecharConexao();
        }

        return (null);
    }

     /**
     * metodo que atualiza os dados no banco
     * @param sessao : Um objeto que tem os novos dados a sereem atualizados
     */
    @Override
    public void atualizar(Sessao sessao) {
       
        try {
             String sql = "UPDATE sessao SET sala_numeroSala=?, filme_codigoFilme=?, horario=? WHERE codigoSessao = ?";
            conectar(sql);
            String hora = DateUtil.hourToString(sessao.getHorario());
            comando.setInt(1, sessao.getSala().getNumeroSala());
            comando.setInt(2, sessao.getFilme().getCodigoFilme());
            comando.setString(3, hora);
            comando.setInt(4, sessao.getCodigoSessao());
            comando.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao atualizar sessao no Banco de Dados!");
            throw new RuntimeException(ex);
        } finally {
            fecharConexao();
        }
    }

    /**
     * método que deleta uma linha de dados do banco de dados
     * @param sessao: Um objeto que contem o ID para deletar uma sessao do banco de dados
     */
    @Override
    public void deletar(Sessao sessao) {
        String sql = "DELETE FROM sessao WHERE codigoSessao = ?";
        try {
            conectar(sql);
            comando.setInt(1, sessao.getCodigoSessao());
            comando.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao deletar Sessao no Banco de Dados!");
            throw new RuntimeException(ex);
        } finally {
            fecharConexao();
        }
       
    }

    
    /**
     * Metodo que devolve um lista de sessoes de acordo com o parametro passado
     * @param horarioParam: Para buscar registros no dados no banco
     * @return : Retorna uma lista do tipo sessao 
     */
    @Override
    public List<Sessao> procurarPorHorario(Date horarioParam) {
        List<Sessao> listaSessoes = new ArrayList<>();
        String sql = "SELECT codigoSessao,horario,numeroSala,quantidadeSala,codigoFilme,nomeFilme,genero,sinopse FROM sessao,sala,filme WHERE horario >= ?  and filme_codigoFilme = codigoFilme and sala_numeroSala = numeroSala";
                
        try{
            conectar(sql);
            String hora = DateUtil.hourToString(horarioParam);
            
            comando.setString(1, hora);
            ResultSet resultado = comando.executeQuery();
            while(resultado.next()){
                int codigoSessao = resultado.getInt("codigoSessao");
                Date horario = resultado.getDate("horario");
                int numeroSala = resultado.getInt("numeroSala");
                int quantidadeSala = resultado.getInt("quantidadeSala");
                int codigoFilme = resultado.getInt("codigoFilme");
                String nomeFilme = resultado.getString("nomeFilme");
                String genero = resultado.getString("genero");
                String sinopse = resultado.getString("sinopse");
                
                Sala sala = new Sala(numeroSala, quantidadeSala);

                Filme filme = new Filme(codigoFilme,nomeFilme, genero, sinopse);
                
                Sessao sessao = new Sessao(codigoSessao,sala,filme,horario);
                listaSessoes.add(sessao);
            }
        }catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar as sessoes pela Sala no Banco de Dados!");
            throw new RuntimeException(ex);
        } finally {
            fecharConexao();
        }
        return (listaSessoes);
    }
}

