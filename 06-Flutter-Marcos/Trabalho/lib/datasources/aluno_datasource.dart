import 'package:trabalho/datasources/elemento_datasource.dart';
import 'package:trabalho/models/elemento.dart';

// class AlunoDatasource extends ElementoDatasource {

//   @override
//   String createTableSql() {
//     // TODO: implement createTableSql
//     throw UnimplementedError();
//   }

//   @override
//   Future<List<Elemento>> getAll() async{
//     final List<Map<String, dynamic>> maps = await getAllDatasource();
//     return maps.map((elemento) => Aluno.fromMap(elemento)).toList();

//   }

//   @override
//   Future<Elemento> getById(String id) async{
//     final Map<String, dynamic> maps = await getByIdDatasource(id);
//     return Aluno.fromMap(elemento)
//   }
// }