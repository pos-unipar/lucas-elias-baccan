package com.example.cadastroalunos.enums;

public enum RegimeEnum {
    ANUAL("Anual"),
    SEMESTRAL("Semestral");

    private String regime;

    RegimeEnum(String periodo) {
        this.regime = periodo;
    }

    @Override
    public String toString() {
        return regime;
    }

    public RegimeEnum findByValue(String value) {
        for (RegimeEnum item : values()) {
            if (item.regime.equals(value)) {
                return item;
            }
        }
        return null;
    }
}
