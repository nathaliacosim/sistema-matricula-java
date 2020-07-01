package dao;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import model.Usuario;
import net.sf.jasperreports.engine.JRException;
public interface DaoUsuario {
    public void salvarUsuario(Usuario u);
    public void editarUsuario(Usuario u);
    public void deletarUsuario(int id);
    public List<Usuario> getUsuarios();
    public boolean login(String login, String senha);
    public void getUsuario(String nomeUsuario);
    public void gerarRelatorioCollection(List list, String CAMINHO_RELATORIO);
    public void gerarRelatorioResultSet(ResultSet rs, HashMap filtro, String CAMINHO_RELATORIO) throws JRException;
}
