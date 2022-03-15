package com.example.cadastroalunos.model;

import com.orm.SugarRecord;

import java.util.Objects;

public class AlunoTurma extends SugarRecord {

    private long alunoId;
    private long turmaId;

    public AlunoTurma() {
    }

    public AlunoTurma(long alunoId, long turmaId) {
        this.alunoId = alunoId;
        this.turmaId = turmaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlunoTurma that = (AlunoTurma) o;
        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
