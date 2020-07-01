package dao;
import java.util.List;
import model.Aula;
public interface DaoAula {
    public void salvarAula(Aula a);
    public void editarAula(Aula a);
    public void excluirAula(int id);
    public int buscarId(String nome);
    public String buscarAula(int id);
    public List<Aula> carregaCombo();
    public List<Aula> getAulas();
    public void decrementaVaga(int id, int numero);
    public int buscarVaga(int id);
    public void gerarRelatorioCollection(List list, String CAMINHO_RELATORIO);
}
