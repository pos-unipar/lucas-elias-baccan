package com.example.cadastroalunos.dao;

import android.util.Log;

import com.example.cadastroalunos.helpers.FakerHelper;
import com.example.cadastroalunos.model.Professor;

import java.util.ArrayList;
import java.util.List;

public class ProfessorDAO {

    static String nome = "PROFESSOR";

    public static long salvar(Professor model) {
        try {
            return model.save();
        } catch (Exception ex) {
            Log.e("Erro", "(" + nome + ") Erro ao salvar: " + ex.getMessage());
            return -1;
        }
    }

    public static Professor getById(int id) {
        try {
            return Professor.findById(Professor.class, id);
        } catch (Exception ex) {
            Log.e("Erro", "(" + nome + ") Erro ao retornar: " + ex.getMessage());
            return null;
        }
    }

    public static List<Professor> getAll() {
        return getAll("", new String[]{}, "");
    }

    public static List<Professor> getAll(String where, String[] whereArgs, String orderBy) {
        List<Professor> list = new ArrayList<>();
        try {
            list = Professor.find(Professor.class, where, whereArgs, "", orderBy, "");
        } catch (Exception ex) {
            Log.e("Erro", "(" + nome + ") Erro ao retornar lista: " + ex.getMessage());
        }
        return list;
    }

    public static boolean delete(Professor model) {
        try {

            // Validar se o professor está em uma diciplina
//            if (!AlunoDAO.getAll("curso = ?", new String[]{model.getId().toString()}, "").isEmpty())
//                return false;

            return Professor.delete(model);
        } catch (Exception ex) {
            Log.e("Erro", "(" + nome + ") Erro ao apagar: " + ex.getMessage());
            return false;
        }
    }

    public static Professor getAleatorio() {
        List<Professor> professorList = getAll();
        if (professorList.isEmpty()) {
            return null;
        }
        return professorList.get(FakerHelper.getIndex(professorList.size()));
    }

}
