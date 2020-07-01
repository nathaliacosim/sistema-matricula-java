package controller;

import dao.DaoAluno;
import dao.DaoAlunoImp;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Aluno;
import model.AlunoReport;
import net.sf.jasperreports.engine.JRException;

public class AlunoController {
    DaoAluno dao = new DaoAlunoImp();
    
    public void salvarAluno(Aluno a){
        dao.salvarAluno(a);
    }
    
    public void editarAluno(Aluno a){
        dao.editarAluno(a);
    }
    
    public void excluirAluno(int id){
        int resp = JOptionPane.showConfirmDialog(null, "Deseja realmente exluir o registro?");
        if (resp == 1) {
            JOptionPane.showMessageDialog(null, "Você não excluiu o registro");
        } else {
            if (id != 0) {
                dao.excluirAluno(id);
                JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Informe um aluno para ser excluído!");
            }
        }
    }
    
    public List<Aluno> getAlunos(){
        List<Aluno> lista = new ArrayList<Aluno>();
        lista = dao.getAlunos();
        return lista;
    }
    
    public int buscarId(String nome) {
        int id = dao.buscarId(nome);
        return id;
    }
    
    public String buscarDescricao(int id) {
        String descricao = dao.buscarAluno(id);
        return descricao;
    }
    
    public List<Aluno> carregaCombo() {
        List<Aluno> lista = new ArrayList<Aluno>();
        lista = dao.carregaCombo();
        return lista;
    }
    
    public List<AlunoReport> getListaAluno() {
        List<Aluno> listaAlunos = new ArrayList<Aluno>();
        List<AlunoReport> lista = new ArrayList<AlunoReport>();

        listaAlunos = dao.getAlunos();
        lista = construirObjetoAlunoReports(listaAlunos);

        return lista;
    }
    
     public void gerarRelatorioGeral() throws JRException {
        String CAMINHO_RELATORIO = "C:\\Users\\NathaliaCosim\\Documents\\NetBeansProjects\\ProvaMatisse\\src\\reports\\alunos.jasper";
        List<AlunoReport> lista = new ArrayList<AlunoReport>();
        lista = getListaAluno();
        dao.gerarRelatorioCollection(lista, CAMINHO_RELATORIO);
    }
    
    public List<AlunoReport> construirObjetoAlunoReports(List<Aluno> listaAlunos) {
        List<AlunoReport> lista = new ArrayList<AlunoReport>();

        for (int i = 0; i < listaAlunos.size(); i++) {
            AlunoReport alunoReport = new AlunoReport();
            alunoReport.setId(listaAlunos.get(i).getId());
            alunoReport.setNome(listaAlunos.get(i).getNome());
            alunoReport.setIdade(listaAlunos.get(i).getIdade());
            alunoReport.setPeso(listaAlunos.get(i).getPeso());
            alunoReport.setSexo(listaAlunos.get(i).getSexo());
            alunoReport.setEndereco(listaAlunos.get(i).getEndereco());
            alunoReport.setTelefone(listaAlunos.get(i).getTelefone());
            alunoReport.setEmail(listaAlunos.get(i).getEmail());
            
            lista.add(alunoReport);
        }
        return lista;
    }
    
    public void gerarRelatorioAluno(String nomeAluno) throws JRException, SQLException {
        if (!nomeAluno.equals("") && !nomeAluno.equals("--Selecione--")) {
            dao.getAluno(nomeAluno);
        } else {
            JOptionPane.showMessageDialog(null, "Informe um aluno para gerar o relatório!");
        }
    }
}
