package com.example.cadastroalunos.activitys.frequencia;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cadastroalunos.R;
import com.example.cadastroalunos.dao.AlunoDAO;
import com.example.cadastroalunos.dao.FrequenciaDAO;
import com.example.cadastroalunos.dao.TurmaDAO;
import com.example.cadastroalunos.model.Aluno;
import com.example.cadastroalunos.model.Diciplina;
import com.example.cadastroalunos.model.Frequencia;
import com.example.cadastroalunos.model.Turma;
import com.example.cadastroalunos.util.FakerUtil;
import com.example.cadastroalunos.util.Util;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class CadastroFrequenciaActivity extends AppCompatActivity {

    private Turma turma;
    private Diciplina diciplina;
    private Frequencia frequencia;
    private List<Aluno> alunoList = new ArrayList<>();

    private AutoCompleteTextView atTurma;
    private AutoCompleteTextView atDiciplina;
    private TextInputLayout atDiciplinaBtn;
    private ListView lista;

    private LinearLayout lnPrincipal;

    private Menu optionsMenu;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_frequencia);

        context = this;

        atTurma = findViewById(R.id.atTurma);
        atDiciplina = findViewById(R.id.atDiciplina);
        atDiciplinaBtn = findViewById(R.id.atDiciplinaBtn);
        lista = findViewById(R.id.listaAluno);

        lnPrincipal = findViewById(R.id.lnPrincipal);

        lista.setAdapter(Util.getChoiceAdapter(this, alunoList));

        atTurma.setAdapter(Util.getAutocompleteAdapter(this, TurmaDAO.getAll()));
        atTurma.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        turma = TurmaDAO.getById(Util.getSubstring(adapterView.getAdapter().getItem(position).toString(), "-"));
                        atDiciplina.setAdapter(Util.getAutocompleteAdapter(context, turma.getDiciplinas()));
                        atDiciplina.setEnabled(true);
                        atDiciplina.setFocusable(true);
                        atDiciplinaBtn.setEnabled(true);
                        atDiciplinaBtn.setFocusable(true);

                        alunoList = turma.getAlunos();
                        lista.setAdapter(Util.getChoiceAdapter(context, alunoList));

                    }
                }
        );

        atDiciplina.setEnabled(false);
        atDiciplina.setFocusable(false);
        atDiciplinaBtn.setEnabled(false);
        atDiciplinaBtn.setFocusable(false);

        Intent iin = getIntent();
        Bundle b = iin.getExtras();

        if (b != null) {
            Long id = (Long) b.get("id");
            frequencia = FrequenciaDAO.getById(id);
            alunoList.clear();
            alunoList.add(frequencia.getAluno());
            lista.setAdapter(Util.getChoiceAdapter(this, alunoList));
            lista.setItemChecked(0,frequencia.isPresente());
            popularCampos(frequencia);
        } else {
            frequencia = new Frequencia();
        }
    }

    private void validaCampos() {
        if (atTurma.getText().toString().equals("")) {
            atTurma.setError("Informe a turma!");
            atTurma.requestFocus();
            return;
        }

        if (atDiciplina.getText().toString().equals("")) {
            atDiciplina.setError("Informe a diciplina!");
            atDiciplina.requestFocus();
            return;
        }

        salvar();
    }

    public void salvar() {
        try {
            if (frequencia.getId() == null) {
                for (int i = 0; i < lista.getCount(); i++) {
                    Frequencia f = new Frequencia(
                            AlunoDAO.getByRa(Util.getSubstring(lista.getItemAtPosition(i).toString(), "-")),
                            turma,
                            diciplina,
                            lista.isItemChecked(i)
                    );
                    FrequenciaDAO.salvar(f);
                }
            } else {
                frequencia.setTurma(turma);
                frequencia.setDiciplina(diciplina);
                frequencia.setPresente(lista.isItemChecked(0));
                FrequenciaDAO.salvar(frequencia);
            }
            setResult(RESULT_OK);
            finish();
        } catch (Exception e) {
            Util.customSnackBar(lnPrincipal, "Erro ao salvar o professor (" + diciplina.getNome() + ") " + "verifique o log", 0);
        }
    }

    public void deletar() {
        if (FrequenciaDAO.delete(frequencia)) {
            setResult(RESULT_OK);
            finish();
        } else {
            Util.customSnackBar(lnPrincipal, "Erro ao deletar o curso (" + frequencia.getId() + ") " + "verifique o log", 0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflaterMenu = getMenuInflater();
        inflaterMenu.inflate(R.menu.menu_cadastro, menu);

        optionsMenu = menu;

        if (frequencia != null && frequencia.getId() != null) {
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
        atDiciplina.setEnabled(false);
        atDiciplina.setFocusable(false);
        atDiciplinaBtn.setEnabled(false);
        atDiciplinaBtn.setFocusable(false);
        atTurma.setText("");
    }

    public void limparTurma(View view) {
        atDiciplina.setEnabled(false);
        atDiciplina.setFocusable(false);
        atDiciplinaBtn.setEnabled(false);
        atDiciplinaBtn.setFocusable(false);
        atDiciplina.setAdapter(null);
        atTurma.setText("");
        atDiciplina.setText("");
    }

    public void limparDiciplina(View view) {
        atDiciplina.setText("");
    }

    private void gerarDados() {
        turma = TurmaDAO.getAleatorio();
        diciplina = turma.getDiciplinas().get(FakerUtil.getIndex(turma.getDiciplinas().size()));

        if (frequencia.getId() != null) {
            frequencia.setTurma(turma);
            frequencia.setDiciplina(diciplina);
        }

        atDiciplina.setEnabled(true);
        atDiciplina.setFocusable(true);
        atDiciplinaBtn.setEnabled(true);
        atDiciplinaBtn.setFocusable(true);

        alunoList = turma.getAlunos();
        lista.setAdapter(Util.getChoiceAdapter(this, alunoList));

        popularCampos(frequencia);
    }

    private void popularCampos(Frequencia frequencia) {
        if (frequencia.getId() == null) {
            atTurma.setText(turma.toString());
            atDiciplina.setText(diciplina.toString());
            alunoList = turma.getAlunos();
        } else {
            atTurma.setText(frequencia.getTurma().toString());
            atDiciplina.setText(frequencia.getDiciplina().toString());
            alunoList = new ArrayList<>();
            alunoList.add(frequencia.getAluno());

            turma = frequencia.getTurma();
            diciplina = frequencia.getDiciplina();
        }
    }
}