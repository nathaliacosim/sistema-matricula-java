package conexao;
import java.sql.*;
import javax.swing.JOptionPane;

public class Conexao {
    public Connection conexao;
    private final String url = "jdbc:mysql://localhost:3306/db_prova?zeroDateTimeBehavior=convertToNull&useSSL=false&serverTimezone=UTC";
    private final String usuario = "root";
    private final String senha = "root";
    private final String driver = "com.mysql.cj.jdbc.Driver";
    
    public Connection getConnection(){
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, usuario, senha);
        } catch (ClassNotFoundException drive) {
            JOptionPane.showMessageDialog(null, "Driver não encontrado! >> " + drive);
        } catch (SQLException sql) {
            JOptionPane.showMessageDialog(null, "Fonte de dados nao encontrada! >> " + sql);
        }
        return conexao;
    }
}
