import 'package:trabalho/datasources/elemento_datasource.dart';
import 'package:trabalho/models/models.dart';

class CursoDatasource extends ElementoDatasource {
  static const String table = 'curso';
  static const String columnId = 'id';
  static const String columnNome = 'nome';

  @override
  String createTable() {
    return '''
      CREATE TABLE IF NOT EXISTS $table (
        $columnId TEXT PRIMARY KEY AUTOINCREMENT,
        $columnNome TEXT NOT NULL
      )
    ''';
  }
}
