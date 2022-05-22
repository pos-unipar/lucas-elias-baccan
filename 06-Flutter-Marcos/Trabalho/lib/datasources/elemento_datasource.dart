import 'package:sqflite/sqflite.dart';
import 'package:trabalho/datasources/local/banco_dados.dart';
import 'package:trabalho/helpers/faker.dart';
import 'package:trabalho/models/models.dart';

class ElementoDatasource<T extends Elemento> {
  final Elemento instance;
  final String nomeTabela;

  ElementoDatasource(
    this.instance, {
    required this.nomeTabela,
  });

  Future<T> fromMap(dynamic map) async {
    return await instance.fromMap(map) as T;
  }

  Future<T> find(int id) async {
    final Database dbClient = await BancoDados().database;
    var map = await dbClient.query(nomeTabela, where: "id = ?", whereArgs: [id]);
    return await fromMap(map.first);
  }

  Future<List<T>> getAll() async {
    final Database dbClient = await BancoDados().database;
    var map = await dbClient.query(nomeTabela);
    List<T> list = [];
    for (var m in map) {
      list.add((await fromMap(m)));
    }
    return list;
  }

  Future<int> insert(Elemento model) async {
    final Database dbClient = await BancoDados().database;
    return await dbClient.insert(nomeTabela, await model.toMap());
  }

  Future<void> update(Elemento model) async {
    final Database dbClient = await BancoDados().database;
    await dbClient.update(
      nomeTabela,
      await model.toMap(),
      where: "id = ?",
      whereArgs: [model.id],
    );
  }

  Future<void> delete(Elemento model) async {
    deleteById(model.id!);
  }

  Future<void> deleteById(int id) async {
    final Database dbClient = await BancoDados().database;
    await dbClient.delete(nomeTabela, where: "id = ?", whereArgs: [id]);
  }

  String createTableSql() {
    throw UnimplementedError();
  }

  Future<T> aleatorio() async {
    List<T> list = await getAll();
    return list[Faker.gerarId(list.length)];
  }
}
