import 'package:trabalho/datasources/datasources.dart';
import 'package:trabalho/models/models.dart';

class CursoDatasource extends ElementoDatasource {
  static const String tabela = 'curso';
  static const String columnId = 'id';
  static const String columnNome = 'nome';

  final dataSource = ElementoDatasource<Curso>(Curso.model(), tableName: tabela);

  CursoDatasource(Elemento instance) : super(instance, tableName: tabela);

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
