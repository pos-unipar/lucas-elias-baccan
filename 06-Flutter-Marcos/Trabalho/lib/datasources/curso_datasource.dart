import 'package:trabalho/datasources/elemento_datasource.dart';
import 'package:trabalho/models/models.dart';

class CursoDatasource extends ElementoDatasource {
  static const String tabela = 'curso';
  static const String columnId = 'id';
  static const String columnNome = 'nome';

  CursoDatasource() : super(tabela);

  @override
  String createTableSql() {
    return '''
      CREATE TABLE IF NOT EXISTS $tabela (
        $columnId INTEGER PRIMARY KEY AUTOINCREMENT,
        $columnNome TEXT NOT NULL
      )
    ''';
  }

  @override
  bool podeDeletar(String id) {
    return false;
  }

  @override
  Future<List<Elemento>> getAll() async {
    final List<Map<String, dynamic>> maps = await getAllDatasource();
    return maps.map((elemento) => Curso.fromMap(elemento)).toList();
  }

  @override
  Future<Elemento> getById(String id) async {
    final Map<String, dynamic> maps = await getByIdDatasource(id);
    return Curso.fromMap(maps);
  }
}
