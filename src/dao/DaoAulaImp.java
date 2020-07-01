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
import model.Aula;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class DaoAulaImp implements DaoAula {
    Connection conexao = null;
    PreparedStatement pstm = null;
    
    @Override
    public void salvarAula(Aula a) {
        conexao = new Conexao().getConnection();
        String sqlinsert = "INSERT INTO tb_aula(descricao, horario, vagas, professor) values(?,?,?,?)";
        try {
            pstm = conexao.prepareStatement(sqlinsert, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            pstm.setString(1, a.getDescricao());
            pstm.setString(2, a.getHorario());
            pstm.setInt(3, a.getVaga());
            pstm.setString(4, a.getProfessor());
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
    public void editarAula(Aula a) {
        conexao = new Conexao().getConnection();
        String query = "UPDATE tb_aula SET descricao=?, horario=?, vagas=?, professor=? WHERE id=?";
        try {
            pstm = conexao.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            pstm.setString(1, a.getDescricao());
            pstm.setString(2, a.getHorario());
            pstm.setInt(3, a.getVaga());
            pstm.setString(4, a.getProfessor());
            pstm.setInt(5, a.getId());
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
    public void excluirAula(int id) {
        int resp = JOptionPane.showConfirmDialog(null, "Deseja realmente exluir o registro?");
        if (resp == 1) {
            JOptionPane.showMessageDialog(null, "Você não excluiu o registro");
        } else {
            conexao = new Conexao().getConnection();
            String query = "DELETE FROM tb_aula WHERE id=?";
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
    public List<Aula> getAulas() {
        ResultSet rs = null;
        List<Aula> lista = new ArrayList<Aula>();
        conexao = new Conexao().getConnection();
        String query = "SELECT * FROM tb_aula";
        try {
            pstm = conexao.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rs = pstm.executeQuery();
            rs.first();
            do {
                Aula a = new Aula();
                a.setId(rs.getInt("id"));
                a.setDescricao(rs.getString("descricao"));
                a.setHorario(rs.getString("horario"));
                a.setVaga(rs.getInt("vagas"));
                a.setProfessor(rs.getString("professor"));
              
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
    public int buscarId(String desc) {
        ResultSet rs = null;
        conexao = new Conexao().getConnection();
        String query = "SELECT id FROM tb_aula WHERE descricao=?";
        int id = 0;
        try {
            pstm = conexao.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            pstm.setString(1, desc);
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

    @Override
    public String buscarAula(int id) {
        ResultSet rs = null;
        conexao = new Conexao().getConnection();
        String query = "SELECT descricao FROM tb_aula WHERE id=?";
        String aluno = "";
        try {
            pstm = conexao.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            if(rs.first())
            {
            aluno = rs.getString("descricao");
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

    @Override
    public List<Aula> carregaCombo() {
        List<Aula> lista = new ArrayList<Aula>();
        conexao = new Conexao().getConnection();
        String query = "SELECT * FROM tb_aula ORDER BY descricao ASC";
        try {
            pstm = conexao.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = pstm.executeQuery();
            rs.first();
            do{
                Aula a = new Aula();
                a.setId(rs.getInt("id"));
                a.setDescricao(rs.getString("descricao"));
                
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
    public void decrementaVaga(int id, int numero) {
        Aula a = new Aula();
        conexao = new Conexao().getConnection();
        String query = "UPDATE tb_aula SET vagas=? WHERE id=?";
        
        if(numero < 0) numero = 0;
        
        try {
            pstm = conexao.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            pstm.setInt(1, numero);
            pstm.setInt(2, id);
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
    public int buscarVaga(int id) {
        ResultSet rs = null;
        conexao = new Conexao().getConnection();
        String query = "SELECT vagas FROM tb_aula WHERE id=?";
        int vaga = 0;
        try {
            pstm = conexao.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            if(rs.first())
            {
            vaga = rs.getInt("vagas");
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
        return vaga;
    }
    
    @Override
    public void gerarRelatorioCollection(List list, String CAMINHO_RELATORIO) {
        HashMap filtro = new HashMap();
        JRBeanCollectionDataSource colecao = new JRBeanCollectionDataSource(list, false);

        JasperPrint imprimir = null;
        try {
            imprimir = JasperFillManager.fillReport(CAMINHO_RELATORIO, filtro, colecao);
        } catch (JRException ex) {
            Logger.getLogger(DaoAulaImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        JasperViewer visualizar = new JasperViewer(imprimir, false);
        visualizar.setVisible(true);
    }
    
}
