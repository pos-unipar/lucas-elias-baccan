package com.example.cadastroalunos.model;

import com.orm.SugarRecord;

import java.util.Objects;

public class DiciplinaTurma extends SugarRecord {

    private Diciplina diciplina;
    private Turma turma;

    public DiciplinaTurma() {
    }

    public DiciplinaTurma(Diciplina diciplina, Turma turma) {
        this.diciplina = diciplina;
        this.turma = turma;
    }

    public Diciplina getDiciplina() {
        return diciplina;
    }

    public void setDiciplina(Diciplina diciplina) {
        this.diciplina = diciplina;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiciplinaTurma that = (DiciplinaTurma) o;
        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
