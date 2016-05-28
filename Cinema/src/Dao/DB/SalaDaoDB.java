package Dao.DB;

import Dao.SalaDao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import model.Sala;

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

    @Override
    public void adicionar(Sala sala) {
        try{
            String sql = "INSERT INTO sala (numeroSala, qunatidadeSala)"
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

    @Override
    public List<Sala> listar() {
        List<Sala> listaSalas = new ArrayList<>();
        String sql = "SELECT * FROM sala";
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
        return null;
    }

    @Override
    public Sala procurarPorId(int id) {
        return null;//não utilizado
    }

    @Override
    public void atualizar(Sala sala) {
        try{
            String sql = "UPDATE sala  SET  numeroSala=?, quantidadeSala=?"
                    +"WHERE numeroSala=?";
            conectar(sql);
            comando.setInt(1, sala.getNumeroSala());
            comando.setInt(2, sala.getQuantidadeSala());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Problema ao atualizar a sala ", "Erro no Sistema", ERROR_MESSAGE);
            throw  new RuntimeException(ex);
        }finally{
            fecharConexao();
        }
    }

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
