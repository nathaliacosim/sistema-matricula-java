package dao;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Matricula;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class DaoMatriculaImp implements DaoMatricula {
    Connection conexao = null;
    PreparedStatement pstm = null;
    
    @Override
    public void salvarMatricula(Matricula m) {
        conexao = new Conexao().getConnection();
        String sqlinsert = "INSERT INTO tb_matricula(data, aluno_id, turma_id) values(?,?,?)";
        try {
            pstm = conexao.prepareStatement(sqlinsert, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            pstm.setString(1, m.getData());
            pstm.setInt(2, m.getAluno());
            pstm.setInt(3, m.getTurma());
            pstm.execute();
            pstm.close();
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
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
    public void editarMatricula(Matricula m) {
        conexao = new Conexao().getConnection();
        String query = "UPDATE tb_matricula SET data=?, aluno_id=?, turma_id=? WHERE id=?";
        try {
            pstm = conexao.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            pstm.setString(1, m.getData());
            pstm.setInt(2, m.getAluno());
            pstm.setInt(3, m.getTurma());
            pstm.setInt(4, m.getId());
            pstm.execute();
            pstm.close();
            JOptionPane.showMessageDialog(null, "Dao: Editado com sucesso!");
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
    public void excluirMatricula(int id) {
        int resp = JOptionPane.showConfirmDialog(null, "Deseja realmente exluir o registro?");
        if (resp == 1) {
            JOptionPane.showMessageDialog(null, "Você não excluiu o registro");
        } else {
            conexao = new Conexao().getConnection();
            String query = "DELETE FROM tb_matricula WHERE id=?";
            try {
                pstm = conexao.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
                pstm.setInt(1, id);
                pstm.execute();
                pstm.close();
                JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
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
    }

    @Override
    public List<Matricula> getMatriculas() {
        ResultSet rs = null;
        List<Matricula> lista = new ArrayList<Matricula>();
        conexao = new Conexao().getConnection();
        String query = "SELECT * FROM tb_matricula";
        try {
            pstm = conexao.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rs = pstm.executeQuery();
            rs.first();
            do {
                Matricula m = new Matricula();
                m.setId(rs.getInt("id"));
                m.setData(rs.getString("data"));
                m.setAluno(rs.getInt("aluno_id"));
                m.setTurma(rs.getInt("turma_id"));
              
                lista.add(m);
            } while (rs.next());
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
    
    @Override
    public Matricula buscarMatricula(int id) {
        ResultSet rs = null;
        conexao = new Conexao().getConnection();
        String query = "SELECT * FROM tb_matricula WHERE id=?";
        Matricula m = new Matricula();
        try {
            pstm = conexao.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            if(rs.first())
            {
                m.setId(rs.getInt("id"));
                m.setData(rs.getString("data"));
                m.setAluno(rs.getInt("aluno_id"));
                m.setTurma(rs.getInt("turma_id"));
            }
        } catch (SQLException update) {
            System.out.println("Erro: " + update);
        } finally {
            try {
                conexao.close();
            } catch (SQLException updateclose) {
                System.out.println("Erro: " + updateclose);
            }
        }
        return m;
    }
    
    @Override
    public void gerarRelatorioCollection(List list, String CAMINHO_RELATORIO) {
        HashMap filtro = new HashMap();
        JRBeanCollectionDataSource colecao = new JRBeanCollectionDataSource(list, false);

        JasperPrint imprimir = null;
        try {
            imprimir = JasperFillManager.fillReport(CAMINHO_RELATORIO, filtro, colecao);
        } catch (JRException ex) {
            Logger.getLogger(DaoMatriculaImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        JasperViewer visualizar = new JasperViewer(imprimir, false);
        visualizar.setVisible(true);
    }
    
}
