package com.example.cadastroalunos.activitys.nota;

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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cadastroalunos.R;
import com.example.cadastroalunos.dao.AlunoDAO;
import com.example.cadastroalunos.dao.DiciplinaDAO;
import com.example.cadastroalunos.dao.NotaDAO;
import com.example.cadastroalunos.dao.TurmaDAO;
import com.example.cadastroalunos.enums.RegimeEnum;
import com.example.cadastroalunos.model.Nota;
import com.example.cadastroalunos.util.FakerUtil;
import com.example.cadastroalunos.util.Util;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class CadastroNotaActivity extends AppCompatActivity {

    private Nota nota;

    private AutoCompleteTextView atTurma;
    private AutoCompleteTextView atDiciplina;
    private TextInputLayout atDiciplinaBtn;
    private AutoCompleteTextView atAluno;
    private TextInputLayout atAlunoBtn;

    private TextInputEditText edNota1;
    private TextInputEditText edNota2;
    private TextInputEditText edNota3;
    private TextInputLayout edNota3Input;
    private TextInputEditText edNota4;
    private TextInputLayout edNota4Input;

    private LinearLayout lnPrincipal;

    private Menu optionsMenu;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_nota);

        context = this;

        atTurma = findViewById(R.id.atTurma);
        atDiciplina = findViewById(R.id.atDiciplina);
        atDiciplinaBtn = findViewById(R.id.atDiciplinaBtn);
        atAluno = findViewById(R.id.atAluno);
        atAlunoBtn = findViewById(R.id.atAlunoBtn);

        edNota1 = findViewById(R.id.edNota1);
        edNota2 = findViewById(R.id.edNota2);
        edNota3 = findViewById(R.id.edNota3);
        edNota4 = findViewById(R.id.edNota4);
        edNota3Input = findViewById(R.id.edNota3Input);
        edNota4Input = findViewById(R.id.edNota4Input);

        lnPrincipal = findViewById(R.id.lnPrincipal);

        Intent iin = getIntent();
        Bundle b = iin.getExtras();

        if (b != null) {
            Long id = (Long) b.get("id");
            nota = NotaDAO.getById(id);
            if (nota.getTurma().getRegime() == RegimeEnum.SEMESTRAL) {
                edNota3Input.setVisibility(View.GONE);
                edNota4Input.setVisibility(View.GONE);
            }
            popularCampos(nota);
        } else {
            nota = new Nota();
            popularCampos(nota);
        }

        atTurma.setAdapter(Util.getAutocompleteAdapter(this, TurmaDAO.getAll()));
        atTurma.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        nota.setTurma(TurmaDAO.getById(Util.getSubstring(adapterView.getAdapter().getItem(position).toString(), "-")));

                        atDiciplina.setAdapter(Util.getAutocompleteAdapter(context, nota.getTurma().getDiciplinas()));
                        atDiciplina.setEnabled(true);
                        atDiciplina.setFocusable(true);
                        atDiciplinaBtn.setEnabled(true);
                        atDiciplinaBtn.setFocusable(true);

                        atAluno.setAdapter(Util.getAutocompleteAdapter(context, nota.getTurma().getAlunos()));
                        atAluno.setEnabled(true);
                        atAluno.setFocusable(true);
                        atAlunoBtn.setEnabled(true);
                        atAlunoBtn.setFocusable(true);

                        if (nota.getTurma().getRegime() == RegimeEnum.SEMESTRAL) {
                            edNota3Input.setVisibility(View.GONE);
                            edNota4Input.setVisibility(View.GONE);
                        } else {
                            edNota3Input.setVisibility(View.VISIBLE);
                            edNota4Input.setVisibility(View.VISIBLE);
                        }
                    }
                }
        );

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

        if (atDiciplina.getText().toString().equals("")) {
            atDiciplina.setError("Informe o aluno!");
            atDiciplina.requestFocus();
            return;
        }

        salvar();
    }

    public void salvar() {
        nota.setTurma(TurmaDAO.getById(Util.getSubstring(atTurma.getText().toString(), "-")));
        nota.setDiciplina(DiciplinaDAO.getById(Util.getSubstring(atDiciplina.getText().toString(), "-")));
        nota.setAluno(AlunoDAO.getByRa(Util.getSubstring(atAluno.getText().toString(), "-")));

        nota.setNota1(Integer.valueOf(edNota1.getText().toString()));
        nota.setNota2(Integer.valueOf(edNota2.getText().toString()));
        nota.setNota3(Integer.valueOf(edNota3.getText().toString()));
        nota.setNota4(Integer.valueOf(edNota4.getText().toString()));


        if (NotaDAO.salvar(nota) > 0) {
            setResult(RESULT_OK);
            finish();
        } else {
            Util.customSnackBar(lnPrincipal, "Erro ao salvar a nota (" + nota.getId() + ") " + "verifique o log", 0);
        }
    }

    public void deletar() {
        if (NotaDAO.delete(nota)) {
            setResult(RESULT_OK);
            finish();
        } else {
            Util.customSnackBar(lnPrincipal, "Erro ao deletar a nota (\" + nota.getId() + \") " + "verifique o log", 0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflaterMenu = getMenuInflater();
        inflaterMenu.inflate(R.menu.menu_cadastro, menu);

        optionsMenu = menu;

        if (nota != null && nota.getId() != null) {
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
        atDiciplina.setText("");
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

    public void limparAluno(View view) {
        atAluno.setText("");
    }

    private void gerarDados() {
        nota.setTurma(TurmaDAO.getAleatorio());
        nota.setDiciplina(nota.getTurma().getDiciplinas().get(FakerUtil.getIndex(nota.getTurma().getDiciplinas().size())));
        nota.setAluno(nota.getTurma().getAlunos().get(FakerUtil.getIndex(nota.getTurma().getAlunos().size())));
        nota.setNota1(FakerUtil.getNumber());
        nota.setNota2(FakerUtil.getNumber());
        if (nota.getTurma().getRegime() == RegimeEnum.SEMESTRAL) {
            nota.setNota3(0);
            nota.setNota4(0);
        } else {
            nota.setNota3(FakerUtil.getNumber());
            nota.setNota4(FakerUtil.getNumber());
        }

        popularCampos(nota);
    }

    private void popularCampos(Nota nota) {
        if (nota.getTurma() != null) {
            atTurma.setText(nota.getTurma().toString());
            if (nota.getTurma().getRegime() == RegimeEnum.SEMESTRAL) {
                edNota3Input.setVisibility(View.GONE);
                edNota4Input.setVisibility(View.GONE);
            } else {
                edNota3Input.setVisibility(View.VISIBLE);
                edNota4Input.setVisibility(View.VISIBLE);
            }
        }
        if (nota.getDiciplina() != null) {
            atDiciplina.setText(nota.getDiciplina().toString());
        }
        if (nota.getAluno() != null) {
            atAluno.setText(nota.getAluno().toString());
        }

        edNota1.setText(String.valueOf(nota.getNota1()));
        edNota2.setText(String.valueOf(nota.getNota2()));
        edNota3.setText(String.valueOf(nota.getNota3()));
        edNota4.setText(String.valueOf(nota.getNota4()));


    }
}