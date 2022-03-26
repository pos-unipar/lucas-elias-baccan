package com.example.cadastroalunos.dao;

import android.util.Log;

import com.example.cadastroalunos.model.Nota;
import com.example.cadastroalunos.util.FakerUtil;

import java.util.ArrayList;
import java.util.List;

public class NotaDAO {

    static String nome = "NOTA";

    public static long salvar(Nota model) {
        try {
            return model.save();
        } catch (Exception ex) {
            Log.e("Erro", "(" + nome + ") Erro ao salvar: " + ex.getMessage());
            return -1;
        }
    }

    public static Nota getById(long id) {
        try {
            return Nota.findById(Nota.class, id);
        } catch (Exception ex) {
            Log.e("Erro", "(" + nome + ") Erro ao retornar: " + ex.getMessage());
            return null;
        }
    }

    public static List<Nota> getAll() {
        return getAll("", new String[]{}, "");
    }

    public static List<Nota> getAll(String where, String[] whereArgs, String orderBy) {
        List<Nota> list = new ArrayList<>();
        try {
            list = Nota.find(Nota.class, where, whereArgs, "", orderBy, "");
        } catch (Exception ex) {
            Log.e("Erro", "(" + nome + ") Erro ao retornar lista: " + ex.getMessage());
        }
        return list;
    }

    public static boolean delete(Nota model) {
        try {
            return Nota.delete(model);
        } catch (Exception ex) {
            Log.e("Erro", "(" + nome + ") Erro ao apagar: " + ex.getMessage());
            return false;
        }
    }

    public static Nota getAleatorio() {
        List<Nota> cursoList = getAll();
        if (cursoList.isEmpty()) {
            return null;
        }
        return cursoList.get(FakerUtil.getIndex(cursoList.size()));
    }

}
