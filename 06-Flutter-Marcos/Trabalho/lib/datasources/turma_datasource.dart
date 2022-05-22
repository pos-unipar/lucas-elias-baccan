import 'package:trabalho/datasources/datasources.dart';
import 'package:trabalho/models/models.dart';

class TurmaDatasource extends ElementoDatasource {
  static const String tabela = 'turma';
  static const String columnId = 'id';
  static const String columnCurso = 'curso';

  final dataSourceTurmaAluno = TurmaAlunoDatasource(TurmaAluno.model());

  TurmaDatasource(Turma instance) : super(instance, nomeTabela: tabela);

  @override
  String createTableSql() {
    return '''
      CREATE TABLE IF NOT EXISTS $tabela (
        $columnId INTEGER PRIMARY KEY AUTOINCREMENT,
        $columnCurso INTEGER NOT NULL REFERENCES curso(id)
      );
    ''';
  }

  @override
  Future<Turma> find(int id, {completo = false}) async {
    Turma turma = await super.find(id) as Turma;
    if (completo) {
      turma.alunos = await getAlunos(turma);
    }
    return turma;
  }

  @override
  Future<List<Turma>> getAll({completo = false}) async {
    List<Elemento> turmas = await super.getAll();
    List<Turma> turmasAntigas = [];
    for (Elemento turma in turmas) {
      turmasAntigas.add(turma as Turma);
      if (completo) {
        turma.alunos = await getAlunos(turma);
      }
    }
    return turmasAntigas;
  }

  Future<List<Aluno>> getAlunos(Turma turma) async {
    List<TurmaAluno> turmaAlunosAntigos = await dataSourceTurmaAluno.getAllTurmaAluno(turma);
    for (TurmaAluno turmaAluno in turmaAlunosAntigos) {
      turma.alunos.add(await AlunoDatasource(Aluno.model()).find(turmaAluno.aluno.id!) as Aluno);
    }
    return turma.alunos;
  }

  @override
  Future<int> insert(Elemento model) async {
    final int id = await super.insert(model);
    Turma turma = model as Turma;
    turma.id = id;

    // Inserir turma_aluno
    for (final aluno in turma.alunos) {
      await dataSourceTurmaAluno.insert(TurmaAluno(turma: turma, aluno: aluno));
    }

    return id;
  }

  @override
  Future<void> update(Elemento model) async {
    await super.update(model);
    final Turma turma = model as Turma;

    // Deletar todos os turma_aluno da turma
    List<TurmaAluno> turmaAlunosAntigos = await dataSourceTurmaAluno.getAllTurmaAluno(turma);
    for (TurmaAluno turmaAluno in turmaAlunosAntigos) {
      await dataSourceTurmaAluno.delete(turmaAluno);
    }

    // Inserir novos turma_aluno
    for (final aluno in turma.alunos) {
      await dataSourceTurmaAluno.insert(TurmaAluno(turma: turma, aluno: aluno));
    }
  }

  @override
  Future<void> delete(Elemento model) async {
    await super.delete(model);
    final Turma turma = model as Turma;
    final dataSourceTurmaAluno = TurmaAlunoDatasource(TurmaAluno.model());

    // Deletar todos os turma_aluno da turma
    List<TurmaAluno> turmaAlunosAntigos = await dataSourceTurmaAluno.getAllTurmaAluno(turma);
    for (TurmaAluno turmaAluno in turmaAlunosAntigos) {
      await dataSourceTurmaAluno.delete(turmaAluno);
    }
  }
}
