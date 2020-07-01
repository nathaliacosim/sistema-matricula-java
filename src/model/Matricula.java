package model;

public class Matricula {
    private int id;
    private String data;
    private int aluno;
    private int turma;

    public Matricula() {}

    public Matricula(int id, String data, int aluno, int turma) {
        this.id = id;
        this.data = data;
        this.aluno = aluno;
        this.turma = turma;
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

    public int getAluno() {
        return aluno;
    }

    public void setAluno(int aluno) {
        this.aluno = aluno;
    }

    public int getTurma() {
        return turma;
    }

    public void setTurma(int turma) {
        this.turma = turma;
    }
}
