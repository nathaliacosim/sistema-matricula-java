package controller;

import dao.DaoAluno;
import dao.DaoAlunoImp;
import dao.DaoAula;
import dao.DaoAulaImp;
import dao.DaoMatricula;
import dao.DaoMatriculaImp;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Matricula;
import model.MatriculaReport;
import net.sf.jasperreports.engine.JRException;

public class MatriculaController {
    DaoMatricula dao = new DaoMatriculaImp();
    DaoAula daoAula = new DaoAulaImp();
    DaoAluno daoAluno = new DaoAlunoImp();
    
    AlunoController ac = new AlunoController();
    AulaController tc = new AulaController();
    UsuarioController uc = new UsuarioController();
    
    
    public void salvarMatricula(Matricula a) throws JRException{
        int numVagas = daoAula.buscarVaga(a.getTurma());
        if(numVagas <= 0){
            JOptionPane.showMessageDialog(null, "Não há mais vagas para esta turma!");
            tc.gerarRelatorioGeral();
        } else {
            dao.salvarMatricula(a);
        }
    }
    
    public void editarMatricula(Matricula a){
        dao.editarMatricula(a);
    }
    
    public void excluirMatricula(int id){
        int resp = JOptionPane.showConfirmDialog(null, "Deseja realmente exluir o registro?");
        if (resp == 1) {
            JOptionPane.showMessageDialog(null, "Você não excluiu o registro");
        } else {
            if (id != 0) {
                dao.excluirMatricula(id);
                JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Informe uma matricula para ser excluída!");
            }
        }
    }
    
    public List<Matricula> getMatriculas(){
        List<Matricula> lista = new ArrayList<Matricula>();
        lista = dao.getMatriculas();
        return lista;
    }
    
    public Matricula buscarMatricula(int i){
        return dao.buscarMatricula(i);
    }
    
    public List<MatriculaReport> getListaMatricula() {
        List<Matricula> listaMatricula = new ArrayList<Matricula>();
        List<MatriculaReport> lista = new ArrayList<MatriculaReport>();

        listaMatricula = dao.getMatriculas();
        lista = construirObjetoMatriculaReports(listaMatricula);

        return lista;
    }
    
     public void gerarRelatorioGeral() throws JRException {
        String CAMINHO_RELATORIO = "C:\\Users\\NathaliaCosim\\Documents\\NetBeansProjects\\ProvaMatisse\\src\\reports\\matriculas.jasper";
        List<MatriculaReport> lista = new ArrayList<MatriculaReport>();
        lista = getListaMatricula();
        dao.gerarRelatorioCollection(lista, CAMINHO_RELATORIO);
    }
    
    public List<MatriculaReport> construirObjetoMatriculaReports(List<Matricula> listaMatricula) {
        List<MatriculaReport> lista = new ArrayList<MatriculaReport>();

        for (int i = 0; i < listaMatricula.size(); i++) {
            MatriculaReport matriculaReport = new MatriculaReport();
            matriculaReport.setId(listaMatricula.get(i).getId());
            matriculaReport.setData(listaMatricula.get(i).getData());
            matriculaReport.setAluno_id(listaMatricula.get(i).getAluno());
            matriculaReport.setTurma_id(listaMatricula.get(i).getTurma());
            
            lista.add(matriculaReport);
        }
        return lista;
    }
    
}
