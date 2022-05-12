import 'package:sqflite/sqflite.dart';
import 'package:trabalho/datasources/banco_dados.dart';
import 'package:trabalho/models/models.dart';

class ElementoDatasource<T extends Elemento> {
  final Elemento instance;
  final String nomeTabela;

  ElementoDatasource(
    this.instance, {
    required this.nomeTabela,
  });

  Elemento fromMap(dynamic map) => instance.fromMap(map);

  Future<T> find(int id) async {
    final Database dbClient = await BancoDados().database;
    var map = await dbClient.query(nomeTabela, where: "id = ?", whereArgs: [id]);
    return fromMap(map.first) as T;
  }

  Future<List<T>> getAll() async {
    final Database dbClient = await BancoDados().database;
    var map = await dbClient.query(nomeTabela);
    return List.generate(map.length, (i) => fromMap(map[i]) as T);
  }

  Future<int> insert(Elemento model) async {
    final Database dbClient = await BancoDados().database;
    return await dbClient.insert(nomeTabela, model.toMap());
  }

  Future<void> update(Elemento model) async {
    final Database dbClient = await BancoDados().database;
    await dbClient.update(
      nomeTabela,
      model.toMap(),
      where: "id = ?",
      whereArgs: [model.id],
    );
  }

  Future<void> delete(Elemento model) async {
    final Database dbClient = await BancoDados().database;
    await dbClient.delete(nomeTabela, where: "id = ?", whereArgs: [model.id]);
  }

  Future<void> deleteById(int id) async {
    final Database dbClient = await BancoDados().database;
    await dbClient.delete(nomeTabela, where: "id = ?", whereArgs: [id]);
  }

  String createTableSql() {
    throw UnimplementedError();
  }
}