import 'package:faker/faker.dart' as faker;
import 'package:trabalho/datasources/datasources.dart';
import 'package:trabalho/models/models.dart';

class Faker {
  static final faker.Faker _faker = faker.Faker();

  static void gerarDadosFake() {
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

    gerarDiciplina();
    gerarDiciplina();
    gerarDiciplina();
    gerarDiciplina();
    gerarDiciplina();
    gerarDiciplina();
    gerarDiciplina();
    gerarDiciplina();
  }

  static int gerarId(int max) {
    return _faker.randomGenerator.integer(max, min: 1);
  }

  static Curso gerarCurso() {
    CursoDatasource _datasource = CursoDatasource(Curso.model());
    var curso = Curso(
      nome: _faker.sport.name(),
    );
    _datasource.insert(curso);
    return curso;
  }

  static Aluno gerarAluno() {
    AlunoDatasource _datasource = AlunoDatasource(Aluno.model());
    var aluno = Aluno(
      nome: _faker.person.name(),
      email: _faker.internet.email(),
      ra: _faker.randomGenerator.integer(999999),
    );
    _datasource.insert(aluno);
    return aluno;
  }

  static Professor gerarProfessor() {
    ProfessorDatasource _datasource = ProfessorDatasource(Professor.model());
    var professor = Professor(
      nome: _faker.person.name(),
    );
    _datasource.insert(professor);
    return professor;
  }

  static Future<Diciplina> gerarDiciplina() async {
    DiciplinaDatasource _datasource = DiciplinaDatasource(Diciplina.model());
    var diciplina = Diciplina(
      nome: _faker.sport.name() + ' Nível ' + _faker.randomGenerator.integer(5, min: 1).toString(),
      professor: await ProfessorDatasource(Professor.model()).aleatorio() as Professor,
    );
    _datasource.insert(diciplina);
    return diciplina;
  }
}
