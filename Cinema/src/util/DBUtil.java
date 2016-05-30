package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author lhries
 */
public class DBUtil {
    private final static String HOST = "localhost";
    private final static String PORT = "3307";
    private final static String BD = "cinema_java";
    private final static String URL = "jdbc:mysql://"+HOST+":"+PORT+"/"+BD;
    private final static String USUARIO = "root";
    private final static String SENHA = "";//usbw no senac
    
    public static Connection getConnection(){
        Connection conexao = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            
        } catch (ClassNotFoundException ex) {
            System.err.println("Erro de Sistema - Classe do Driver Nao Encontrada!");
            throw new RuntimeException(ex);
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema na conexão do banco de dados");
            throw new RuntimeException(ex);
        }
        return(conexao);
    }
    
}
