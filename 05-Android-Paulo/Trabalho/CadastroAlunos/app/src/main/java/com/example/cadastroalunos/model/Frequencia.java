package com.example.cadastroalunos.model;

import com.orm.SugarRecord;

import java.util.Objects;

public class Frequencia extends SugarRecord {

    private Aluno aluno;
    private Turma turma;
    private Diciplina diciplina;
    private boolean presente = false;

    public Frequencia() {
    }

    public Frequencia(
            Aluno aluno,
            Turma turma,
            Diciplina diciplina,
            boolean presente
    ) {
        this.aluno = aluno;
        this.turma = turma;
        this.diciplina = diciplina;
        this.presente = presente;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public Diciplina getDiciplina() {
        return diciplina;
    }

    public void setDiciplina(Diciplina diciplina) {
        this.diciplina = diciplina;
    }

    public boolean isPresente() {
        return presente;
    }

    public void setPresente(boolean presente) {
        this.presente = presente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frequencia that = (Frequencia) o;
        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
