import 'package:trabalho/datasources/datasources.dart';
import 'package:trabalho/models/models.dart';

class AlunoDatasource extends ElementoDatasource {
  static const String tabela = 'aluno';
  static const String columnId = 'id';
  static const String columnNome = 'nome';

  final dataSource = ElementoDatasource<Aluno>(Aluno.model(), tableName: tabela);

  AlunoDatasource(Elemento instance) : super(instance, tableName: tabela);

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