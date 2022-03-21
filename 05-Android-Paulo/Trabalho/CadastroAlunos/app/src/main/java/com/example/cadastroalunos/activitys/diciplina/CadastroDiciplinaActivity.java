package com.example.cadastroalunos.activitys.diciplina;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cadastroalunos.R;
import com.example.cadastroalunos.dao.DiciplinaDAO;
import com.example.cadastroalunos.dao.ProfessorDAO;
import com.example.cadastroalunos.model.Diciplina;
import com.example.cadastroalunos.model.Professor;
import com.example.cadastroalunos.util.FakerUtil;
import com.example.cadastroalunos.util.Util;
import com.google.android.material.textfield.TextInputEditText;

public class CadastroDiciplinaActivity extends AppCompatActivity {

    private Diciplina diciplina;

    private TextInputEditText edNomeDiciplina;
    private AutoCompleteTextView atProfessor;

    private LinearLayout lnPrincipal;

    private Menu optionsMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_diciplina);

        edNomeDiciplina = findViewById(R.id.edNomeDiciplina);
        atProfessor = findViewById(R.id.atProfessor);

        lnPrincipal = findViewById(R.id.lnPrincipalDiciplina);

        atProfessor.setAdapter(Util.getAutocompleteAdapter(this, ProfessorDAO.getAll()));

        Intent iin = getIntent();
        Bundle b = iin.getExtras();

        if (b != null) {
            Long id = (Long) b.get("id");
            diciplina = DiciplinaDAO.getById(id.intValue());
            popularCampos(diciplina);
        } else {
            diciplina = new Diciplina();
        }
    }

    private void validaCampos() {
        if (edNomeDiciplina.getText().toString().equals("")) {
            edNomeDiciplina.setError("Informe o Nome da diciplina!");
            edNomeDiciplina.requestFocus();
            return;
        }

        if (atProfessor.getText().toString().equals("")) {
            atProfessor.setError("Informe o professor!");
            atProfessor.requestFocus();
            return;
        }

        salvar();
    }

    public void salvar() {
        diciplina.setNome(edNomeDiciplina.getText().toString());
        diciplina.setProfessor(ProfessorDAO.getByRa(Util.getSubstring(atProfessor.getText().toString(), "-")));

        if (DiciplinaDAO.salvar(diciplina) > 0) {
            setResult(RESULT_OK);
            finish();
        } else {
            Util.customSnackBar(lnPrincipal, "Erro ao salvar o professor (" + diciplina.getNome() + ") " + "verifique o log", 0);
        }
    }

    public void deletar() {
        if (DiciplinaDAO.delete(diciplina)) {
            setResult(RESULT_OK);
            finish();
        } else {
            Util.customSnackBar(lnPrincipal, "Erro ao deletar o curso (" + diciplina.getNome() + ") " + "verifique o log", 0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflaterMenu = getMenuInflater();
        inflaterMenu.inflate(R.menu.menu_cadastro, menu);

        optionsMenu = menu;

        if (diciplina != null && diciplina.getId() != null) {
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
        edNomeDiciplina.setText("");
        atProfessor.setText("");
    }

    private void gerarDados() {
        final Professor professor = ProfessorDAO.getAleatorio();
        final Diciplina diciplina = FakerUtil.gerarDiciplinaFake(false, professor);

        popularCampos(diciplina);
    }

    private void popularCampos(Diciplina diciplina) {
        edNomeDiciplina.setText(diciplina.getNome());
        atProfessor.setText(diciplina.getProfessor().toString());
    }

}