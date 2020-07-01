package model;

public class Aula {
    private int id;
    private String descricao;
    private String horario;
    private int vaga;
    private String professor;

    public Aula() {}

    public Aula(int id, String descricao, String horario, int vaga, String professor) {
        this.id = id;
        this.descricao = descricao;
        this.horario = horario;
        this.vaga = vaga;
        this.professor = professor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public int getVaga() {
        return vaga;
    }

    public void setVaga(int vaga) {
        this.vaga = vaga;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }
}
