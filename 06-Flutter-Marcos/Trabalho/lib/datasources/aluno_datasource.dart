import 'package:trabalho/datasources/datasources.dart';
import 'package:trabalho/models/models.dart';

class AlunoDatasource extends ElementoDatasource {
  static const String tabela = 'aluno';
  static const String columnId = 'id';
  static const String columnNome = 'nome';
  static const String columnRa = 'ra';
  static const String columnEmail = 'email';

  AlunoDatasource(Aluno instance) : super(instance, nomeTabela: tabela);

  @override
  String createTableSql() {
    return '''
      CREATE TABLE IF NOT EXISTS $tabela (
        $columnId INTEGER PRIMARY KEY AUTOINCREMENT,
        $columnNome TEXT NOT NULL,
        $columnRa INTEGER NOT NULL,
        $columnEmail TEXT NOT NULL
      )
    ''';
  }
}
