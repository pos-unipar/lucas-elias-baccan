import 'package:sqflite/sqlite_api.dart';
import 'package:trabalho/datasources/datasources.dart';
import 'package:trabalho/models/models.dart';

class TurmaDiciplinaDatasource extends ElementoDatasource {
  static const String tabela = 'turma_diciplina';
  static const String columnId = 'id';
  static const String columnTurma = 'turma_id';
  static const String columnDiciplina = 'diciplina_id';

  TurmaDiciplinaDatasource(TurmaDiciplina instance) : super(instance, nomeTabela: tabela);

  @override
  String createTableSql() {
    return '''
      CREATE TABLE IF NOT EXISTS $tabela (
        $columnId INTEGER PRIMARY KEY AUTOINCREMENT,
        $columnTurma INTEGER NOT NULL REFERENCES turma(id),
        $columnDiciplina INTEGER NOT NULL REFERENCES aluno(id)
      );
    ''';
  }

  Future<List<TurmaDiciplina>> getAllTurmaDiciplina(Turma turma) async {
    final Database dbClient = await BancoDados().database;
    var map = await dbClient.query(nomeTabela, where: columnTurma + ' = ?', whereArgs: [turma.id]);
    List<TurmaDiciplina> list = [];
    for (var m in map) {
      list.add((await instance.fromMap(m)) as TurmaDiciplina);
    }
    return list;
  }
}
