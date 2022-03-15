package com.example.cadastroalunos.enums;

public enum PeriodoEnum {
    SERIE1("1a Série"),
    SERIE2("2a Série"),
    SERIE3("3a Série"),
    SERIE4("4a Série");

    private String periodo;

    PeriodoEnum(String periodo) {
        this.periodo = periodo;
    }

    @Override
    public String toString() {
        return periodo;
    }

    public PeriodoEnum findByValue(String value) {
        for (PeriodoEnum item : values()) {
            if (item.periodo.equals(value)) {
                return item;
            }
        }
        return null;
    }
}
