package com.example.cadastroalunos.model;

import com.orm.SugarRecord;

import java.util.Objects;

public class Diciplina extends SugarRecord {

    private String nome;
    private Professor professor;

    public Diciplina() {
    }

    public Diciplina(String nome, Professor professor) {
        this.nome = nome;
        this.professor = professor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Diciplina diciplina = (Diciplina) o;
        return getId() == diciplina.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
