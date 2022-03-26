package com.example.cadastroalunos.activitys.frequencia;

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
import com.example.cadastroalunos.activitys.nota.CadastroNotaActivity;
import com.example.cadastroalunos.adapters.AlunoAdapter;
import com.example.cadastroalunos.adapters.DiciplinaAdapter;
import com.example.cadastroalunos.adapters.FrequenciaAdapter;
import com.example.cadastroalunos.dao.AlunoDAO;
import com.example.cadastroalunos.dao.CursoDAO;
import com.example.cadastroalunos.dao.FrequenciaDAO;
import com.example.cadastroalunos.dao.TurmaDAO;
import com.example.cadastroalunos.model.Frequencia;
import com.example.cadastroalunos.model.Nota;
import com.example.cadastroalunos.util.Util;

import java.util.ArrayList;
import java.util.List;

public class ListaFrequenciaActivity extends AppCompatActivity implements FrequenciaAdapter.OnListenner {

    private RecyclerView rvListaFrequencia;
    private LinearLayout lnLista;
    private List<Frequencia> listaFrequencia = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_frequencia);

        lnLista = findViewById(R.id.lnListaFrequencia);

        atualizaLista();
    }

    public void atualizaLista(){
        listaFrequencia = FrequenciaDAO.getAll("", new String[]{}, "id asc");

        rvListaFrequencia = findViewById(R.id.rvListaFrequencia);
        FrequenciaAdapter adapter = new FrequenciaAdapter(listaFrequencia, this, this);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rvListaFrequencia.setLayoutManager(llm);
        rvListaFrequencia.setAdapter(adapter);
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

        Intent intent = new Intent(this, CadastroFrequenciaActivity.class);
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
        Intent intent = new Intent(this, CadastroFrequenciaActivity.class);
        intent.putExtra("id", listaFrequencia.get(position).getId());
        startActivityForResult(intent, 1);
    }
}