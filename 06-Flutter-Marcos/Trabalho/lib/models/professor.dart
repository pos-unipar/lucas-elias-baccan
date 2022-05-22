import 'package:trabalho/datasources/datasources.dart';
import 'package:trabalho/models/elemento.dart';

class Professor extends Elemento {
  late String nome;

  Professor.model() : super();

  Professor({
    id,
    required this.nome,
  }) : super(id: id);

  @override
  Future<Map<String, Object?>> toMap() async {
    return {
      'id': id,
      ProfessorDatasource.columnNome: nome,
    };
  }

  @override
  Future<Elemento> fromMap(Map<String, dynamic> map) async {
    Elemento model = Professor(
      nome: map[ProfessorDatasource.columnNome] as String,
    );
    model.id = map['id'] as int?;
    return model;
  }

  @override
  String toString() {
    return "$id - $nome";
  }

  @override
  operator ==(other) => other is Professor && id == other.id;

  @override
  int get hashCode => id.hashCode;
}
