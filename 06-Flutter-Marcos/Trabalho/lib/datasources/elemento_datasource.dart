import 'package:sqflite/sqflite.dart';
import 'package:trabalho/datasources/banco_dados.dart';
import 'package:trabalho/models/models.dart';

abstract class ElementoDatasource<T extends Elemento> {
  final String nomeTabela;

  ElementoDatasource(this.nomeTabela);

  String createTableSql();

  Future<void> insert(T elemento) async {
    Database db = await BancoDados().database;

    await db.insert(
      nomeTabela,
      elemento.toMap(),
      conflictAlgorithm: ConflictAlgorithm.replace,
    );
  }

  Future<void> update(Elemento elemento) async {
    Database db = await BancoDados().database;

    await db.update(
      nomeTabela,
      elemento.toMap(),
      where: 'id = ?',
      whereArgs: [elemento.id],
    );
  }

  Future<void> delete(String id) async {
    Database db = await BancoDados().database;

    if (!podeDeletar(id)) {
      throw Exception('Não é possível deletar este elemento da tabela $nomeTabela com id $id');
    }

    await db.delete(
      nomeTabela,
      where: 'id = ?',
      whereArgs: [id],
    );
  }

  Future<List<T>> getAll();
  Future<List<Map<String, Object?>>> getAllDatasource() async {
    Database db = await BancoDados().database;

    return await db.query(nomeTabela);
  }

  Future<T> getById(String id);
  Future<Map<String, dynamic>> getByIdDatasource(String id) async {
    Database db = await BancoDados().database;

    final List<Map<String, dynamic>> maps = await db.query(
      nomeTabela,
      where: 'id = ?',
      whereArgs: [id],
    );

    return maps.first;
  }

  bool podeDeletar(String id) {
    return true;
  }
}
