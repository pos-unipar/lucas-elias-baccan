package com.example.cadastroalunos.activitys.turma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.cadastroalunos.R;
import com.example.cadastroalunos.dao.AlunoDAO;
import com.example.cadastroalunos.dao.DiciplinaDAO;
import com.example.cadastroalunos.dao.TurmaDAO;
import com.example.cadastroalunos.model.Aluno;
import com.example.cadastroalunos.model.Diciplina;
import com.example.cadastroalunos.model.Turma;
import com.example.cadastroalunos.util.Util;

public class GerenciarDiciplinasActivity extends AppCompatActivity {

    private Turma turma;

    private AutoCompleteTextView atDiciplina;
    private ListView lista;

    private ImageButton btLimparDiciplina;
    private Button btAdicionarDiciplina;
    private Button btRemoverDiciplina;

    private LinearLayout lnPrincipal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenciar_diciplinas);

        atDiciplina = findViewById(R.id.atDiciplina);
        lista = findViewById(R.id.listaDiciplina);

        btLimparDiciplina = findViewById(R.id.btLimparDiciplina);
        btAdicionarDiciplina = findViewById(R.id.btAdicionarDiciplina);
        btRemoverDiciplina = findViewById(R.id.btRemoverDiciplina);

        lnPrincipal = findViewById(R.id.lnPrincipal);

        Intent iin = getIntent();
        Bundle b = iin.getExtras();

        if (b != null) {
            Long id = (Long) b.get("id");
            turma = TurmaDAO.getById(id.intValue());

            setTitle("Turma: " + turma.getId() + "-" + turma.getCurso().getNome());
        }

        atDiciplina.setAdapter(Util.getAutocompleteAdapter(this, DiciplinaDAO.getAll()));

        atualizarLista();
    }

    private void atualizarLista(){
        lista.setAdapter(Util.getAutocompleteAdapter(this, TurmaDAO.getDiciplinaFromTurma(turma)));
    }

    public void limparCampos(View view) {
        atDiciplina.setText("");
    }

    public void adicionarDiciplina(View view) {
        if(atDiciplina.getText().toString().equals("")){
            Util.customSnackBar(lnPrincipal, "Selecione uma diciplina", 2);
        } else {
            Diciplina diciplina = DiciplinaDAO.getById((long) Util.getSubstring(atDiciplina.getText().toString(), "-"));
            if(turma.getDiciplinas().contains(diciplina)){
                Util.customSnackBar(lnPrincipal, "Diciplina já está na turma!", 0);
                return;
            }
            turma.getDiciplinas().add(diciplina);
            TurmaDAO.salvarAlteracaoDiciplinas(turma);
            atualizarLista();
        }
    }

    public void removerDiciplina(View view) {
        if(atDiciplina.getText().toString().equals("")){
            Util.customSnackBar(lnPrincipal, "Selecione uma diciplina", 2);
        } else {
            Diciplina diciplina = DiciplinaDAO.getById((long) Util.getSubstring(atDiciplina.getText().toString(), "-"));
            if(!turma.getDiciplinas().contains(diciplina)){
                Util.customSnackBar(lnPrincipal, "Diciplina não está na turma!", 0);
                return;
            }
            turma.getDiciplinas().remove(diciplina);
            TurmaDAO.salvarAlteracaoDiciplinas(turma);
            atualizarLista();
        }
    }

}