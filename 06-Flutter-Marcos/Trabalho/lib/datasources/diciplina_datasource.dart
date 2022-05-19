import 'package:trabalho/datasources/datasources.dart';
import 'package:trabalho/models/models.dart';

class DiciplinaDatasource extends ElementoDatasource {
  static const String tabela = 'diciplina';
  static const String columnId = 'id';
  static const String columnNome = 'nome';
  static const String columnProfessor = 'professor';

  final dataSource = ElementoDatasource<Diciplina>(Diciplina.model(), nomeTabela: tabela);

  DiciplinaDatasource(Elemento instance) : super(instance, nomeTabela: tabela);

  @override
  String createTableSql() {
    return '''
      CREATE TABLE IF NOT EXISTS $tabela (
        $columnId INTEGER PRIMARY KEY AUTOINCREMENT,
        $columnNome TEXT NOT NULL,
        $columnProfessor TEXT NOT NULL
      )
    ''';
  }
}
