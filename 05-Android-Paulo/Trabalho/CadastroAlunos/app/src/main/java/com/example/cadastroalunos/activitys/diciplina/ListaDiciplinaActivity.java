package com.example.cadastroalunos.activitys.diciplina;

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
import com.example.cadastroalunos.adapters.AlunoAdapter;
import com.example.cadastroalunos.adapters.DiciplinaAdapter;
import com.example.cadastroalunos.dao.AlunoDAO;
import com.example.cadastroalunos.dao.CursoDAO;
import com.example.cadastroalunos.dao.DiciplinaDAO;
import com.example.cadastroalunos.dao.ProfessorDAO;
import com.example.cadastroalunos.model.Aluno;
import com.example.cadastroalunos.model.Diciplina;
import com.example.cadastroalunos.util.Util;

import java.util.ArrayList;
import java.util.List;

public class ListaDiciplinaActivity extends AppCompatActivity implements DiciplinaAdapter.OnListenner {

    private RecyclerView rvListaDiciplina;
    private LinearLayout lnLista;
    private List<Diciplina> listaDiciplina = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_diciplina);

        lnLista = findViewById(R.id.lnListaDiciplina);

        atualizaLista();
    }

    public void atualizaLista(){
        listaDiciplina = DiciplinaDAO.getAll("", new String[]{}, "nome asc");

        rvListaDiciplina = findViewById(R.id.rvListDiciplinas);
        DiciplinaAdapter adapter = new DiciplinaAdapter(listaDiciplina, this, this);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rvListaDiciplina.setLayoutManager(llm);
        rvListaDiciplina.setAdapter(adapter);
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
        if(ProfessorDAO.getAll().isEmpty()) {
            Util.customSnackBar(lnLista, "Não existem professores cadastrados!", 2);
            return;
        }

        Intent intent = new Intent(this, CadastroDiciplinaActivity.class);
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
        Intent intent = new Intent(this, CadastroDiciplinaActivity.class);
        intent.putExtra("id", listaDiciplina.get(position).getId());
        startActivityForResult(intent, 1);
    }
}