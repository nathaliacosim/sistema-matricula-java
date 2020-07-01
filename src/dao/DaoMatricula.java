package dao;

import java.util.List;
import model.Matricula;

public interface DaoMatricula {
    public void salvarMatricula(Matricula m);
    public void editarMatricula(Matricula m);
    public void excluirMatricula(int id);
    public List<Matricula> getMatriculas();
    public Matricula buscarMatricula(int id);
    public void gerarRelatorioCollection(List list, String CAMINHO_RELATORIO);
}
