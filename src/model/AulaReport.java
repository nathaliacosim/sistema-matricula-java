package model;

public class AulaReport {
    private int id;
    private String descricao;
    private String horario;
    private int vagas;
    private String professor;

    public AulaReport() {}

    public AulaReport(int id, String descricao, String horario, int vagas, String professor) {
        this.id = id;
        this.descricao = descricao;
        this.horario = horario;
        this.vagas = vagas;
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

    public int getVagas() {
        return vagas;
    }

    public void setVagas(int vaga) {
        this.vagas = vaga;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }
}
