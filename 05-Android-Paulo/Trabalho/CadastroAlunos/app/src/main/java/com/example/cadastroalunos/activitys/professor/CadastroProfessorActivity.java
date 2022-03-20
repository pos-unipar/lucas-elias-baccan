package com.example.cadastroalunos.activitys.professor;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cadastroalunos.R;
import com.example.cadastroalunos.dao.ProfessorDAO;
import com.example.cadastroalunos.util.FakerUtil;
import com.example.cadastroalunos.model.Professor;
import com.example.cadastroalunos.util.Util;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public class CadastroProfessorActivity extends AppCompatActivity {

    private Professor professor;

    private TextInputEditText edRaProfessor;
    private TextInputEditText edNomeProfessor;
    private TextInputEditText edCpfProfessor;
    private TextInputEditText edDtNascProfessor;
    private TextInputEditText edDtMatProfessor;

    private LinearLayout lnPrincipal;

    private int vAno;
    private int vMes;
    private int vDia;

    private View dataSelecionada;

    private Menu optionsMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_professor);

        edRaProfessor = findViewById(R.id.edRaProfessor);
        edNomeProfessor = findViewById(R.id.edNomeProfessor);
        edCpfProfessor = findViewById(R.id.edCpfProfessor);
        edDtNascProfessor = findViewById(R.id.edDtNascProfessor);
        edDtMatProfessor = findViewById(R.id.edDtMatProfessor);

        lnPrincipal = findViewById(R.id.lnPrincipalProfessor);

        setDataAtual();

        Intent iin = getIntent();
        Bundle b = iin.getExtras();

        if (b != null) {
            Long id = (Long) b.get("id");
            professor = ProfessorDAO.getById(id.intValue());
            popularCampos(professor);
        } else {
            professor = new Professor();
        }
    }

    private void setDataAtual() {
        Calendar calendar = Calendar.getInstance();
        vDia = calendar.get(Calendar.DAY_OF_MONTH);
        vMes = calendar.get(Calendar.MONTH);
        vAno = calendar.get(Calendar.YEAR);
    }

    private void validaCampos() {
        if (edRaProfessor.getText().toString().equals("")) {
            edRaProfessor.setError("Informe o RA do professor!");
            edRaProfessor.requestFocus();
            return;
        }

        if (edNomeProfessor.getText().toString().equals("")) {
            edNomeProfessor.setError("Informe o Nome do professor!");
            edNomeProfessor.requestFocus();
            return;
        }

        if (edCpfProfessor.getText().toString().equals("")) {
            edCpfProfessor.setError("Informe o CPF do professor!");
            edCpfProfessor.requestFocus();
            return;
        }

        if (edDtNascProfessor.getText().toString().equals("")) {
            edDtNascProfessor.setError("Informe a data de nascimento do professor!");
            edDtNascProfessor.requestFocus();
            return;
        }

        if (edDtMatProfessor.getText().toString().equals("")) {
            edDtMatProfessor.setError("Informe a data de matricula do professor!");
            edDtMatProfessor.requestFocus();
            return;
        }

        salvar();
    }

    public void salvar() {
        professor.setRa(Integer.parseInt(edRaProfessor.getText().toString()));
        professor.setNome(edNomeProfessor.getText().toString());
        professor.setCpf(edCpfProfessor.getText().toString());
        professor.setDtNasc(edDtNascProfessor.getText().toString());
        professor.setDtMatricula(edDtMatProfessor.getText().toString());

        if (ProfessorDAO.salvar(professor) > 0) {
            setResult(RESULT_OK);
            finish();
        } else {
            Util.customSnackBar(lnPrincipal, "Erro ao salvar o professor (" + professor.getNome() + ") " + "verifique o log", 0);
        }
    }

    public void deletar() {
        if (ProfessorDAO.delete(professor)) {
            setResult(RESULT_OK);
            finish();
        } else {
            Util.customSnackBar(lnPrincipal, "Erro ao deletar o curso (" + professor.getNome() + ") " + "verifique o log", 0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflaterMenu = getMenuInflater();
        inflaterMenu.inflate(R.menu.menu_cadastro, menu);

        optionsMenu = menu;

        if (professor != null && professor.getId() != null) {
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
        edRaProfessor.setText("");
        edNomeProfessor.setText("");
        edCpfProfessor.setText("");
        edDtNascProfessor.setText("");
        edDtMatProfessor.setText("");
    }

    public void selecionarData(View view) {
        dataSelecionada = view;
        showDialog(0);
    }

    private DatePickerDialog.OnDateSetListener setDatePicker =
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                    vAno = year;
                    vMes = month;
                    vDia = day;

                    atualizaData();
                }
            };

    private void atualizaData() {
        TextInputEditText edit = (TextInputEditText) dataSelecionada;
        edit.setText(new StringBuilder().append(vDia).append("/")
                .append(vMes + 1).append("/")
                .append(vAno));
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        setDataAtual();
        return new DatePickerDialog(this, setDatePicker, vAno, vMes, vDia);
    }

    private void gerarDados() {
        final Professor professor = FakerUtil.gerarProfessorFake(false);

        edRaProfessor.setText(String.valueOf(professor.getRa()));
        edNomeProfessor.setText(professor.getNome());
        edCpfProfessor.setText(professor.getCpf());
        edDtNascProfessor.setText(professor.getDtNasc());
        edDtMatProfessor.setText(professor.getDtMatricula());

        popularCampos(professor);
    }

    private void popularCampos(Professor professor) {
        edRaProfessor.setText(String.valueOf(professor.getRa()));
        edNomeProfessor.setText(professor.getNome());
        edCpfProfessor.setText(professor.getCpf());
        edDtNascProfessor.setText(professor.getDtNasc());
        edDtMatProfessor.setText(professor.getDtMatricula());
    }
}