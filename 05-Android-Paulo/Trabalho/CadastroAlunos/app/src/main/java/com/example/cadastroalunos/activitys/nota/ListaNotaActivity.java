package com.example.cadastroalunos.activitys.nota;

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
import com.example.cadastroalunos.adapters.NotaAdapter;
import com.example.cadastroalunos.dao.AlunoDAO;
import com.example.cadastroalunos.dao.CursoDAO;
import com.example.cadastroalunos.dao.NotaDAO;
import com.example.cadastroalunos.dao.TurmaDAO;
import com.example.cadastroalunos.model.Aluno;
import com.example.cadastroalunos.model.Nota;
import com.example.cadastroalunos.util.Util;

import java.util.ArrayList;
import java.util.List;

public class ListaNotaActivity extends AppCompatActivity implements NotaAdapter.OnListenner {

    private RecyclerView rvListaNotas;
    private LinearLayout lnLista;
    private List<Nota> listaNota = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_nota);

        lnLista = findViewById(R.id.lnListaNota);

        atualizaLista();
    }

    public void atualizaLista(){
        listaNota = NotaDAO.getAll("", new String[]{}, "id asc");

        rvListaNotas = findViewById(R.id.rvListaNota);
        NotaAdapter adapter = new NotaAdapter(listaNota, this, this);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rvListaNotas.setLayoutManager(llm);
        rvListaNotas.setAdapter(adapter);
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
        if(TurmaDAO.getAll().isEmpty()) {
            Util.customSnackBar(lnLista, "Não existem turmas cadastradas!", 2);
            return;
        }

        Intent intent = new Intent(this, CadastroNotaActivity.class);
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
        Intent intent = new Intent(this, CadastroNotaActivity.class);
        intent.putExtra("id", listaNota.get(position).getId());
        startActivityForResult(intent, 1);
    }
}