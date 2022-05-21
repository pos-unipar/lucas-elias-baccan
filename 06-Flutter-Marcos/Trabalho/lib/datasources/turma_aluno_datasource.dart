import 'package:sqflite/sqlite_api.dart';
import 'package:trabalho/datasources/datasources.dart';
import 'package:trabalho/models/models.dart';

class TurmaAlunoDatasource extends ElementoDatasource {
  static const String tabela = 'turma_aluno';
  static const String columnId = 'id';
  static const String columnTurma = 'turma_id';
  static const String columnAluno = 'aluno_id';

  final dataSource = ElementoDatasource<TurmaAluno>(TurmaAluno.model(), nomeTabela: tabela);

  TurmaAlunoDatasource(Elemento instance) : super(instance, nomeTabela: tabela);

  @override
  String createTableSql() {
    return '''
      CREATE TABLE IF NOT EXISTS $tabela (
        $columnId INTEGER PRIMARY KEY AUTOINCREMENT,
        $columnTurma INTEGER NOT NULL REFERENCES turma(id),
        $columnAluno INTEGER NOT NULL REFERENCES aluno(id)
      );
    ''';
  }

  Future<List<TurmaAluno>> getAllTurmaAluno(Turma turma) async {
    final Database dbClient = await BancoDados().database;
    var map = await dbClient.query(nomeTabela, where: columnTurma + ' = ?', whereArgs: [turma.id]);
    List<TurmaAluno> list = [];
    for (var m in map) {
      list.add((await fromMap(m)) as TurmaAluno);
    }
    return list;
  }
}
