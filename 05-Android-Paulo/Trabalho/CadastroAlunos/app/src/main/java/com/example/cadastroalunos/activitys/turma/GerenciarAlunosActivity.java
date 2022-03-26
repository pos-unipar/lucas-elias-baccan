package com.example.cadastroalunos.activitys.turma;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cadastroalunos.R;
import com.example.cadastroalunos.dao.AlunoDAO;
import com.example.cadastroalunos.dao.TurmaDAO;
import com.example.cadastroalunos.model.Aluno;
import com.example.cadastroalunos.model.Turma;
import com.example.cadastroalunos.util.Util;

public class GerenciarAlunosActivity extends AppCompatActivity {

    private Turma turma;

    private AutoCompleteTextView atAluno;
    private ListView lista;

    private ImageButton btLimparAluno;
    private Button btAdicionarAluno;
    private Button btRemoverAluno;

    private LinearLayout lnPrincipal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenciar_alunos);

        atAluno = findViewById(R.id.atAluno);
        lista = findViewById(R.id.listaAluno);

        btLimparAluno = findViewById(R.id.btLimparAluno);
        btAdicionarAluno = findViewById(R.id.btAdicionarAluno);
        btRemoverAluno = findViewById(R.id.btRemoverAluno);

        lnPrincipal = findViewById(R.id.lnPrincipal);

        Intent iin = getIntent();
        Bundle b = iin.getExtras();

        if (b != null) {
            Long id = (Long) b.get("id");
            turma = TurmaDAO.getById(id);

            setTitle("Turma: " + turma.getId() + "-" + turma.getCurso().getNome());
        }

        atAluno.setAdapter(Util.getAutocompleteAdapter(this, AlunoDAO.getAll()));

        atualizarLista();
    }

    private void atualizarLista(){
        lista.setAdapter(Util.getAutocompleteAdapter(this, TurmaDAO.getAlunosFromTurma(turma)));
    }

    public void limparCampos(View view) {
        atAluno.setText("");
    }

    public void adicionarAluno(View view) {
        if(atAluno.getText().toString().equals("")){
            Util.customSnackBar(lnPrincipal, "Selecione um aluno", 2);
        } else {
            Aluno aluno = AlunoDAO.getByRa(Util.getSubstring(atAluno.getText().toString(), "-"));
            if(turma.getAlunos().contains(aluno)){
                Util.customSnackBar(lnPrincipal, "Aluno já está na turma!", 0);
                return;
            }
            turma.getAlunos().add(aluno);
            TurmaDAO.salvarAlteracaoAlunos(turma);
            atualizarLista();
        }
    }

    public void removerAluno(View view) {
        if(atAluno.getText().toString().equals("")){
            Util.customSnackBar(lnPrincipal, "Selecione um aluno", 2);
        } else {
            Aluno aluno = AlunoDAO.getByRa(Util.getSubstring(atAluno.getText().toString(), "-"));
            if(!turma.getAlunos().contains(aluno)){
                Util.customSnackBar(lnPrincipal, "Aluno não está na turma!", 0);
                return;
            }
            turma.getAlunos().remove(aluno);
            TurmaDAO.salvarAlteracaoAlunos(turma);
            atualizarLista();
        }
    }

}