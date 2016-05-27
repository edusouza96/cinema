package Dao.DB;

import exceptions.DBException;
import Dao.FilmeDao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Filme;

public class FilmeDaoDB extends DaoDB<Filme> implements FilmeDao {
  

    @Override
    public void adicionar(Filme filme) {
        int id = 0;
        try {
            String sql = "INSERT INTO filme (nomeFilme, genero, sinopse) "
                    + "VALUES (?,?,?)";

            conectarObtendoId(sql);
            comando.setString(1, filme.getNomeFilme());
            comando.setString(2, filme.getGenero());
            comando.setString(3, filme.getSinopse());
            comando.executeUpdate();
            ResultSet resultado = comando.getGeneratedKeys();
            if (resultado.next()) {
                id = resultado.getInt(1);
                filme.setCodigoFilme(id);
            }
            else{
                System.err.println("Erro de Sistema - Nao gerou o id conforme esperado!");
                throw new DBException("Nao gerou o id conforme esperado!");
            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao salvar filme no Banco de Dados!");
            throw new RuntimeException(ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public void deletar(Filme filme) {
        try {
            String sql = "DELETE FROM filme WHERE codigoFilme = ?";

            conectar(sql);
            comando.setInt(1, filme.getCodigoFilme());
            comando.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao deletar filme no Banco de Dados!");
            throw new RuntimeException(ex);
        } finally {
            fecharConexao();
        }

    }

    @Override
    public void atualizar(Filme filme) {
        try {
            String sql = "UPDATE filme SET nomeFilme=?, genero=?, sinopse=? "
                    + "WHERE codigoFilme=?";

            conectar(sql);
            comando.setString(1, filme.getNomeFilme());
            comando.setString(2, filme.getGenero());
            comando.setString(3, filme.getSinopse());
            comando.setInt(4, filme.getCodigoFilme());
            comando.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao atualizar filme no Banco de Dados!");
            throw new RuntimeException(ex);
        } finally {
            fecharConexao();
        }

    }

    @Override
    public List<Filme> listar() {
        List<Filme> listaFilmes = new ArrayList<>();

        String sql = "SELECT * FROM filme";

        try {
            conectar(sql);

            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int codigoFilme = resultado.getInt("codigoFilme");
                String nomeFilme = resultado.getString("nomeFilme");
                String genero = resultado.getString("genero");
                String sinopse = resultado.getString("sinopse");
                

                Filme filme = new Filme(codigoFilme, nomeFilme, genero, sinopse);

                listaFilmes.add(filme);

            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar os filmes no Banco de Dados!");
            throw new RuntimeException(ex);
        } finally {
            fecharConexao();
        }

        return (listaFilmes);
    }

    @Override
    public Filme procurarPorId(int id) {
        String sql = "SELECT * FROM filme WHERE id = ?";

        try {
            conectar(sql);
            comando.setInt(1, id);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                String nomeFilme = resultado.getString("nomeFilme");
                String genero = resultado.getString("genero");
                String sinopse = resultado.getString("sinopse");
               

                Filme filme = new Filme(id, nomeFilme, genero, sinopse);

                return filme;

            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar o filme pelo Codigo no Banco de Dados!");
            throw new RuntimeException(ex);
        } finally {
            fecharConexao();
        }

        return (null);
    }

    @Override
    public Filme procurarPorNome(String nome) {
        String sql = "SELECT * FROM filme WHERE nomeFilme = ?";

        try {
            conectar(sql);
            comando.setString(1, nome);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                int codigoFilme = resultado.getInt("codigoFilme");
                String genero = resultado.getString("genero");
                String sinopse = resultado.getString("sinopse");
                
                Filme filme = new Filme(codigoFilme, nome, genero, sinopse);

                return filme;

            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar o filme pelo Nome no Banco de Dados!");
            throw new RuntimeException(ex);
        } finally {
            fecharConexao();
        }

        return (null);
    }

    @Override
    public List<Filme> procurarPorGenero(String genero) {
        List<Filme> listaFilmes = new ArrayList<>();
        String sql = "SELECT * FROM filme WHERE genero LIKE ?";

        try {
            conectar(sql);
            comando.setString(1, "%" + genero + "%");
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int codigoFilme = resultado.getInt("codigoFilme");
                String nomeFilme = resultado.getString("nomeFilme");
                String generoF = resultado.getString("genero");
                String sinopse = resultado.getString("sinopse");
                
                Filme filme = new Filme(codigoFilme, nomeFilme, generoF, sinopse);

                listaFilmes.add(filme);

            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar os filmes pelo genero no Banco de Dados!");
            throw new RuntimeException(ex);
        } finally {
            fecharConexao();
        }
        return (listaFilmes);
    }
}
