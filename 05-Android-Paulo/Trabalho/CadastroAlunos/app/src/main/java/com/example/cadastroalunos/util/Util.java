package com.example.cadastroalunos.util;

import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.cadastroalunos.R;
import com.google.android.material.snackbar.Snackbar;
import com.orm.SugarRecord;

import java.util.List;

public class Util {

    /***
     * SnackBar customizado
     * @param view: Onde será exibido
     * @param msg: Mensagem
     * @param tipo: tipo do icone 0-Erro, 1-Sucesso, 2-Atenção
     */
    public static void customSnackBar(View view, String msg, int tipo) {
        Snackbar snackbar = Snackbar.make(view, msg, Snackbar.LENGTH_LONG);
        View viewSnack = snackbar.getView();
        TextView tv = (TextView) viewSnack.findViewById(R.id.snackbar_text);
        if (tipo == 0)
            tv.setCompoundDrawablesRelativeWithIntrinsicBounds(R.mipmap.ic_cancel, 0, 0, 0);
        if (tipo == 1)
            tv.setCompoundDrawablesRelativeWithIntrinsicBounds(R.mipmap.ic_confirm, 0, 0, 0);

        snackbar.show();
    }

    public static int getIndexFromSpinner(Spinner spinner, String myString) {
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)) {
                return i + 1;
            }
        }

        return 0;
    }

    public static ArrayAdapter<String> getAutocompleteAdapter(Context context, List<? extends SugarRecord> objectList) {
        String[] itensList = new String[objectList.size()];
        for (int i = 0; i < objectList.size(); i++) {
            Object item = objectList.get(i);
            itensList[i] = item.toString();
        }
        //Criando o adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                context,
                android.R.layout.simple_dropdown_item_1line,
                itensList
        );
        return adapter;
    }

    public static int getSubstring(String string, String divider) {
        return Integer.parseInt(string.substring(0, string.indexOf(divider)).trim());
    }

}
