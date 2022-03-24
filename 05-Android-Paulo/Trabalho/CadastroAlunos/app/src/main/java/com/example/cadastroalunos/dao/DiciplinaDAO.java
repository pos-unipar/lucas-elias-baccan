package com.example.cadastroalunos.dao;

import android.util.Log;

import com.example.cadastroalunos.util.FakerUtil;
import com.example.cadastroalunos.model.Diciplina;

import java.util.ArrayList;
import java.util.List;

public class DiciplinaDAO {

    static String nome = "DICIPLINA";

    public static long salvar(Diciplina model) {
        try {
            return model.save();
        } catch (Exception ex) {
            Log.e("Erro", "(" + nome + ") Erro ao salvar: " + ex.getMessage());
            return -1;
        }
    }

    public static Diciplina getById(Long id) {
        try {
            return Diciplina.findById(Diciplina.class, id);
        } catch (Exception ex) {
            Log.e("Erro", "(" + nome + ") Erro ao retornar: " + ex.getMessage());
            return null;
        }
    }

    public static List<Diciplina> getAll() {
        return getAll("", new String[]{}, "");
    }

    public static List<Diciplina> getAll(String where, String[] whereArgs, String orderBy) {
        List<Diciplina> list = new ArrayList<>();
        try {
            list = Diciplina.find(Diciplina.class, where, whereArgs, "", orderBy, "");
        } catch (Exception ex) {
            Log.e("Erro", "(" + nome + ") Erro ao retornar lista: " + ex.getMessage());
        }
        return list;
    }

    public static boolean delete(Diciplina model) {
        try {
            return Diciplina.delete(model);
        } catch (Exception ex) {
            Log.e("Erro", "(" + nome + ") Erro ao apagar: " + ex.getMessage());
            return false;
        }
    }

    public static Diciplina getAleatorio() {
        List<Diciplina> diciplinaList = getAll();
        if (diciplinaList.isEmpty()) {
            return null;
        }
        return diciplinaList.get(FakerUtil.getIndex(diciplinaList.size()));
    }

}
