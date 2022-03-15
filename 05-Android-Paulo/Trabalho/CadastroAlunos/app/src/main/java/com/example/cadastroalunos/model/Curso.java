package com.example.cadastroalunos.model;

import com.orm.SugarRecord;

import java.util.Objects;

public class Curso extends SugarRecord {

    private String nome;

    public Curso() {
    }

    public Curso(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Curso curso = (Curso) o;
        return getId() == curso.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return nome;

    }
}
