package com.example.cadastroalunos.activitys.boletim;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cadastroalunos.R;
import com.example.cadastroalunos.adapters.BoletimAdapter;
import com.example.cadastroalunos.dao.DiciplinaDAO;
import com.example.cadastroalunos.dao.TurmaDAO;
import com.example.cadastroalunos.model.Aluno;
import com.example.cadastroalunos.model.Boletim;
import com.example.cadastroalunos.model.Diciplina;
import com.example.cadastroalunos.model.Turma;
import com.example.cadastroalunos.util.Util;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class ListaBoletimActivity extends AppCompatActivity {

    private Turma turma;
    private Diciplina diciplina;

    private RecyclerView rvListaBoletim;
    private LinearLayout lnLista;
    private List<Boletim> listaABoletims = new ArrayList<>();

    private AutoCompleteTextView atTurma;
    private AutoCompleteTextView atDiciplina;
    private TextInputLayout atDiciplinaBtn;

    private Context context;

    public ListaBoletimActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_boletim);

        context = this;

        lnLista = findViewById(R.id.lnListaAluno);
        atTurma = findViewById(R.id.atTurma);
        atDiciplina = findViewById(R.id.atDiciplina);
        atDiciplinaBtn = findViewById(R.id.atDiciplinaBtn);

        atualizaLista();

        atTurma.setAdapter(Util.getAutocompleteAdapter(this, TurmaDAO.getAll()));
        atTurma.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        turma = TurmaDAO.getById(Util.getSubstring(adapterView.getAdapter().getItem(position).toString(), "-"));

                        atDiciplina.setAdapter(Util.getAutocompleteAdapter(context, turma.getDiciplinas()));
                        atDiciplina.setEnabled(true);
                        atDiciplina.setFocusable(true);
                        atDiciplinaBtn.setEnabled(true);
                        atDiciplinaBtn.setFocusable(true);
                        atualizaLista();
                    }
                }
        );

        atDiciplina.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        diciplina = DiciplinaDAO.getById(Util.getSubstring(adapterView.getAdapter().getItem(position).toString(), "-"));
                        atualizaLista();
                    }
                }
        );
    }

    public void atualizaLista() {
        List<Turma> turmaList = TurmaDAO.getAll();
        if (turma != null) {
            turmaList.clear();
            turmaList.add(TurmaDAO.getById(turma.getId()));
        }

        listaABoletims = new ArrayList<>();
        for (Turma t : turmaList) {
            List<Diciplina> diciplinaList = new ArrayList<>();
            if (diciplina == null) {
                diciplinaList = t.getDiciplinas();
            } else {
                diciplinaList.add(diciplina);
            }

            for (Diciplina d : diciplinaList) {
                for (Aluno a : t.getAlunos()) {
                    Boletim boletim = new Boletim(t, d, a);
                    listaABoletims.add(boletim);
                }
            }
        }

        rvListaBoletim = findViewById(R.id.rvListaBoletim);
        BoletimAdapter adapter = new BoletimAdapter(listaABoletims, this);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rvListaBoletim.setLayoutManager(llm);
        rvListaBoletim.setAdapter(adapter);
    }

    public void limparTurma(View view) {
        atDiciplina.setEnabled(false);
        atDiciplina.setFocusable(false);
        atDiciplinaBtn.setEnabled(false);
        atDiciplinaBtn.setFocusable(false);
        atDiciplina.setAdapter(null);
        atTurma.setText("");
        atDiciplina.setText("");

        turma = null;
        diciplina = null;

        atualizaLista();
    }

    public void limparDiciplina(View view) {
        atDiciplina.setText("");

        diciplina = null;

        atualizaLista();
    }
}