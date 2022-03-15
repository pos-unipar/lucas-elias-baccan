package com.example.cadastroalunos.activitys.curso;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cadastroalunos.R;
import com.example.cadastroalunos.adapters.CursoAdapter;
import com.example.cadastroalunos.dao.CursoDAO;
import com.example.cadastroalunos.model.Curso;
import com.example.cadastroalunos.util.Util;

import java.util.ArrayList;
import java.util.List;

public class ListaCursoActivity extends AppCompatActivity implements CursoAdapter.OnCursoListenner {

    private RecyclerView rvListaCursos;
    private LinearLayout lnLista;
    private List<Curso> cursoList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_curso);

        lnLista = findViewById(R.id.lnListaCurso);

        atualizaLista();
    }

    public void atualizaLista() {
        cursoList = CursoDAO.getAll("", new String[]{}, "nome asc");
        Log.e("PHS", "Tamanho da lista: " + cursoList.size());

        rvListaCursos = findViewById(R.id.rvListaCursos);
        CursoAdapter adapter = new CursoAdapter(cursoList, this, this);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rvListaCursos.setLayoutManager(llm);
        rvListaCursos.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflaterMenu = getMenuInflater();
        inflaterMenu.inflate(R.menu.menu_lista, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mn_add:
                abrirCadastro();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void abrirCadastro() {
        Intent intent = new Intent(this, CadastroCursoActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Util.customSnackBar(lnLista, "Sucesso ao executar ação!", 1);
        }
        atualizaLista();
    }

    @Override
    public void onCursoClick(int position) {
        Intent intent = new Intent(this, CadastroCursoActivity.class);
        intent.putExtra("id", cursoList.get(position).getId());
        startActivityForResult(intent, 1);
    }
}