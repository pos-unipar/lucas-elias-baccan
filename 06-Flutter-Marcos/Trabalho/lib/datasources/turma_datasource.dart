import 'package:trabalho/datasources/datasources.dart';
import 'package:trabalho/models/models.dart';

class TurmaDatasource extends ElementoDatasource {
  static const String tabela = 'turma';
  static const String columnId = 'id';
  static const String columnCurso = 'curso';

  static const String tabelaTurmaAluno = 'turma_aluno';
  static const String columnTurma = 'turma_id';
  static const String columnAluno = 'aluno_id';

  final dataSource = ElementoDatasource<Turma>(Turma.model(), nomeTabela: tabela);

  TurmaDatasource(Elemento instance) : super(instance, nomeTabela: tabela);

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
  Future<int> insert(Elemento model) async {
    final int id = await super.insert(model);
    final Turma turma = model as Turma;
    final dataSourceTurmaAluno = TurmaAlunoDatasource(TurmaAluno.model());

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
    final dataSourceTurmaAluno = TurmaAlunoDatasource(TurmaAluno.model());

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
