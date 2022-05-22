import 'package:trabalho/datasources/datasources.dart';
import 'package:trabalho/models/models.dart';

class TurmaDatasource extends ElementoDatasource {
  static const String tabela = 'turma';
  static const String columnId = 'id';
  static const String columnCurso = 'curso';

  final dataSourceTurmaAluno = TurmaAlunoDatasource(TurmaAluno.model());
  final dataSourceTurmaDiciplina = TurmaDiciplinaDatasource(TurmaDiciplina.model());

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
      turma.diciplinas = await getDiciplinas(turma);
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
        turma.diciplinas = await getDiciplinas(turma);
      }
    }
    return turmasAntigas;
  }

  Future<List<Aluno>> getAlunos(Turma turma) async {
    List<TurmaAluno> turmaAlunos = await dataSourceTurmaAluno.getAllTurmaAluno(turma);
    turma.alunos.clear();
    for (TurmaAluno turmaAluno in turmaAlunos) {
      turma.alunos.add(await AlunoDatasource(Aluno.model()).find(turmaAluno.aluno.id!) as Aluno);
    }
    turma.alunos = turma.alunos;
    return turma.alunos;
  }

  Future<List<Diciplina>> getDiciplinas(Turma turma) async {
    List<TurmaDiciplina> turmaDiciplina = await dataSourceTurmaDiciplina.getAllTurmaDiciplina(turma);
    turma.diciplinas.clear();
    for (TurmaDiciplina turmaDiciplina in turmaDiciplina) {
      turma.diciplinas.add(await DiciplinaDatasource(Diciplina.model()).find(turmaDiciplina.diciplina.id!) as Diciplina);
    }
    turma.diciplinas = turma.diciplinas;
    return turma.diciplinas;
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

    // Inserir turma_diciplina
    for (final diciplina in turma.diciplinas) {
      await dataSourceTurmaDiciplina.insert(TurmaDiciplina(turma: turma, diciplina: diciplina));
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

    // Deletar todos os turma_diciplina da turma
    List<TurmaDiciplina> turmaDiciplinasAntigos = await dataSourceTurmaDiciplina.getAllTurmaDiciplina(turma);
    for (TurmaDiciplina turmaDiciplina in turmaDiciplinasAntigos) {
      await dataSourceTurmaDiciplina.delete(turmaDiciplina);
    }

    // Inserir novos turma_diciplina
    for (final diciplina in turma.diciplinas) {
      await dataSourceTurmaDiciplina.insert(TurmaDiciplina(turma: turma, diciplina: diciplina));
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
