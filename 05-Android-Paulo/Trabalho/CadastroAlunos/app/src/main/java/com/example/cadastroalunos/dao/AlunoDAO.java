package com.example.cadastroalunos.dao;

import android.util.Log;

import com.example.cadastroalunos.model.Professor;
import com.example.cadastroalunos.util.FakerUtil;
import com.example.cadastroalunos.model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    static String nome = "ALUNO";

    public static long salvar(Aluno model) {
        try {
            return model.save();
        } catch (Exception ex) {
            Log.e("Erro", "(" + nome + ") Erro ao salvar: " + ex.getMessage());
            return -1;
        }
    }

    public static Aluno getById(int id) {
        try {
            return Aluno.findById(Aluno.class, id);
        } catch (Exception ex) {
            Log.e("Erro", "(" + nome + ") Erro ao retornar: " + ex.getMessage());
            return null;
        }
    }

    public static Aluno getByRa(int ra) {
        try {
            List<Aluno> alunoList = getAll("ra = ?", new String[]{String.valueOf(ra)}, "");
            if (!alunoList.isEmpty()) {
                return alunoList.get(0);
            }
            return null;
        } catch (Exception ex) {
            Log.e("Erro", "(" + nome + ") Erro ao retornar: " + ex.getMessage());
            return null;
        }
    }

    public static List<Aluno> getAll() {
        return getAll("", new String[]{}, "");
    }

    public static List<Aluno> getAll(String where, String[] whereArgs, String orderBy) {
        List<Aluno> list = new ArrayList<>();
        try {
            list = Aluno.find(Aluno.class, where, whereArgs, "", orderBy, "");
        } catch (Exception ex) {
            Log.e("Erro", "(" + nome + ") Erro ao retornar lista: " + ex.getMessage());
        }
        return list;
    }

    public static boolean delete(Aluno model) {
        try {
            return Aluno.delete(model);
        } catch (Exception ex) {
            Log.e("Erro", "(" + nome + ") Erro ao apagar: " + ex.getMessage());
            return false;
        }
    }

    public static Aluno getAleatorio() {
        List<Aluno> alunoList = getAll();
        if (alunoList.isEmpty()) {
            return null;
        }
        return alunoList.get(FakerUtil.getIndex(alunoList.size()));
    }

}
