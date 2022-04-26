import 'package:sqflite/sqflite.dart';
import 'package:trabalho/datasources/banco_dados.dart';
import 'package:trabalho/models/models.dart';

class ElementoDatasource {
  Future<void> insert(String tabela, Elemento elemento) async {
    Database db = await BancoDados().database;

    await db.insert(
      tabela,
      elemento.toMap(),
      conflictAlgorithm: ConflictAlgorithm.replace,
    );
  }

  Future<void> update(String tabela, Elemento elemento) async {
    Database db = await BancoDados().database;

    await db.update(
      tabela,
      elemento.toMap(),
      where: 'id = ?',
      whereArgs: [elemento.id],
    );
  }

  Future<void> delete(String tabela, String id) async {
    Database db = await BancoDados().database;

    await db.delete(
      tabela,
      where: 'id = ?',
      whereArgs: [id],
    );
  }

  Future<List<Elemento>> getAll(String tabela, Elemento modelo) async {
    Database db = await BancoDados().database;

    final List<Map<String, dynamic>> maps = await db.query(tabela);

    return maps.map((elemento) => modelo.fromMap(elemento)).toList();
  }

  Future<Elemento> getById(String tabela, Elemento modelo, String id) async {
    Database db = await BancoDados().database;

    final List<Map<String, dynamic>> maps = await db.query(
      tabela,
      where: 'id = ?',
      whereArgs: [id],
    );

    return modelo.fromMap(maps.first);
  }
}

abstract class ElementoDatasourceSql implements ElementoDatasource {
  String createTable();
}
