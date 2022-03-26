package com.example.cadastroalunos.activitys.aluno;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cadastroalunos.R;
import com.example.cadastroalunos.dao.AlunoDAO;
import com.example.cadastroalunos.dao.CursoDAO;
import com.example.cadastroalunos.enums.PeriodoEnum;
import com.example.cadastroalunos.util.FakerUtil;
import com.example.cadastroalunos.model.Aluno;
import com.example.cadastroalunos.model.Curso;
import com.example.cadastroalunos.util.CpfMask;
import com.example.cadastroalunos.util.Util;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;
import java.util.List;

import fr.ganfra.materialspinner.MaterialSpinner;

public class CadastroAlunoActivity extends AppCompatActivity {

    private Aluno aluno;

    private TextInputEditText edRaAluno;
    private TextInputEditText edNomeAluno;
    private TextInputEditText edCpfAluno;
    private TextInputEditText edDtNascAluno;
    private TextInputEditText edDtMatAluno;
    private MaterialSpinner spCursos;
    private MaterialSpinner spPeriodo;

    private LinearLayout lnPrincipal;

    private int vAno;
    private int vMes;
    private int vDia;

    private View dataSelecionada;

    private Menu optionsMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_aluno);

        edRaAluno = findViewById(R.id.edRaAluno);
        edNomeAluno = findViewById(R.id.edNomeAluno);
        edCpfAluno = findViewById(R.id.edCpfAluno);
        edDtNascAluno = findViewById(R.id.edDtNascAluno);
        edDtMatAluno = findViewById(R.id.edDtMatAluno);
        
        lnPrincipal = findViewById(R.id.lnPrincipal);

        edDtNascAluno.setFocusable(false);
        edDtMatAluno.setFocusable(false);

        edCpfAluno.addTextChangedListener(CpfMask.insert(edCpfAluno));

        iniciaSpinners();

        setDataAtual();

        Intent iin = getIntent();
        Bundle b = iin.getExtras();

        if (b != null) {
            Long id = (Long) b.get("id");
            aluno = AlunoDAO.getById(id.intValue());
            popularCampos(aluno);
        } else {
            aluno = new Aluno();
        }
    }

    private void setDataAtual() {
        Calendar calendar = Calendar.getInstance();
        vDia = calendar.get(Calendar.DAY_OF_MONTH);
        vMes = calendar.get(Calendar.MONTH);
        vAno = calendar.get(Calendar.YEAR);
    }

    private void iniciaSpinners() {
        spCursos = findViewById(R.id.spCursos);
        spPeriodo = findViewById(R.id.spPeriodo);

        List<Curso> cursosList = CursoDAO.getAll("", new String[]{}, "");
        Curso[] cursos = cursosList.toArray(new Curso[cursosList.size()]);

        PeriodoEnum periodos[] = PeriodoEnum.values();

        ArrayAdapter adapterCursos = new ArrayAdapter(this, android.R.layout.simple_list_item_1, cursos);
        ArrayAdapter adapterPeriodo = new ArrayAdapter(this, android.R.layout.simple_list_item_1, periodos);

        spCursos.setAdapter(adapterCursos);
        spPeriodo.setAdapter(adapterPeriodo);
    }

    //Validação dos campos
    private void validaCampos() {
        //Valida o campo Ra aluno
        if (edRaAluno.getText().toString().equals("")) {
            edRaAluno.setError("Informe o RA do aluno!");
            edRaAluno.requestFocus();
            return;
        }

        //Valida o campo de nome do aluno
        if (edNomeAluno.getText().toString().equals("")) {
            edNomeAluno.setError("Informe o Nome do aluno!");
            edNomeAluno.requestFocus();
            return;
        }

        //Valida o campo de CPF do aluno
        if (edCpfAluno.getText().toString().equals("")) {
            edCpfAluno.setError("Informe o CPF do aluno!");
            edCpfAluno.requestFocus();
            return;
        }

        //Valida o campo de data de nascimento do aluno
        if (edDtNascAluno.getText().toString().equals("")) {
            edDtNascAluno.setError("Informe a data de nascimento do aluno!");
            edDtNascAluno.requestFocus();
            return;
        }

        //Valida o campo de data de matricula do aluno
        if (edDtMatAluno.getText().toString().equals("")) {
            edDtMatAluno.setError("Informe a data de matricula do aluno!");
            edDtMatAluno.requestFocus();
            return;
        }

        // Validar campo curso
        if (spCursos.getSelectedItem() == null) {
            spCursos.setError("Informe o curso do aluno!");
            spCursos.requestFocus();
            return;
        }

        // Validar campo periodo
        if (spPeriodo.getSelectedItem() == null) {
            spPeriodo.setError("Informe o período do aluno!");
            spPeriodo.requestFocus();
            return;
        }

        salvar();
    }

    public void salvar() {
        aluno.setRa(Integer.parseInt(edRaAluno.getText().toString()));
        aluno.setNome(edNomeAluno.getText().toString());
        aluno.setCpf(edCpfAluno.getText().toString());
        aluno.setDtNasc(edDtNascAluno.getText().toString());
        aluno.setDtMatricula(edDtMatAluno.getText().toString());
        aluno.setCurso((Curso) spCursos.getSelectedItem());
        aluno.setPeriodo((PeriodoEnum) spPeriodo.getSelectedItem());

        if (AlunoDAO.salvar(aluno) > 0) {
            setResult(RESULT_OK);
            finish();
        } else {
            Util.customSnackBar(lnPrincipal, "Erro ao salvar o aluno (" + aluno.getNome() + ") " + "verifique o log", 0);
        }
    }

    public void deletar(){
        if(!AlunoDAO.podeDeletar(aluno)){
            Util.customSnackBar(lnPrincipal, "Essa informação está sento utilizada em outro local", 0);
            return;
        }

        if(AlunoDAO.delete(aluno)){
            setResult(RESULT_OK);
            finish();
        } else {
            Util.customSnackBar(lnPrincipal, "Erro ao deletar o curso (" + aluno.getNome() + ") " + "verifique o log", 0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflaterMenu = getMenuInflater();
        inflaterMenu.inflate(R.menu.menu_cadastro, menu);

        optionsMenu = menu;

        if(aluno != null && aluno.getId() != null){
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
        edRaAluno.setText("");
        edNomeAluno.setText("");
        edCpfAluno.setText("");
        edDtNascAluno.setText("");
        edDtMatAluno.setText("");
        spCursos.setSelection(0);
        spPeriodo.setSelection(0);
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
        final Curso curso = CursoDAO.getAleatorio();
        final Aluno aluno = FakerUtil.gerarAlunoFake(false, curso);

        popularCampos(aluno);
    }

    private void popularCampos(Aluno aluno) {
        edRaAluno.setText(String.valueOf(aluno.getRa()));
        edNomeAluno.setText(aluno.getNome());
        edCpfAluno.setText(aluno.getCpf());
        edDtNascAluno.setText(aluno.getDtNasc());
        edDtMatAluno.setText(aluno.getDtMatricula());

        spCursos.setSelection(Util.getIndexFromSpinner(spCursos, aluno.getCurso().toString()));
        spPeriodo.setSelection(Util.getIndexFromSpinner(spPeriodo, aluno.getPeriodo().toString()));
    }

}