package controller;

import dao.DaoAula;
import dao.DaoAulaImp;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Aula;
import model.AulaReport;
import net.sf.jasperreports.engine.JRException;

public class AulaController {
    DaoAula dao = new DaoAulaImp();
    
    public void salvarAula(Aula a){
        dao.salvarAula(a);
    }
    
    public void editarAula(Aula a){
        dao.editarAula(a);
    }
    
    public void excluirAula(int id){
        int resp = JOptionPane.showConfirmDialog(null, "Deseja realmente exluir o registro?");
        if (resp == 1) {
            JOptionPane.showMessageDialog(null, "Você não excluiu o registro");
        } else {
            if (id != 0) {
                dao.excluirAula(id);
                JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Informe uma marca para ser excluída!");
            }
        }
    }
    
    public List<Aula> getAulas(){
        List<Aula> lista = new ArrayList<Aula>();
        lista = dao.getAulas();
        return lista;
    }
    
    public int buscarId(String nome) {
        int id = dao.buscarId(nome);
        return id;
    }
    
    public String buscarDescricao(int id) {
        String descricao = dao.buscarAula(id);
        return descricao;
    }
    
    public List<Aula> carregaCombo() {
        List<Aula> lista = new ArrayList<Aula>();
        lista = dao.carregaCombo();
        return lista;
    }
    
    public int buscarVaga(int id) {
        int vaga = dao.buscarVaga(id);
        return vaga;
    }
    
    public void decrementaVaga(int id, int numero) {
        dao.decrementaVaga(id, numero);
    }
    
    public List<AulaReport> getListaAula() {
        List<Aula> listaAulas= new ArrayList<Aula>();
        List<AulaReport> lista = new ArrayList<AulaReport>();

        listaAulas = dao.getAulas();
        lista = construirObjetoAulaReports(listaAulas);

        return lista;
    }
    
     public void gerarRelatorioGeral() throws JRException {
        String CAMINHO_RELATORIO = "C:\\Users\\NathaliaCosim\\Documents\\NetBeansProjects\\ProvaMatisse\\src\\reports\\turmas.jasper";
        List<AulaReport> lista = new ArrayList<AulaReport>();
        lista = getListaAula();
        dao.gerarRelatorioCollection(lista, CAMINHO_RELATORIO);
    }
    
    public List<AulaReport> construirObjetoAulaReports(List<Aula> listaAulas) {
        List<AulaReport> lista = new ArrayList<AulaReport>();

        for (int i = 0; i < listaAulas.size(); i++) {
            AulaReport aulaReport = new AulaReport();
            aulaReport.setId(listaAulas.get(i).getId());
            aulaReport.setDescricao(listaAulas.get(i).getDescricao());
            aulaReport.setHorario(listaAulas.get(i).getHorario());
            aulaReport.setProfessor(listaAulas.get(i).getProfessor());
            aulaReport.setVagas(listaAulas.get(i).getVaga());
            
            lista.add(aulaReport);
        }
        return lista;
    }
}
