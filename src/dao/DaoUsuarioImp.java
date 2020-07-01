package dao;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Usuario;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.UsuarioReport;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class DaoUsuarioImp implements DaoUsuario {
    Connection conexao = null;
    PreparedStatement pstm = null;
    
    @Override
    public void salvarUsuario(Usuario u) {
        conexao = new Conexao().getConnection();
        String sqlinsert = "INSERT INTO tb_usuario(nome, email, senha) values(?,?,?)";
        try {
            pstm = conexao.prepareStatement(sqlinsert, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            pstm.setString(1, u.getNome());
            pstm.setString(2, u.getEmail());
            pstm.setString(3, u.getSenha());
            pstm.execute();
            pstm.close();
        } catch (SQLException insert) {
            System.out.println("Erro: " + insert);
        } finally {
            try {
                conexao.close();
            } catch (SQLException insertclose) {
                System.out.println("Erro: " + insertclose);
            }
        }
    }

    @Override
    public void editarUsuario(Usuario u) {
        conexao = new Conexao().getConnection();
        String query = "UPDATE tb_usuario SET nome=?, email=?, senha=? WHERE id=?";
        try {
            pstm = conexao.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            pstm.setString(1, u.getNome());
            pstm.setString(2, u.getEmail());
            pstm.setString(3, u.getSenha());
            pstm.setInt(4, u.getId());
            pstm.execute();
            pstm.close();
        } catch (SQLException update) {
            System.out.println("Erro: " + update);
        } finally {
            try {
                conexao.close();
            } catch (SQLException updateclose) {
                System.out.println("Erro: " + updateclose);
            }
        }
    }

    @Override
    public void deletarUsuario(int id) {
        conexao = new Conexao().getConnection();
        String query = "DELETE FROM tb_usuario WHERE id=?";
        try {
            pstm = conexao.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            pstm.setInt(1, id);
            pstm.execute();
            pstm.close();
        } catch (SQLException delete) {
            System.out.println("Erro: " + delete);
        } finally {
            try {
                conexao.close();
            } catch (SQLException deleteclose) {
                System.out.println("Erro: " + deleteclose);
            }
        }
    }

    @Override
    public List<Usuario> getUsuarios() {
        
        ResultSet rs = null;
        List<Usuario> lista = new ArrayList<Usuario>();
        conexao = new Conexao().getConnection();
        String query = "SELECT * FROM tb_usuario";
        
        try {
            pstm = conexao.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rs = pstm.executeQuery();
            rs.first();
            do {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));

                lista.add(usuario);

            } while(rs.next());
            
        } catch (SQLException errolistar) {
            System.out.println("Erro: " + errolistar);
        } finally {
            try {
                conexao.close();
            } catch (SQLException errofechar) {
                System.out.println("Erro: " + errofechar);
            }
        }
        return lista;
    }
    
    public void criarUsuarioPadrao() {
        List<Usuario> usuarios = new ArrayList<Usuario>();
        usuarios = getUsuarios();
        if (usuarios.isEmpty()) {
            Usuario novoUsuario = new Usuario();
            novoUsuario.setNome("admin");
            novoUsuario.setEmail("admin@ifms.edu.br");
            novoUsuario.setSenha("admin");
            
            salvarUsuario(novoUsuario);
        }
    }
    
    @Override
    public boolean login(String login, String senha){
        String sql = "SELECT * FROM tb_usuario WHERE nome=? AND senha=?";
        ResultSet rs;
        try {
            conexao = new Conexao().getConnection();
            pstm = conexao.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            this.pstm.setString(1, login);
            this.pstm.setString(2, senha);
            rs = pstm.executeQuery();
         
            if(rs.first()){
                this.pstm.close();
                conexao.close();
                return true;
            }
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Usuario Invalido!");
        }
        return false;
    }
    
    @Override
    public void getUsuario(String nomeUsuario) {
        ResultSet rs;
        List<UsuarioReport> lista = new ArrayList<UsuarioReport>();
        String query = "SELECT id, nome, email FROM tb_usuario WHERE nome=?";
        try {
            conexao = new Conexao().getConnection();
            pstm = conexao.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            pstm.setString(1, nomeUsuario);
            rs = pstm.executeQuery();

            HashMap filtro = new HashMap();
            filtro.put("paramNome", nomeUsuario);
            String CAMINHO_RELATORIO = "C:\\Users\\NathaliaCosim\\Documents\\NetBeansProjects\\ProvaMatisse\\src\\reports\\rel_usuario.jasper";
            gerarRelatorioResultSet(rs, filtro, CAMINHO_RELATORIO);
        } catch (SQLException errolistar) {
            System.out.println("Erro: " + errolistar);
        } catch (JRException ex) {
            Logger.getLogger(DaoUsuarioImp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conexao.close();
            } catch (SQLException errofechar) {
                System.out.println("Erro: " + errofechar);
            }
        }
    }

    @Override
    public void gerarRelatorioResultSet(ResultSet rs, HashMap filtro, String CAMINHO_RELATORIO) throws JRException {
        JRResultSetDataSource jrrs = new JRResultSetDataSource(rs);

        JasperPrint imprimir = JasperFillManager.fillReport(CAMINHO_RELATORIO, filtro, jrrs);
        JasperViewer visualizar = new JasperViewer(imprimir, false);
        visualizar.setVisible(true);
    }
    
    @Override
    public void gerarRelatorioCollection(List list, String CAMINHO_RELATORIO) {
        HashMap filtro = new HashMap();
        JRBeanCollectionDataSource colecao = new JRBeanCollectionDataSource(list, false);

        JasperPrint imprimir = null;
        try {
            imprimir = JasperFillManager.fillReport(CAMINHO_RELATORIO, filtro, colecao);
        } catch (JRException ex) {
            Logger.getLogger(DaoUsuarioImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        JasperViewer visualizar = new JasperViewer(imprimir, false);
        visualizar.setVisible(true);
    }
}   
