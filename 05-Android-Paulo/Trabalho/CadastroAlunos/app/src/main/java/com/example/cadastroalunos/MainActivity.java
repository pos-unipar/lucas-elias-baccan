package com.example.cadastroalunos;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cadastroalunos.activitys.aluno.ListaAlunoActivity;
import com.example.cadastroalunos.activitys.curso.ListaCursoActivity;
import com.example.cadastroalunos.dao.AlunoDAO;
import com.example.cadastroalunos.dao.CursoDAO;
import com.example.cadastroalunos.dao.TurmaDAO;
import com.example.cadastroalunos.enums.RegimeEnum;
import com.example.cadastroalunos.helpers.FakerHelper;
import com.example.cadastroalunos.model.Aluno;
import com.example.cadastroalunos.model.AlunoTurma;
import com.example.cadastroalunos.model.Curso;
import com.example.cadastroalunos.model.Turma;
import com.orm.SchemaGenerator;
import com.orm.SugarContext;
import com.orm.SugarDb;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void navegarAlunos(View view) {
        Intent intent = new Intent(this, ListaAlunoActivity.class);
        startActivity(intent);
    }

    public void navegarProfessores(View view) {
        Toast.makeText(this, "Professores", Toast.LENGTH_SHORT).show();
    }

    public void navegarCursos(View view) {
        Intent intent = new Intent(this, ListaCursoActivity.class);
        startActivity(intent);
    }

    public void navegarDiciplinas(View view) {
        Toast.makeText(this, "Diciplinas", Toast.LENGTH_SHORT).show();
    }

    public void navegarTurmas(View view) {
        Toast.makeText(this, "Turmas", Toast.LENGTH_SHORT).show();
    }

    public void navegarFrequencia(View view) {
        Toast.makeText(this, "Frequencia", Toast.LENGTH_SHORT).show();
    }

    public void navegarNotas(View view) {
        Toast.makeText(this, "Notas", Toast.LENGTH_SHORT).show();
    }

    public void limparBancoDados(View view) {
        // Limpar banco de dados
        SugarContext.terminate();
        SchemaGenerator schemaGenerator = new SchemaGenerator(getApplicationContext());
        schemaGenerator.deleteTables(new SugarDb(getApplicationContext()).getDB());
        SugarContext.init(getApplicationContext());
        schemaGenerator.createDatabase(new SugarDb(getApplicationContext()).getDB());

        Toast.makeText(this, "Dados apagados", Toast.LENGTH_SHORT).show();
    }

    public void gerarValores(View view) {

        // CURSOS
        FakerHelper.gerarCursoFake(true);
        FakerHelper.gerarCursoFake(true);

        Curso curso1 = CursoDAO.getById(1);
        Curso curso2 = CursoDAO.getById(2);

        // ALUNOS
        FakerHelper.gerarAlunoFake(true, curso1);
        FakerHelper.gerarAlunoFake(true, curso1);
        FakerHelper.gerarAlunoFake(true, curso1);
        FakerHelper.gerarAlunoFake(true, curso2);
        FakerHelper.gerarAlunoFake(true); // Tem que gerar um terceiro curso


        List<Aluno> alunos = AlunoDAO.getAll("", new String[]{}, "");
        Log.v("", "" + alunos.size());

        Curso curso = new Curso("Curso 1");
        curso.save();

//        Curso curso,
//        List<Aluno> alunos,
//        List<Diciplina> diciplinas,
//        RegimeEnum regime

        Turma turma = new Turma(
                curso,
                alunos,
                null,
                RegimeEnum.ANUAL
        );
        TurmaDAO.salvar(turma);

        List<Turma> turmas = TurmaDAO.getAll("", new String[]{}, "");
        Log.v("", "" + turmas.size());

        List<AlunoTurma> alunoTurmaList = AlunoTurma.listAll(AlunoTurma.class);
        Log.v("", "" + alunoTurmaList.size());

        TurmaDAO.delete(turma);

        alunoTurmaList = AlunoTurma.listAll(AlunoTurma.class);
        Log.v("", "" + alunoTurmaList.size());


        Toast.makeText(this, "Dados gerados!", Toast.LENGTH_SHORT).show();

    }

}