import 'package:trabalho/datasources/datasources.dart';
import 'package:trabalho/models/models.dart';

class ProfessorDatasource extends ElementoDatasource {
  static const String tabela = 'professor';
  static const String columnId = 'id';
  static const String columnNome = 'nome';

  ProfessorDatasource(Professor instance) : super(instance, nomeTabela: tabela);

  @override
  String createTableSql() {
    return '''
      CREATE TABLE IF NOT EXISTS $tabela (
        $columnId INTEGER PRIMARY KEY AUTOINCREMENT,
        $columnNome TEXT NOT NULL
      )
    ''';
  }
}
