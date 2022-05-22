import 'dart:io';
import 'package:path/path.dart';

import 'package:path_provider/path_provider.dart';
import 'package:sqflite/sqflite.dart';
import 'package:trabalho/datasources/datasources.dart';
import 'package:trabalho/models/models.dart';

class BancoDados {
  static const String _databaseName = 'controle-escola.db';

  static final BancoDados _instance = BancoDados._internal();

  factory BancoDados() => _instance;

  BancoDados._internal();

  static Database? _database;

  Future<Database> get database async {
    if (_database != null) {
      return _database!;
    }

    _database = await initDB();

    return _database!;
  }

  Future<Database> initDB() async {
    Directory directory = await getApplicationDocumentsDirectory(); //returns a directory which stores permanent files
    final path = join(directory.path, _databaseName); //create path to database

    return await openDatabase(path, version: 1, onCreate: (Database database, int version) async {
      await database.execute(CursoDatasource(Curso.model()).createTableSql());
      await database.execute(AlunoDatasource(Aluno.model()).createTableSql());
      await database.execute(ProfessorDatasource(Professor.model()).createTableSql());
      await database.execute(DiciplinaDatasource(Diciplina.model()).createTableSql());
      await database.execute(TurmaDatasource(Turma.model()).createTableSql());
      await database.execute(TurmaAlunoDatasource(TurmaAluno.model()).createTableSql());
      await database.execute(TurmaDiciplinaDatasource(TurmaDiciplina.model()).createTableSql());
      await database.execute(LancamentoPresencaDatasource(LancamentoPresenca.model()).createTableSql());
    });
  }

  Future<void> close() async {
    final Database dbClient = await database;
    await dbClient.close();
  }

  Future<void> deletar() async {
    final Directory directory = await getApplicationDocumentsDirectory();
    final path = join(directory.path, _databaseName);
    deleteDatabase(path);
    _database = null;
  }
}
