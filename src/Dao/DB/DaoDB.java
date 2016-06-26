package Dao.DB;

import exceptions.DBException;
import util.DBUtil;
import Dao.Dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Classe abstrata que tem os métodos de comando de banco
 * @param <T> 
 */
public abstract class DaoDB<T> implements Dao<T>{
    protected Connection conexao;
    protected PreparedStatement comando;

    /**
     * método que inicia uma conexão com banco
     * @param sql : recebe um query a ser executada
     * @return :retorna o link e os acessos acessos a base de dados
     * @throws SQLException :lança uma excessão caso aconteça um erro no procedimento  
     */
    public Connection conectar(String sql) throws SQLException {
        conexao = DBUtil.getConnection();
        comando = conexao.prepareStatement(sql);
        return conexao;
    }

    /**
     *  método que inicia uma conexão com banco, e gera automaticamente uma ID para a row de dados
     * @param sql :recebe um query a ser executada
     * @throws SQLException : lança uma excessão caso aconteça um erro no procedimento  
     */
    public void conectarObtendoId(String sql) throws SQLException {
        conexao = DBUtil.getConnection();
        comando = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
    }

    /**
     * Metodo que encerra a conexão com banco de dados
     */
    public void fecharConexao() {
        try {
            if (comando != null) {
                comando.close();
            }
            if (conexao != null) {
                conexao.close();
            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Erro ao encerrar a conexao");
            throw new DBException(ex);

        }

    }
    
}
