package com.example.cadastroalunos.activitys.turma;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.example.cadastroalunos.R;
import com.example.cadastroalunos.adapters.TurmaAdapter;
import com.example.cadastroalunos.dao.CursoDAO;
import com.example.cadastroalunos.dao.TurmaDAO;
import com.example.cadastroalunos.model.Turma;
import com.example.cadastroalunos.util.Util;

import java.util.ArrayList;
import java.util.List;

public class ListaTurmaActivity extends AppCompatActivity implements TurmaAdapter.OnListenner {

    private RecyclerView rvListaTurmas;
    private LinearLayout lnListaTurma;
    private List<Turma> turmaList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_turma);

        lnListaTurma = findViewById(R.id.lnListaTurma);

        atualizaLista();
    }

    public void atualizaLista() {
        turmaList = TurmaDAO.getAll("", new String[]{}, "id asc");

        rvListaTurmas = findViewById(R.id.rvListaTurmas);
        TurmaAdapter adapter = new TurmaAdapter(turmaList, this, this);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rvListaTurmas.setLayoutManager(llm);
        rvListaTurmas.setAdapter(adapter);
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
        if(CursoDAO.getAll().isEmpty()) {
            Util.customSnackBar(lnListaTurma, "Não existem cursos cadastrados!", 2);
            return;
        }

        Intent intent = new Intent(this, CadastroTurmaActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Util.customSnackBar(lnListaTurma, "Sucesso ao executar ação!", 1);
        }
        atualizaLista();
    }

    @Override
    public void onListennerClick(int position) {
        Intent intent = new Intent(this, CadastroTurmaActivity.class);
        intent.putExtra("id", turmaList.get(position).getId());
        startActivityForResult(intent, 1);
    }
}