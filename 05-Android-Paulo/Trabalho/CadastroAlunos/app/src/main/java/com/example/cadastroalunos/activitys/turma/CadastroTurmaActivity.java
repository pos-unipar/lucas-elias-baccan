package com.example.cadastroalunos.activitys.turma;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cadastroalunos.R;
import com.example.cadastroalunos.dao.CursoDAO;
import com.example.cadastroalunos.dao.ProfessorDAO;
import com.example.cadastroalunos.dao.TurmaDAO;
import com.example.cadastroalunos.enums.PeriodoEnum;
import com.example.cadastroalunos.enums.RegimeEnum;
import com.example.cadastroalunos.model.Curso;
import com.example.cadastroalunos.model.Diciplina;
import com.example.cadastroalunos.model.Professor;
import com.example.cadastroalunos.model.Turma;
import com.example.cadastroalunos.util.FakerUtil;
import com.example.cadastroalunos.util.Util;

import fr.ganfra.materialspinner.MaterialSpinner;

public class CadastroTurmaActivity extends AppCompatActivity {

    private Turma turma;

    private AutoCompleteTextView atCurso;
    private MaterialSpinner spRegime;

    private LinearLayout lnPrincipal;

    private Menu optionsMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_turma);

        atCurso = findViewById(R.id.atCurso);

        iniciaSpinners();

        atCurso.setAdapter(Util.getAutocompleteAdapter(this, CursoDAO.getAll()));

        Intent iin = getIntent();
        Bundle b = iin.getExtras();

        if (b != null) {
            Long id = (Long) b.get("id");
            turma = TurmaDAO.getById(id.intValue());
            popularCampos(turma);
        } else {
            turma = new Turma();
        }
    }

    private void iniciaSpinners() {
        spRegime = findViewById(R.id.spRegime);

        RegimeEnum regimes[] = RegimeEnum.values();

        ArrayAdapter adapterPeriodo = new ArrayAdapter(this, android.R.layout.simple_list_item_1, regimes);

        spRegime.setAdapter(adapterPeriodo);
    }


    private void validaCampos() {
        if (atCurso.getText().toString().equals("")) {
            atCurso.setError("Informe o curso!");
            atCurso.requestFocus();
            return;
        }

        if (spRegime.getSelectedItem() == null) {
            spRegime.setError("Informe o regime!");
            spRegime.requestFocus();
            return;
        }

        salvar();
    }

    public void salvar() {
        turma.setCurso(CursoDAO.getById(Util.getSubstring(atCurso.getText().toString(), "-")));
        turma.setRegime((RegimeEnum) spRegime.getSelectedItem());

        if (TurmaDAO.salvar(turma) > 0) {
            setResult(RESULT_OK);
            finish();
        } else {
            Util.customSnackBar(lnPrincipal, "Erro ao salvar a turma (" + turma.getId() + ") " + "verifique o log", 0);
        }
    }

    public void deletar() {
        if (TurmaDAO.delete(turma)) {
            setResult(RESULT_OK);
            finish();
        } else {
            Util.customSnackBar(lnPrincipal, "Erro ao deletar a turma (" + turma.getId() + ") " + "verifique o log", 0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflaterMenu = getMenuInflater();
        inflaterMenu.inflate(R.menu.menu_cadastro, menu);

        optionsMenu = menu;

        if (turma != null && turma.getId() != null) {
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
        atCurso.setText("");
        spRegime.setSelection(0);
    }

    private void gerarDados() {
        final Curso curso = CursoDAO.getAleatorio();
        final Turma turma = FakerUtil.gerarTurmaFake(false, curso);

        popularCampos(turma);
    }

    private void popularCampos(Turma turma) {
        atCurso.setText(turma.getCurso().toString());
        spRegime.setSelection(Util.getIndexFromSpinner(spRegime, turma.getRegime().toString()));
    }
}