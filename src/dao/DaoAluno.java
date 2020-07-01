package dao;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import model.Aluno;
import net.sf.jasperreports.engine.JRException;
public interface DaoAluno {
    public void salvarAluno(Aluno a);
    public void editarAluno(Aluno a);
    public void excluirAluno(int id);
    public int buscarId(String nome);
    public String buscarAluno(int id);
    public List<Aluno> carregaCombo();
    public List<Aluno> getAlunos();
    public void getAluno(String nomeAluno);
    public void gerarRelatorioCollection(List list, String CAMINHO_RELATORIO);
    public void gerarRelatorioResultSet(ResultSet rs, HashMap filtro, String CAMINHO_RELATORIO) throws JRException;
}