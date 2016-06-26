package Dao.DB;

import Dao.SalaDao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import model.Sala;

/**
 * Classe que implementa os métodos para manipular os dados na base de dados
 */
public class SalaDaoDB extends DaoDB<Sala> implements SalaDao{

    @Override
    public Sala procurarPorNumero(int numero) {
        String sql = "SELECT * FROM sala WHERE numeroSala = ? ";
        try{
            conectar(sql);
            comando.setInt(1, numero);
            ResultSet resultado = comando.executeQuery();
            
            if(resultado.next()){
                int quantidadeSala = resultado.getInt("quantidadeSala");
                Sala sala = new Sala(numero, quantidadeSala);
                return sala;
            }
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Problema ao localizar a sala pelo número", "Erro no Sistema", ERROR_MESSAGE);
            throw  new RuntimeException(ex);
        }finally{
            fecharConexao();
        }
        return null;
    }

    /**
     * Método que envia os dados para o banco de dados
     * @param sala: um objeto sala que contem as informações a ser enviadas ao banco
     */
    @Override
    public void adicionar(Sala sala) {
        try{
            String sql = "INSERT INTO sala (numeroSala, quantidadeSala)"
                    + "VALUES (?,?)";
            conectar(sql);
            comando.setInt(1, sala.getNumeroSala());
            comando.setInt(2, sala.getQuantidadeSala());
            comando.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Problema ao salvar a sala ", "Erro no Sistema", ERROR_MESSAGE);
            throw  new RuntimeException(ex);
        }finally{
            fecharConexao();
        }
                
    }

    /**
     * Metodo que lista as salas do banco
     * @return : Traz uma lista de salas
     */
    @Override
    public List<Sala> listar() {
        List<Sala> listaSalas = new ArrayList<>();
        String sql = "SELECT * FROM sala order by numeroSala";
        try{
            conectar(sql);
            ResultSet resultado = comando.executeQuery();
            
            while(resultado.next()){
                int numeroSala = resultado.getInt("numeroSala");
                int quantidadeSala = resultado.getInt("quantidadeSala");
                
                Sala sala = new Sala(numeroSala, quantidadeSala);
                listaSalas.add(sala);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Problema ao buscar as sala ", "Erro no Sistema", ERROR_MESSAGE);
            throw  new RuntimeException(ex);
        }finally{
            fecharConexao();
        }
        return (listaSalas);
    }

    /**
     * Este Método não é utilizado pois a sala não tem ID definido e é numero da sala o 
     * que torna um objeto unico
     * @param id receberia ID se houver
     * @return retorna um objeto do tipo sala
     */
    @Override
    public Sala procurarPorId(int id) {
        return null;//não utilizado
    }

    /**
     * metodo que atualiza os dados no banco
     * @param sala : Um objeto que tem os novos dados a serem atualizados
     */
    @Override
    public void atualizar(Sala sala) {
        try{
            String sql = "UPDATE sala  SET  quantidadeSala=? WHERE numeroSala=?";
            conectar(sql);
            comando.setInt(1, sala.getQuantidadeSala());
            comando.setInt(2, sala.getNumeroSala());
            comando.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Problema ao atualizar a sala ", "Erro no Sistema", ERROR_MESSAGE);
            throw  new RuntimeException(ex);
        }finally{
            fecharConexao();
        }
    }

    /**
     * método que deleta uma linha de dados do banco de dados
     * @param sala: Um objeto que contem o ID para deletar uma sala do banco de dados
     */
    @Override
    public void deletar(Sala sala) {
        try{
            String sql = "DELETE FROM sala WHERE numeroSala = ?";
            conectar(sql);
            comando.setInt(1, sala.getNumeroSala());
            comando.executeUpdate();
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Problema ao deletar a sala ", "Erro no Sistema", ERROR_MESSAGE);
            throw  new RuntimeException(ex);
        }finally{
            fecharConexao();
        }
    }
    
}
