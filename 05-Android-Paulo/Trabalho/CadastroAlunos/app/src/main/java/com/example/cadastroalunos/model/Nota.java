package com.example.cadastroalunos.model;

import com.orm.SugarRecord;

import java.util.Objects;

public class Nota extends SugarRecord {

    // Se for anual,vai ter 4 notas, se for semestral, tem 2 notas somente

    private Aluno aluno;
    private Turma turma;
    private Diciplina diciplina;
    private int nota1;
    private int nota2;
    private int nota3;
    private int nota4;

    public Nota() {
    }

    public Nota(
            Aluno aluno,
            Turma turma,
            Diciplina diciplina,
            int nota1,
            int nota2,
            int nota3,
            int nota4
    ) {
        this.aluno = aluno;
        this.turma = turma;
        this.diciplina = diciplina;
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.nota3 = nota3;
        this.nota4 = nota4;
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

    public int getNota1() {
        return nota1;
    }

    public void setNota1(int nota1) {
        this.nota1 = nota1;
    }

    public int getNota2() {
        return nota2;
    }

    public void setNota2(int nota2) {
        this.nota2 = nota2;
    }

    public int getNota3() {
        return nota3;
    }

    public void setNota3(int nota3) {
        this.nota3 = nota3;
    }

    public int getNota4() {
        return nota4;
    }

    public void setNota4(int nota4) {
        this.nota4 = nota4;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nota that = (Nota) o;
        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
