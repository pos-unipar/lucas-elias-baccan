import 'package:sqflite/sqlite_api.dart';
import 'package:trabalho/datasources/datasources.dart';
import 'package:trabalho/models/models.dart';

class LancamentoPresencaDatasource extends ElementoDatasource {
  static const String tabela = 'lancamento_presenca';
  static const String columnId = 'id';
  static const String columnTurma = 'turma_id';
  static const String columnDisciplina = 'disciplina_id';
  static const String columnAluno = 'aluno_id';
  static const String columnData = 'data';
  static const String columnPresenca = 'presenca';

  LancamentoPresencaDatasource(LancamentoPresenca instance) : super(instance, nomeTabela: tabela);

  @override
  String createTableSql() {
    return '''
      CREATE TABLE IF NOT EXISTS $tabela (
        $columnId INTEGER PRIMARY KEY AUTOINCREMENT,
        $columnTurma INTEGER NOT NULL REFERENCES turma(id),
        $columnDisciplina INTEGER NOT NULL REFERENCES disciplina(id),
        $columnAluno INTEGER NOT NULL REFERENCES aluno(id),
        $columnData TEXT NOT NULL,
        $columnPresenca INTEGER NOT NULL
      )
    ''';
  }

  Future<List<LancamentoPresenca>> getAllbyTurmaDiciplinaAluno(Turma turma, Diciplina diciplina, Aluno aluno) async {
    final Database dbClient = await BancoDados().database;
    var map = await dbClient.query(
      nomeTabela,
      where: columnTurma + ' = ? AND ' + columnDisciplina + ' = ? AND ' + columnAluno + ' = ?',
      whereArgs: [turma.id, diciplina.id, aluno.id],
    );
    List<LancamentoPresenca> list = [];
    for (var m in map) {
      list.add((await instance.fromMap(m)) as LancamentoPresenca);
    }
    return list;
  }
}
