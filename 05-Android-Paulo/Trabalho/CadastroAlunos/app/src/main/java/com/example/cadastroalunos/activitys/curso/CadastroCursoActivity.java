package com.example.cadastroalunos.activitys.curso;

import android.content.Intent;
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

    private Curso curso;

    private TextInputEditText edNomeCurso;
    private LinearLayout lnPrincipal;

    private Menu optionsMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_curso);

        edNomeCurso = findViewById(R.id.edNomeCurso);
        lnPrincipal = findViewById(R.id.lnPrincipalCurso);

        Intent iin = getIntent();
        Bundle b = iin.getExtras();

        if (b != null) {
            Long id = (Long) b.get("id");
            curso = CursoDAO.getById(id.intValue());
            popularCampos(curso);
        }
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
        curso.setNome(edNomeCurso.getText().toString());

        if (CursoDAO.salvar(curso) > 0) {
            setResult(RESULT_OK);
            finish();
        } else {
            Util.customSnackBar(lnPrincipal, "Erro ao salvar o curso (" + curso.getNome() + ") " + "verifique o log", 0);
        }
    }

    public void deletar(){
        if(CursoDAO.delete(curso)){
            setResult(RESULT_OK);
            finish();
        } else {
            Util.customSnackBar(lnPrincipal, "Erro ao deletar o curso (" + curso.getNome() + ") " + "verifique o log", 0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflaterMenu = getMenuInflater();
        inflaterMenu.inflate(R.menu.menu_cadastro, menu);

        optionsMenu = menu;

        if(curso != null){
            optionsMenu.findItem(R.id.mn_deletar).setVisible(true);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mn_deletar:
                deletar();
                return true;
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
        popularCampos(curso);
    }

    private void popularCampos(Curso curso) {
        edNomeCurso.setText(curso.getNome());
    }
}