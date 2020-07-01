package model;

public class MatriculaReport {
    private int id;
    private String data;
    private int aluno_id;
    private int turma_id;
    private String vagas;

    public MatriculaReport() {}

    public MatriculaReport(int id, String data, int aluno_id, int turma_id, String vagas) {
        this.id = id;
        this.data = data;
        this.aluno_id = aluno_id;
        this.turma_id = turma_id;
        this.vagas = vagas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getAluno_id() {
        return aluno_id;
    }

    public void setAluno_id(int aluno_id) {
        this.aluno_id = aluno_id;
    }

    public int getTurma_id() {
        return turma_id;
    }

    public void setTurma_id(int turma_id) {
        this.turma_id = turma_id;
    }

    public String getVagas() {
        return vagas;
    }

    public void setVagas(String vagas) {
        this.vagas = vagas;
    }
}
