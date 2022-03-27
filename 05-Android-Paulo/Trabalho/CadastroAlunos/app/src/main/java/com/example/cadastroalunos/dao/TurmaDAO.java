package com.example.cadastroalunos.dao;

import android.util.Log;

import com.example.cadastroalunos.model.Aluno;
import com.example.cadastroalunos.model.AlunoTurma;
import com.example.cadastroalunos.model.Curso;
import com.example.cadastroalunos.model.Diciplina;
import com.example.cadastroalunos.model.DiciplinaTurma;
import com.example.cadastroalunos.model.Professor;
import com.example.cadastroalunos.model.Turma;
import com.example.cadastroalunos.util.FakerUtil;

import java.util.ArrayList;
import java.util.List;

public class TurmaDAO {

    private static String nome = "TURMA";

    public static long salvar(Turma model) {
        try {
            long id = model.save();

            salvarAlteracaoAlunos(model);
            salvarAlteracaoDiciplinas(model);

            return id;
        } catch (Exception ex) {
            Log.e("Erro", "(" + nome + ") Erro ao salvar: " + ex.getMessage());
            return -1;
        }
    }

    public static Turma getById(long id) {
        try {
            Turma turma = Turma.findById(Turma.class, id);
            turma.setAlunos(getAlunosFromTurma(turma));
            turma.setDiciplinas(getDiciplinaFromTurma(turma));
            return turma;
        } catch (Exception ex) {
            Log.e("Erro", "(" + nome + ") Erro ao retornar: " + ex.getMessage());
            return null;
        }
    }

    public static List<Turma> getAll() {
        return getAll("", new String[]{}, "");
    }

    public static List<Turma> getAll(String where, String[] whereArgs, String orderBy) {
        List<Turma> list = new ArrayList<>();
        try {
            list = Turma.find(Turma.class, where, whereArgs, "", orderBy, "");
            for (Turma t : list) {
                t.setAlunos(getAlunosFromTurma(t));
                t.setDiciplinas(getDiciplinaFromTurma(t));
            }
        } catch (Exception ex) {
            Log.e("Erro", "(" + nome + ") Erro ao retornar lista: " + ex.getMessage());
        }
        return list;
    }

    public static boolean delete(Turma model) {
        try {
            AlunoTurma.deleteAll(AlunoTurma.class, "turma = ?", new String[]{String.valueOf(model.getId())});
            DiciplinaTurma.deleteAll(DiciplinaTurma.class, "turma = ?", new String[]{String.valueOf(model.getId())});

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

    public static List<Diciplina> getDiciplinaFromTurma(Turma turma) {
        try {
            List<DiciplinaTurma> alunoTurmaList = DiciplinaTurma.find(DiciplinaTurma.class, "turma = ?", new String[]{String.valueOf(turma.getId())});
            List<Diciplina> diciplinas = new ArrayList<>();
            for (DiciplinaTurma dt : alunoTurmaList) {
                diciplinas.add(dt.getDiciplina());
            }

            turma.setDiciplinas(diciplinas);

            return diciplinas;
        } catch (Exception ex) {
            Log.e("Erro", "(" + nome + ") Erro ao retornar diciplinas: " + ex.getMessage());
            return null;
        }
    }

    public static void salvarAlteracaoDiciplinas(Turma model) {
        if (model.getId() == null) {
            model = getById((int) salvar(model));
        }
        // Delete all AlunoTurma
        DiciplinaTurma.deleteAll(DiciplinaTurma.class, "turma = ?", new String[]{String.valueOf(model.getId())});
        for (Diciplina diciplina : model.getDiciplinas()) {
            DiciplinaTurma dt = new DiciplinaTurma(DiciplinaDAO.getById(diciplina.getId()), model);
            dt.save();
        }
    }

    public static Turma getAleatorio() {
        List<Turma> turmaList = getAll();
        if (turmaList.isEmpty()) {
            return null;
        }
        return turmaList.get(FakerUtil.getIndex(turmaList.size()));
    }

    public static Diciplina getDiciplinaAleatoriaFromTurma(Turma turma){
        List<Diciplina> diciplinaList = turma.getDiciplinas();
        if (diciplinaList.isEmpty()) {
            return null;
        }
        return diciplinaList.get(FakerUtil.getIndex(diciplinaList.size()));
    }

    public static Aluno getAlunoAleatoriaFromTurma(Turma turma){
        List<Aluno> alunoList = turma.getAlunos();
        if (alunoList.isEmpty()) {
            return null;
        }
        return alunoList.get(FakerUtil.getIndex(alunoList.size()));
    }
}
