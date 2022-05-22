import 'package:faker/faker.dart' as faker;
import 'package:trabalho/datasources/datasources.dart';
import 'package:trabalho/models/models.dart';

class Faker {
  static final faker.Faker _faker = faker.Faker();

  static void gerarDadosFake() async {
    gerarCurso();
    gerarCurso();
    gerarCurso();
    gerarCurso();
    gerarCurso();

    gerarAluno();
    gerarAluno();
    gerarAluno();
    gerarAluno();
    gerarAluno();
    gerarAluno();

    gerarProfessor();
    gerarProfessor();
    gerarProfessor();
    gerarProfessor();
    gerarProfessor();
    gerarProfessor();
    gerarProfessor();

    await gerarDiciplina();
    await gerarDiciplina();
    await gerarDiciplina();
    await gerarDiciplina();
    await gerarDiciplina();
    await gerarDiciplina();
    await gerarDiciplina();
    await gerarDiciplina();

    await gerarTurma();
    await gerarTurma();
    await gerarTurma();
    await gerarTurma();
    await gerarTurma();
    await gerarTurma();

    await gerarFrequencia();
    await gerarFrequencia();
    await gerarFrequencia();

    await gerarNotas();
  }

  static int gerarId(int max) {
    return _faker.randomGenerator.integer(max, min: 1);
  }

  static Future<Curso> gerarCurso() async {
    CursoDatasource _datasource = CursoDatasource(Curso.model());
    var curso = Curso(
      nome: _faker.sport.name(),
    );
    await _datasource.insert(curso);
    return curso;
  }

  static Future<Aluno> gerarAluno() async {
    AlunoDatasource _datasource = AlunoDatasource(Aluno.model());
    var aluno = Aluno(
      nome: _faker.person.name(),
      email: _faker.internet.email(),
      ra: _faker.randomGenerator.integer(999999),
    );
    await _datasource.insert(aluno);
    return aluno;
  }

  static Future<Professor> gerarProfessor() async {
    ProfessorDatasource _datasource = ProfessorDatasource(Professor.model());
    var professor = Professor(
      nome: _faker.person.name(),
    );
    await _datasource.insert(professor);
    return professor;
  }

  static Future<Diciplina> gerarDiciplina() async {
    DiciplinaDatasource _datasource = DiciplinaDatasource(Diciplina.model());
    var diciplina = Diciplina(
      nome: _faker.sport.name() + ' Nível ' + _faker.randomGenerator.integer(5, min: 1).toString(),
      professor: await ProfessorDatasource(Professor.model()).aleatorio() as Professor,
    );
    await _datasource.insert(diciplina);
    return diciplina;
  }

  static Future<Turma> gerarTurma() async {
    TurmaDatasource _datasource = TurmaDatasource(Turma.model());
    var turma = Turma(
      curso: await CursoDatasource(Curso.model()).aleatorio() as Curso,
    );

    for (var i = 0; i < _faker.randomGenerator.integer(3, min: 1); i++) {
      turma.alunos.add(await AlunoDatasource(Aluno.model()).aleatorio() as Aluno);
      turma.diciplinas.add(await DiciplinaDatasource(Diciplina.model()).aleatorio() as Diciplina);
    }

    await _datasource.insert(turma);
    return turma;
  }

  static Future<void> gerarFrequencia() async {
    LancamentoPresencaDatasource _datasource = LancamentoPresencaDatasource(LancamentoPresenca.model());
    List<Turma> turmas = await TurmaDatasource(Turma.model()).getAll(completo: true);
    for (Turma turma in turmas) {
      for (Diciplina diciplina in turma.diciplinas) {
        for (Aluno aluno in turma.alunos) {
          var lancamento = LancamentoPresenca(
            turma: turma,
            aluno: aluno,
            diciplina: diciplina,
            presenca: _faker.randomGenerator.boolean(),
            data: _faker.date.dateTime(minYear: 2020, maxYear: 2022),
          );
          await _datasource.insert(lancamento);
        }
      }
    }
  }

  static Future<void> gerarNotas() async {
    LancamentoNotaDatasource _datasource = LancamentoNotaDatasource(LancamentoNota.model());
    List<Turma> turmas = await TurmaDatasource(Turma.model()).getAll(completo: true);
    for (Turma turma in turmas) {
      for (Diciplina diciplina in turma.diciplinas) {
        for (Aluno aluno in turma.alunos) {
          var lancamento = LancamentoNota(
            turma: turma,
            aluno: aluno,
            diciplina: diciplina,
            nota1: _faker.randomGenerator.integer(100, min: 1),
            nota2: _faker.randomGenerator.integer(100, min: 80),
            nota3: _faker.randomGenerator.integer(100, min: 50),
            nota4: _faker.randomGenerator.integer(100, min: 50),
          );
          await _datasource.insert(lancamento);
        }
      }
    }
  }
}
