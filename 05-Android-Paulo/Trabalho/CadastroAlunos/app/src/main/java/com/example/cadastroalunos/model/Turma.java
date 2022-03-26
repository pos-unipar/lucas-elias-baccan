package com.example.cadastroalunos.model;

import com.example.cadastroalunos.dao.TurmaDAO;
import com.example.cadastroalunos.enums.RegimeEnum;
import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Turma extends SugarRecord {

    private Curso curso;

    @Ignore
    private List<Aluno> alunos = new ArrayList<Aluno>();

    @Ignore
    private List<Diciplina> diciplinas = new ArrayList<Diciplina>();

    private RegimeEnum regime;

    public Turma() {
    }

    public Turma(
            Curso curso,
            RegimeEnum regime
    ) {
        this.curso = curso;
        this.regime = regime;
    }

    public Turma(
            Curso curso,
            List<Aluno> alunos,
            List<Diciplina> diciplinas,
            RegimeEnum regime
    ) {
        this.curso = curso;
        this.alunos = alunos;
        this.diciplinas = diciplinas;
        this.regime = regime;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public List<Aluno> getAlunos() {
        if (alunos.isEmpty())
            alunos = TurmaDAO.getAlunosFromTurma(this);
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public List<Diciplina> getDiciplinas() {
        if (diciplinas.isEmpty())
            diciplinas = TurmaDAO.getDiciplinaFromTurma(this);
        return diciplinas;
    }

    public void setDiciplinas(List<Diciplina> diciplinas) {
        this.diciplinas = diciplinas;
    }

    public RegimeEnum getRegime() {
        return regime;
    }

    public void setRegime(RegimeEnum regime) {
        this.regime = regime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Turma turma = (Turma) o;
        return getId() == turma.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return getId() + " - " + curso.getNome();
    }
}
