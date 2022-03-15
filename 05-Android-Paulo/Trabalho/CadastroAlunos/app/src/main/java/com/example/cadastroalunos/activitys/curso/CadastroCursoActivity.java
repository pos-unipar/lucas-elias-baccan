package com.example.cadastroalunos.activitys.curso;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cadastroalunos.R;
import com.example.cadastroalunos.dao.CursoDAO;
import com.example.cadastroalunos.helpers.FakerHelper;
import com.example.cadastroalunos.model.Curso;
import com.example.cadastroalunos.util.Util;
import com.google.android.material.textfield.TextInputEditText;

public class CadastroCursoActivity extends AppCompatActivity {

    private TextInputEditText edNomeCurso;
    private LinearLayout lnPrincipal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_curso);

        edNomeCurso = findViewById(R.id.edNomeCurso);
        lnPrincipal = findViewById(R.id.lnPrincipalCurso);
    }

    //Validação dos campos
    private void validaCampos() {
        //Valida o campo de nome do curso
        if (edNomeCurso.getText().toString().equals("")) {
            edNomeCurso.setError("Informe o Nome do curso!");
            edNomeCurso.requestFocus();
            return;
        }

        salvar();
    }

    public void salvar() {
        Curso curso = new Curso();
        curso.setNome(edNomeCurso.getText().toString());

        if (CursoDAO.salvar(curso) > 0) {
            setResult(RESULT_OK);
            finish();
        } else {
            Util.customSnackBar(lnPrincipal, "Erro ao salvar o curso (" + curso.getNome() + ") " + "verifique o log", 0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflaterMenu = getMenuInflater();
        inflaterMenu.inflate(R.menu.menu_cadastro, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mn_gerar_dados:
                gerarDados();
                return true;
            case R.id.mn_limpar:
                limparCampos();
                return true;
            case R.id.mn_salvar:
                validaCampos();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void limparCampos() {
        edNomeCurso.setText("");
    }

    private void gerarDados() {
        final Curso curso = FakerHelper.gerarCursoFake(false);

        edNomeCurso.setText(curso.getNome());
    }
}