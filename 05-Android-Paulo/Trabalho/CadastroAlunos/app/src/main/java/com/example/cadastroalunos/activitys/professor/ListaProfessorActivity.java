package com.example.cadastroalunos.activitys.professor;

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
import com.example.cadastroalunos.activitys.aluno.CadastroAlunoActivity;
import com.example.cadastroalunos.adapters.ProfessorAdapter;
import com.example.cadastroalunos.dao.CursoDAO;
import com.example.cadastroalunos.dao.ProfessorDAO;
import com.example.cadastroalunos.model.Professor;
import com.example.cadastroalunos.util.Util;

import java.util.ArrayList;
import java.util.List;

public class ListaProfessorActivity extends AppCompatActivity implements ProfessorAdapter.OnListenner {

    private RecyclerView rvListaProfessor;
    private LinearLayout lnLista;
    private List<Professor> professorList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_professor);

        lnLista = findViewById(R.id.lnListaProfessor);

        atualizaLista();
    }

    public void atualizaLista(){
        professorList = ProfessorDAO.getAll("", new String[]{}, "nome asc");

        rvListaProfessor = findViewById(R.id.rvListaProfessor);
        ProfessorAdapter adapter = new ProfessorAdapter(professorList, this, this);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rvListaProfessor.setLayoutManager(llm);
        rvListaProfessor.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflaterMenu = getMenuInflater();
        inflaterMenu.inflate(R.menu.menu_lista, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mn_add:
                abrirCadastro();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void abrirCadastro() {
        if(CursoDAO.getAll().isEmpty()) {
            Util.customSnackBar(lnLista, "Não existem cursos cadastrados!", 2);
            return;
        }

        Intent intent = new Intent(this, CadastroProfessorActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            Util.customSnackBar(lnLista, "Sucesso ao executar ação!", 1);
        }
        atualizaLista();
    }

    @Override
    public void onListennerClick(int position) {
        Intent intent = new Intent(this, CadastroProfessorActivity.class);
        intent.putExtra("id", professorList.get(position).getId());
        startActivityForResult(intent, 1);
    }
}