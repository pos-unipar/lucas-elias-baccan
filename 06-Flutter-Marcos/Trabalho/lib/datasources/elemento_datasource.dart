import 'package:sqflite/sqflite.dart';
import 'package:trabalho/datasources/banco_dados.dart';
import 'package:trabalho/models/models.dart';

class ElementoDatasource<T extends Elemento> {
  final Elemento instance;
  final String tableName;

  ElementoDatasource(
    this.instance, {
    required this.tableName,
  });

  Elemento fromMap(dynamic map) => instance.fromMap(map);

  Future<T> find(int id) async {
    final Database dbClient = await BancoDados().database;
    var map = await dbClient.query(tableName, where: "id = ?", whereArgs: [id]);
    return fromMap(map.first) as T;
  }

  Future<List<T>> getAll() async {
    final Database dbClient = await BancoDados().database;
    var map = await dbClient.query(tableName);
    return List.generate(map.length, (i) => fromMap(map[i]) as T);
  }

  Future<int> insert(Elemento model) async {
    final Database dbClient = await BancoDados().database;
    return await dbClient.insert(tableName, model.toMap());
  }

  Future<void> update(Elemento model) async {
    final Database dbClient = await BancoDados().database;
    await dbClient.update(
      tableName,
      model.toMap(),
      where: "id = ?",
      whereArgs: [model.id],
    );
  }

  Future<void> delete(Elemento model) async {
    final Database dbClient = await BancoDados().database;
    await dbClient.delete(tableName, where: "id = ?", whereArgs: [model.id]);
  }

  Future<void> deleteById(int id) async {
    final Database dbClient = await BancoDados().database;
    await dbClient.delete(tableName, where: "id = ?", whereArgs: [id]);
  }

  String createTableSql() {
    throw UnimplementedError();
  }
}

//   final String nomeTabela;

//   ElementoDatasource(this.nomeTabela);

//   

//   Future<void> insert(T elemento) async {
//     Database db = await BancoDados().database;

//     await db.insert(
//       nomeTabela,
//       elemento.toMap(),
//       conflictAlgorithm: ConflictAlgorithm.replace,
//     );
//   }

//   Future<void> update(Elemento elemento) async {
//     Database db = await BancoDados().database;

//     await db.update(
//       nomeTabela,
//       elemento.toMap(),
//       where: 'id = ?',
//       whereArgs: [elemento.id],
//     );
//   }

//   Future<void> delete(String id) async {
//     Database db = await BancoDados().database;

//     if (!podeDeletar(id)) {
//       throw Exception('Não é possível deletar este elemento da tabela $nomeTabela com id $id');
//     }

//     await db.delete(
//       nomeTabela,
//       where: 'id = ?',
//       whereArgs: [id],
//     );
//   }

//   Future<List<T>> getAll();
//   Future<List<Map<String, Object?>>> getAllDatasource() async {
//     Database db = await BancoDados().database;

//     return await db.query(nomeTabela);
//   }

//   Future<T> getById(String id);
//   Future<Map<String, dynamic>> getByIdDatasource(String id) async {
//     Database db = await BancoDados().database;

//     final List<Map<String, dynamic>> maps = await db.query(
//       nomeTabela,
//       where: 'id = ?',
//       whereArgs: [id],
//     );

//     return maps.first;
//   }

//   bool podeDeletar(String id) {
//     return true;
//   }
// }
