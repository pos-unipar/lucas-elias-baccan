package com.example.cadastroalunos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cadastroalunos.activitys.aluno.ListaAlunoActivity;
import com.example.cadastroalunos.activitys.boletim.ListaBoletimActivity;
import com.example.cadastroalunos.activitys.curso.ListaCursoActivity;
import com.example.cadastroalunos.activitys.diciplina.ListaDiciplinaActivity;
import com.example.cadastroalunos.activitys.frequencia.ListaFrequenciaActivity;
import com.example.cadastroalunos.activitys.nota.ListaNotaActivity;
import com.example.cadastroalunos.activitys.professor.ListaProfessorActivity;
import com.example.cadastroalunos.activitys.turma.ListaTurmaActivity;
import com.example.cadastroalunos.dao.AlunoDAO;
import com.example.cadastroalunos.dao.CursoDAO;
import com.example.cadastroalunos.dao.DiciplinaDAO;
import com.example.cadastroalunos.dao.ProfessorDAO;
import com.example.cadastroalunos.dao.TurmaDAO;
import com.example.cadastroalunos.model.Turma;
import com.example.cadastroalunos.util.FakerUtil;
import com.orm.SchemaGenerator;
import com.orm.SugarContext;
import com.orm.SugarDb;

import java.util.ArrayList;
import java.util.HashSet;

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
        Intent intent = new Intent(this, ListaProfessorActivity.class);
        startActivity(intent);
    }

    public void navegarCursos(View view) {
        Intent intent = new Intent(this, ListaCursoActivity.class);
        startActivity(intent);
    }

    public void navegarDiciplinas(View view) {
        Intent intent = new Intent(this, ListaDiciplinaActivity.class);
        startActivity(intent);
    }

    public void navegarTurmas(View view) {
        Intent intent = new Intent(this, ListaTurmaActivity.class);
        startActivity(intent);
    }

    public void navegarFrequencia(View view) {
        Intent intent = new Intent(this, ListaFrequenciaActivity.class);
        startActivity(intent);
    }

    public void navegarNotas(View view) {
        Intent intent = new Intent(this, ListaNotaActivity.class);
        startActivity(intent);
    }

    public void navegarBoletim(View view) {
        Intent intent = new Intent(this, ListaBoletimActivity.class);
        startActivity(intent);
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

        // Curso
        FakerUtil.gerarCursoFake(true);
        FakerUtil.gerarCursoFake(true);
        FakerUtil.gerarCursoFake(true);
        FakerUtil.gerarCursoFake(true);
        FakerUtil.gerarCursoFake(true);

        // Aluno
        FakerUtil.gerarAlunoFake(true, CursoDAO.getAleatorio());
        FakerUtil.gerarAlunoFake(true, CursoDAO.getAleatorio());
        FakerUtil.gerarAlunoFake(true, CursoDAO.getAleatorio());
        FakerUtil.gerarAlunoFake(true, CursoDAO.getAleatorio());
        FakerUtil.gerarAlunoFake(true, CursoDAO.getAleatorio());

        // Professor
        FakerUtil.gerarProfessorFake(true);
        FakerUtil.gerarProfessorFake(true);
        FakerUtil.gerarProfessorFake(true);
        FakerUtil.gerarProfessorFake(true);
        FakerUtil.gerarProfessorFake(true);

        // Diciplina
        FakerUtil.gerarDiciplinaFake(true, ProfessorDAO.getAleatorio());
        FakerUtil.gerarDiciplinaFake(true, ProfessorDAO.getAleatorio());
        FakerUtil.gerarDiciplinaFake(true, ProfessorDAO.getAleatorio());
        FakerUtil.gerarDiciplinaFake(true, ProfessorDAO.getAleatorio());
        FakerUtil.gerarDiciplinaFake(true, ProfessorDAO.getAleatorio());

        // Turma
        Turma t;
        t = FakerUtil.gerarTurmaFake(true, CursoDAO.getAleatorio());
        t.getAlunos().add(AlunoDAO.getAleatorio());
        t.getAlunos().add(AlunoDAO.getAleatorio());
        t.getAlunos().add(AlunoDAO.getAleatorio());
        t.setAlunos(new ArrayList<>(new HashSet<>(t.getAlunos())));
        t.getDiciplinas().add(DiciplinaDAO.getAleatorio());
        t.getDiciplinas().add(DiciplinaDAO.getAleatorio());
        t.getDiciplinas().add(DiciplinaDAO.getAleatorio());
        t.setDiciplinas(new ArrayList<>(new HashSet<>(t.getDiciplinas())));
        TurmaDAO.salvar(t);

        FakerUtil.gerarFrequenciaFake(true, TurmaDAO.getAlunoAleatoriaFromTurma(t), t, TurmaDAO.getDiciplinaAleatoriaFromTurma(t));
        FakerUtil.gerarFrequenciaFake(true, TurmaDAO.getAlunoAleatoriaFromTurma(t), t, TurmaDAO.getDiciplinaAleatoriaFromTurma(t));
        FakerUtil.gerarFrequenciaFake(true, TurmaDAO.getAlunoAleatoriaFromTurma(t), t, TurmaDAO.getDiciplinaAleatoriaFromTurma(t));

        FakerUtil.gerarNotaFake(true, TurmaDAO.getAlunoAleatoriaFromTurma(t), t, TurmaDAO.getDiciplinaAleatoriaFromTurma(t));
        FakerUtil.gerarNotaFake(true, TurmaDAO.getAlunoAleatoriaFromTurma(t), t, TurmaDAO.getDiciplinaAleatoriaFromTurma(t));
        FakerUtil.gerarNotaFake(true, TurmaDAO.getAlunoAleatoriaFromTurma(t), t, TurmaDAO.getDiciplinaAleatoriaFromTurma(t));

        t = FakerUtil.gerarTurmaFake(true, CursoDAO.getAleatorio());
        t.getAlunos().add(AlunoDAO.getAleatorio());
        t.getAlunos().add(AlunoDAO.getAleatorio());
        t.getAlunos().add(AlunoDAO.getAleatorio());
        t.setAlunos(new ArrayList<>(new HashSet<>(t.getAlunos())));
        t.getDiciplinas().add(DiciplinaDAO.getAleatorio());
        t.getDiciplinas().add(DiciplinaDAO.getAleatorio());
        t.getDiciplinas().add(DiciplinaDAO.getAleatorio());
        t.setDiciplinas(new ArrayList<>(new HashSet<>(t.getDiciplinas())));
        TurmaDAO.salvar(t);

        FakerUtil.gerarFrequenciaFake(true, TurmaDAO.getAlunoAleatoriaFromTurma(t), t, TurmaDAO.getDiciplinaAleatoriaFromTurma(t));
        FakerUtil.gerarFrequenciaFake(true, TurmaDAO.getAlunoAleatoriaFromTurma(t), t, TurmaDAO.getDiciplinaAleatoriaFromTurma(t));
        FakerUtil.gerarFrequenciaFake(true, TurmaDAO.getAlunoAleatoriaFromTurma(t), t, TurmaDAO.getDiciplinaAleatoriaFromTurma(t));
        FakerUtil.gerarFrequenciaFake(true, TurmaDAO.getAlunoAleatoriaFromTurma(t), t, TurmaDAO.getDiciplinaAleatoriaFromTurma(t));
        FakerUtil.gerarFrequenciaFake(true, TurmaDAO.getAlunoAleatoriaFromTurma(t), t, TurmaDAO.getDiciplinaAleatoriaFromTurma(t));
        FakerUtil.gerarFrequenciaFake(true, TurmaDAO.getAlunoAleatoriaFromTurma(t), t, TurmaDAO.getDiciplinaAleatoriaFromTurma(t));

        FakerUtil.gerarNotaFake(true, TurmaDAO.getAlunoAleatoriaFromTurma(t), t, TurmaDAO.getDiciplinaAleatoriaFromTurma(t));
        FakerUtil.gerarNotaFake(true, TurmaDAO.getAlunoAleatoriaFromTurma(t), t, TurmaDAO.getDiciplinaAleatoriaFromTurma(t));
        FakerUtil.gerarNotaFake(true, TurmaDAO.getAlunoAleatoriaFromTurma(t), t, TurmaDAO.getDiciplinaAleatoriaFromTurma(t));
        FakerUtil.gerarNotaFake(true, TurmaDAO.getAlunoAleatoriaFromTurma(t), t, TurmaDAO.getDiciplinaAleatoriaFromTurma(t));
        FakerUtil.gerarNotaFake(true, TurmaDAO.getAlunoAleatoriaFromTurma(t), t, TurmaDAO.getDiciplinaAleatoriaFromTurma(t));
        FakerUtil.gerarNotaFake(true, TurmaDAO.getAlunoAleatoriaFromTurma(t), t, TurmaDAO.getDiciplinaAleatoriaFromTurma(t));

        t = FakerUtil.gerarTurmaFake(true, CursoDAO.getAleatorio());
        t.getAlunos().add(AlunoDAO.getAleatorio());
        t.getAlunos().add(AlunoDAO.getAleatorio());
        t.getAlunos().add(AlunoDAO.getAleatorio());
        t.setAlunos(new ArrayList<>(new HashSet<>(t.getAlunos())));
        t.getDiciplinas().add(DiciplinaDAO.getAleatorio());
        t.getDiciplinas().add(DiciplinaDAO.getAleatorio());
        t.getDiciplinas().add(DiciplinaDAO.getAleatorio());
        t.setDiciplinas(new ArrayList<>(new HashSet<>(t.getDiciplinas())));
        TurmaDAO.salvar(t);

        FakerUtil.gerarFrequenciaFake(true, TurmaDAO.getAlunoAleatoriaFromTurma(t), t, TurmaDAO.getDiciplinaAleatoriaFromTurma(t));
        FakerUtil.gerarFrequenciaFake(true, TurmaDAO.getAlunoAleatoriaFromTurma(t), t, TurmaDAO.getDiciplinaAleatoriaFromTurma(t));
        FakerUtil.gerarFrequenciaFake(true, TurmaDAO.getAlunoAleatoriaFromTurma(t), t, TurmaDAO.getDiciplinaAleatoriaFromTurma(t));

        FakerUtil.gerarNotaFake(true, TurmaDAO.getAlunoAleatoriaFromTurma(t), t, TurmaDAO.getDiciplinaAleatoriaFromTurma(t));
        FakerUtil.gerarNotaFake(true, TurmaDAO.getAlunoAleatoriaFromTurma(t), t, TurmaDAO.getDiciplinaAleatoriaFromTurma(t));
        FakerUtil.gerarNotaFake(true, TurmaDAO.getAlunoAleatoriaFromTurma(t), t, TurmaDAO.getDiciplinaAleatoriaFromTurma(t));

        t = FakerUtil.gerarTurmaFake(true, CursoDAO.getAleatorio());
        t.getAlunos().add(AlunoDAO.getAleatorio());
        t.getAlunos().add(AlunoDAO.getAleatorio());
        t.getAlunos().add(AlunoDAO.getAleatorio());
        t.setAlunos(new ArrayList<>(new HashSet<>(t.getAlunos())));
        t.getDiciplinas().add(DiciplinaDAO.getAleatorio());
        t.getDiciplinas().add(DiciplinaDAO.getAleatorio());
        t.getDiciplinas().add(DiciplinaDAO.getAleatorio());
        t.setDiciplinas(new ArrayList<>(new HashSet<>(t.getDiciplinas())));
        TurmaDAO.salvar(t);

        FakerUtil.gerarFrequenciaFake(true, TurmaDAO.getAlunoAleatoriaFromTurma(t), t, TurmaDAO.getDiciplinaAleatoriaFromTurma(t));
        FakerUtil.gerarFrequenciaFake(true, TurmaDAO.getAlunoAleatoriaFromTurma(t), t, TurmaDAO.getDiciplinaAleatoriaFromTurma(t));
        FakerUtil.gerarFrequenciaFake(true, TurmaDAO.getAlunoAleatoriaFromTurma(t), t, TurmaDAO.getDiciplinaAleatoriaFromTurma(t));

        FakerUtil.gerarNotaFake(true, TurmaDAO.getAlunoAleatoriaFromTurma(t), t, TurmaDAO.getDiciplinaAleatoriaFromTurma(t));
        FakerUtil.gerarNotaFake(true, TurmaDAO.getAlunoAleatoriaFromTurma(t), t, TurmaDAO.getDiciplinaAleatoriaFromTurma(t));
        FakerUtil.gerarNotaFake(true, TurmaDAO.getAlunoAleatoriaFromTurma(t), t, TurmaDAO.getDiciplinaAleatoriaFromTurma(t));

        Toast.makeText(this, "Dados gerados!", Toast.LENGTH_SHORT).show();

    }

}