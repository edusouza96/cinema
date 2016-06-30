package Dao.DB;

import Dao.AssentoDao;
import exceptions.DBException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import model.Assento;
import util.DateUtil;

/**
 * Classe que implementa os métodos para manipular os dados na base de dados
 */
public class AssentoDaoDB extends DaoDB<Assento> implements AssentoDao{

    /**
     * faz uma consulta com base em uma e um codigo de sessao
     * @param data  Uma data do dia que queira fazer busca
     * @param codigo Código da sessão que quer buscar
     * @return retorna um objeto do tipo Assento
     */
    @Override
    public Assento consultarPorDataCodigo(java.util.Date data, int codigo) {
        String sql = "SELECT * FROM assento WHERE sessao_codigoSessao = ? AND data = ?";
        
        try{
            conectar(sql);
            comando.setInt(1, codigo);
            comando.setString(2, DateUtil.dateToStringEUA(data));
            ResultSet resultado = comando.executeQuery();
            
            if(resultado.next()){
                int codigoAssento = resultado.getInt("codigoAssento");
                int sessao_codigoSessao = resultado.getInt("sessao_codigoSessao");
                int assentosLivres = resultado.getInt("assentosLivres");
                String date = resultado.getString("data");
         
                SessaoDaoDB sessao = new SessaoDaoDB();
                Assento assento = new Assento(codigoAssento,(sessao.procurarPorId(sessao_codigoSessao)), assentosLivres, data);
                System.out.println(assento);
                return assento;
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Problema de localização", "Erro no Sistema", ERROR_MESSAGE);
        } finally{
            fecharConexao();
        }
         return null;
            
    }

    /**
     * Faz a inserção dos dados para o banco de dados
     * @param assento : Objeto assento recebido da venda
     */
    @Override
    public void adicionar(Assento assento) {
        int id=0;
        try{
            String sql = "INSERT INTO assento (sessao_codigoSessao,assentosLivres,data)"
                    + "VALUES (?,?,?)";
            conectarObtendoId(sql);
            String data = DateUtil.dateToStringEUA(assento.getData());
            comando.setInt(1, assento.getSessao().getCodigoSessao());
            comando.setInt(2, assento.getAssentoLivres());
            comando.setString(3, data);
            comando.executeUpdate();
            ResultSet resultado = comando.getGeneratedKeys();
            if(resultado.next()){
                id = resultado.getInt(1);
                assento.setCodigoAssento(id);
            }else{
                System.err.println("Erro de Sistema - Nao gerou o id conforme esperado!");
                throw new DBException("Nao gerou o id conforme esperado!");
            }
        }catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema para disponibilizar assento!");
        } finally {
            fecharConexao();
        }
    }

    /**
     * Lista os acentos rigistrados
     * @return retorna um array de assentos
     */
    @Override
    public List<Assento> listar() {
        List<Assento> listaAssentos = new ArrayList<>();
        String sql = "SELECT * FROM assento ";
        try{
            conectar(sql);
            ResultSet resultado = comando.executeQuery();
            while(resultado.next()){
                int codigoAssento = resultado.getInt("codigoAssento");
                int sessao_codigoSessao = resultado.getInt("sessao_codigoSessao");
                int assentosLivres = resultado.getInt("assentosLivres");
                Date date = resultado.getDate("data");
                SessaoDaoDB sessao = new SessaoDaoDB();
                
                Assento assento = new Assento(codigoAssento,(sessao.procurarPorId(sessao_codigoSessao)),assentosLivres,date);
                listaAssentos.add(assento);
            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar os lugares!");
        } finally {
            fecharConexao();
        }
        return (listaAssentos);
    }

    /**
     * Faz uma busca no banco procurando através da condição do codigo
     * @param id parametro á utilizar para procurar o assento
     * @return Retorna o objeto encontrado
     */
    @Override
    public Assento procurarPorId(int id) {
        String sql = "SELECT * FROM assento WHERE codigoAssento = ?  ";
        try{
            conectar(sql);
            comando.setInt(1, id);
            ResultSet resultado = comando.executeQuery();
            if(resultado.next()){
                int codigoAssento = resultado.getInt("codigoAssento");
                int sessao_codigoSessao = resultado.getInt("sessao_codigoSessao");
                int assentosLivres = resultado.getInt("assentosLivres");
                Date date = resultado.getDate("data");
                SessaoDaoDB sessao = new SessaoDaoDB();
                Assento assento = new Assento(codigoAssento,(sessao.procurarPorId(sessao_codigoSessao)), assentosLivres, date);
                return assento;
            }
        }catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar os lugares!");
        } finally {
            fecharConexao();
        }

        return (null);
    }

    /**
     * Atualiza a quantidade de assentos disponiveis
     * @param assento janela
     */
    @Override
    public void atualizar(Assento assento) {
         try {
             String sql = "UPDATE assento SET assentosLivres=? WHERE codigoAssento = ?";
            conectar(sql);
            comando.setInt(1, assento.getAssentoLivres());
            comando.setInt(2, assento.getCodigoAssento());
           
            comando.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao atualizar o numero de assentos disponiveis!");
        } finally {
            fecharConexao();
        }
    }

    /**
     * Remove o objeto Assento, possivelmente não será utilizada
     * @param assento objeto assento recebido para ser excluido
     */
    @Override
    public void deletar(Assento assento) {
         String sql = "DELETE FROM assento WHERE codigoAssento = ?";
        try {
            conectar(sql);
            comando.setInt(1, assento.getCodigoAssento());
            comando.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao deletar!");
        } finally {
            fecharConexao();
        }
        
    }
    
}
