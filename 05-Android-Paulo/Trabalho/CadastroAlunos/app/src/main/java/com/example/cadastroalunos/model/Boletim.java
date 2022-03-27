package com.example.cadastroalunos.model;

import com.example.cadastroalunos.dao.FrequenciaDAO;
import com.example.cadastroalunos.dao.NotaDAO;
import com.example.cadastroalunos.enums.RegimeEnum;

import java.util.List;

public class Boletim {

    private Turma turma;
    private Diciplina diciplina;
    private Aluno aluno;

    private boolean aprovadoNota = false;
    private boolean aprovadoFrequencia = false;

    public Boletim() {
    }

    public Boletim(Turma turma, Diciplina diciplina, Aluno aluno) {
        this.turma = turma;
        this.diciplina = diciplina;
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

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public String getFrequencia() {
        List<Frequencia> frequenciaList = FrequenciaDAO.getAll(
                "turma = ? AND diciplina = ? AND aluno = ?",
                new String[]{
                        String.valueOf(turma.getId()),
                        String.valueOf(diciplina.getId()),
                        String.valueOf(aluno.getId())
                },
                ""
        );
        int presencas = 0;
        for (Frequencia frequencia : frequenciaList) {
            if (frequencia.isPresente()) {
                presencas++;
            }
        }

        if (frequenciaList.size() > 0 && (((double) presencas / (double) frequenciaList.size())) > 0.3) {
            aprovadoFrequencia = true;
        }

        return presencas + "/" + frequenciaList.size();
    }

    public String getMedia() {
        List<Nota> notaList = NotaDAO.getAll(
                "turma = ? AND diciplina = ? AND aluno = ?",
                new String[]{
                        String.valueOf(turma.getId()),
                        String.valueOf(diciplina.getId()),
                        String.valueOf(aluno.getId())
                },
                ""
        );

        int totalNotas = 0;
        int quantNotas = 0;

        for (Nota nota : notaList) {
            if (turma.getRegime() == RegimeEnum.SEMESTRAL) {
                quantNotas = quantNotas - 2;
            }

            totalNotas = totalNotas + nota.getNota1();
            totalNotas = totalNotas + nota.getNota2();
            totalNotas = totalNotas + nota.getNota3();
            totalNotas = totalNotas + nota.getNota4();
            quantNotas = quantNotas + 4;

        }

        if (quantNotas == 0) {
            return "0/100";
        }

        int media = (totalNotas / quantNotas);

        if (media >= 70) {
            aprovadoNota = true;
        }

        return media + "/100";
    }

    public boolean isAprovado() {
        return aprovadoFrequencia && aprovadoNota;
    }
}
