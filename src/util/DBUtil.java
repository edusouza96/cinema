package util;

import exceptions.RTException;
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
    private final static String SENHA = "usbw";//usbw no senac
    
    public static Connection getConnection(){
        Connection conexao = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            
        } catch (ClassNotFoundException ex) {
            try {
                throw new RTException("Erro de Sistema - Classe do Driver Nao Encontrada!");
            } catch (RTException ex1) {
               
            }
            
        } catch (SQLException ex) {
            
            try {
                throw new RTException("Erro de Sistema - Problema na conexão do banco de dados");
            } catch (RTException ex1) {
               
            }
        }
        return(conexao);
    }
    
}
