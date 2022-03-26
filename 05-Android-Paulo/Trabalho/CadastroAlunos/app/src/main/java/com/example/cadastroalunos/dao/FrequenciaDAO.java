package com.example.cadastroalunos.dao;

import android.util.Log;

import com.example.cadastroalunos.model.Frequencia;
import com.example.cadastroalunos.util.FakerUtil;

import java.util.ArrayList;
import java.util.List;

public class FrequenciaDAO {

    static String nome = "FREQUENCIA";

    public static long salvar(Frequencia model) {
        try {
            return model.save();
        } catch (Exception ex) {
            Log.e("Erro", "(" + nome + ") Erro ao salvar: " + ex.getMessage());
            return -1;
        }
    }

    public static Frequencia getById(long id) {
        try {
            return Frequencia.findById(Frequencia.class, id);
        } catch (Exception ex) {
            Log.e("Erro", "(" + nome + ") Erro ao retornar: " + ex.getMessage());
            return null;
        }
    }

    public static List<Frequencia> getAll() {
        return getAll("", new String[]{}, "");
    }

    public static List<Frequencia> getAll(String where, String[] whereArgs, String orderBy) {
        List<Frequencia> list = new ArrayList<>();
        try {
            list = Frequencia.find(Frequencia.class, where, whereArgs, "", orderBy, "");
        } catch (Exception ex) {
            Log.e("Erro", "(" + nome + ") Erro ao retornar lista: " + ex.getMessage());
        }
        return list;
    }

    public static boolean delete(Frequencia model) {
        try {
            return Frequencia.delete(model);
        } catch (Exception ex) {
            Log.e("Erro", "(" + nome + ") Erro ao apagar: " + ex.getMessage());
            return false;
        }
    }

    public static Frequencia getAleatorio() {
        List<Frequencia> cursoList = getAll();
        if (cursoList.isEmpty()) {
            return null;
        }
        return cursoList.get(FakerUtil.getIndex(cursoList.size()));
    }

}
