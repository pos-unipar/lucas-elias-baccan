package com.example.cadastroalunos.model;

import com.orm.SugarRecord;

import java.util.Objects;

public class DiciplinaTurma extends SugarRecord {

    private long diciplinaId;
    private long turmaId;

    public DiciplinaTurma() {
    }

    public DiciplinaTurma(long diciplinaId, long turmaId) {
        this.diciplinaId = diciplinaId;
        this.turmaId = turmaId;
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
