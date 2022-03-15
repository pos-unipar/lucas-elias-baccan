package com.example.cadastroalunos.dao;

import android.util.Log;

import com.example.cadastroalunos.helpers.FakerHelper;
import com.example.cadastroalunos.model.Curso;

import java.util.ArrayList;
import java.util.List;

public class CursoDAO {

    static String nome = "CURSO";

    public static long salvar(Curso model) {
        try {
            return model.save();
        } catch (Exception ex) {
            Log.e("Erro", "(" + nome + ") Erro ao salvar: " + ex.getMessage());
            return -1;
        }
    }

    public static Curso getById(int id) {
        try {
            return Curso.findById(Curso.class, id);
        } catch (Exception ex) {
            Log.e("Erro", "(" + nome + ") Erro ao retornar: " + ex.getMessage());
            return null;
        }
    }

    public static List<Curso> getAll() {
        return getAll("", new String[]{}, "");
    }

    public static List<Curso> getAll(String where, String[] whereArgs, String orderBy) {
        List<Curso> list = new ArrayList<>();
        try {
            list = Curso.find(Curso.class, where, whereArgs, "", orderBy, "");
        } catch (Exception ex) {
            Log.e("Erro", "(" + nome + ") Erro ao retornar lista: " + ex.getMessage());
        }
        return list;
    }

    public static boolean delete(Curso model) {
        try {
            if(!AlunoDAO.getAll("curso = ?", new String[]{model.getId().toString()}, "").isEmpty())
                return false;

            return Curso.delete(model);
        } catch (Exception ex) {
            Log.e("Erro", "(" + nome + ") Erro ao apagar: " + ex.getMessage());
            return false;
        }
    }

    public static Curso getAleatorio() {
        List<Curso> cursoList = getAll();
        if (cursoList.isEmpty()) {
            return null;
        }
        return cursoList.get(FakerHelper.getIndex(cursoList.size()));
    }

}
