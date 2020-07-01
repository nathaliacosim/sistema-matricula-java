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
import model.Aluno;
import model.AlunoReport;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class DaoAlunoImp implements DaoAluno {
    Connection conexao = null;
    PreparedStatement pstm = null;
    
    @Override
    public void salvarAluno(Aluno a) {
        conexao = new Conexao().getConnection();
        String sqlinsert = "INSERT INTO tb_aluno(nome, sexo, idade, peso, telefone, email, endereco) values(?,?,?,?,?,?,?)";
        try {
            pstm = conexao.prepareStatement(sqlinsert, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            pstm.setString(1, a.getNome());
            pstm.setString(2, a.getSexo());
            pstm.setInt(3, a.getIdade());
            pstm.setInt(4, a.getPeso());
            pstm.setString(5, a.getTelefone());
            pstm.setString(6, a.getEmail());
            pstm.setString(7, a.getEndereco());
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
    public void editarAluno(Aluno a) {
        conexao = new Conexao().getConnection();
        String query = "UPDATE tb_aluno SET nome=?, sexo=?, idade=?, peso=?, telefone=?, email=?, endereco=? WHERE id=?";
        try {
            pstm = conexao.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            pstm.setString(1, a.getNome());
            pstm.setString(2, a.getSexo());
            pstm.setInt(3, a.getIdade());
            pstm.setInt(4, a.getPeso());
            pstm.setString(5, a.getTelefone());
            pstm.setString(6, a.getEmail());
            pstm.setString(7, a.getEndereco());
            pstm.setInt(8, a.getId());
            pstm.execute();
            pstm.close();
            JOptionPane.showMessageDialog(null, "Editado com sucesso!");
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
    public void excluirAluno(int id) {
        int resp = JOptionPane.showConfirmDialog(null, "Deseja realmente exluir o registro?");
        if (resp == 1) {
            JOptionPane.showMessageDialog(null, "Você não excluiu o registro");
        } else {
            conexao = new Conexao().getConnection();
            String query = "DELETE FROM tb_aluno WHERE id=?";
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
    public List<Aluno> getAlunos() {
        ResultSet rs = null;
        List<Aluno> lista = new ArrayList<Aluno>();
        conexao = new Conexao().getConnection();
        String query = "SELECT * FROM tb_aluno";
        try {
            pstm = conexao.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rs = pstm.executeQuery();
            rs.first();
            do {
                Aluno a = new Aluno();
                a.setId(rs.getInt("id"));
                a.setNome(rs.getString("nome"));
                a.setSexo(rs.getString("sexo"));
                a.setIdade(rs.getInt("idade"));
                a.setPeso(rs.getInt("peso"));
                a.setTelefone(rs.getString("telefone"));
                a.setEmail(rs.getString("email"));
                a.setEndereco(rs.getString("endereco"));
                
                lista.add(a);
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
    public int buscarId(String nome) {
        ResultSet rs = null;
        conexao = new Conexao().getConnection();
        String query = "SELECT id FROM tb_aluno WHERE nome=?";
        int id = 0;
        try {
            pstm = conexao.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            pstm.setString(1, nome);
            rs = pstm.executeQuery();
            if(rs.first())
            {
            id = rs.getInt("id");
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
        return id;
    }
    
    public String buscarAluno(int id){
        ResultSet rs = null;
        conexao = new Conexao().getConnection();
        String query = "SELECT nome FROM tb_aluno WHERE id=?";
        String aluno = "";
        try {
            pstm = conexao.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            if(rs.first())
            {
            aluno = rs.getString("nome");
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
        return aluno;
    }
    
    public List<Aluno> carregaCombo() {
        List<Aluno> lista = new ArrayList<Aluno>();
        conexao = new Conexao().getConnection();
        String query = "SELECT * FROM tb_aluno ORDER BY nome ASC";
        try {
            pstm = conexao.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = pstm.executeQuery();
            rs.first();
            do{
                Aluno a = new Aluno();
                a.setId(rs.getInt("id"));
                a.setNome(rs.getString("nome"));
                
                lista.add(a);
            }while(rs.next());
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
    public void gerarRelatorioCollection(List list, String CAMINHO_RELATORIO) {
        HashMap filtro = new HashMap();
        JRBeanCollectionDataSource colecao = new JRBeanCollectionDataSource(list, false);

        JasperPrint imprimir = null;
        try {
            imprimir = JasperFillManager.fillReport(CAMINHO_RELATORIO, filtro, colecao);
        } catch (JRException ex) {
            Logger.getLogger(DaoAlunoImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        JasperViewer visualizar = new JasperViewer(imprimir, false);
        visualizar.setVisible(true);
    }
    
    @Override
    public void getAluno(String nomeAluno) {
        ResultSet rs;
        List<AlunoReport> lista = new ArrayList<AlunoReport>();
        String query = "SELECT id, nome, sexo, idade, peso, telefone, email, endereco FROM tb_aluno WHERE nome=?";
        try {
            conexao = new Conexao().getConnection();
            pstm = conexao.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            pstm.setString(1, nomeAluno);
            rs = pstm.executeQuery();

            HashMap filtro = new HashMap();
            filtro.put("paramNome", nomeAluno);
            String CAMINHO_RELATORIO = "C:\\Users\\NathaliaCosim\\Documents\\NetBeansProjects\\ProvaMatisse\\src\\reports\\aluno_single.jasper";
            gerarRelatorioResultSet(rs, filtro, CAMINHO_RELATORIO);
        } catch (SQLException errolistar) {
            System.out.println("Erro: " + errolistar);
        } catch (JRException ex) {
            Logger.getLogger(DaoAlunoImp.class.getName()).log(Level.SEVERE, null, ex);
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
}
