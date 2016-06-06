/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao.DB;

import Dao.VendaDao;
import exceptions.DBException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import model.Venda;
import util.DateUtil;

/**
 *Classe que implementa os métodos para manipular os dados na base de dados
 */
public class VendaDaoDB extends DaoDB<Venda> implements VendaDao{

    /**
     * Responsavel por fazer o registro da venda
     * @param venda, objeto recebido para realizar a venda
     */
    @Override
    public void adicionar(Venda venda) {
        int id=0;
         try{
            
            String sql = "INSERT INTO venda ( sessao_codigoSessao, data) VALUES (?,?)";
            conectarObtendoId(sql);
            String data = DateUtil.dateToStringEUA(venda.getData());
            
            comando.setInt(1, venda.getFilmeSalaSessao().getCodigoSessao());
            comando.setString(2, data);
            comando.executeUpdate();
            ResultSet resultado = comando.getGeneratedKeys();
            if(resultado.next()){
                id = resultado.getInt(1);
                venda.setRegistroVenda(id);
            }else{
                System.err.println("Erro de Sistema - Nao gerou o id conforme esperado!");
                throw new DBException("Nao gerou o id conforme esperado!");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Problema ao registrar a venda ", "Erro no Sistema", ERROR_MESSAGE);
            throw  new RuntimeException(ex);
        }finally{
            fecharConexao();
        }
    }

    @Override
    public List<Venda> listar() {
        List<Venda> listaVendas = new ArrayList<>();
        String sql = "SELECT * FROM venda ";
        try{
            conectar(sql);
            ResultSet resultado = comando.executeQuery();
            while(resultado.next()){
                int registroVenda = resultado.getInt("registroVenda");
                int sessao_codigoSessao = resultado.getInt("sessao_codigoSessao");
                Date date = resultado.getDate("data");
                SessaoDaoDB sessao = new SessaoDaoDB();
                
                Venda venda = new Venda(registroVenda,(sessao.procurarPorId(registroVenda)), date);
                listaVendas.add(venda);
            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar as vendas!");
            throw new RuntimeException(ex);
        } finally {
            fecharConexao();
        }
        return (listaVendas);
    }

    /**
     * Taz uma venda através do id, que nesse caso é o registro de venda
     * @param id registro de venda recebido
     * @return retorna um objeto venda
     */
    @Override
    public Venda procurarPorId(int id) {
         String sql = "SELECT * FROM venda WHERE registroVenda = ?  ";
        try{
            conectar(sql);
            comando.setInt(1, id);
            ResultSet resultado = comando.executeQuery();
            if(resultado.next()){
                int registroVenda = resultado.getInt("registroVenda");
                int sessao_codigoSessao = resultado.getInt("sessao_codigoSessao");
                Date date = resultado.getDate("data");
                SessaoDaoDB sessao = new SessaoDaoDB();
                Venda venda = new Venda(registroVenda,(sessao.procurarPorId(registroVenda)), date);
                return venda;
            }
        }catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar a venda!");
            throw new RuntimeException(ex);
        } finally {
            fecharConexao();
        }

        return (null);
    }

    /**
     * Serveria para atualizar mas no caso desta entidade não é necessario
     * @param venda 
     */
    @Override
    public void atualizar(Venda venda) {
        throw new UnsupportedOperationException("Not supported yet."); //Metodo inutil aqui
    }

    /**
     * Serveria para deletar, mas nessa caso a entidade não necessita deste método
     * @param venda 
     */
    @Override
    public void deletar(Venda venda) {
        throw new UnsupportedOperationException("Not supported yet."); //Metodo inutil aqui
    }
    
}
