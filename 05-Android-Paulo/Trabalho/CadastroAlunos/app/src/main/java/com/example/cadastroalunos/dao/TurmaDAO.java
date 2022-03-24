package com.example.cadastroalunos.dao;

import android.util.Log;

import com.example.cadastroalunos.model.Aluno;
import com.example.cadastroalunos.model.AlunoTurma;
import com.example.cadastroalunos.model.Turma;

import java.util.ArrayList;
import java.util.List;

public class TurmaDAO {

    private static String nome = "TURMA";

    public static long salvar(Turma model) {
        try {
            long id = model.save();

            salvarAlteracaoAlunos(model);

            return id;
        } catch (Exception ex) {
            Log.e("Erro", "(" + nome + ") Erro ao salvar: " + ex.getMessage());
            return -1;
        }
    }

    public static Turma getById(int id) {
        try {
            Turma turma = Turma.findById(Turma.class, id);
            turma.setAlunos(getAlunosFromTurma(turma));
            return turma;
        } catch (Exception ex) {
            Log.e("Erro", "(" + nome + ") Erro ao retornar: " + ex.getMessage());
            return null;
        }
    }

    public static List<Turma> getAll(String where, String[] whereArgs, String orderBy) {
        List<Turma> list = new ArrayList<>();
        try {
            list = Turma.find(Turma.class, where, whereArgs, "", orderBy, "");
            for (Turma t : list) {
                t.setAlunos(getAlunosFromTurma(t));
            }
        } catch (Exception ex) {
            Log.e("Erro", "(" + nome + ") Erro ao retornar lista: " + ex.getMessage());
        }
        return list;
    }

    public static boolean delete(Turma model) {
        try {
            // Deletar os "AlunoTurma"
            List<AlunoTurma> alunoTurmaList = AlunoTurma.find(
                    AlunoTurma.class,
                    "turma = ? ",
                    new String[]{model.getId().toString()}
            );
            for (AlunoTurma alunoTurma : alunoTurmaList) {
                alunoTurma.delete();
            }

            return Turma.delete(model);
        } catch (Exception ex) {
            Log.e("Erro", "(" + nome + ") Erro ao apagar: " + ex.getMessage());
            return false;
        }
    }

    public static List<Aluno> getAlunosFromTurma(Turma turma) {
        try {
            List<AlunoTurma> alunoTurmaList = AlunoTurma.find(AlunoTurma.class, "turma = ?", new String[]{String.valueOf(turma.getId())});
            List<Aluno> alunos = new ArrayList<>();
            for (AlunoTurma at : alunoTurmaList) {
                alunos.add(at.getAluno());
            }

            turma.setAlunos(alunos);

            return alunos;
        } catch (Exception ex) {
            Log.e("Erro", "(" + nome + ") Erro ao retornar alunos: " + ex.getMessage());
            return null;
        }
    }

    public static void salvarAlteracaoAlunos(Turma model) {
        if (model.getId() == null) {
            model = getById((int) salvar(model));
        }
        // Delete all AlunoTurma
        AlunoTurma.deleteAll(AlunoTurma.class, "turma = ?", new String[]{String.valueOf(model.getId())});
        for (Aluno aluno : model.getAlunos()) {
            AlunoTurma at = new AlunoTurma(AlunoDAO.getByRa(aluno.getRa()), model);
            at.save();
        }
    }
}
